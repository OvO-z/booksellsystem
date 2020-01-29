package com.bss.user.domain.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@MapperScan({"com.bss.user.domain.mapper", "com.bss.user.domain.dao"})
public class MybatisConfig {
}
