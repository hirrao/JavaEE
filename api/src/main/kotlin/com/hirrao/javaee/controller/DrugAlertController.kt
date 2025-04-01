package com.hirrao.javaee.controller

import com.hirrao.javaee.entity.Result
import com.hirrao.javaee.service.DrugAlertService
import com.hirrao.javaee.utils.Result.success
import com.hirrao.javaee.utils.SnowFlake
import com.hirrao.javaee.utils.ThreadLocalUtil.get
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.time.LocalDate
import java.time.LocalTime

@RestController
@RequestMapping("/drugAlert")
class DrugAlertController @Autowired internal constructor(private val drugAlertService: DrugAlertService) {
    private val snowFlake = SnowFlake(1, 5)

    data class DataInsertDrug(val drugId: Long, val alertTimeRow: String)
    data class DataUpdateDrugAlertIsActiveById(val alertId: Long, val isActive: Int)
    data class DataUpdateDrugAlertEatTimeById(val alertId: Long, val eatTimeRow: String)
    data class DataDeleteDrugAlertById(val alertId: Long, val drugId: Long)

    @PostMapping("/insertDrug")
    fun insertDrug(dataInsertDrug: DataInsertDrug): Result {
        val alertId = snowFlake.nextId()
        val user = get()
        val uid = user.uid
        val (drugId, alertTimeRow) = dataInsertDrug
        val alertTime = alertTimeRow.let { LocalTime.parse(it) }
        val eatTime = LocalDate.parse("2000-01-01")
        drugAlertService.insertDrugAlert(alertId, uid, drugId, alertTime, eatTime)
        return success()
    }

    @PostMapping("/updateDrugAlertIsActiveById")
    fun updateDrugAlertIsActiveById(@RequestBody dataUpdateDrugAlertIsActiveById: DataUpdateDrugAlertIsActiveById): Result {
        val (alertId, isActive) = dataUpdateDrugAlertIsActiveById
        drugAlertService.updateDrugAlertIsActiveById(alertId, isActive)
        return success()
    }

    @PostMapping("/updateDrugAlertEatTimeById")
    fun updateDrugAlertEatTimeById(@RequestBody dataUpdateDrugAlertEatTimeById: DataUpdateDrugAlertEatTimeById): Result {
        val (alertId, eatTimeRow) = dataUpdateDrugAlertEatTimeById
        val eatTime = eatTimeRow.let { LocalDate.parse(it) }
        drugAlertService.updateDrugAlertEatTimeById(alertId, eatTime)
        return success()
    }

    @PostMapping("/deleteDrugAlertById")
    fun deleteDrugAlertById(@RequestBody dataDeleteDrugAlertById: DataDeleteDrugAlertById): Result {
        val (alertId, drugId) = dataDeleteDrugAlertById
        val user = get()
        val uid = user.uid
        drugAlertService.deleteDrugAlertById(alertId, uid, drugId)
        return success()
    }
}
