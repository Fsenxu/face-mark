package com.heqichao.springBootDemo.base.config;

import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import javax.sql.DataSource;
public interface DbConfig {

    /**
     * 定义数据源
     *
     * @return
     * @throws Exception
     */
    DataSource  dataSource() throws Exception;


    /**
     * 定义失误管理器
     *
     * @param dataSource
     * @return
     */
    DataSourceTransactionManager transactionManager(DataSource dataSource);

}