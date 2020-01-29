package com.bss.book.domain.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * 添加图书分类更新参数
 * Created by QAQ on 2019/4/23
 */
@Getter
@Setter
public class BookCategoryParam {

    private Integer bookcount;

    private String description;

    private String keywords;

    private Integer level;

    private String name;

    private Integer parentId;
}
