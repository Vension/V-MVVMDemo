package me.vension.mvvm.demo.http.transformoer

import io.reactivex.Observable
import io.reactivex.ObservableSource
import io.reactivex.ObservableTransformer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * ========================================================
 * @author: Created by Vension on 2018/6/11 14:28.
 * @email:  2506856664@qq.com
 * @desc:   统一线程调度处理
 * ========================================================
 */
class SchedulerTransformer<T> : ObservableTransformer<T,T>{

    override fun apply(upstream: Observable<T>): ObservableSource<T> {
        return upstream.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }

}