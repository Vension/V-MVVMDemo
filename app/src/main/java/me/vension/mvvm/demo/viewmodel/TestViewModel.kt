package me.vension.mvvm.demo.viewmodel

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.MutableLiveData
import android.util.Log
import me.vension.mvvm.demo.entitys.TestEntity

/**
 * ========================================================
 * @author: Created by Vension on 2018/6/8 12:31.
 * @email:  2506856664@qq.com
 * @desc:   character determines attitude, attitude determines destiny
 * ========================================================
 */
class TestViewModel(application: Application) : AndroidViewModel(application) {
    val test = MutableLiveData<TestEntity>()

    fun setTestEntity(title : String,desc:String,author:String){
        test.value = TestEntity(title,desc,author)
    }

    fun getTestEntity(): MutableLiveData<TestEntity> {
        return test
    }

    // 当MyActivity被销毁时，Framework会调用ViewModel的onCleared()
    override fun onCleared() {
        Log.e("TestViewModel", "==========onCleared()==========")
        super.onCleared()
    }

    companion object {
        fun getFormatContent(title:String,desc:String,author: String):String{
            val mBuffer = StringBuilder()
            mBuffer.append("标题：").append(title)
                    .append("\n简介：").append(desc)
                    .append("\n作者：").append(author)
            return mBuffer.toString()
        }
    }
}

