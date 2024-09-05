package cn.hirrao.javaee.controller;

import cn.hirrao.javaee.entity.DrugManageResult;
import cn.hirrao.javaee.entity.Result;
import cn.hirrao.javaee.service.DrugAlertService;
import cn.hirrao.javaee.service.DrugService;
import cn.hirrao.javaee.utils.SnowFlake;
import com.baomidou.mybatisplus.annotation.TableField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.sql.Time;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/drug")
public class DrugController {
    private final SnowFlake snowFlakeDrug = new SnowFlake(1, 4);

    private final SnowFlake snowFlakeDrugAlert = new SnowFlake(1, 5);

    private final DrugService drugService;

    private final DrugAlertService drugAlertService;

    @Autowired
    DrugController(DrugService drugService,DrugAlertService drugAlertService){
        this.drugService=drugService;
        this.drugAlertService=drugAlertService;
    }

    @PostMapping("/getPageDrugsInfo")
    public Result getPageDrugsInfo(@RequestBody Map<String, String> map){
        var uid=Long.parseLong(map.get("uid"));
        var curPage=Integer.parseInt(map.get("curPage"));
        var size=Integer.parseInt(map.get("size"));
        return Result.success(drugService.getPageDrugInfo(uid,curPage,size));
    }

    @PostMapping("/getPageDrugsInfoByDrugName")
    public Result getPageDrugsInfoByDrugName(@RequestBody Map<String, String> map){
        var uid=Long.parseLong(map.get("uid"));
        var curPage=Integer.parseInt(map.get("curPage"));
        var size=Integer.parseInt(map.get("size"));
        var drugName=map.get("drugName");
        if(!drugName.isEmpty()){
            System.out.println("notEmpty");
            return Result.success(drugService.getPageDrugInfoByDrugName(uid,curPage,size,drugName));
        }else{
            System.out.println("Empty");
            return Result.success(drugService.getPageDrugInfo(uid,curPage,size));
        }
    }

    @PostMapping("/getPageDrugInfoTotal")
    public Result getPageDrugInfoTotal(@RequestBody Map<String, String> map){
        var uid=Long.parseLong(map.get("uid"));
        return Result.success(drugService.getPageDrugInfoTotal(uid));
    }

    @PostMapping("/getPageDrugInfoTotalByDrugName")
    public Result getPageDrugInfoTotalByDrugName(@RequestBody Map<String, String> map){
        var uid=Long.parseLong(map.get("uid"));
        var drugName=map.get("drugName");
        if(!drugName.isEmpty()){
            return Result.success(drugService.getPageDrugInfoTotalByDrugName(uid,drugName));
        }else{
            return Result.success(drugService.getPageDrugInfoTotal(uid));
        }
    }

    @PostMapping("/getPageDrugsAlertInfo")
    public Result getPageDrugsAlertInfo(@RequestBody Map<String, String> map){
        var uid=Long.parseLong(map.get("uid"));
        var curPage=Integer.parseInt(map.get("curPage"));
        var size=Integer.parseInt(map.get("size"));
        return Result.success(drugService.getPageDrugAlertInfo(uid,curPage,size));
    }

    @PostMapping("/getPageDrugsAlertInfoByDrugName")
    public Result getPageDrugsAlertInfoByDrugName(@RequestBody Map<String, String> map){
        var uid=Long.parseLong(map.get("uid"));
        var curPage=Integer.parseInt(map.get("curPage"));
        var size=Integer.parseInt(map.get("size"));
        var drugName=map.get("drugName");
        if(!drugName.isEmpty()){
            System.out.println("notEmpty");
            return Result.success(drugService.getPageDrugAlertInfoByDrugName(uid,curPage,size,drugName));
        }else{
            System.out.println("Empty");
            return Result.success(drugService.getPageDrugAlertInfo(uid,curPage,size));
        }
    }

    @PostMapping("/getPageDrugAlertInfoTotal")
    public Result getPageDrugAlertInfoTotal(@RequestBody Map<String, String> map){
        var uid=Long.parseLong(map.get("uid"));
        return Result.success(drugService.getPageDrugAlertInfoTotal(uid));
    }

    @PostMapping("/getPageDrugAlertInfoTotalByDrugName")
    public Result getPageDrugAlertInfoTotalByDrugName(@RequestBody Map<String, String> map){
        var uid=Long.parseLong(map.get("uid"));
        var drugName=map.get("drugName");
        if(!drugName.isEmpty()){
            return Result.success(drugService.getPageDrugAlertInfoTotalByDrugName(uid,drugName));
        }else{
            return Result.success(drugService.getPageDrugAlertInfoTotal(uid));
        }
    }

    @PostMapping("/insertDrug")
    public Result insertDrug(@RequestBody Map<String, String> map){
        long drugId=snowFlakeDrug.nextId();
        var uid=Long.parseLong(map.get("uid"));
        var drugName=map.get("drugName");
        var frequency=map.get("frequency");
        var unit=map.get("unit");
        var dosage=Float.parseFloat(map.get("dosage"));
        var isActive=Integer.parseInt(map.get("isActive"));
        drugService.insertDrug(drugId,uid, drugName, frequency, unit, dosage, isActive);
        return Result.success();
    }

    @PostMapping("/insertDrugAndAlert")
    public Result insertDrugAndAlert(@RequestBody Map<String, String> map){
        long drugId=snowFlakeDrug.nextId();
        var uid=Long.parseLong(map.get("uid"));
        var drugName=map.get("drugName");
        var frequency=map.get("frequency");
        var unit=map.get("unit");
        var dosage=Float.parseFloat(map.get("dosage"));
        var isActive=Integer.parseInt(map.get("isActive"));
        System.out.println(uid);
        System.out.println(drugName);
        System.out.println(frequency);
        System.out.println(unit);
        System.out.println(dosage);
        System.out.println(isActive);
        drugService.insertDrug(drugId,uid, drugName, frequency, unit, dosage, isActive);
        long alertId=snowFlakeDrugAlert.nextId();
        var alertTime= Time.valueOf(map.get("alertTime"));
        Date eatTime= Date.valueOf("2000-01-01");
        System.out.println("-------------");
        System.out.println(alertTime);
        System.out.println(eatTime);
        drugAlertService.insertDrugAlert(alertId, uid, drugId, alertTime, eatTime);
        return Result.success();
    }

    @PostMapping("/deleteDrugById")
    public Result deleteDrugById(@RequestBody Map<String, String> map){
        var uid=Long.parseLong(map.get("uid"));
        var drugId=Long.parseLong(map.get("drugId"));
        drugService.deleteDrugById(uid, drugId);
        return Result.success();
    }
}
