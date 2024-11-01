package cn.hirrao.javaee.service

import jakarta.annotation.Resource
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDate

@SpringBootTest
@Transactional
internal open class BloodPressureServiceTest {
    @Resource
    private val bloodPressureService: BloodPressureService? = null

    @Resource
    private val userService: UserService? = null

    @BeforeEach
    fun init() {
        userService!!.register(1, "test", "test", "13574851354")
    }


    @get:Test
    val bloodPressureList: Unit
        get() {
        }

    @Test
    fun insertBloodPressure() {
        bloodPressureService!!.insertBloodPressure(1, 1, 120f, 80f, "2021-01-01 12:00:00", "正常", "低风险")
    }

    @get:Test
    val bloodPressureTable: Unit
        get() {
            bloodPressureService!!.insertBloodPressure(1, 1, 120f, 80f, "2021-01-01 12:00:00", "正常", "低风险")
            bloodPressureService.insertBloodPressure(2, 1, 130f, 90f, "2021-01-02 12:00:00", "正常", "中风险")
            println(bloodPressureService.getBloodPressureTable(LocalDate.parse("2021-01-01"), 1L))
        }


    @get:Test
    val riskLevel: Unit
        get() {
        }
}