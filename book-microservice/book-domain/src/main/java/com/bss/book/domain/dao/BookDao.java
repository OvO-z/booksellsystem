package com.bss.book.domain.dao;

import com.bss.book.domain.dto.BookQueryParam;
import com.bss.book.domain.model.Book;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by QAQ on 2019/5/4
 */
public interface BookDao {
    List<Book> listByQuery(BookQueryParam param);
    int updateCount(@Param("id") Long id, @Param("count") Integer count);
    int cancel(@Param("id") Long id, @Param("count") Integer count);
}
