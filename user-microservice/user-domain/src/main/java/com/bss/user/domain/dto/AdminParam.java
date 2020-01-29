package com.bss.user.domain.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * Created by QAQ on 2019/5/9
 */
@Getter
@Setter
public class AdminParam {
    private String username;
    private String password;
    private String icon;
    private String email;
    private String nickName;
    private List<Integer> roles;
    private Integer status;
}
