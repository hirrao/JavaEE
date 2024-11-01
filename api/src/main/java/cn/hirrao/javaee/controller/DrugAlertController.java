package cn.hirrao.javaee.controller;

import cn.hirrao.javaee.entity.Result;
import cn.hirrao.javaee.service.DrugAlertService;
import cn.hirrao.javaee.utils.SnowFlake;
import cn.hirrao.javaee.utils.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.LocalTime;
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
        var alertId = snowFlake.nextId();
        var user = ThreadLocalUtil.get();
        var uid = user.getUid();
        var drugId = Long.parseLong(map.get("drugId"));
        var alertTime = LocalTime.parse(map.get("alertTime"));
        var eatTime = LocalDate.parse("2000-01-01");
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
        var eatTime = LocalDate.parse(map.get("eatTime"));
        drugAlertService.updateDrugAlertEatTimeById(alertId, eatTime);
        return Result.success();
    }

    @PostMapping("/deleteDrugAlertById")
    public Result deleteDrugAlertById(@RequestBody Map<String, String> map) {
        var alertId = Long.parseLong(map.get("alertId"));
        var user = ThreadLocalUtil.get();
        var uid = user.getUid();
        var drugId = Long.parseLong(map.get("drugId"));
        drugAlertService.deleteDrugAlertById(alertId, uid, drugId);
        return Result.success();
    }
}
