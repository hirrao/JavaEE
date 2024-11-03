package cn.hirrao.javaee.controller

import cn.hirrao.javaee.entity.Result
import cn.hirrao.javaee.service.HeartRateService
import cn.hirrao.javaee.utils.Result.error
import cn.hirrao.javaee.utils.Result.success
import cn.hirrao.javaee.utils.SnowFlake
import cn.hirrao.javaee.utils.StringUtil.isEmpty
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/heartRate")
class HeartRateController(private val heartRateService: HeartRateService) {
    private val logger: Logger = LoggerFactory.getLogger(javaClass)
    private val snowFlake = SnowFlake(1, 3)

    @GetMapping("/searchHRById")
    fun searchHRById(@RequestBody map: Map<String?, String>): Result {
        logger.debug("通过id查找心率信息{}", map)
        val userId = map["userId"]!!.toLong()
        val result = heartRateService.searchHeartRateById(userId)
        return success(result)
    }

    @PostMapping("/insertHR")
    fun insertHR(@RequestBody map: Map<String?, String?>): Result {
        logger.debug("插入心率信息{}", map)
        val userId = map["userId"]
        val heartRate = map["heartRate"]
        val date = map["date"]
        if (isEmpty(userId) || isEmpty(heartRate) || isEmpty(date)) {
            return error(101, "错误输入")
        }
        heartRateService.insertHeartRate(snowFlake.nextId(), userId!!.toLong(), heartRate!!.toFloat(), date)
        return success()
    }
}
