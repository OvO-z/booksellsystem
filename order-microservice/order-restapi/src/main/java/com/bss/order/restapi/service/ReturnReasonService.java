package com.bss.order.restapi.service;

import com.bss.order.object.dto.ReasonParam;
import com.bss.order.object.model.ReturnReason;

import java.util.List;

/**
 * Created by QAQ on 2019/5/31
 */
public interface ReturnReasonService {

    List<ReturnReason> getListAll();
    List<ReturnReason> getList(Integer pageNum, Integer pageSize);
    ReturnReason create(ReasonParam param);
    ReturnReason update(Long id,ReasonParam param);
    Long delete(Long id);
    ReturnReason get(Long id);
}
