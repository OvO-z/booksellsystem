package com.bss.re.domain.mapper;

import com.bss.re.domain.model.Re;
import com.bss.re.domain.model.ReExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ReMapper {
    int countByExample(ReExample example);

    int deleteByExample(ReExample example);

    int deleteByPrimaryKey(Long id);

    int insert(Re record);

    int insertSelective(Re record);

    List<Re> selectByExample(ReExample example);

    Re selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") Re record, @Param("example") ReExample example);

    int updateByExample(@Param("record") Re record, @Param("example") ReExample example);

    int updateByPrimaryKeySelective(Re record);

    int updateByPrimaryKey(Re record);
}