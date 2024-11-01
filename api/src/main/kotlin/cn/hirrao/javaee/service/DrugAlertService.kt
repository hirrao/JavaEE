package cn.hirrao.javaee.service;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;

@Service
public interface DrugAlertService {
    void insertDrugAlert(long alertId, long uid, long drugId, LocalTime alertTime, LocalDate eatTime);

    void updateDrugAlertIsActiveById(long alertId,int isActive);

    void updateDrugAlertEatTimeById(long alertId, LocalDate eatTime);

    void deleteDrugAlertById(long alertId,long uid,long drugId);
}
