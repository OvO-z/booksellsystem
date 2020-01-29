package com.bss.book.restapi.service.impl;

import com.bss.book.domain.dao.BookCategoryDao;
import com.bss.book.domain.dto.BookCategoryParam;
import com.bss.book.domain.dto.BookCategoryWithChildrenItem;
import com.bss.book.domain.mapper.BookCategoryMapper;
import com.bss.book.domain.model.BookCategory;
import com.bss.book.domain.util.CopyUtil;
import com.bss.book.restapi.service.BookCategoryService;
import com.github.pagehelper.PageHelper;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * Created by QAQ on 2019/4/23
 */
@Service
@Log4j
public class BookCategoryServiceImpl implements BookCategoryService {

    @Autowired
    BookCategoryMapper bookCategoryMapper;

    @Autowired
    BookCategoryDao bookCategoryDao;

    @Override
    public int create(BookCategoryParam bookCategoryParam) {
        BookCategory bookCategory = CopyUtil.copy(bookCategoryParam,BookCategory.class);
        bookCategory.setBookcount(0);
        bookCategoryMapper.insertSelective(bookCategory);
        return bookCategory.getId();
    }

    @Override
    public int deleteById(Integer id) {
        return bookCategoryMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int update(Integer id, BookCategoryParam bookCategoryParam) {
        BookCategory bookCategory = CopyUtil.copy(bookCategoryParam,BookCategory.class);
        bookCategory.setId(id);
        int count = bookCategoryMapper.updateByPrimaryKey(bookCategory);
        return bookCategory.getId();
    }

    @Override
    public List<BookCategoryWithChildrenItem> listWithChildren() {
        return bookCategoryDao.listWithChildren();
    }

    @Override
    public BookCategory getById(Integer id) {
        return bookCategoryMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<BookCategory> listAll(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        return bookCategoryDao.listAll();
    }
}
