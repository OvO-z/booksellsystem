package com.bss.order.restapi.service.impl;

import com.bss.order.domain.dao.OrderDao;
import com.bss.order.domain.dto.BookParam;
import com.bss.order.domain.dto.OrderParam;
import com.bss.order.domain.mapper.CommentMapper;
import com.bss.order.domain.mapper.OrderItemMapper;
import com.bss.order.domain.mapper.OrdersMapper;
import com.bss.order.domain.util.CopyUtil;
import com.bss.order.object.CommentParam;
import com.bss.order.object.dto.OrderQueryParam;
import com.bss.order.domain.dto.OrderWithItem;
import com.bss.order.object.model.OrderItem;
import com.bss.order.object.model.OrderItemExample;
import com.bss.order.object.model.Orders;
import com.bss.order.restapi.mqchannel.OutputSource;
import com.bss.order.restapi.service.CommentService;
import com.bss.order.restapi.service.OrderService;
import com.bss.order.restapi.utils.SnowFlake;
import com.github.pagehelper.PageHelper;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by QAQ on 2019/5/26
 */
@Service
@EnableBinding(OutputSource.class)
@Log4j
public class OrderServiceImpl implements OrderService {

    @Autowired
    OrdersMapper ordersMapper;

    @Autowired
    OrderDao orderDao;

    @Autowired
    OrderItemMapper itemMapper;

    @Autowired
    CommentService commentService;

    @Autowired
    @Output(OutputSource.ORDERCHANNEL)
    private MessageChannel ordersChannel;

    @Autowired
    @Output(OutputSource.CANCEL)
    private MessageChannel cancel;

    @Override
    public Long create(OrderParam param) {
        long order_sn = SnowFlake.nextId();
        Orders order = CopyUtil.copy(param,Orders.class);
        order.setOrderSn(order_sn+"");
        order.setCreateTime(new Date());
        ordersMapper.insertSelective(order);
        List<OrderItem> items = new ArrayList<>();
        for(BookParam book: param.getBookList()){
            OrderItem item = new OrderItem();
            item.setOrderId(order.getId());
            item.setProductId(book.getIsbn());
            item.setOrderSn(order_sn);
            item.setProductPrice(book.getPrice());
            item.setProductCount(book.getCount());
            item.setProductPic(book.getPic());
            item.setProductName(book.getName());
            items.add(item);
        }
        orderDao.insertListOrderItem(items);
        return order.getId();
    }

    @Override
    public Orders get(Long id) {
        return ordersMapper.selectByPrimaryKey(id);
    }

    @Override
    public Orders update(Long id, OrderParam param) { //支付订单时操作、需修改图书数量
        Orders order = CopyUtil.copy(param,Orders.class);
        order.setId(id);
        ordersMapper.updateByPrimaryKeySelective(order);
        OrderItemExample itemExample = new OrderItemExample();
        itemExample.createCriteria().andOrderIdEqualTo(id);
        List<OrderItem> items = itemMapper.selectByExample(itemExample);
        ordersChannel.send(MessageBuilder.withPayload(items).build());
        log.info("修改 -> ID = " + order.getId());
        return order;
    }

    @Override
    public Orders updateStatus(Long id, Integer status) {
        Orders orders = ordersMapper.selectByPrimaryKey(id);
        orders.setStatus(status);
        ordersMapper.updateByPrimaryKeySelective(orders);
        OrderItemExample example = new OrderItemExample();
        example.createCriteria().andOrderIdEqualTo(id);
        List<OrderItem> items = itemMapper.selectByExample(example);
        if (status == -3 || status == -5){
            cancel.send(MessageBuilder.withPayload(items).build());
        }
        if (status == 3){
            for (OrderItem item: items){
                CommentParam param = new CommentParam();
                param.setBookIsbn(item.getProductId());
                param.setOrderId(item.getOrderId());
                param.setOrderItemId(item.getOrderId());
                param.setUpdateStatus(0);
                param.setStatus(1);
                param.setUserId(orders.getMemberId());
                param.setBookPic(item.getProductPic());
                param.setBookName(item.getProductName());
                commentService.create(param);
            }

        }
        return orders;
    }

    @Override
    public List<Orders> listAll(OrderQueryParam param,Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        return orderDao.listAll(param);
    }

    @Override
    public List<OrderItem> getItems(Long id) {
        PageHelper.startPage(1,100);
        OrderItemExample example = new OrderItemExample();
        example.createCriteria().andOrderIdEqualTo(id);
        return itemMapper.selectByExample(example);
    }

    @Override
    public List<OrderWithItem> listWithItems(Long userId,Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        return orderDao.listWithItem(userId);
    }
}
