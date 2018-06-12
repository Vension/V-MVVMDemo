package me.vension.mvvm.demo.ui.adapters

import android.content.Context
import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.text.SpannableString
import android.text.SpannableStringBuilder
import android.text.style.TextAppearanceSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import me.vension.mvvm.demo.R
import me.vension.mvvm.demo.model.Topic
import me.vension.mvvm.demo.widgets.TimeTextView

/**
 * ========================================================
 * @author: Created by Vension on 2018/6/11 17:59.
 * @email:  2506856664@qq.com
 * @desc:   character determines attitude, attitude determines destiny
 * ========================================================
 */
class TopicListAdapter(private val context: Context,var data:List<Topic>) :RecyclerView.Adapter<TopicListAdapter.MyViewHolder>(){


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_topic_list, parent, false)
        val viewHolder = MyViewHolder(view)
        viewHolder.cardView.setOnClickListener {
            onItemClickListener?.onItemClick(viewHolder.cardView, viewHolder.cardView.tag as Int)
        }
        return viewHolder
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val item = data[position]
//        holder?.title?.text = item.title
        holder?.time?.text = item.createdAt
        holder?.summary?.text = item.summary
        holder?.cardView?.tag = position
        val ssb = SpannableStringBuilder(item.title + "  " + holder?.time?.text)
        val textAppearanceSpan = TextAppearanceSpan(context, R.style.time_textAppearance)
        ssb.setSpan(textAppearanceSpan, item.title.length + 2, ssb.length, SpannableString.SPAN_INCLUSIVE_INCLUSIVE)
        holder?.title?.text = ssb
    }

    var onItemClickListener: OnItemClickListener? = null


    inner class MyViewHolder : RecyclerView.ViewHolder {
        var cardView: CardView
        var title: TextView
        var time: TimeTextView
        var summary: TextView
        constructor(view:View):super(view){
            cardView = view.findViewById(R.id.cardView)
            title = view.findViewById(R.id.title)
            time = view.findViewById(R.id.time)
            summary = view.findViewById(R.id.summary)
        }
    }

    interface OnItemClickListener {
        fun onItemClick(view: View, position: Int)
    }



}