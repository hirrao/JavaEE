package com.hirrao.javaee.controller;

import com.hirrao.javaee.entity.Result;
import com.hirrao.javaee.entity.User;
import com.hirrao.javaee.service.DrugAlertService;
import com.hirrao.javaee.service.DrugService;
import com.hirrao.javaee.utils.SnowFlake;
import com.hirrao.javaee.utils.ThreadLocalUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.sql.Time;
import java.util.Map;

@RestController
@RequestMapping("/drug")
public class DrugController {
    private final SnowFlake snowFlakeDrug = new SnowFlake(1, 4);

    private final SnowFlake snowFlakeDrugAlert = new SnowFlake(1, 5);

    private final DrugService drugService;

    private final DrugAlertService drugAlertService;
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    DrugController(DrugService drugService, DrugAlertService drugAlertService) {
        this.drugService = drugService;
        this.drugAlertService = drugAlertService;
    }

    @PostMapping("/getPageDrugsInfo")
    public Result getPageDrugsInfo(@RequestBody Map<String, String> map) {
        User user = ThreadLocalUtil.get();
        var uid = user.getUid();
        var curPage = Integer.parseInt(map.get("curPage"));
        var size = Integer.parseInt(map.get("size"));
        return Result.success(drugService.getPageDrugInfo(uid, curPage, size));
    }

    @PostMapping("/getPageDrugsInfoByDrugName")
    public Result getPageDrugsInfoByDrugName(@RequestBody Map<String, String> map) {
        User user = ThreadLocalUtil.get();
        var uid = user.getUid();
        var curPage = Integer.parseInt(map.get("curPage"));
        var size = Integer.parseInt(map.get("size"));
        var drugName = map.get("drugName");
        if (!drugName.isEmpty()) {
            System.out.println("notEmpty");
            return Result.success(drugService.getPageDrugInfoByDrugName(uid, curPage, size, drugName));
        } else {
            System.out.println("Empty");
            return Result.success(drugService.getPageDrugInfo(uid, curPage, size));
        }
    }

    @GetMapping("/getPageDrugInfoTotal")
    public Result getPageDrugInfoTotal() {
        User user = ThreadLocalUtil.get();
        var uid = user.getUid();
        return Result.success(drugService.getPageDrugInfoTotal(uid));
    }

    @PostMapping("/getPageDrugInfoTotalByDrugName")
    public Result getPageDrugInfoTotalByDrugName(@RequestBody Map<String, String> map) {
        User user = ThreadLocalUtil.get();
        var uid = user.getUid();
        var drugName = map.get("drugName");
        if (!drugName.isEmpty()) {
            return Result.success(drugService.getPageDrugInfoTotalByDrugName(uid, drugName));
        } else {
            return Result.success(drugService.getPageDrugInfoTotal(uid));
        }
    }

    @PostMapping("/getPageDrugsAlertInfo")
    public Result getPageDrugsAlertInfo(@RequestBody Map<String, String> map) {
        User user = ThreadLocalUtil.get();
        var uid = user.getUid();
        var curPage = Integer.parseInt(map.get("curPage"));
        var size = Integer.parseInt(map.get("size"));
        return Result.success(drugService.getPageDrugAlertInfo(uid, curPage, size));
    }

    @PostMapping("/getPageDrugsAlertInfoByDrugName")
    public Result getPageDrugsAlertInfoByDrugName(@RequestBody Map<String, String> map) {
        User user = ThreadLocalUtil.get();
        var uid = user.getUid();
        var curPage = Integer.parseInt(map.get("curPage"));
        var size = Integer.parseInt(map.get("size"));
        var drugName = map.get("drugName");
        if (!drugName.isEmpty()) {
            System.out.println("notEmpty");
            return Result.success(drugService.getPageDrugAlertInfoByDrugName(uid, curPage, size, drugName));
        } else {
            System.out.println("Empty");
            return Result.success(drugService.getPageDrugAlertInfo(uid, curPage, size));
        }
    }

    @GetMapping("/getPageDrugAlertInfoTotal")
    public Result getPageDrugAlertInfoTotal() {
        User user = ThreadLocalUtil.get();
        var uid = user.getUid();
        return Result.success(drugService.getPageDrugAlertInfoTotal(uid));
    }

    @PostMapping("/getPageDrugAlertInfoTotalByDrugName")
    public Result getPageDrugAlertInfoTotalByDrugName(@RequestBody Map<String, String> map) {
        User user = ThreadLocalUtil.get();
        var uid = user.getUid();
        var drugName = map.get("drugName");
        if (!drugName.isEmpty()) {
            return Result.success(drugService.getPageDrugAlertInfoTotalByDrugName(uid, drugName));
        } else {
            return Result.success(drugService.getPageDrugAlertInfoTotal(uid));
        }
    }

    @PostMapping("/insertDrug")
    public Result insertDrug(@RequestBody Map<String, String> map) {
        long drugId = snowFlakeDrug.nextId();
        User user = ThreadLocalUtil.get();
        var uid = user.getUid();
        var drugName = map.get("drugName");
        var frequency = map.get("frequency");
        var unit = map.get("unit");
        var dosage = Float.parseFloat(map.get("dosage"));
        var isActive = Integer.parseInt(map.get("isActive"));
        drugService.insertDrug(drugId, uid, drugName, frequency, unit, dosage, isActive);
        return Result.success();
    }

    @PostMapping("/insertDrugAndAlert")
    public Result insertDrugAndAlert(@RequestBody Map<String, String> map) {
        long drugId = snowFlakeDrug.nextId();
        User user = ThreadLocalUtil.get();
        var uid = user.getUid();
        var drugName = map.get("drugName");
        var frequency = map.get("frequency");
        var unit = map.get("unit");
        var dosage = Float.parseFloat(map.get("dosage"));
        var isActive = Integer.parseInt(map.get("isActive"));
        logger.debug("uid{}", uid);
        drugService.insertDrug(drugId, uid, drugName, frequency, unit, dosage, isActive);
        long alertId = snowFlakeDrugAlert.nextId();
        var alertTime = Time.valueOf(map.get("alertTime"));
        Date eatTime = Date.valueOf("2000-01-01");
        drugAlertService.insertDrugAlert(alertId, uid, drugId, alertTime, eatTime);
        return Result.success();
    }

    @PostMapping("/updateDrugIsActiveById")
    public Result updateDrugIsActiveById(@RequestBody Map<String, String> map) {
        var drugId = Long.parseLong(map.get("drugId"));
        User user = ThreadLocalUtil.get();
        var uid = user.getUid();
        var isActive = Integer.parseInt(map.get("isActive"));
        drugService.updateDrugIsActiveById(drugId, uid, isActive);
        return Result.success();
    }

    @PostMapping("/deleteDrugById")
    public Result deleteDrugById(@RequestBody Map<String, String> map) {
        User user = ThreadLocalUtil.get();
        var uid = user.getUid();
        var drugId = Long.parseLong(map.get("drugId"));
        drugService.deleteDrugById(uid, drugId);
        return Result.success();
    }

    @PostMapping("/deleteDrugAndDrugAlertById")
    public Result deleteDrugAndDrugAlertById(@RequestBody Map<String, String> map) {
        var alertId = Long.parseLong(map.get("alertId"));
        User user = ThreadLocalUtil.get();
        var uid = user.getUid();
        var drugId = Long.parseLong(map.get("drugId"));
        drugService.deleteDrugById(uid, drugId);
        drugAlertService.deleteDrugAlertById(alertId, uid, drugId);
        return Result.success();
    }
}
