package me.vension.mvvm.demo.viewmodel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import me.vension.mvvm.demo.MyApplication
import me.vension.mvvm.demo.http.transformoer.SchedulerTransformer
import me.vension.mvvm.demo.model.News
import me.vension.mvvm.demo.model.NewsType
import me.vension.mvvm.demo.repository.DataRepository
import me.vension.mvvm.demo.toDate

/**
 * ========================================================
 * @author: Created by Vension on 2018/6/11 11:03.
 * @email:  2506856664@qq.com
 * @desc:   character determines attitude, attitude determines destiny
 * ========================================================
 */
class NewsViewModel(private val newsType : NewsType,private val pageSize : Int) : ViewModel() {

    private val liveData : MutableLiveData<List<News>> = MutableLiveData()
    private var isFirstPage = true
    private var lastCursor : Long = 0L
    private var newsList = ArrayList<News>()

    fun getLiveData() : LiveData<List<News>>{
        lastCursor = System.currentTimeMillis()
        fetchData()
        return liveData
    }

    fun refresh(){
        isFirstPage = true
        lastCursor = System.currentTimeMillis()
        fetchData()
    }

    fun loadMore(){
        isFirstPage = false
        fetchData()
    }

    private fun fetchData() {
        val observable = if (newsType == NewsType.TechNews){
            DataRepository.getInstance(MyApplication.getAppContext()).getTechNews(lastCursor, pageSize)
        }else{
            DataRepository.getInstance(MyApplication.getAppContext()).getDevNews(lastCursor, pageSize)
        }
        observable.compose(SchedulerTransformer())
                .subscribe({data ->
                    if (isFirstPage){
                        newsList.clear()
                    }
                    newsList.addAll(newsList.size,data.data?.toList()!!)
                    liveData.value = newsList
                    lastCursor = data.data?.last()?.publishDate!!.toDate("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")?.time!!
                },{
                    liveData.value = null
                })
    }


}