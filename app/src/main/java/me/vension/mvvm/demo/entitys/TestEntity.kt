package me.vension.mvvm.demo.entitys

import java.io.Serializable

/**
 * ========================================================
 * @author: Created by Vension on 2018/6/8 12:25.
 * @email:  2506856664@qq.com
 * @desc:   character determines attitude, attitude determines destiny
 * ========================================================
 */

/**
 * @param title 标题
 * @param desc  简介
 * @param author 作者
 */
class TestEntity(var title : String,
                 var desc : String,
                 var author : String
                 ): Serializable