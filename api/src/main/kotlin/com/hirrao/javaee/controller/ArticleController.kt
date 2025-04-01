package com.hirrao.javaee.controller

import com.hirrao.javaee.entity.Article
import com.hirrao.javaee.entity.Result
import com.hirrao.javaee.service.ArticleService
import com.hirrao.javaee.utils.Result.success
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/articles")
class ArticleController @Autowired constructor(private val articleService: ArticleService) {
    data class DataAddArticle(val title: String, val description: String, val image: String, val content: String)
    data class DataModifyArticleInfo(
        val id: Long, val title: String, val description: String, val image: String, val content: String
    )

    data class DataArticleInfo(val curPage: Int, val size: Int)

    data class DataSearchArticleByCondition(
        val curPage: Int, val size: Int, val searchCondition: String, val conditionValue: String
    )

    @GetMapping
    fun getAllArticles(): List<Article?>? {
        return articleService.findAll()
    }

    @PostMapping("/add")
    fun addArticle(dataAddArticle: DataAddArticle): Result {
        val (title, description, image, content) = dataAddArticle
        articleService.addArticle(title, description, image, content)

        return success()
    }


    @PostMapping("/articlesInfo")
    fun articlesInfo(dataArticleInfo: DataArticleInfo): Result {
        val (curPage, size) = dataArticleInfo
        return success(articleService.articlesInfo(curPage, size))

    }

    @PostMapping("/modifyArticleInfo")
    fun modifyArticleInfo(dataModifyArticleInfo: DataModifyArticleInfo): Result {
        val (id, title, description, image, content) = dataModifyArticleInfo
        articleService.modifyArticleInfo(id, title, description, image, content)

        return success()
    }

    @PostMapping("/deleteArticle")
    fun deleteArticle(id: Long): Result {
        articleService.deleteArticle(id)
        return success()
    }

    @PostMapping("/searchArticleByCondition")
    fun searchArticleByCondition(dataSearchArticleByCondition: DataSearchArticleByCondition): Result {
        val (curPage, size, searchCondition, conditionValue) = dataSearchArticleByCondition
        return if (searchCondition.isNotEmpty() && conditionValue.isNotEmpty()) {
            //            return Result.success();
            success(
                articleService.searchArticleByCondition(
                    curPage, size, searchCondition, conditionValue
                )
            )
        } else {
            success(articleService.articlesInfo(curPage, size))
        }
    }

}
