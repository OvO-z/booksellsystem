package com.bss.order.domain.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@MapperScan({"com.bss.order.domain.mapper","com.bss.order.domain.dao"})
public class MybatisConfig {
}
