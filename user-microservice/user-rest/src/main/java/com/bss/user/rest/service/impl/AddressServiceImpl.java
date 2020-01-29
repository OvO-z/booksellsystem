package com.bss.user.rest.service.impl;

import com.bss.user.domain.dto.AddressParam;
import com.bss.user.domain.mapper.AddressMapper;
import com.bss.user.domain.model.Address;
import com.bss.user.domain.model.AddressExample;
import com.bss.user.domain.util.CopyUtil;
import com.bss.user.rest.service.AddressService;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by QAQ on 2019/5/24
 */
@Service
@Log4j
public class AddressServiceImpl implements AddressService {

    @Autowired
    AddressMapper addressMapper;

    @Override
    public List<Address> get(Long userId) {
        AddressExample example = new AddressExample();
        example.createCriteria().andUserIdEqualTo(userId);
        return addressMapper.selectByExample(example);
    }

    @Override
    public Address update(Integer id, AddressParam param) {
        Address address = CopyUtil.copy(param,Address.class);
        log.error(address);
        address.setAddressId(id);
        addressMapper.updateByPrimaryKeySelective(address);
        return address;
    }

    @Override
    public int delete(Integer id) {
       return addressMapper.deleteByPrimaryKey(id);
    }

    @Override
    public Address create(AddressParam param) {
        Address address = CopyUtil.copy(param,Address.class);
        addressMapper.insertSelective(address);
        return address;
    }
}
