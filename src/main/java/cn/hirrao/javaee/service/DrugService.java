package cn.hirrao.javaee.service;

import cn.hirrao.javaee.entity.DrugManageResult;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface DrugService {
    void insertDrug(long drugId,long uid,String drugName,String frequency,String unit,Float dosage,int isActive);
//    List<DrugManageResult> getAllDrugInfo(long uid);

    List<DrugManageResult> getPageDrugInfo(long uid,int curPage,int size);

    List<DrugManageResult> getPageDrugInfoByDrugName(long uid,int curPage,int size,String drugName);

    int getPageDrugInfoTotal(long uid);

    int getPageDrugInfoTotalByDrugName(long uid,String drugName);

    List<DrugManageResult> getPageDrugAlertInfo(long uid,int curPage,int size);

    List<DrugManageResult> getPageDrugAlertInfoByDrugName(long uid,int curPage,int size,String drugName);

    int getPageDrugAlertInfoTotal(long uid);

    int getPageDrugAlertInfoTotalByDrugName(long uid,String drugName);

    void deleteDrugById(long uid,long drugId);
}
