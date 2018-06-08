package me.vension.mvvm.demo.fragments

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_test_2.*
import me.vension.mvvm.demo.R
import me.vension.mvvm.demo.entitys.TestEntity
import me.vension.mvvm.demo.vm.TestViewModel

/**
 * ========================================================
 * @author: Created by Vension on 2018/6/8 14:37.
 * @email:  2506856664@qq.com
 * @desc:   character determines attitude, attitude determines destiny
 * ========================================================
 */
class TestFragment2 : Fragment(){

    lateinit var mViewModel:TestViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_test_2,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mViewModel = ViewModelProviders.of(activity!!).get(TestViewModel::class.java)
        mViewModel.getTestEntity().observe(this, Observer<TestEntity> { accountBean ->
            fragment_text_view.text = TestViewModel.getFormatContent(accountBean!!.title, accountBean!!.desc, accountBean!!.author)
        })
    }
}