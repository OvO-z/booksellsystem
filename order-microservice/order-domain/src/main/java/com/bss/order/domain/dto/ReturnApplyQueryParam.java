package com.bss.order.domain.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by QAQ on 2019/5/31
 */

@Getter
@Setter
public class ReturnApplyQueryParam {
    private Long id;
    private Integer type;
    private Integer status;
    private String createTime;
}
