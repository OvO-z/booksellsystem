package com.bss.order.domain.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by QAQ on 2019/5/31
 */

@Getter
@Setter
public class ReturnApplyParam {
    private Long id;

    private Long orderId;

    private String orderSn;

    private String createTime;

    private String memberUsername;

    private BigDecimal returnAmount;

    private String returnPhone;

    private String returnName;

    private Integer status;

    private Integer type;

    private String reason;

    private String description;

    private String proofPics;

    private String expressNumber;

    private String receiverAddress;

    private String receiverName;

    private String receiverPhone;
}
