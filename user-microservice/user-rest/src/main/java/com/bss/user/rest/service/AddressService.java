package com.bss.user.rest.service;

import com.bss.user.domain.dto.AddressParam;
import com.bss.user.domain.model.Address;

import java.util.List;

/**
 * Created by QAQ on 2019/5/24
 */
public interface AddressService {
    List<Address> get(Long userId);
    Address update(Integer id, AddressParam param);
    int delete(Integer id);
    Address create(AddressParam param);
}
