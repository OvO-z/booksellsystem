package com.bss.book.domain.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@MapperScan({"com.bss.book.domain.mapper","com.bss.book.domain.dao"})
public class MybatisConfig {
}
