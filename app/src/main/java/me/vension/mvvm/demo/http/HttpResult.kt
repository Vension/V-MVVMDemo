package me.vension.mvvm.demo.http

/**
 * ========================================================
 * @author: Created by Vension on 2018/6/11 12:03.
 * @email:  2506856664@qq.com
 * @desc:   character determines attitude, attitude determines destiny
 * ========================================================
 */
class HttpResult<T> {

    var pageSize = 0
    var totalItems = 0L
    var totalPages = 0
    var data:List<T>? = null

}