package com.hirrao.javaee.controller

import com.hirrao.javaee.entity.Result
import com.hirrao.javaee.service.BloodPressureService
import com.hirrao.javaee.utils.BloodPressureUtil.generateClassification
import com.hirrao.javaee.utils.BloodPressureUtil.generateRiskLevel
import com.hirrao.javaee.utils.Result.error
import com.hirrao.javaee.utils.Result.success
import com.hirrao.javaee.utils.SnowFlake
import com.hirrao.javaee.utils.ThreadLocalUtil.get
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.time.LocalDate

@RestController
@RequestMapping("/bp")
class BloodPressureController(private val bloodPressureService: BloodPressureService) {
    private val logger: Logger = LoggerFactory.getLogger(javaClass)
    private val snowFlake = SnowFlake(1, 2)

    data class DataInsert(val date: String, val sbp: String, val dbp: String)

    @PostMapping("/record/uid")
    fun getUid(@RequestBody dateRow: String): Result {
        logger.debug("/record/uid接受请求{}", dateRow)
        val user = get()
        val date = dateRow.let { LocalDate.parse(it) }
        val result = bloodPressureService.getBloodPressureList(user.uid, date)
        return success(result)
    }

    @PostMapping("/record/table")
    fun getTable(@RequestBody dateRow: String): Result {
        logger.debug("/record/table接受请求{}", dateRow)
        val user = get()
        val date = dateRow.let { LocalDate.parse(it) }
        val result = bloodPressureService.getBloodPressureTable(date, user.uid)
        return success(result)
    }

    @PostMapping("/record/riskLevel")
    fun getRiskLevel(@RequestBody dateRow: String): Result {
        logger.debug("/record/riskLevel接受请求{}", dateRow)
        val user = get()
        val date = dateRow.let { LocalDate.parse(it) }
        val result = bloodPressureService.getRiskLevel(user.uid, date)
        return success(result)
    }

    @PostMapping("/record/insert")
    fun insert(@RequestBody dataInsert: DataInsert): Result {
        logger.debug("/record/insert接受请求{}", dataInsert)
        val (recordTime, sbp, dbp) = dataInsert
        val user = get()
        val userId = user.uid
        if (recordTime.isEmpty() || sbp.isEmpty() || dbp.isEmpty()) {
            return error(101, "参数错误")
        }

        //将string处理为数值进行处理
        val sbpValue = sbp.toFloat()
        val dbpValue = dbp.toFloat()

        // 生成 classification 和 riskLevel
        val classification = generateClassification(sbpValue, dbpValue)
        val riskLevel = generateRiskLevel(sbpValue, dbpValue)

        bloodPressureService.insertBloodPressure(
            snowFlake.nextId(), userId, sbpValue, dbpValue, recordTime, classification, riskLevel
        )
        return success()
    }
}
