package cn.hirrao.javaee.entity

import com.baomidou.mybatisplus.annotation.TableField
import com.baomidou.mybatisplus.annotation.TableId
import com.baomidou.mybatisplus.annotation.TableName
import lombok.AllArgsConstructor
import lombok.Getter
import lombok.NoArgsConstructor
import lombok.Setter
import java.time.LocalDate

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@TableName("bloodPressureLog")
class BloodPressure {
    @TableId(value = "bplId")
    private val bplId: Long? = null
    private val uid: Long? = null

    @TableField("SBP")
    private val SBP: Float? = null

    @TableField("DBP")
    private val DBP: Float? = null

    @TableField("recordTime")
    private val recordTime: LocalDate? = null

    @TableField("riskLevel")
    private val riskLevel: String? = null
    private val classification: String? = null
}
