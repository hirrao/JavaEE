package com.hirrao.javaee.service

import com.hirrao.javaee.service.impl.DrugServiceImpl
import com.hirrao.javaee.service.impl.UserServiceImpl
import io.kotest.core.spec.style.StringSpec
import io.kotest.extensions.spring.SpringExtension
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.transaction.annotation.Transactional

@SpringBootTest
@Transactional
class DrugServiceImplTest(
    private val drugService: DrugServiceImpl, private val userService: UserServiceImpl
) : StringSpec({

    extensions(SpringExtension)

    beforeTest {
        userService.register(1, "test", "test", "12345678912")
    }

    "insertDrug" {
        drugService.insertDrug(1L, 1, "test", "test", "test", 1.0f, 1)
    }

    "pageDrugInfo" {
        drugService.getPageDrugInfo(10491, 1, 5)
    }

    "pageDrugInfoByDrugName" {
        drugService.getPageDrugInfoByDrugName(1, 1, 1, "test")
    }

    "pageDrugInfoTotal" {}

    "pageDrugInfoTotalByDrugName" {
        drugService.getPageDrugInfoTotalByDrugName(1, "test")
    }

    "pageDrugAlertInfo" {}

    "pageDrugAlertInfoByDrugName" {}

    "pageDrugAlertInfoTotal" {}

    "pageDrugAlertInfoTotalByDrugName" {}

    "updateDrugIsActiveById " {}

    "deleteDrugById" {}
})