package cn.hirrao.javaee.entity

import com.baomidou.mybatisplus.annotation.TableField
import com.baomidou.mybatisplus.annotation.TableId
import com.baomidou.mybatisplus.annotation.TableName
import lombok.AllArgsConstructor
import lombok.Getter
import lombok.Setter
import java.sql.Date
import java.sql.Time

@Getter
@Setter
@AllArgsConstructor
@TableName("DrugAlert")
class DrugAlert {
    @TableId(value = "alertId") //@TableField("alertId")
    private val alertId: Long? = null
    private val uid: Long? = null

    @TableField("drugId")
    private val drugId: Long? = null

    @TableField("alertTime")
    private val alertTime: Time? = null

    @TableField("eatTime")
    private val eatTime: Date? = null
}
