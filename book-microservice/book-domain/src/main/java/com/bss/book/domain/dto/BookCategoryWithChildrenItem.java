package com.bss.book.domain.dto;

//import com.bss.book.domain.model.BookCategory;
import com.bss.book.domain.model.BookCategory;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * Created by QAQ on 2019/4/23
 */
@Getter
@Setter
public class BookCategoryWithChildrenItem extends BookCategory {
    private List<BookCategory> children;
}
