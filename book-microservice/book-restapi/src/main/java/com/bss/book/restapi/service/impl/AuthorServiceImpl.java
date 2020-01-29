package com.bss.book.restapi.service.impl;

import com.bss.book.domain.dto.AuthorParam;
import com.bss.book.domain.mapper.AuthorMapper;
import com.bss.book.domain.model.Author;
import com.bss.book.domain.model.AuthorExample;
import com.bss.book.domain.util.CopyUtil;
import com.bss.book.restapi.service.AuthorService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

/**
 * Created by QAQ on 2019/4/24
 */
@Service
public class AuthorServiceImpl implements AuthorService {

    @Autowired
    AuthorMapper authorMapper;

    @Override
    public String create(AuthorParam param) {

        Author author = CopyUtil.copy(param,Author.class);
        author.setId(UUID.randomUUID().toString());
        authorMapper.insert(author);
        return author.getId();
    }

    @Override
    public String deleteById(String id) {
        authorMapper.deleteByPrimaryKey(id);
        return id;
    }

    @Override
    public String update(AuthorParam param) {
        Author author = CopyUtil.copy(param,Author.class);
        authorMapper.updateByPrimaryKeySelective(author);
        return author.getId();
    }

    @Override
    public Author getById(String id) {
        return authorMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Author> listAll(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        AuthorExample example = new AuthorExample();
        return authorMapper.selectByExample(example);
    }

    @Override
    public List<Author> getList() {
        AuthorExample example = new AuthorExample();
        return authorMapper.selectByExample(example);
    }
}
