package com.hirrao.javaee.service;

import com.hirrao.javaee.entity.DrugManageResult;
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

    void updateDrugIsActiveById(long drugId,long uid,int isActive);

    void deleteDrugById(long uid,long drugId);
}
