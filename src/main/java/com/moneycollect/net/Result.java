package com.moneycollect.net;

import java.io.Serializable;

public class Result implements Serializable {
    private static final long serialVersionUID = 123L;

    public static final String DEFAULT_SUCCESS_CODE = "success";

    /**
     * code：success
     */
    private String code = DEFAULT_SUCCESS_CODE;

    /**
     * message
     */
    private String msg = "success";

    private Object data;

    public boolean success() {
        return code.equals(DEFAULT_SUCCESS_CODE);
    }


    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}