package com.hirrao.javaee.controller

import com.hirrao.javaee.entity.Result
import com.hirrao.javaee.service.HeartRateService
import com.hirrao.javaee.utils.Result.error
import com.hirrao.javaee.utils.Result.success
import com.hirrao.javaee.utils.SnowFlake
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/heartRate")
class HeartRateController(private val heartRateService: HeartRateService) {
    private val logger: Logger = LoggerFactory.getLogger(javaClass)
    private val snowFlake = SnowFlake(1, 3)

    data class DataInsertHR(val userId: Long, val heartRate: Float, val date: String)

    @PostMapping("/searchHRById")
    fun searchHRById(@RequestBody userId: Long): Result {
        logger.debug("通过id查找心率信息{}", userId)
        val result = heartRateService.searchHeartRateById(userId)
        return success(result)
    }

    @PostMapping("/insertHR")
    fun insertHR(@RequestBody dataInsertHR: DataInsertHR): Result {
        logger.debug("插入心率信息{}", dataInsertHR)
        val (userId, heartRate, date) = dataInsertHR
        if (date.isEmpty()) {
            return error(101, "错误输入")
        }
        heartRateService.insertHeartRate(snowFlake.nextId(), userId, heartRate, date)
        return success()
    }
}
