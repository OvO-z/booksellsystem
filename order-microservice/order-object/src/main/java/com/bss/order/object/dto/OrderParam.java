package com.bss.order.object.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * Created by QAQ on 2019/5/26
 */

@Getter
@Setter
public class OrderParam {
    private Long id;

    private Long memberId;

    private String orderSn;

    private Date createTime;

    private String memberUsername;

    private BigDecimal totalAmount;

    private Integer payType;

    private Integer status;

    private String receiverName;

    private String receiverPhone;

    private String receiverAddress;

    private String note;

    private Integer confirmStatus;

    private Integer deleteStatus;

    private Date paymentTime;

    private Date receiveTime;

    private Date commentTime;

    private Date modifyTime;

    private List<BookParam> bookList;
}
