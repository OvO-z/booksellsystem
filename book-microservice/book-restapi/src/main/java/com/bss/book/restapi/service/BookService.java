package com.bss.book.restapi.service;

import com.bss.book.domain.dto.BookParam;
import com.bss.book.domain.dto.BookQueryParam;
import com.bss.book.domain.model.Book;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by QAQ on 2019/4/28
 */
public interface BookService {
    @Transactional
    Long create(BookParam param);

    @Transactional
    Long deleteById(Long isbn);

    @Transactional
    Long update(Long isbn, BookParam param);

    Book getById(Long isbn);

    List<Book> listAll(BookQueryParam param,Integer pageNum, Integer pageSize);
}
