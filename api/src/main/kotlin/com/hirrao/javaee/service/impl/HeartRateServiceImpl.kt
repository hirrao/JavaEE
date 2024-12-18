package com.hirrao.javaee.service.impl

import com.hirrao.javaee.entity.HeartRate
import com.hirrao.javaee.mapper.HeartRateMapper
import com.hirrao.javaee.service.HeartRateService
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
