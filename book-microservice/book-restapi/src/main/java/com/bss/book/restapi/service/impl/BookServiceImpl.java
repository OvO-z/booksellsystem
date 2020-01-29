package com.bss.book.restapi.service.impl;

import com.bss.book.domain.dao.BookDao;
import com.bss.book.domain.dto.BookParam;
import com.bss.book.domain.dto.BookQueryParam;
import com.bss.book.domain.dto.CommonResult;
import com.bss.book.domain.mapper.BookMapper;
import com.bss.book.domain.model.Book;
import com.bss.book.domain.util.CopyUtil;
import com.bss.book.restapi.mqchannel.InputSource;
import com.bss.book.restapi.service.BookService;
import com.bss.order.object.model.OrderItem;
import com.github.pagehelper.PageHelper;
import com.google.gson.Gson;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by QAQ on 2019/4/28
 */

@Service
@Log4j
@EnableBinding(InputSource.class)
public class BookServiceImpl implements BookService {

    @Autowired
    BookMapper bookMapper;

    @Autowired
    BookDao bookDao;

    @Override
    public Long create(BookParam param) {
        Book book = CopyUtil.copy(param,Book.class);
        book.setSale(0);
        bookMapper.insertSelective(book);
        return book.getIsbn();
    }

    @Override
    public Long deleteById(Long isbn) {
        bookMapper.deleteByPrimaryKey(isbn);
        return isbn;
    }

    @Override
    public Long update(Long isbn, BookParam param) {
        Book book = CopyUtil.copy(param,Book.class);
        bookMapper.updateByPrimaryKeySelective(book);
        return book.getIsbn();
    }

    @Override
    public Book getById(Long isbn) {
        return bookMapper.selectByPrimaryKey(isbn);
    }

    @Override
    public List<Book> listAll(BookQueryParam param,Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        List<Book> list = bookDao.listByQuery(param);
        return list;
    }

    @ServiceActivator(inputChannel = InputSource.ORDERCHANNEL,outputChannel = InputSource.REPLYCHANNEL)
    public Object accept(List<OrderItem> items){
        if (items != null){
            for (OrderItem item : items){
                bookDao.updateCount(item.getProductId(),item.getProductCount());
                log.info("修改[delete] -> Book:" + new Gson().toJson(item));
            }
        }
        return new CommonResult().success(items);
    }

    @ServiceActivator(inputChannel = InputSource.CANCEL,outputChannel = InputSource.REPLYCHANNEL)
    public Object cancel(List<OrderItem> items){
        if (items != null){
            for (OrderItem item : items){
                bookDao.cancel(item.getProductId(),item.getProductCount());
                log.info("修改 [cancel]-> Book:" + new Gson().toJson(item));
            }
        }
        return new CommonResult().success(items);
    }
}
