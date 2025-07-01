package com.hirrao.javaee.controller;

import com.hirrao.javaee.entity.Result;
import com.hirrao.javaee.entity.User;
import com.hirrao.javaee.service.DrugAlertService;
import com.hirrao.javaee.utils.SnowFlake;
import com.hirrao.javaee.utils.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Date;
import java.sql.Time;
import java.util.Map;

@RestController
@RequestMapping("/drugAlert")
public class DrugAlertController {
    private final SnowFlake snowFlake = new SnowFlake(1, 5);

    private final DrugAlertService drugAlertService;

    @Autowired
    DrugAlertController(DrugAlertService drugAlertService) {
        this.drugAlertService = drugAlertService;
    }

    @PostMapping("/insertDrug")
    public Result insertDrug(@RequestBody Map<String, String> map) {
        long alertId = snowFlake.nextId();
        User user = ThreadLocalUtil.get();
        var uid = user.getUid();
        var drugId = Long.parseLong(map.get("drugId"));
        var alertTime = Time.valueOf(map.get("alertTime"));
        Date eatTime = Date.valueOf("2000-01-01");
        drugAlertService.insertDrugAlert(alertId, uid, drugId, alertTime, eatTime);
        return Result.success();
    }

    @PostMapping("/updateDrugAlertIsActiveById")
    public Result updateDrugAlertIsActiveById(@RequestBody Map<String, String> map) {
        var alertId = Long.parseLong(map.get("alertId"));
        var isActive = Integer.parseInt(map.get("isActive"));
        drugAlertService.updateDrugAlertIsActiveById(alertId, isActive);
        return Result.success();
    }

    @PostMapping("/updateDrugAlertEatTimeById")
    public Result updateDrugAlertEatTimeById(@RequestBody Map<String, String> map) {
        var alertId = Long.parseLong(map.get("alertId"));
        Date eatTime = Date.valueOf(map.get("eatTime"));
        drugAlertService.updateDrugAlertEatTimeById(alertId, eatTime);
        return Result.success();
    }

    @PostMapping("/deleteDrugAlertById")
    public Result deleteDrugAlertById(@RequestBody Map<String, String> map) {
        var alertId = Long.parseLong(map.get("alertId"));
        User user = ThreadLocalUtil.get();
        var uid = user.getUid();
        var drugId = Long.parseLong(map.get("drugId"));
        drugAlertService.deleteDrugAlertById(alertId, uid, drugId);
        return Result.success();
    }
}
