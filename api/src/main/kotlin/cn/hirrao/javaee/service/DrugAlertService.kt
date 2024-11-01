package cn.hirrao.javaee.service

import org.springframework.stereotype.Service
import java.time.LocalDate
import java.time.LocalTime

@Service
interface DrugAlertService {
    fun insertDrugAlert(alertId: Long, uid: Long, drugId: Long, alertTime: LocalTime?, eatTime: LocalDate?)

    fun updateDrugAlertIsActiveById(alertId: Long, isActive: Int)

    fun updateDrugAlertEatTimeById(alertId: Long, eatTime: LocalDate?)

    fun deleteDrugAlertById(alertId: Long, uid: Long, drugId: Long)
}
