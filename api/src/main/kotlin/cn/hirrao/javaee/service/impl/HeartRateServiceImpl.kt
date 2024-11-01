package cn.hirrao.javaee.service.impl

import cn.hirrao.javaee.entity.HeartRate
import cn.hirrao.javaee.mapper.HeartRateMapper
import cn.hirrao.javaee.service.HeartRateService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class HeartRateServiceImpl @Autowired constructor(private val heartRateMapper: HeartRateMapper) : HeartRateService {
    override fun searchHeartRateById(uid: Long?): List<HeartRate?>? {
        return heartRateMapper.searchHeartRateById(uid)
    }

    override fun insertHeartRate(hrlId: Long, uid: Long, heartRate: Float, recordTime: String?) {
        heartRateMapper.insertHeartRate(hrlId, uid, heartRate, recordTime)
    }
}
