package cn.hirrao.javaee.service.impl

import cn.hirrao.javaee.entity.Blog
import cn.hirrao.javaee.mapper.BlogMapper
import cn.hirrao.javaee.service.BlogService
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper
import com.baomidou.mybatisplus.core.metadata.IPage
import com.baomidou.mybatisplus.extension.plugins.pagination.Page
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class BlogServiceImpl @Autowired constructor(private val blogMapper: BlogMapper) : BlogService {
    override fun findByBlogId(bolgId: Long?): Blog? {
        return blogMapper.findByBlogId(bolgId)
    }

    override fun findByUid(uid: Int): List<Blog?>? {
        return blogMapper.findByUid(uid)
    }

    override fun findByTitle(title: String?): List<Blog?>? {
        return blogMapper.findByTitle(title)
    }

    override fun addBlog(
        blogId: Long?, content: String?, createTime: String?, updateTime: String?, uid: Int, title: String?
    ) {
        blogMapper.addBlog(blogId, content, createTime, updateTime, uid, title)
    }

    override fun update(blogId: Long?, content: String?, updateTime: String?, title: String?) {
        blogMapper.update(blogId, content, updateTime, title)
    }

    override fun delete(blogId: Long?) {
        blogMapper.delete(blogId)
    }

    override fun search(curPage: Int, size: Int, uid: Int): IPage<Blog?>? {
        val page = Page<Blog>(curPage.toLong(), size.toLong())
        val queryWrapper = QueryWrapper<Blog>()
        queryWrapper.eq("uid", uid)
        return blogMapper.selectPage(page, queryWrapper)
    }
}
