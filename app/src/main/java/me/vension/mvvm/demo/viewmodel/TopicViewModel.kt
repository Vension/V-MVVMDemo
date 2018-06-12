package me.vension.mvvm.demo.viewmodel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import me.vension.mvvm.demo.MyApplication
import me.vension.mvvm.demo.http.transformoer.SchedulerTransformer
import me.vension.mvvm.demo.model.Topic
import me.vension.mvvm.demo.repository.DataRepository

/**
 * ========================================================
 * @author: Created by Vension on 2018/6/11 17:47.
 * @email:  2506856664@qq.com
 * @desc:   character determines attitude, attitude determines destiny
 * ========================================================
 */
class TopicViewModel(private val pageSize: Int): ViewModel(){
    private val liveData:MutableLiveData<List<Topic>> = MutableLiveData()
    private var isFirstPage = true
    private var lastCursor:Long? = null
    private val topicList = ArrayList<Topic>()

    fun getLiveData():LiveData<List<Topic>>{
        fetchData()
        return liveData
    }

    fun refresh(){
        isFirstPage = true
        lastCursor = null
        fetchData()
    }

    fun loadMore(){
        isFirstPage = false
        fetchData()
    }

    private fun fetchData() {
        val observable = DataRepository.getInstance(MyApplication.getAppContext())
                .getTopics(lastCursor, pageSize)

        observable.compose(SchedulerTransformer())
                .subscribe({data ->
                    if (isFirstPage){
                        topicList.clear()
                    }
                    topicList.addAll(topicList.size,data.data?.toList()!!)
                    liveData.value = topicList
                    lastCursor = data.data?.last()?.order
                },{
                    liveData.value = null
                })
    }

}