package me.vension.mvvm.demo.ui.activitys

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_test_viewmodel.*
import me.vension.mvvm.demo.R
import me.vension.mvvm.demo.ui.fragments.test.TestFragment1
import me.vension.mvvm.demo.ui.fragments.test.TestFragment2
import me.vension.mvvm.demo.viewmodel.TestViewModel

/**
 * ========================================================
 * @author: Created by Vension on 2018/6/8 17:32.
 * @email:  2506856664@qq.com
 * @desc:   测试LiveData + ViewModel
 * ========================================================
 */
class TestViewModelActivity : AppCompatActivity() {

    private lateinit var mViewModel : TestViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test_viewmodel)

        supportFragmentManager.beginTransaction().replace(R.id.fragment_container_1, TestFragment1()).commit()
        supportFragmentManager.beginTransaction().replace(R.id.fragment_container_2, TestFragment2()).commit()

        mViewModel = ViewModelProviders.of(this).get(TestViewModel::class.java)

        main_set_button.setOnClickListener {
            mViewModel.setTestEntity("我是标题-main","我是简介-main","我是作者-main")
        }

        mViewModel.getTestEntity().observe(this, Observer {
            textView.text = TestViewModel.getFormatContent(it!!.title,it!!.desc,it!!.author)
        })
    }
}
