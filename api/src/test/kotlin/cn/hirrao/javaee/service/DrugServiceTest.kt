package cn.hirrao.javaee.service

import jakarta.annotation.Resource
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.transaction.annotation.Transactional

@SpringBootTest
@Transactional
internal open class DrugServiceTest {
    @Resource
    private val drugService: DrugService? = null

    @Resource
    private val userService: UserService? = null

    @BeforeEach
    fun init() {
        userService!!.register(1, "test", "test", "12345678912")
    }

    @Test
    fun insertDrug() {
        drugService!!.insertDrug(1L, 1, "test", "test", "test", 1.0f, 1)
    }

    @get:Test
    val pageDrugInfo: Unit
        get() {
            drugService!!.getPageDrugInfo(1049164671497670656L, 1, 5)
        }

    @get:Test
    val pageDrugInfoByDrugName: Unit
        get() {
            drugService!!.getPageDrugInfoByDrugName(1, 1, 1, "test")
        }

    @get:Test
    val pageDrugInfoTotal: Unit
        get() {
        }

    @get:Test
    val pageDrugInfoTotalByDrugName: Unit
        get() {
            drugService!!.getPageDrugInfoTotalByDrugName(1, "test")
        }

    @get:Test
    val pageDrugAlertInfo: Unit
        get() {
        }

    @get:Test
    val pageDrugAlertInfoByDrugName: Unit
        get() {
        }

    @get:Test
    val pageDrugAlertInfoTotal: Unit
        get() {
        }

    @get:Test
    val pageDrugAlertInfoTotalByDrugName: Unit
        get() {
        }

    @Test
    fun updateDrugIsActiveById() {
    }

    @Test
    fun deleteDrugById() {
    }
}