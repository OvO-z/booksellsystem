package com.bss.user.domain.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by QAQ on 2019/5/24
 */

@Getter
@Setter
public class AddressParam {
    private Integer addressId;

    private Long userId;

    private String username;

    private String address;

    private String phone;
}
