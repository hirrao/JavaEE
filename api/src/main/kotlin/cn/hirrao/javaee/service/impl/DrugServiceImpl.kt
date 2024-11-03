package cn.hirrao.javaee.service.impl

import cn.hirrao.javaee.entity.DrugManageResult
import cn.hirrao.javaee.mapper.DrugMapper
import cn.hirrao.javaee.service.DrugService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class DrugServiceImpl @Autowired constructor(private val drugMapper: DrugMapper) : DrugService {
    override fun insertDrug(
        drugId: Long,
        uid: Long,
        drugName: String?,
        frequency: String?,
        unit: String?,
        dosage: Float?,
        isActive: Int
    ) {
        drugMapper.insertDrug(drugId, uid, drugName, frequency, unit, dosage, isActive)
    }

    //    @Override
    //    public List<DrugManageResult> getAllDrugInfo(long uid){
    //        return drugMapper.getAllDrugInfo(uid);
    //    }
    override fun getPageDrugInfo(uid: Long, curPage: Int, size: Int): List<DrugManageResult?>? {
        return drugMapper.getPageDrugInfo(uid, (curPage - 1) * size, size)
    }

    override fun getPageDrugInfoByDrugName(
        uid: Long,
        curPage: Int,
        size: Int,
        drugName: String?
    ): List<DrugManageResult?>? {
        return drugMapper.getPageDrugInfoByDrugName(uid, (curPage - 1) * size, size, drugName)
    }

    override fun getPageDrugInfoTotal(uid: Long): Int {
        return drugMapper.getPageDrugInfoTotal(uid)
    }

    override fun getPageDrugInfoTotalByDrugName(uid: Long, drugName: String?): Int {
        return drugMapper.getPageDrugInfoTotalByDrugName(uid, drugName)
    }

    override fun getPageDrugAlertInfo(uid: Long, curPage: Int, size: Int): List<DrugManageResult?>? {
        return drugMapper.getPageDrugAlertInfo(uid, (curPage - 1) * size, size)
    }

    override fun getPageDrugAlertInfoByDrugName(
        uid: Long,
        curPage: Int,
        size: Int,
        drugName: String?
    ): List<DrugManageResult?>? {
        return drugMapper.getPageDrugAlertInfoByDrugName(uid, (curPage - 1) * size, size, drugName)
    }

    override fun getPageDrugAlertInfoTotal(uid: Long): Int {
        return drugMapper.getPageDrugAlertInfoTotal(uid)
    }

    override fun getPageDrugAlertInfoTotalByDrugName(uid: Long, drugName: String?): Int {
        return drugMapper.getPageDrugAlertInfoTotalByDrugName(uid, drugName)
    }

    override fun updateDrugIsActiveById(drugId: Long, uid: Long, isActive: Int) {
        drugMapper.updateDrugIsActiveById(drugId, uid, isActive)
    }

    override fun deleteDrugById(uid: Long, drugId: Long) {
        drugMapper.deleteDrugById(uid, drugId)
    }
}
