package cn.hirrao.javaee.entity

import com.baomidou.mybatisplus.annotation.TableField
import com.baomidou.mybatisplus.annotation.TableId
import lombok.AllArgsConstructor
import lombok.Getter
import lombok.Setter

@Getter
@Setter
@AllArgsConstructor
class Drug {
    @TableId(value = "drugId") //@TableField("drugId")
    private val drugId: Long? = null
    private val uid: Long? = null

    @TableField("drugName")
    private val drugName: String? = null
    private val frequency: String? = null
    private val unit: String? = null
    private val dosage: Float? = null

    @TableField("isActive")
    private val isActive = 0
}
