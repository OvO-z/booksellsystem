package com.bss.order.restapi.service;

import com.bss.order.domain.dto.ReturnApplyParam;
import com.bss.order.domain.dto.ReturnApplyQueryParam;
import com.bss.order.object.model.ReturnApply;

import java.util.List;

/**
 * Created by QAQ on 2019/5/31
 */
public interface ReturnApplyService {
    List<ReturnApply> getList(ReturnApplyQueryParam param, Integer pageNum, Integer pageSize);
    ReturnApply create(ReturnApplyParam param);
    ReturnApply update(Long id,ReturnApplyParam param);
    Long delete(Long id);
    ReturnApply get(Long id);
}
