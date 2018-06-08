package me.vension.mvvm.demo

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import me.vension.mvvm.demo.fragments.TestFragment1
import me.vension.mvvm.demo.fragments.TestFragment2
import me.vension.mvvm.demo.vm.TestViewModel

class MainActivity : AppCompatActivity() {

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_home -> {
                message.setText(R.string.title_home)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_dashboard -> {
                message.setText(R.string.title_dashboard)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_notifications -> {
                message.setText(R.string.title_notifications)
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    private lateinit var mViewModel : TestViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)

        supportFragmentManager.beginTransaction().replace(R.id.fragment_container_1,TestFragment1()).commit()
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
