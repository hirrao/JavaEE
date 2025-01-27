package com.hirrao.javaee.service.impl

import com.hirrao.javaee.entity.BloodPressure
import com.hirrao.javaee.entity.BloodPressureTable
import com.hirrao.javaee.mapper.BloodPressureMapper
import com.hirrao.javaee.mapper.BloodPressureTableMapper
import com.hirrao.javaee.service.BloodPressureService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.time.LocalDate

@Service
class BloodPressureServiceImpl @Autowired constructor(
    private val bloodPressureMapper: BloodPressureMapper, private val bloodPressureTableMapper: BloodPressureTableMapper
) : BloodPressureService {
    override fun getBloodPressureList(uid: Int, date: LocalDate?): List<BloodPressure?>? {
        return bloodPressureMapper.getBloodPressureList(uid, date)
    }

    override fun insertBloodPressure(
        bplId: Long,
        userId: Int,
        sbp: Float,
        dbp: Float,
        recordTime: String?,
        classification: String?,
        riskLevel: String?
    ) {
        bloodPressureMapper.insertBloodPressure(bplId, userId, sbp, dbp, recordTime, classification, riskLevel)
    }

    override fun getBloodPressureTable(date: LocalDate?, uid: Int): BloodPressureTable? {
        return bloodPressureTableMapper.getBloodPressureTable(date, uid)
    }

    override fun getRiskLevel(uid: Int, date: LocalDate?): List<String?>? {
        return bloodPressureMapper.getRiskLevel(uid, date)
    }
}
