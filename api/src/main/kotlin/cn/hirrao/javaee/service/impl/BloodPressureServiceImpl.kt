package cn.hirrao.javaee.service.impl

import cn.hirrao.javaee.entity.BloodPressure
import cn.hirrao.javaee.entity.BloodPressureTable
import cn.hirrao.javaee.mapper.BloodPressureMapper
import cn.hirrao.javaee.mapper.BloodPressureTableMapper
import cn.hirrao.javaee.service.BloodPressureService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.time.LocalDate

@Service
class BloodPressureServiceImpl @Autowired constructor(
    private val bloodPressureMapper: BloodPressureMapper, private val bloodPressureTableMapper: BloodPressureTableMapper
) : BloodPressureService {
    override fun getBloodPressureList(uid: Long?, date: LocalDate?): List<BloodPressure?>? {
        return bloodPressureMapper.getBloodPressureList(uid, date)
    }

    override fun insertBloodPressure(
        bplId: Long,
        userId: Long,
        sbp: Float,
        dbp: Float,
        recordTime: String?,
        classification: String?,
        riskLevel: String?
    ) {
        bloodPressureMapper.insertBloodPressure(bplId, userId, sbp, dbp, recordTime, classification, riskLevel)
    }

    override fun getBloodPressureTable(date: LocalDate?, uid: Long?): BloodPressureTable? {
        return bloodPressureTableMapper.getBloodPressureTable(date, uid)
    }

    override fun getRiskLevel(uid: Long?, date: LocalDate?): List<String?>? {
        return bloodPressureMapper.getRiskLevel(uid, date)
    }
}
