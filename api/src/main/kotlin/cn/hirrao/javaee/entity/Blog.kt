package cn.hirrao.javaee.entity

import com.baomidou.mybatisplus.annotation.TableField
import com.baomidou.mybatisplus.annotation.TableId
import lombok.AllArgsConstructor
import lombok.Getter
import lombok.Setter

@Getter
@Setter
@AllArgsConstructor
class Blog {
    @TableId(value = "blogId")
    private val blogId: Long? = null

    @TableField("content")
    private val content: String? = null

    @TableField("createTime")
    private val createTime: String? = null

    @TableField("updateTime")
    private val updateTime: String? = null
    private val uid: Long? = null
    private val title: String? = null
}
