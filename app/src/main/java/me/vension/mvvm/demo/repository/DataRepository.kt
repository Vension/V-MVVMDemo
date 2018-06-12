package me.vension.mvvm.demo.repository

import android.content.Context
import com.google.gson.GsonBuilder
import io.reactivex.Observable
import me.vension.mvvm.demo.http.ApiService
import me.vension.mvvm.demo.http.DefaultOkHttpClient
import me.vension.mvvm.demo.http.HttpResult
import me.vension.mvvm.demo.model.News
import me.vension.mvvm.demo.model.Topic
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

/**
 * ========================================================
 * @author: Created by Vension on 2018/6/11 11:49.
 * @email:  2506856664@qq.com
 * @desc:   数据知识库
 * ========================================================
 */
class DataRepository private constructor(context: Context){

    private val BASE_URL = "https://api.readhub.me/"
    private val mApiService : ApiService

    init {
        val builder = Retrofit.Builder()
        builder.baseUrl(BASE_URL)
                .client(DefaultOkHttpClient.getOKHttpClient(context))
                .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        val retrofit = builder.build()
        mApiService = retrofit.create(ApiService::class.java)
    }

    /**DataRepository的伴生对象*/
    companion object {
        private var instance : DataRepository? = null
        fun getInstance(context: Context):DataRepository{
            if (instance == null){
                synchronized(DataRepository::class.java){
                    if (instance == null){
                        instance = DataRepository(context)
                    }
                }
            }
            return instance!!
        }
    }

    /**
     * 热门话题
     */
    fun getTopics(lastCursor:Long?,pageSize:Int): Observable<HttpResult<Topic>> {
        return mApiService.getTopics(lastCursor,pageSize)
    }

    /**
     * 科技动态
     */
    fun getTechNews(lastCursor:Long?,pageSize:Int): Observable<HttpResult<News>> {
        return mApiService.getTechNews(lastCursor,pageSize)
    }

    /**
     * 开发者资讯
     */
    fun getDevNews(lastCursor:Long?,pageSize:Int): Observable<HttpResult<News>> {
        return mApiService.getDevNews(lastCursor,pageSize)
    }



}