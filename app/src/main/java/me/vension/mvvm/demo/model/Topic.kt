package me.vension.mvvm.demo.model

/**
 * ========================================================
 * @author: Created by Vension on 2018/6/11 12:05.
 * @email:  2506856664@qq.com
 * @desc:   character determines attitude, attitude determines destiny
 * ========================================================
 */
class Topic {
    var id: String = ""
    var createdAt: String = ""
    var order: Long = 0L
    var publishDate: String = ""
    var summary: String = ""
    var title: String = ""
    var updatedAt: String = ""
    var newsArray: Array<News> = emptyArray()
}