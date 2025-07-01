package com.hirrao.javaee.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@TableName("bloodPressureLog")
public class BloodPressure {
    @TableId(value = "bplId")
    private Long bplId;
    private Long uid;
    @TableField("SBP")
    private Float SBP;
    @TableField("DBP")
    private Float DBP;
    @TableField("recordTime")
    private LocalDate recordTime;
    @TableField("riskLevel")
    private String riskLevel;
    private String classification;
}
