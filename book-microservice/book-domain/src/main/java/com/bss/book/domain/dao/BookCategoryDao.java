package com.bss.book.domain.dao;

import com.bss.book.domain.dto.BookCategoryWithChildrenItem;
import com.bss.book.domain.model.BookCategory;

import java.util.List;

/**
 * 图书分类自定义Dao
 * @Author Z
 * @Date 2019/4/23
 */
public interface BookCategoryDao {
    List<BookCategoryWithChildrenItem> listWithChildren();

    List<BookCategory> listAll();
}
