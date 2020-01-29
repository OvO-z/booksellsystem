package com.bss.order.domain.dao;

import com.bss.order.object.dto.OrderQueryParam;
import com.bss.order.domain.dto.OrderWithItem;
import com.bss.order.object.model.OrderItem;
import com.bss.order.object.model.Orders;

import java.util.List;

/**
 * Created by QAQ on 2019/5/26
 */
public interface OrderDao {
    int insertListOrderItem(List<OrderItem> items);
    List<Orders> listAll(OrderQueryParam param);
    List<OrderWithItem> listWithItem(Long id);
}
