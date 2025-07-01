package com.hirrao.javaee.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;
import java.sql.Time;

@Getter
@Setter
@AllArgsConstructor
public class DrugManageResult {
    private Long drugId;
    private String drugName;
    private String frequency;
    private String unit;
    private Float dosage;
    private int isActive;

    private Long alertId;
    private Time alertTime;
    private Date eatTime;
}
