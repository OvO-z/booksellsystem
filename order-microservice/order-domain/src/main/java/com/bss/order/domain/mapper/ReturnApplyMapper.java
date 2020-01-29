package com.bss.order.domain.mapper;

import com.bss.order.object.model.ReturnApply;
import com.bss.order.object.model.ReturnApplyExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ReturnApplyMapper {
    int countByExample(ReturnApplyExample example);

    int deleteByExample(ReturnApplyExample example);

    int deleteByPrimaryKey(Long id);

    int insert(ReturnApply record);

    int insertSelective(ReturnApply record);

    List<ReturnApply> selectByExample(ReturnApplyExample example);

    ReturnApply selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") ReturnApply record, @Param("example") ReturnApplyExample example);

    int updateByExample(@Param("record") ReturnApply record, @Param("example") ReturnApplyExample example);

    int updateByPrimaryKeySelective(ReturnApply record);

    int updateByPrimaryKey(ReturnApply record);
}