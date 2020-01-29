package com.bss.order.object.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * Created by QAQ on 2019/5/27
 */

@Getter
@Setter
public class OrderQueryParam {
    private Long memberId;
    private String orderSn;
    private Integer status;
    private String receiverName;
    private String createTime;
}
