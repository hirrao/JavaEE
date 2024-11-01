package cn.hirrao.javaee.service.impl;

import cn.hirrao.javaee.mapper.DrugAlertMapper;
import cn.hirrao.javaee.service.DrugAlertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;

@Service
public class DrugAlertServiceImpl implements DrugAlertService {
    private final DrugAlertMapper drugAlertMapper;

    @Autowired
    public DrugAlertServiceImpl(DrugAlertMapper drugAlertMapper){
        this.drugAlertMapper=drugAlertMapper;
    }

    @Override
    public void insertDrugAlert(long alertId, long uid, long drugId, LocalTime alertTime, LocalDate eatTime) {
        drugAlertMapper.insertDrugAlert(alertId, uid, drugId, alertTime, eatTime);
    }

    @Override
    public void updateDrugAlertIsActiveById(long alertId,int isActive){
        drugAlertMapper.updateDrugAlertIsActiveById(alertId,isActive);
    }

    @Override
    public void updateDrugAlertEatTimeById(long alertId, LocalDate eatTime) {
        drugAlertMapper.updateDrugAlertEatTimeById(alertId, eatTime);
    }

    @Override
    public void deleteDrugAlertById(long alertId,long uid,long drugId){
        drugAlertMapper.deleteDrugAlertById(alertId,uid,drugId);
    }
}
