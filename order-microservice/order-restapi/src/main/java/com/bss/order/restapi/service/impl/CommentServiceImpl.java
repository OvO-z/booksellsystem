package com.bss.order.restapi.service.impl;

import com.bss.order.domain.mapper.CommentMapper;
import com.bss.order.domain.mapper.OrdersMapper;
import com.bss.order.domain.util.CopyUtil;
import com.bss.order.object.CommentParam;
import com.bss.order.object.model.*;
import com.bss.order.restapi.service.CommentService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Date;

/**
 * Created by QAQ on 2019/6/1
 */

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    CommentMapper commentMapper;

    @Autowired
    OrdersMapper ordersMapper;

    @Override
    public List<Comment> getPageList(CommentParam param, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        return getList(param);
    }

    @Override
    public List<Comment> getList(CommentParam param) {
        CommentExample example = new CommentExample();
        if (param != null) {
            CommentExample.Criteria criteria = example.createCriteria();
            if (param.getId() != null)
                criteria.andIdEqualTo(param.getId());
            if (param.getBookIsbn() != null)
                criteria.andBookIsbnEqualTo(param.getBookIsbn());
            if (param.getOrderId() != null)
                criteria.andOrderIdEqualTo(param.getOrderId());
            if (param.getUserId() != null)
                criteria.andUserIdEqualTo(param.getUserId());
            if (param.getStatus() != null)
                criteria.andStatusEqualTo(param.getStatus());
            if (param.getUpdateStatus() != null)
                criteria.andUpdateStatusEqualTo(param.getUpdateStatus());
            if (param.getUpdateTime() != null)
                criteria.andUpdateTimeLike("%" +param.getUpdateTime() +"%");
            if (param.getOrderItemId() != null)
                criteria.andOrderItemIdEqualTo(param.getOrderItemId());
        }
        return commentMapper.selectByExample(example);
    }

    @Override
    public Comment create(CommentParam param) {
        Comment comment = CopyUtil.copy(param,Comment.class);
        commentMapper.insertSelective(comment);
        return comment;
    }

    @Override
    public Comment update(Long id, CommentParam param) {
        Comment comment = CopyUtil.copy(param,Comment.class);
        comment.setUpdateTime(new Date().toString());
        commentMapper.updateByPrimaryKeySelective(comment);
        comment = commentMapper.selectByPrimaryKey(comment.getId());
        if (verifyCommentAll(comment.getOrderId())){
            changeOrderStatus(comment.getOrderId());
        }
        return comment;
    }

    public boolean verifyCommentAll(Long orderId){
        CommentExample example = new CommentExample();
        example.createCriteria().andOrderIdEqualTo(orderId).andUpdateStatusEqualTo(0);
        List<Comment> comments = commentMapper.selectByExample(example);
        return comments.size() == 0;
    }

    public void changeOrderStatus(Long orderId){
        Orders orders = new Orders();
        orders.setStatus(4);
        orders.setId(orderId);
        ordersMapper.updateByPrimaryKeySelective(orders);
    }
}
