package com.bss.re.domain.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * Created by QAQ on 2019/5/30
 */
@Getter
@Setter
public class UserSimilar {
    private String userId;
    private double similar;
}
