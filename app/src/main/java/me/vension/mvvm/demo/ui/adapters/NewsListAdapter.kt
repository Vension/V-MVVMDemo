package me.vension.mvvm.demo.ui.adapters

import android.content.Context
import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import me.vension.mvvm.demo.R
import me.vension.mvvm.demo.model.News
import me.vension.mvvm.demo.widgets.TimeTextView

/**
 * ========================================================
 * @author: Created by Vension on 2018/6/11 14:39.
 * @email:  2506856664@qq.com
 * @desc:   character determines attitude, attitude determines destiny
 * ========================================================
 */
class NewsListAdapter(private val context: Context,var data : List<News>): RecyclerView.Adapter<NewsListAdapter.MyViewHolder>(){

     var onItemClickListener:OnItemClickListener? =null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_news_list,parent,false)
        val viewHolder = MyViewHolder(view)
        viewHolder.cardView.setOnClickListener {
            onItemClickListener?.onItemClick(viewHolder.cardView,viewHolder.cardView.tag as Int)
        }
        return viewHolder
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        var item = data[position]
        holder.title.text = item.title
        holder.time.text = item.publishDate
        holder.summary.text = item.summary
        holder.siteAndAuthor.text = item.siteName + if (item.authorName != null)"/${item.authorName}" else ""
        holder.cardView.tag = position
    }

    inner class MyViewHolder:RecyclerView.ViewHolder {
        var cardView : CardView
        var title : TextView
        var time : TimeTextView
        var summary : TextView
        var siteAndAuthor : TextView
        constructor(view: View) : super(view){
            cardView = view.findViewById(R.id.cardView)
            title = view.findViewById(R.id.title)
            time = view.findViewById(R.id.time)
            summary = view.findViewById(R.id.summary)
            siteAndAuthor = view.findViewById(R.id.siteAndAuthor)
        }
    }

    interface OnItemClickListener {
        fun onItemClick(view: View, position: Int)
    }

}