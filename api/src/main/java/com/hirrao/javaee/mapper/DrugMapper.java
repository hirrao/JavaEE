package com.hirrao.javaee.mapper;

import com.hirrao.javaee.entity.Drug;
import com.hirrao.javaee.entity.DrugManageResult;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.*;

import java.util.List;
@Mapper
public interface DrugMapper extends BaseMapper<Drug> {
    @Insert("INSERT INTO drug VALUES (#{drugId},#{uid}, #{drugName},#{frequency},#{unit}, #{dosage},#{isActive})")
    void insertDrug(long drugId,long uid,String drugName,String frequency,String unit,Float dosage,int isActive);

//    @Select("SELECT d.drugId, d.drugName, d.frequency, d.unit, d.dosage, d.isActive, da.alertId, da.alertTime, da.eatTime FROM drug d JOIN drugAlert da ON d.drugId=da.drugId AND d.uid=da.uid WHERE d.uid=#{uid} LIMIT 1,2")
//    List<DrugManageResult> getAllDrugInfo(long uid);
    @Select("SELECT d.drugId, d.drugName, d.frequency, d.unit, d.dosage, d.isActive, da.alertId, da.alertTime, da.eatTime FROM drug d JOIN drugAlert da ON d.drugId=da.drugId AND d.uid=da.uid WHERE d.uid=#{uid} AND d.isActive=1 ORDER BY da.alertTime LIMIT #{curPage},#{size}")
    List<DrugManageResult> getPageDrugInfo(long uid,int curPage,int size);

    @Select("SELECT d.drugId, d.drugName, d.frequency, d.unit, d.dosage, d.isActive, da.alertId, da.alertTime, da.eatTime FROM drug d JOIN drugAlert da ON d.drugId=da.drugId AND d.uid=da.uid WHERE d.uid=#{uid} AND d.isActive=1 AND d.drugName LIKE CONCAT('%',#{drugName},'%') ORDER BY da.alertTime LIMIT #{curPage},#{size}")
    List<DrugManageResult> getPageDrugInfoByDrugName(long uid,int curPage,int size,String drugName);

    @Select("SELECT COUNT(*) FROM drug d JOIN drugAlert da ON d.drugId=da.drugId AND d.uid=da.uid WHERE d.uid=#{uid} AND d.isActive=1")
    int getPageDrugInfoTotal(long uid);

    @Select("SELECT COUNT(*) FROM drug d JOIN drugAlert da ON d.drugId=da.drugId AND d.uid=da.uid WHERE d.uid=#{uid} AND d.isActive=1 AND d.drugName LIKE CONCAT('%',#{drugName},'%')")
    int getPageDrugInfoTotalByDrugName(long uid,String drugName);

    @Select("SELECT d.drugId, d.drugName, d.frequency, d.unit, d.dosage, d.isActive, da.alertId, da.alertTime, da.eatTime FROM drug d JOIN drugAlert da ON d.drugId=da.drugId AND d.uid=da.uid WHERE d.uid=#{uid} ORDER BY da.alertTime LIMIT #{curPage},#{size}")
    List<DrugManageResult> getPageDrugAlertInfo(long uid,int curPage,int size);

    @Select("SELECT d.drugId, d.drugName, d.frequency, d.unit, d.dosage, d.isActive, da.alertId, da.alertTime, da.eatTime FROM drug d JOIN drugAlert da ON d.drugId=da.drugId AND d.uid=da.uid WHERE d.uid=#{uid} AND d.drugName LIKE CONCAT('%',#{drugName},'%') ORDER BY da.alertTime LIMIT #{curPage},#{size}")
    List<DrugManageResult> getPageDrugAlertInfoByDrugName(long uid,int curPage,int size,String drugName);

    @Select("SELECT COUNT(*) FROM drug d JOIN drugAlert da ON d.drugId=da.drugId AND d.uid=da.uid WHERE d.uid=#{uid}")
    int getPageDrugAlertInfoTotal(long uid);

    @Select("SELECT COUNT(*) FROM drug d JOIN drugAlert da ON d.drugId=da.drugId AND d.uid=da.uid WHERE d.uid=#{uid} AND d.drugName LIKE CONCAT('%',#{drugName},'%')")
    int getPageDrugAlertInfoTotalByDrugName(long uid,String drugName);

    @Update("UPDATE drug SET isActive=#{isActive} WHERE drugId=#{drugId} AND uid=#{uid}")
    void updateDrugIsActiveById(long drugId,long uid,int isActive);

    @Delete("DELETE FROM drug WHERE uid=#{uid} AND drugId=#{drugId}")
    void deleteDrugById(long uid,long drugId);
}
