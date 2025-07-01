package com.hirrao.javaee.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;
import java.sql.Time;

@Getter
@Setter
@AllArgsConstructor
@TableName("DrugAlert")
public class DrugAlert {
    @TableId(value = "alertId")
    //@TableField("alertId")
    private Long alertId;
    private Long uid;
    @TableField("drugId")
    private Long drugId;
    @TableField("alertTime")
    private Time alertTime;
    @TableField("eatTime")
    private Date eatTime;
}
