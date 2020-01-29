package com.bss.book.restapi.service;

import com.bss.book.domain.dto.AuthorParam;
import com.bss.book.domain.model.Author;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by QAQ on 2019/4/24
 */
public interface AuthorService {

    @Transactional
    String create(AuthorParam param);

    @Transactional
    String deleteById(String id);

    @Transactional
    String update(AuthorParam param);

    Author getById(String id);

    List<Author> listAll(Integer pageNum, Integer pageSize);

    List<Author> getList();
}
