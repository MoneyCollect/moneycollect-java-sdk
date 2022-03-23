package com.moneycollect.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
public class MoneyCollectCollection<T> {

    private List<T> data;

    private Boolean hasMore;

}
