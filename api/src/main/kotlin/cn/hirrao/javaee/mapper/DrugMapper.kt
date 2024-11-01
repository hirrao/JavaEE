package cn.hirrao.javaee.mapper

import cn.hirrao.javaee.entity.Drug
import cn.hirrao.javaee.entity.DrugManageResult
import com.baomidou.mybatisplus.core.mapper.BaseMapper
import org.apache.ibatis.annotations.*

@Mapper
interface DrugMapper : BaseMapper<Drug?> {
    @Insert("INSERT INTO drug VALUES (#{drugId},#{uid}, #{drugName},#{frequency},#{unit}, #{dosage},#{isActive})")
    fun insertDrug(
        drugId: Long,
        uid: Long,
        drugName: String?,
        frequency: String?,
        unit: String?,
        dosage: Float?,
        isActive: Int
    )

    //    @Select("SELECT d.drugId, d.drugName, d.frequency, d.unit, d.dosage, d.isActive, da.alertId, da.alertTime, da.eatTime FROM drug d JOIN drugAlert da ON d.drugId=da.drugId AND d.uid=da.uid WHERE d.uid=#{uid} LIMIT 1,2")
    //    List<DrugManageResult> getAllDrugInfo(long uid);
    @Select("SELECT COUNT(*) FROM drug d JOIN drugAlert da ON d.drugId=da.drugId AND d.uid=da.uid WHERE d.uid=#{uid} AND d.isActive=1")
    fun getPageDrugInfoTotal(uid: Long): Int


    @Select("SELECT COUNT(*) FROM drug d JOIN drugAlert da ON d.drugId=da.drugId AND d.uid=da.uid WHERE d.uid=#{uid}")
    fun getPageDrugAlertInfoTotal(uid: Long): Int

    @Update("UPDATE drug SET isActive=#{isActive} WHERE drugId=#{drugId} AND uid=#{uid}")
    fun updateDrugIsActiveById(drugId: Long, uid: Long, isActive: Int)

    @Delete("DELETE FROM drug WHERE uid=#{uid} AND drugId=#{drugId}")
    fun deleteDrugById(uid: Long, drugId: Long)


    @Select("SELECT d.drugId, d.drugName, d.frequency, d.unit, d.dosage, d.isActive, da.alertId, da.alertTime, da.eatTime FROM drug d JOIN drugAlert da ON d.drugId=da.drugId AND d.uid=da.uid WHERE d.uid=#{uid} AND d.isActive=1 ORDER BY da.alertTime LIMIT #{size} OFFSET #{curPage}")
    fun getPageDrugInfo(uid: Long, curPage: Int, size: Int): List<DrugManageResult?>?

    @Select("SELECT d.drugId, d.drugName, d.frequency, d.unit, d.dosage, d.isActive, da.alertId, da.alertTime, da.eatTime FROM drug d JOIN drugAlert da ON d.drugId=da.drugId AND d.uid=da.uid WHERE d.uid=#{uid} AND d.isActive=1 AND d.drugName LIKE '%' || #{drugName} || '%' ORDER BY da.alertTime LIMIT #{size} OFFSET #{curPage}")
    fun getPageDrugInfoByDrugName(uid: Long, curPage: Int, size: Int, drugName: String?): List<DrugManageResult?>?

    @Select("SELECT COUNT(*) FROM drug d JOIN drugAlert da ON d.drugId=da.drugId AND d.uid=da.uid WHERE d.uid=#{uid} AND d.isActive=1 AND d.drugName LIKE '%' || #{drugName} || '%'")
    fun getPageDrugInfoTotalByDrugName(uid: Long, drugName: String?): Int

    @Select("SELECT d.drugId, d.drugName, d.frequency, d.unit, d.dosage, d.isActive, da.alertId, da.alertTime, da.eatTime FROM drug d JOIN drugAlert da ON d.drugId=da.drugId AND d.uid=da.uid WHERE d.uid=#{uid} ORDER BY da.alertTime LIMIT #{size} OFFSET #{curPage}")
    fun getPageDrugAlertInfo(uid: Long, curPage: Int, size: Int): List<DrugManageResult?>?

    @Select("SELECT d.drugId, d.drugName, d.frequency, d.unit, d.dosage, d.isActive, da.alertId, da.alertTime, da.eatTime FROM drug d JOIN drugAlert da ON d.drugId=da.drugId AND d.uid=da.uid WHERE d.uid=#{uid} AND d.drugName LIKE '%' || #{drugName} || '%' ORDER BY da.alertTime LIMIT #{size} OFFSET #{curPage}")
    fun getPageDrugAlertInfoByDrugName(uid: Long, curPage: Int, size: Int, drugName: String?): List<DrugManageResult?>?

    @Select("SELECT COUNT(*) FROM drug d JOIN drugAlert da ON d.drugId=da.drugId AND d.uid=da.uid WHERE d.uid=#{uid} AND d.drugName LIKE '%' || #{drugName} || '%'")
    fun getPageDrugAlertInfoTotalByDrugName(uid: Long, drugName: String?): Int
}
