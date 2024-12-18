package com.hirrao.javaee.service

import com.hirrao.javaee.entity.HeartRate
import org.springframework.stereotype.Service

@Service
interface HeartRateService {
    fun searchHeartRateById(uid: Long?): List<HeartRate?>?
    fun insertHeartRate(hrlId: Long, uid: Long, heartRate: Float, recordTime: String?)
}
