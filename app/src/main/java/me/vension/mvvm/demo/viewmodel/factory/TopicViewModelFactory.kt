package name.dmx.readhubclient.viewmodel.factory

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import me.vension.mvvm.demo.viewmodel.TopicViewModel

/**
 * ========================================================
 * 作  者：Vension
 * 日  期：2018/6/11 15:42
 * 描  述：创建带构造参数的ViewModel
 * ========================================================
 */

class TopicViewModelFactory(private val pageSize: Int) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TopicViewModel::class.java)) {
            return TopicViewModel(pageSize) as T
        } else {
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}