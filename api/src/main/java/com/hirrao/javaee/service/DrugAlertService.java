package com.hirrao.javaee.service;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.sql.Time;

@Service
public interface DrugAlertService {
    void insertDrugAlert(long alertId, long uid, long drugId, Time alertTime, Date eatTime);

    void updateDrugAlertIsActiveById(long alertId,int isActive);

    void updateDrugAlertEatTimeById(long alertId,Date eatTime);

    void deleteDrugAlertById(long alertId,long uid,long drugId);
}
