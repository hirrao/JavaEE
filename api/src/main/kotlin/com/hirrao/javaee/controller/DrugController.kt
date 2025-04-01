package com.hirrao.javaee.controller

import com.hirrao.javaee.entity.Result
import com.hirrao.javaee.service.DrugAlertService
import com.hirrao.javaee.service.DrugService
import com.hirrao.javaee.utils.Result.success
import com.hirrao.javaee.utils.SnowFlake
import com.hirrao.javaee.utils.ThreadLocalUtil.get
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import java.time.LocalDate
import java.time.LocalTime

@RestController
@RequestMapping("/drug")
class DrugController @Autowired internal constructor(
    private val drugService: DrugService, private val drugAlertService: DrugAlertService
) {
    private val snowFlakeDrug = SnowFlake(1, 4)
    private val snowFlakeDrugAlert = SnowFlake(1, 5)
    private val logger: Logger = LoggerFactory.getLogger(javaClass)

    data class DataGetPageDrugsInfo(val curPage: Int, val size: Int)
    data class DataGetPageDrugsInfoByDrugName(val curPage: Int, val size: Int, val drugName: String)
    data class DataGetPageDrugsAlertInfo(val curPage: Int, val size: Int)
    data class DataGetPageDrugsAlertInfoByDrugName(val curPage: Int, val size: Int, val drugName: String)
    data class DataInsertDrug(
        val drugName: String, val frequency: String, val unit: String, val dosage: Float, val isActive: Int
    )

    data class DataInsertDrugAndAlert(
        val drugName: String,
        val frequency: String,
        val unit: String,
        val dosage: Float,
        val isActive: Int,
        val alertTimeRow: String
    )

    data class DataUpdateDrugIsActiveById(val drugId: Long, val isActive: Int)
    data class DataUpdateDrugAlertIsActiveById(val alertId: Long, val drugId: Long)


    @PostMapping("/getPageDrugsInfo")
    fun getPageDrugsInfo(@RequestBody getPageDrugsInfo: DataGetPageDrugsInfo): Result {
        val (curPage, size) = getPageDrugsInfo
        val user = get()
        val uid = user.uid
        return success(drugService.getPageDrugInfo(uid, curPage, size))
    }

    @PostMapping("/getPageDrugsInfoByDrugName")
    fun getPageDrugsInfoByDrugName(@RequestBody dataGetPageDrugsInfoByDrugName: DataGetPageDrugsInfoByDrugName): Result {
        val (curPage, size, drugName) = dataGetPageDrugsInfoByDrugName
        val user = get()
        val uid = user.uid
        if (drugName.isNotEmpty()) {
            println("notEmpty")
            return success(drugService.getPageDrugInfoByDrugName(uid, curPage, size, drugName))
        } else {
            println("Empty")
            return success(drugService.getPageDrugInfo(uid, curPage, size))
        }
    }

    @GetMapping("/getPageDrugInfoTotal")
    fun pageDrugInfoTotal(): Result {
        val user = get()
        val uid = user.uid
        return success(drugService.getPageDrugInfoTotal(uid))
    }

    @PostMapping("/getPageDrugInfoTotalByDrugName")
    fun getPageDrugInfoTotalByDrugName(@RequestBody drugName: String): Result {
        val user = get()
        val uid = user.uid
        return if (drugName.isNotEmpty()) {
            success(drugService.getPageDrugInfoTotalByDrugName(uid, drugName))
        } else {
            success(drugService.getPageDrugInfoTotal(uid))
        }
    }

    @PostMapping("/getPageDrugsAlertInfo")
    fun getPageDrugsAlertInfo(@RequestBody dataGetPageDrugsAlertInfo: DataGetPageDrugsAlertInfo): Result {
        val user = get()
        val uid = user.uid
        val (curPage, size) = dataGetPageDrugsAlertInfo
        return success(drugService.getPageDrugAlertInfo(uid, curPage, size))
    }

    @PostMapping("/getPageDrugsAlertInfoByDrugName")
    fun getPageDrugsAlertInfoByDrugName(@RequestBody dataGetPageDrugAlertInfoByDrugName: DataGetPageDrugsAlertInfoByDrugName): Result {
        val user = get()
        val uid = user.uid
        val (curPage, size, drugName) = dataGetPageDrugAlertInfoByDrugName
        if (drugName.isNotEmpty()) {
            println("notEmpty")
            return success(drugService.getPageDrugAlertInfoByDrugName(uid, curPage, size, drugName))
        } else {
            println("Empty")
            return success(drugService.getPageDrugAlertInfo(uid, curPage, size))
        }
    }

    @GetMapping("/getPageDrugAlertInfoTotal")
    fun pageDrugAlertInfoTotal(): Result {
        val user = get()
        val uid = user.uid
        return success(drugService.getPageDrugAlertInfoTotal(uid))
    }


    @PostMapping("/getPageDrugAlertInfoTotalByDrugName")
    fun getPageDrugAlertInfoTotalByDrugName(@RequestBody drugName: String): Result {
        val user = get()
        val uid = user.uid
        return if (drugName.isNotEmpty()) {
            success(drugService.getPageDrugAlertInfoTotalByDrugName(uid, drugName))
        } else {
            success(drugService.getPageDrugAlertInfoTotal(uid))
        }
    }

    @PostMapping("/insertDrug")
    fun insertDrug(@RequestBody dataInsertDrug: DataInsertDrug): Result {
        val (drugName, frequency, unit, dosage, isActive) = dataInsertDrug
        val drugId = snowFlakeDrug.nextId()
        val user = get()
        val uid = user.uid
        drugService.insertDrug(drugId, uid, drugName, frequency, unit, dosage, isActive)
        return success()
    }

    @PostMapping("/insertDrugAndAlert")
    fun insertDrugAndAlert(@RequestBody dataInsertDrugAndAlert: DataInsertDrugAndAlert): Result {
        val (drugName, frequency, unit, dosage, isActive, alertTimeRow) = dataInsertDrugAndAlert
        val drugId = snowFlakeDrug.nextId()
        val user = get()
        val uid = user.uid
        logger.debug("uid{}", uid)
        drugService.insertDrug(drugId, uid, drugName, frequency, unit, dosage, isActive)
        val alertId = snowFlakeDrugAlert.nextId()
        val alertTime = alertTimeRow.let { LocalTime.parse(it) }
        val eatTime = LocalDate.parse("2000-01-01")
        drugAlertService.insertDrugAlert(alertId, uid, drugId, alertTime, eatTime)
        return success()
    }


    @PostMapping("/updateDrugIsActiveById")
    fun updateDrugIsActiveById(@RequestBody dataUpdateDrugIsActiveById: DataUpdateDrugIsActiveById): Result {
        val (drugId, isActive) = dataUpdateDrugIsActiveById
        val user = get()
        val uid = user.uid
        drugService.updateDrugIsActiveById(drugId, uid, isActive)
        return success()
    }

    @PostMapping("/deleteDrugById")
    fun deleteDrugById(@RequestBody drugId: Long): Result {
        val user = get()
        val uid = user.uid
        drugService.deleteDrugById(uid, drugId)
        return success()
    }

    @PostMapping("/deleteDrugAndDrugAlertById")
    fun deleteDrugAndDrugAlertById(@RequestBody dataUpdateDrugAlertIsActiveById: DataUpdateDrugAlertIsActiveById): Result {
        val (alertId, drugId) = dataUpdateDrugAlertIsActiveById
        val user = get()
        val uid = user.uid
        drugService.deleteDrugById(uid, drugId)
        drugAlertService.deleteDrugAlertById(alertId, uid, drugId)
        return success()
    }
}
