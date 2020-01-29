package com.bss.order.restapi.service.impl;

import com.bss.order.domain.dto.ReturnApplyParam;
import com.bss.order.domain.dto.ReturnApplyQueryParam;
import com.bss.order.domain.mapper.ReturnApplyMapper;
import com.bss.order.domain.util.CopyUtil;
import com.bss.order.object.model.ReturnApply;
import com.bss.order.object.model.ReturnApplyExample;
import com.bss.order.restapi.service.ReturnApplyService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Date;

/**
 * Created by QAQ on 2019/5/31
 */
@Service
public class ReturnApplyServiceImpl implements ReturnApplyService {

    @Autowired
    ReturnApplyMapper applyMapper;

    @Override
    public List<ReturnApply> getList(ReturnApplyQueryParam param, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        ReturnApplyExample example = new ReturnApplyExample();
        if (param != null) {
            ReturnApplyExample.Criteria criteria = example.createCriteria();
            if (param.getId() != null)
                criteria.andIdEqualTo(param.getId());
            if (param.getStatus() != null)
                criteria.andStatusEqualTo(param.getStatus());
            if (param.getType() != null)
                criteria.andTypeEqualTo(param.getType());
            if (param.getCreateTime() != null)
                criteria.andCreateTimeLike("%"+param.getCreateTime()+"%");
        }
        List<ReturnApply> applies = applyMapper.selectByExample(example);
        return applies;
    }

    @Override
    public ReturnApply create(ReturnApplyParam param) {
        ReturnApply apply = CopyUtil.copy(param, ReturnApply.class);
        apply.setCreateTime(new Date().toString());
        applyMapper.insertSelective(apply);
        return apply;
    }

    @Override
    public ReturnApply update(Long id, ReturnApplyParam param) {
        ReturnApply apply = CopyUtil.copy(param, ReturnApply.class);
        apply.setId(id);
        applyMapper.updateByPrimaryKeySelective(apply);
        return apply;
    }

    @Override
    public Long delete(Long id) {
        applyMapper.deleteByPrimaryKey(id);
        return id;
    }

    @Override
    public ReturnApply get(Long id) {
        return applyMapper.selectByPrimaryKey(id);
    }
}
