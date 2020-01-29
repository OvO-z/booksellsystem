package com.bss.book.restapi.service;

import com.bss.book.domain.dto.BookCategoryParam;
import com.bss.book.domain.dto.BookCategoryWithChildrenItem;
import com.bss.book.domain.model.BookCategory;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 图书分类Service
 * Created by QAQ on 2019/4/23
 */
public interface BookCategoryService {

    @Transactional
    int create(BookCategoryParam bookCategoryParam);

    @Transactional
    int deleteById(Integer id);

    @Transactional
    int update(Integer id, BookCategoryParam bookCategoryParam);

    List<BookCategoryWithChildrenItem> listWithChildren();

    BookCategory getById(Integer id);

    List<BookCategory> listAll(Integer pageNum, Integer pageSize);
}
