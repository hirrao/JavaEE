package cn.hirrao.javaee.controller

import cn.hirrao.javaee.entity.Article
import cn.hirrao.javaee.entity.Result
import cn.hirrao.javaee.entity.success
import cn.hirrao.javaee.service.ArticleService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/articles")
class ArticleController @Autowired constructor(private val articleService: ArticleService) {

    @GetMapping
    fun getAllArticles(): List<Article?>? {
        return articleService.findAll()
    }

    @PostMapping("/add")
    fun addArticle(@RequestBody map: Map<String?, String?>): Result {
        val title = map["title"]
        val description = map["description"]
        val image = map["image"]
        val content = map["content"]
        articleService.addArticle(title, description, image, content)
        return success()
    }


    @PostMapping("/articlesInfo")
    fun articlesInfo(@RequestBody map: Map<String?, String>): Result {
        val curPage = map["curPage"]!!.toInt()
        val size = map["size"]!!.toInt()
        return success(articleService.articlesInfo(curPage, size))
    }

    @PostMapping("/modifyArticleInfo")
    fun modifyArticleInfo(@RequestBody map: Map<String?, String>): Result {
        val id = map["id"]!!.toLong()
        val title = map["title"]
        val description = map["description"]
        val image = map["image"]
        val content = map["content"]

        println("modifyArticleInfo id:$id")
        println("modifyArticleInfo title:$title")
        println("modifyArticleInfo description:$description")
        println("modifyArticleInfo image:$image")
        println("modifyArticleInfo content:$content")

        articleService.modifyArticleInfo(id, title, description, image, content)
        return success()
    }

    @PostMapping("/deleteArticle")
    fun deleteArticle(@RequestBody map: Map<String?, String>): Result {
        val id = map["id"]!!.toLong()
        articleService.deleteArticle(id)
        println("delete id:$id")
        return success()
    }

    @PostMapping("/searchArticleByCondition")
    fun searchArticleByCondition(@RequestBody map: Map<String?, String>): Result {
        val curPage = map["curPage"]!!.toInt()
        val size = map["size"]!!.toInt()
        val searchCondition = map["searchCondition"]
        val conditionValue = map["conditionValue"]

        println("curPage:$curPage")
        println("size:$size")
        println("searchCondition:$searchCondition")
        println("conditionValue:$conditionValue")

        return if (searchCondition!!.isNotEmpty() && conditionValue!!.isNotEmpty()) {
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
