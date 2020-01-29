package com.bss.order.restapi.service.impl;

import com.bss.order.domain.mapper.ReturnReasonMapper;
import com.bss.order.domain.util.CopyUtil;
import com.bss.order.object.dto.ReasonParam;
import com.bss.order.object.model.ReturnReason;
import com.bss.order.object.model.ReturnReasonExample;
import com.bss.order.restapi.service.ReturnReasonService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by QAQ on 2019/5/31
 */
@Service
public class ReturnReasonServiceImpl implements ReturnReasonService {

    @Autowired
    ReturnReasonMapper mapper;

    @Override
    public List<ReturnReason> getListAll() {
        ReturnReasonExample example = new ReturnReasonExample();
        return mapper.selectByExample(example);
    }

    @Override
    public List<ReturnReason> getList(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        List<ReturnReason> reasons = getListAll();
        return reasons;
    }

    @Override
    public ReturnReason create(ReasonParam param) {
        ReturnReason reason = CopyUtil.copy(param, ReturnReason.class);
        reason.setCreateTime(new Date());
        mapper.insertSelective(reason);
        return reason;
    }

    @Override
    public ReturnReason update(Long id, ReasonParam param) {
        ReturnReason reason = CopyUtil.copy(param, ReturnReason.class);
        reason.setId(id);
        mapper.updateByPrimaryKeySelective(reason);
        return reason;
    }

    @Override
    public Long delete(Long id) {
        mapper.deleteByPrimaryKey(id);
        return id;
    }

    @Override
    public ReturnReason get(Long id) {
        return mapper.selectByPrimaryKey(id);
    }
}
