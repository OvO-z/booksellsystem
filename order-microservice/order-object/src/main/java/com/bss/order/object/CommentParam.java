package com.bss.order.object;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * Created by QAQ on 2019/6/1
 */

@Getter
@Setter
public class CommentParam {
    private Long id;

    private Long orderId;

    private Long userId;

    private Long orderItemId;

    private String comment;

    private BigDecimal rate;

    private Long bookIsbn;

    private Integer status;

    private Integer updateStatus;

    private String updateTime;

    private String userHeader;

    private String bookPic;

    private String bookName;

    private String username;
}
