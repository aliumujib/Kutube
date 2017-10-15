package com.abdulmujibaliu.koutube.fragments.rvadapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.abdulmujibaliu.koutube.R
import kotlinx.android.synthetic.main.item_video_card.view.*

/**
 * Created by abdulmujibaliu on 10/15/17.
 */

class VideoRVAdapter(context: Context): RecyclerView.Adapter<VideoRVAdapter.ViewHolder>(){

    private val context = context;

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        //holder?.bindItem()
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        val  view = LayoutInflater.from(context).inflate(R.layout.item_video_card, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return 10
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val videoTitle: TextView = itemView.video_title
        val posterTitle: TextView = itemView.poster_name
        val timeStamp: TextView = itemView.post_time_stamp
        val videoDesc: TextView = itemView.video_desc

        val accountThumbnail: ImageView = itemView.channel_image
        val videoImage: ImageView = itemView.video_image


        fun bindItem(){}


    }
}