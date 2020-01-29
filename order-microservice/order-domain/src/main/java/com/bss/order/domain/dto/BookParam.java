package com.bss.order.domain.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * Created by QAQ on 2019/5/26
 */

@Getter
@Setter
public class BookParam {
    private Long isbn;
    private String name;
    private String pic;
    private BigDecimal price;
    private Integer count;
}
