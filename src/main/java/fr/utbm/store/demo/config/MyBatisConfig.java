package fr.utbm.store.demo.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@Configuration
@EnableTransactionManagement
@MapperScan({"fr.utbm.store.demo.mapper","fr.utbm.store.demo.dao"})
public class MyBatisConfig {
}
