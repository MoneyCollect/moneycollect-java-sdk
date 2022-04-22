package com.moneycollect.net;


import com.moneycollect.exception.SignatureVerificationException;
import com.moneycollect.model.Event;
import com.moneycollect.util.JsonUtil;
import com.moneycollect.util.StringUtils;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

public final class Webhook {
  private static final long DEFAULT_TOLERANCE = 300;
  public static final String REQUEST_TIME_HEADER = "request-time";
  public static final String SIGNATURE_HEADER = "signature";
  private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");

  /**
   * Returns an Event instance using the provided JSON payload. Throws a JsonSyntaxException if the
   * payload is not valid JSON, and a SignatureVerificationException if the signature verification
   * fails for any reason.
   *
   * @param payload the payload sent by MoneyCollect.
   * @param signature the contents of the signature header sent by MoneyCollect.
   * @param secret secret used to generate the signature.
   * @return the Event instance
   * @throws SignatureVerificationException if the verification fails.
   */
  public static Event constructEvent(String payload, String signature,String requestTime, String secret)
          throws SignatureVerificationException {
    return constructEvent(payload, signature,requestTime, secret, DEFAULT_TOLERANCE);
  }

  /**
   * Returns an Event instance using the provided JSON payload. Throws a JsonSyntaxException if the
   * payload is not valid JSON, and a SignatureVerificationException if the signature verification
   * fails for any reason.
   *
   * @param payload the payload sent by MoneyCollect.
   * @param signature the contents of the signature header sent by MoneyCollect.
   * @param secret secret used to generate the signature.
   * @param tolerance maximum difference in seconds allowed between the header's timestamp and the
   *     current time
   * @return the Event instance
   * @throws SignatureVerificationException if the verification fails.
   */
  public static Event constructEvent(
          String payload, String signature,String requestTime, String secret, long tolerance)
          throws SignatureVerificationException {
    Event event = JsonUtil.parseObject(payload, Event.class);
    Signature.verifyHeader(payload, signature,requestTime, secret, tolerance);
    return event;
  }

  public static final class Signature {

    /**
     * Verifies the signature header sent by MoneyCollect. Throws a SignatureVerificationException if the
     * verification fails for any reason.
     *
     * @param payload the payload sent by MoneyCollect.
     * @param signature the contents of the signature header sent by MoneyCollect.
     * @param secret secret used to generate the signature.
     * @param tolerance maximum difference allowed between the header's timestamp and the current
     *     time
     * @throws SignatureVerificationException if the verification fails.
     */
    public static boolean verifyHeader(
            String payload, String signature,String requestTime, String secret, long tolerance)
            throws SignatureVerificationException {
      // Get timestamp and signatures from header
      if (requestTime == null) {
        throw new SignatureVerificationException(
                "Unable to extract timestamp and signature from header", signature);
      }
      if (signature == null) {
        throw new SignatureVerificationException(
                "No signature found with expected scheme", signature);
      }

      // Compute expected signature
      String signedPayload = String.format("%s.%s", requestTime, payload);
      String expectedSignature;
      try {
        expectedSignature = computeSignature(signedPayload, secret);
      } catch (Exception e) {
        throw new SignatureVerificationException(
                "Unable to compute signature for payload", signature);
      }

      // Check if expected signature is found in list of header's signatures
      if (!StringUtils.secureCompare(expectedSignature, signature)) {
        throw new SignatureVerificationException(
                "No signatures found matching the expected signature for payload", signature);
      }

      // Check tolerance
      if ((tolerance > 0) && (getTimestamp(requestTime) < (Util.getTimeNow() - tolerance))) {
        throw new SignatureVerificationException("Timestamp outside the tolerance zone", signature);
      }

      return true;
    }

    private static long getTimestamp(String headerDate){
      return LocalDateTime.parse(headerDate,DATE_FORMAT).toInstant(ZoneOffset.UTC).getEpochSecond();
    }


    /**
     * Computes the signature for a given payload and secret.
     *
     * <p>The current scheme used by MoneyCollect ("v1") is HMAC/SHA-256.
     *
     * @param payload the payload to sign.
     * @param secret the secret used to generate the signature.
     * @return the signature as a string.
     */
    private static String computeSignature(String payload, String secret)
            throws NoSuchAlgorithmException, InvalidKeyException {
      return Util.computeHmacSha256(secret, payload);
    }
  }

  public static final class Util {
    /**
     * Computes the HMAC/SHA-256 code for a given key and message.
     *
     * @param key the key used to generate the code.
     * @param message the message.
     * @return the code as a string.
     */
    public static String computeHmacSha256(String key, String message)
            throws NoSuchAlgorithmException, InvalidKeyException {
      Mac hasher = Mac.getInstance("HmacSHA256");
      hasher.init(new SecretKeySpec(key.getBytes(StandardCharsets.UTF_8), "HmacSHA256"));
      byte[] hash = hasher.doFinal(message.getBytes(StandardCharsets.UTF_8));
      String result = "";
      for (byte b : hash) {
        result += Integer.toString((b & 0xff) + 0x100, 16).substring(1);
      }
      return result;
    }

    /**
     * Returns the current UTC timestamp in seconds.
     *
     * @return the timestamp as a long.
     */
    public static long getTimeNow() {
      long time = System.currentTimeMillis() / 1000L;
      return time;
    }
  }
}
