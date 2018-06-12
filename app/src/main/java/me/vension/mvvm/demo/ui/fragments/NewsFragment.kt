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
import kotlinx.android.synthetic.main.fragment_news.*
import me.vension.mvvm.demo.R
import me.vension.mvvm.demo.getNewsType
import me.vension.mvvm.demo.model.News
import me.vension.mvvm.demo.model.NewsType
import me.vension.mvvm.demo.putNewsType
import me.vension.mvvm.demo.ui.activitys.AgentWebActivity
import me.vension.mvvm.demo.ui.adapters.NewsListAdapter
import me.vension.mvvm.demo.viewmodel.NewsViewModel
import name.dmx.readhubclient.viewmodel.factory.NewsViewModelFactory

/**
 * ========================================================
 * @author: Created by Vension on 2018/6/8 18:11.
 * @email:  2506856664@qq.com
 * @desc:   热门话题、开发者资讯
 * ========================================================
 */
class NewsFragment : Fragment() {
    private val PAGE_SIZE = 10
    private var dataList : List<News> = ArrayList()
    private lateinit var newsViewModel : NewsViewModel
    private lateinit var newsLiveData : LiveData<List<News>>
    private var mAdapter : NewsListAdapter? = null
    private var newsType:NewsType = NewsType.TechNews

    companion object {
        val KEY_NEWS_TYPE = "KEY_NEWS_TYPE"
        fun newInstance(newsType: NewsType): NewsFragment {
            val fragment = NewsFragment()
            val bundle = Bundle()
            bundle.putNewsType(KEY_NEWS_TYPE, newsType)
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        newsType = arguments?.getNewsType(KEY_NEWS_TYPE)!!
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_news,container,false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        if (this.activity != null){
            newsViewModel = ViewModelProviders.of(this,NewsViewModelFactory(newsType,PAGE_SIZE)).get(NewsViewModel::class.java)
            newsLiveData = newsViewModel.getLiveData()
            newsLiveData.observe(this,getObserver())
            smartRefreshLayout.setOnRefreshListener {
                newsViewModel.refresh()
            }
            smartRefreshLayout.setOnLoadMoreListener {
                newsViewModel.loadMore()
            }
        }
    }

    private fun getObserver()= Observer<List<News>> { newsList ->
        if (newsList != null){
            dataList = newsList
            if (mAdapter == null){
                mAdapter = NewsListAdapter(context!!,dataList)
                mAdapter?.onItemClickListener = onItemClickListener
                recyclerView.layoutManager = LinearLayoutManager(context)
                recyclerView.adapter = mAdapter
            }else{
                mAdapter?.data = dataList
            }
            smartRefreshLayout.finishLoadMore()
            smartRefreshLayout.finishRefresh()
            mAdapter?.notifyDataSetChanged()
            recyclerView.scrollToPosition(dataList.size - PAGE_SIZE)
        }
    }


    private val onItemClickListener = object : NewsListAdapter.OnItemClickListener {
        override fun onItemClick(view: View, position: Int) {
            val item = dataList[position]
            val intent = AgentWebActivity.makeIntent(context!!, item.url, item.title, "")
            startActivity(intent)
        }
    }


}