package me.vension.mvvm.demo

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.View
import android.widget.Toast
import me.vension.mvvm.demo.model.NewsType
import java.text.SimpleDateFormat
import java.util.*

/**
 * ========================================================
 * 作  者：Vension
 * 日  期：2018/4/2 16:15
 * 描  述：拓展类
 * ========================================================
 */

fun Fragment.showToast(content: String): Toast {
    val toast = Toast.makeText(MyApplication.getAppContext(), content, Toast.LENGTH_SHORT)
    toast.show()
    return toast
}

fun Context.showToast(content: String): Toast {
    val toast = Toast.makeText(MyApplication.getAppContext(), content, Toast.LENGTH_SHORT)
    toast.show()
    return toast
}


fun View.dip2px(dipValue: Float): Int {
    val scale = this.resources.displayMetrics.density
    return (dipValue * scale + 0.5f).toInt()
}

fun View.px2dip(pxValue: Float): Int {
    val scale = this.resources.displayMetrics.density
    return (pxValue / scale + 0.5f).toInt()
}

fun String.toDate(pattern: String): Date? {
    try {
        val sdf = SimpleDateFormat(pattern, Locale.CHINA)
        sdf.timeZone = TimeZone.getTimeZone("UTC")
        return sdf.parse(this)
    } catch (ex: Exception) {
        ex.printStackTrace()
    }
    return null
}

fun durationFormat(duration: Long?): String {
    val minute = duration!! / 60
    val second = duration % 60
    return if (minute <= 9) {
        if (second <= 9) {
            "0$minute' 0$second''"
        } else {
            "0$minute' $second''"
        }
    } else {
        if (second <= 9) {
            "$minute' 0$second''"
        } else {
            "$minute' $second''"
        }
    }
}

/**
 * 数据流量格式化
 */
fun Context.dataFormat(total: Long): String {
    var result: String
    var speedReal: Int = (total / (1024)).toInt()
    result = if (speedReal < 512) {
        speedReal.toString() + " KB"
    } else {
        val mSpeed = speedReal / 1024.0
        (Math.round(mSpeed * 100) / 100.0).toString() + " MB"
    }
    return result
}


fun Bundle.putNewsType(key: String, newsType: NewsType) {
    this.putInt(key, newsType.ordinal)
}

fun Bundle.getNewsType(key: String): NewsType {
    val value = this.getInt(key)
    return when (value) {
        NewsType.TechNews.ordinal -> NewsType.TechNews
        NewsType.DevNews.ordinal -> NewsType.DevNews
        else -> NewsType.TechNews
    }
}