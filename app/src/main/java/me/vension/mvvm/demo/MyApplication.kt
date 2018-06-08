package me.vension.mvvm.demo

import android.app.Application
import android.content.Context
import android.content.res.Resources
import com.scwang.smartrefresh.header.MaterialHeader
import com.scwang.smartrefresh.layout.SmartRefreshLayout
import com.scwang.smartrefresh.layout.footer.ClassicsFooter

/**
 * ========================================================
 * @author: Created by Vension on 2018/6/8 12:08.
 * @email:  2506856664@qq.com
 * @desc:   character determines attitude, attitude determines destiny
 * ========================================================
 */
class MyApplication : Application() {

    init {
        //设置全局的Header构建器
        SmartRefreshLayout.setDefaultRefreshHeaderCreator{ context, layout ->
            //全局设置主题颜色
            layout.setPrimaryColorsId(R.color.colorPrimary, android.R.color.white)
            //指定为经典Header，默认是 贝塞尔雷达Header
            MaterialHeader(context)
        }
        //设置全局的Footer构建器
        SmartRefreshLayout.setDefaultRefreshFooterCreator { context, _ ->
            //指定为经典Footer，默认是 BallPulseFooter
            ClassicsFooter(context).setDrawableSize(20F)
        }
    }

    companion object {

        private lateinit var mApplication: Application
        /**
         * 获取当前应用上下文对象
         */
        fun getAppContext(): Context {
            return mApplication
        }

        /**
         * 获取资源文件访问对象
         */
        fun getResource(): Resources {
            return mApplication.resources
        }

    }


    override fun onCreate() {
        super.onCreate()
        mApplication = this
    }

}