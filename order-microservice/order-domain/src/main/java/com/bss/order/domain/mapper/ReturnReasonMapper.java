package com.bss.order.domain.mapper;

import com.bss.order.object.model.ReturnReason;
import com.bss.order.object.model.ReturnReasonExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ReturnReasonMapper {
    int countByExample(ReturnReasonExample example);

    int deleteByExample(ReturnReasonExample example);

    int deleteByPrimaryKey(Long id);

    int insert(ReturnReason record);

    int insertSelective(ReturnReason record);

    List<ReturnReason> selectByExample(ReturnReasonExample example);

    ReturnReason selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") ReturnReason record, @Param("example") ReturnReasonExample example);

    int updateByExample(@Param("record") ReturnReason record, @Param("example") ReturnReasonExample example);

    int updateByPrimaryKeySelective(ReturnReason record);

    int updateByPrimaryKey(ReturnReason record);
}