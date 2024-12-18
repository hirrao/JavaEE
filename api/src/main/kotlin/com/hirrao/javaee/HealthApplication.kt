package com.hirrao.javaee

import org.mybatis.spring.annotation.MapperScan
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

@SpringBootApplication
@MapperScan("com.hirrao.javaee.mapper")
open class HealthApplication

fun main(args: Array<String>) {
    SpringApplication.run(HealthApplication::class.java, *args)
}