package cn.hirrao.javaee.service.impl;

import cn.hirrao.javaee.entity.DrugManageResult;
import cn.hirrao.javaee.mapper.DrugMapper;
import cn.hirrao.javaee.service.DrugService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DrugServiceImpl implements DrugService {
    private final DrugMapper drugMapper;

    @Autowired
    public DrugServiceImpl(DrugMapper drugMapper){this.drugMapper=drugMapper;}

    @Override
    public void insertDrug(long drugId,long uid,String drugName,String frequency,String unit,Float dosage,int isActive){
        drugMapper.insertDrug(drugId,uid, drugName, frequency, unit, dosage, isActive);
    }
//    @Override
//    public List<DrugManageResult> getAllDrugInfo(long uid){
//        return drugMapper.getAllDrugInfo(uid);
//    }

    @Override
    public List<DrugManageResult> getPageDrugInfo(long uid,int curPage,int size){
        return drugMapper.getPageDrugInfo(uid,(curPage-1)*size,size);
    }

    @Override
    public List<DrugManageResult> getPageDrugInfoByDrugName(long uid,int curPage,int size,String drugName){
        return drugMapper.getPageDrugInfoByDrugName(uid,(curPage-1)*size,size,drugName);
    }

    @Override
    public int getPageDrugInfoTotal(long uid){
        return drugMapper.getPageDrugInfoTotal(uid);
    }

    @Override
    public int getPageDrugInfoTotalByDrugName(long uid,String drugName){
        return drugMapper.getPageDrugInfoTotalByDrugName(uid,drugName);
    }

    @Override
    public List<DrugManageResult> getPageDrugAlertInfo(long uid,int curPage,int size){
        return drugMapper.getPageDrugAlertInfo(uid,(curPage-1)*size,size);
    }

    @Override
    public List<DrugManageResult> getPageDrugAlertInfoByDrugName(long uid,int curPage,int size,String drugName){
        return drugMapper.getPageDrugAlertInfoByDrugName(uid,(curPage-1)*size,size,drugName);
    }

    @Override
    public int getPageDrugAlertInfoTotal(long uid){
        return drugMapper.getPageDrugAlertInfoTotal(uid);
    }

    @Override
    public int getPageDrugAlertInfoTotalByDrugName(long uid,String drugName){
        return drugMapper.getPageDrugAlertInfoTotalByDrugName(uid,drugName);
    }

    @Override
    public void updateDrugIsActiveById(long drugId,long uid,int isActive){
        drugMapper.updateDrugIsActiveById(drugId, uid, isActive);
    }

    @Override
    public void deleteDrugById(long uid,long drugId){
        drugMapper.deleteDrugById(uid, drugId);
    }
}
