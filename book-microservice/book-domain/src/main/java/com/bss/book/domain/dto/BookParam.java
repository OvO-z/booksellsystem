package com.bss.book.domain.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * Created by QAQ on 2019/4/28
 */

@Getter
@Setter
public class BookParam {
    private Long isbn;

    private String authorName;

    private String bookName;

    private String description;

    private Integer pages;

    private String pic;

    private String press;

    private Double price;

    private Integer publishStatus;

    private Date publishTime;

    private Integer stock;

    private Integer verifyStatus;

    private String authorId;

    private Integer categoryId;

    private String albumPic;
}
