package com.bss.order.restapi.service;

import com.bss.order.domain.dto.OrderParam;
import com.bss.order.object.dto.OrderQueryParam;
import com.bss.order.domain.dto.OrderWithItem;
import com.bss.order.object.model.OrderItem;
import com.bss.order.object.model.Orders;

import java.util.List;

/**
 * Created by QAQ on 2019/5/26
 */
public interface OrderService {
    Long create(OrderParam param);
    Orders get(Long id);
    Orders update(Long id, OrderParam param);
    Orders updateStatus(Long id, Integer status);
    List<Orders> listAll(OrderQueryParam param,Integer pageNum, Integer pageSize);
    List<OrderItem> getItems(Long id);
    List<OrderWithItem> listWithItems(Long userId,Integer pageNum, Integer pageSize);
}
