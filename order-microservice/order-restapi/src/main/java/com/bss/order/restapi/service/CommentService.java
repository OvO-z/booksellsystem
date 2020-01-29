package com.bss.order.restapi.service;

import com.bss.order.object.CommentParam;
import com.bss.order.object.model.Comment;

import java.util.List;

/**
 * Created by QAQ on 2019/6/1
 */
public interface CommentService {

    List<Comment> getPageList(CommentParam param, Integer pageNum, Integer pageSize);
    List<Comment> getList(CommentParam param);
    Comment create(CommentParam param);
    Comment update(Long id,CommentParam param);
}
