package com.hirrao.javaee.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Drug {
    @TableId(value = "drugId")
    //@TableField("drugId")
    private Long drugId;
    private Long uid;
    @TableField("drugName")
    private String drugName;
    private String frequency;
    private String unit;
    private Float dosage;
    @TableField("isActive")
    private int isActive;
}
