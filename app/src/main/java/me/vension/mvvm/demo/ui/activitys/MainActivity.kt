package me.vension.mvvm.demo.ui.activitys

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentTransaction
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import me.vension.mvvm.demo.R
import me.vension.mvvm.demo.model.NewsType
import me.vension.mvvm.demo.showToast
import me.vension.mvvm.demo.ui.fragments.NewsFragment
import me.vension.mvvm.demo.ui.fragments.TopicFragment

/**
 * ========================================================
 * 作  者：Vension
 * 日  期：2018/6/8 17:37
 * 描  述：主界面
 * ========================================================
 */

class MainActivity : AppCompatActivity() {

    private var mTabFragment1: TopicFragment? = null
    private var mTabFragment2: NewsFragment? = null
    private var mTabFragment3: NewsFragment? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(toolbar)

        main_navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
        switchFragment(0)
        main_navigation.menu.getItem(0).isChecked = true

        btn_test_viewmodel.setOnClickListener {
            startActivity(Intent(this, TestViewModelActivity::class.java))
        }
    }


    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_home -> {
                switchFragment(0)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_dashboard -> {
                switchFragment(1)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_notifications -> {
                switchFragment(2)
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }


    /**
     * 切换Fragment
     * @param position 下标
     */
    private fun switchFragment(position: Int) {
        val transaction = supportFragmentManager.beginTransaction()
        hideFragments(transaction)
        when (position) {
            0 -> mTabFragment1?.let {
                transaction.show(it)
            } ?: TopicFragment.newInstance().let {
                mTabFragment1 = it
                transaction.add(R.id.main_content, it, "home")
            }
            1 -> mTabFragment2?.let {
                transaction.show(it)
            } ?: NewsFragment.newInstance(NewsType.TechNews).let {
                mTabFragment2 = it
                transaction.add(R.id.main_content, it, "discovery") }
            2 -> mTabFragment3?.let {
                transaction.show(it)
            } ?: NewsFragment.newInstance(NewsType.DevNews).let {
                mTabFragment3 = it
                transaction.add(R.id.main_content, it, "hot") }
            else -> {
                transaction.add(R.id.main_content, Fragment(), "agent")
            }
        }
        transaction.commitAllowingStateLoss()
    }


    /**
     * 隐藏所有的Fragment
     * @param transaction transaction
     */
    private fun hideFragments(transaction: FragmentTransaction) {
        mTabFragment1?.let { transaction.hide(it) }
        mTabFragment2?.let { transaction.hide(it) }
        mTabFragment3?.let { transaction.hide(it) }
    }

    private var exitTime: Long = 0
    override fun onBackPressed() {
        val currentTime = System.currentTimeMillis()
        if (currentTime - exitTime < 2000) {
            super.onBackPressed()
        } else {
            showToast("再按一次退出程序")
            exitTime = currentTime
        }
    }


}
