package com.hirrao.javaee.config

import com.baomidou.mybatisplus.annotation.DbType
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor
import org.apache.ibatis.session.SqlSessionFactory
import org.mybatis.spring.SqlSessionFactoryBean
import org.mybatis.spring.SqlSessionTemplate
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import javax.sql.DataSource

@Configuration
class MyBatisPlusConfig {
    @Bean
    fun paginationInterceptor(): MybatisPlusInterceptor {
        val interceptor = MybatisPlusInterceptor()
        val paginationInnerInterceptor = PaginationInnerInterceptor(DbType.MYSQL)
        interceptor.addInnerInterceptor(paginationInnerInterceptor)
        return interceptor
    }


    @Bean
    fun sqlSessionFactory(dataSource: DataSource): SqlSessionFactory {
        val sessionFactory = SqlSessionFactoryBean()
        sessionFactory.setDataSource(dataSource)
        return sessionFactory.`object`!!
    }


    fun sqlSessionTemplate(sqlSessionFactory: SqlSessionFactory): SqlSessionTemplate {
        return SqlSessionTemplate(sqlSessionFactory)
    }
}
