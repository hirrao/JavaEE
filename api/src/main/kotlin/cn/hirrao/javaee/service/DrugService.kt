package cn.hirrao.javaee.service

import cn.hirrao.javaee.entity.DrugManageResult
import org.springframework.stereotype.Service

@Service
interface DrugService {
    fun insertDrug(
        drugId: Long,
        uid: Long,
        drugName: String?,
        frequency: String?,
        unit: String?,
        dosage: Float?,
        isActive: Int
    )

    //    List<DrugManageResult> getAllDrugInfo(long uid);
    fun getPageDrugInfo(uid: Long, curPage: Int, size: Int): List<DrugManageResult?>?

    fun getPageDrugInfoByDrugName(uid: Long, curPage: Int, size: Int, drugName: String?): List<DrugManageResult?>?

    fun getPageDrugInfoTotal(uid: Long): Int

    fun getPageDrugInfoTotalByDrugName(uid: Long, drugName: String?): Int

    fun getPageDrugAlertInfo(uid: Long, curPage: Int, size: Int): List<DrugManageResult?>?

    fun getPageDrugAlertInfoByDrugName(uid: Long, curPage: Int, size: Int, drugName: String?): List<DrugManageResult?>?

    fun getPageDrugAlertInfoTotal(uid: Long): Int

    fun getPageDrugAlertInfoTotalByDrugName(uid: Long, drugName: String?): Int

    fun updateDrugIsActiveById(drugId: Long, uid: Long, isActive: Int)

    fun deleteDrugById(uid: Long, drugId: Long)
}
