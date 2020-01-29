package com.bss.order.domain.dto;

import com.bss.order.object.model.OrderItem;
import com.bss.order.object.model.Orders;

import java.util.List;

/**
 * Created by QAQ on 2019/5/31
 */
public class OrderWithItem extends Orders {
    private List<OrderItem> items;
}
