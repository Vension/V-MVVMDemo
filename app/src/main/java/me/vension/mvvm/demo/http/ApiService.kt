package me.vension.mvvm.demo.http

import io.reactivex.Observable
import me.vension.mvvm.demo.model.News
import me.vension.mvvm.demo.model.Topic
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * ========================================================
 * @author: Created by Vension on 2018/6/11 11:51.
 * @email:  2506856664@qq.com
 * @desc:   character determines attitude, attitude determines destiny
 * ========================================================
 */
interface ApiService{

    /**热门话题*/
    @GET("topic")
    fun getTopics(@Query("lastCursor")lastCursor:Long?,@Query("pageSize")pageSize:Int) : Observable<HttpResult<Topic>>

    /**科技动态*/
    @GET("news")
    fun getTechNews(@Query("lastCursor")lastCursor:Long?,@Query("pageSize")pageSize:Int) : Observable<HttpResult<News>>

    /**开发者资讯*/
    @GET("technews")
    fun getDevNews(@Query("lastCursor")lastCursor:Long?,@Query("pageSize")pageSize:Int) : Observable<HttpResult<News>>

}