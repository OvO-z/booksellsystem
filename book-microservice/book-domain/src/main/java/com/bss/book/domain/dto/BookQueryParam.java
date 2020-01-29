package com.bss.book.domain.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by QAQ on 2019/4/29
 */
@Getter
@Setter
public class BookQueryParam {
    private Long isbn;
    private Integer publishStatus;
    private Integer verifyStatus;
    private String keywords;
    private Integer categoryId;
}
