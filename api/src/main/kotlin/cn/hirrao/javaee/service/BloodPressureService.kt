package cn.hirrao.javaee.service

import cn.hirrao.javaee.entity.BloodPressure
import cn.hirrao.javaee.entity.BloodPressureTable
import org.springframework.stereotype.Service
import java.time.LocalDate

@Service
interface BloodPressureService {
    fun getBloodPressureList(uid: Long?, date: LocalDate?): List<BloodPressure?>?

    fun insertBloodPressure(
        bplId: Long,
        userId: Long,
        sbp: Float,
        dbp: Float,
        recordTime: String?,
        classification: String?,
        riskLevel: String?
    )

    fun getBloodPressureTable(date: LocalDate?, uid: Long?): BloodPressureTable?

    fun getRiskLevel(uid: Long?, date: LocalDate?): List<String?>?
}
