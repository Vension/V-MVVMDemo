package me.vension.mvvm.demo.ui.fragments.test

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_test_1.*
import me.vension.mvvm.demo.R
import me.vension.mvvm.demo.entitys.TestEntity
import me.vension.mvvm.demo.viewmodel.TestViewModel

/**
 * ========================================================
 * @author: Created by Vension on 2018/6/8 14:21.
 * @email:  2506856664@qq.com
 * @desc:   character determines attitude, attitude determines destiny
 * ========================================================
 */
class TestFragment1 : Fragment(){

    private lateinit var mViewModel: TestViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_test_1, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activity?.let {
            mViewModel = ViewModelProviders.of(it).get(TestViewModel::class.java)
        }

        fragment_set_button.setOnClickListener{v ->
            mViewModel.getTestEntity().postValue(TestEntity("我是标题_1","我是简介_1","Vension"))
        }

        mViewModel.getTestEntity().observe(this, Observer<TestEntity> {
            fragment_text_view.text = TestViewModel.getFormatContent(it?.title!!, it?.desc, it?.author)
        })

    }

}