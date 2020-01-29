package com.bss.re.domain.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@MapperScan({"com.bss.re.domain.mapper", "com.bss.re.domain.dao"})
public class MybatisConfig {
}
