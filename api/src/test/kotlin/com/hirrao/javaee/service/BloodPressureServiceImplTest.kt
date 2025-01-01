package com.hirrao.javaee.service

import com.hirrao.javaee.service.impl.BloodPressureServiceImpl
import com.hirrao.javaee.service.impl.UserServiceImpl
import io.kotest.core.spec.style.StringSpec
import io.kotest.extensions.spring.SpringExtension
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDate

@SpringBootTest
@Transactional
class BloodPressureServiceImplTest(
    private val bloodPressureService: BloodPressureServiceImpl, private val userService: UserServiceImpl
) : StringSpec({

    extensions(SpringExtension)

    beforeTest {
        userService.register(1, "test", "test", "13574851354")
    }


    "bloodPressureList" {
    }

    "insertBloodPressure" {
        bloodPressureService.insertBloodPressure(1, 1, 120f, 80f, "2021-01-01 12:00:00", "正常", "低风险")
    }

    "bloodPressureTable" {
        bloodPressureService.insertBloodPressure(1, 1, 120f, 80f, "2021-01-01 12:00:00", "正常", "低风险")
        bloodPressureService.insertBloodPressure(2, 1, 130f, 90f, "2021-01-02 12:00:00", "正常", "中风险")
        println(bloodPressureService.getBloodPressureTable(LocalDate.parse("2021-01-01"), 1))
    }


    "riskLevel" {
    }
})