package cn.hirrao.javaee.service.impl;

import cn.hirrao.javaee.mapper.DrugAlertMapper;
import cn.hirrao.javaee.service.DrugAlertService;
import cn.hirrao.javaee.service.DrugService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.sql.Time;

@Service
public class DrugAlertServiceImpl implements DrugAlertService {
    private final DrugAlertMapper drugAlertMapper;

    @Autowired
    public DrugAlertServiceImpl(DrugAlertMapper drugAlertMapper){
        this.drugAlertMapper=drugAlertMapper;
    }

    @Override
    public void insertDrugAlert(long alertId, long uid, long drugId, Time alertTime, Date eatTime){
        drugAlertMapper.insertDrugAlert(alertId, uid, drugId, alertTime, eatTime);
    }

    @Override
    public void updateDrugAlertIsActiveById(long alertId,int isActive){
        drugAlertMapper.updateDrugAlertIsActiveById(alertId,isActive);
    }

    @Override
    public void updateDrugAlertEatTimeById(long alertId,Date eatTime){
        drugAlertMapper.updateDrugAlertEatTimeById(alertId, eatTime);
    }

    @Override
    public void deleteDrugAlertById(long alertId,long uid,long drugId){
        drugAlertMapper.deleteDrugAlertById(alertId,uid,drugId);
    }
}
