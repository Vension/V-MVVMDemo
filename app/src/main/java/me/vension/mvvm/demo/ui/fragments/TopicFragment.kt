package me.vension.mvvm.demo.ui.fragments

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_topic.*
import me.vension.mvvm.demo.R
import me.vension.mvvm.demo.model.Topic
import me.vension.mvvm.demo.ui.activitys.AgentWebActivity
import me.vension.mvvm.demo.ui.adapters.TopicListAdapter
import me.vension.mvvm.demo.viewmodel.TopicViewModel
import name.dmx.readhubclient.viewmodel.factory.TopicViewModelFactory

/**
 * ========================================================
 * @author: Created by Vension on 2018/6/11 17:45.
 * @email:  2506856664@qq.com
 * @desc:   character determines attitude, attitude determines destiny
 * ========================================================
 */
class TopicFragment : Fragment() {

    private val PAGE_SIZE = 10
    private var dataList:List<Topic> = ArrayList()
    private lateinit var topicViewModel : TopicViewModel
    private lateinit var topicLiveData:LiveData<List<Topic>>
    private var mAdapter: TopicListAdapter? = null

    companion object {
        fun newInstance(): TopicFragment {
            val topicFragment = TopicFragment()
            return topicFragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater?.inflate(R.layout.fragment_topic, container, false)
        return view!!
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        if (this.activity != null) {
            topicViewModel = ViewModelProviders.of(this, TopicViewModelFactory(PAGE_SIZE)).get(TopicViewModel::class.java)
            topicLiveData = topicViewModel.getLiveData()
            topicLiveData.observe(this, getObserver())
            smartRefreshLayout.setOnRefreshListener {
                topicViewModel.refresh()
            }
            smartRefreshLayout.setOnLoadMoreListener {
                topicViewModel.loadMore()
            }
        }
    }

    private fun getObserver() = Observer<List<Topic>> { newsList ->
        if (newsList != null) {
            dataList = newsList
            if (mAdapter == null) {
                mAdapter = TopicListAdapter(context!!, dataList)
                mAdapter!!.onItemClickListener = onItemClickListener
                recyclerView.layoutManager = LinearLayoutManager(context)
                recyclerView.adapter = mAdapter
            } else {
                mAdapter?.data = dataList
            }
            smartRefreshLayout.finishLoadMore()
            smartRefreshLayout.finishRefresh()
            mAdapter?.notifyDataSetChanged()
            recyclerView.scrollToPosition(dataList.size - PAGE_SIZE)
        }
    }

    private val onItemClickListener = object : TopicListAdapter.OnItemClickListener {
        override fun onItemClick(view: View, position: Int) {
            val item = dataList[position]
            val intent = AgentWebActivity.makeIntent(context!!, "https://readhub.me/topic/${item.id}", item.title, "")
            startActivity(intent)
        }
    }


}