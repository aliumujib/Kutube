package com.abdulmujibaliu.koutube.fragments.rvadapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.abdulmujibaliu.koutube.R
import com.abdulmujibaliu.koutube.data.models.YoutubeVideo
import com.abdulmujibaliu.koutube.fragments.childfragments.VideoClickListener
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_video_card.view.*

/**
 * Created by abdulmujibaliu on 10/15/17.
 */

class VideoRVAdapter(context: Context, videoClickListener: VideoClickListener) : RecyclerView.Adapter<VideoRVAdapter.ViewHolder>() {

    val context = context;
    val videoClickListener = videoClickListener

    val TAG: String = javaClass.simpleName

    private var data: MutableList<YoutubeVideo> = mutableListOf()

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        holder?.bindItem(data[position], context, videoClickListener)
    }


    fun addAll(data: List<YoutubeVideo>) {
        this.data.addAll(data)
        Log.d(TAG, data.size.toString());
        this.notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_video_card, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return this.data.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val videoTitle: TextView = itemView.video_title
        val posterTitle: TextView = itemView.poster_name
        val timeStamp: TextView = itemView.post_time_stamp
        val videoDesc: TextView = itemView.video_desc
        val duration: TextView = itemView.duration

        val accountThumbnail: ImageView = itemView.channel_image
        val videoImage: ImageView = itemView.video_image


        fun bindItem(youtubeVideo: YoutubeVideo, context: Context, videoClickListener: VideoClickListener) {
            videoTitle.text = youtubeVideo.itemTitle
            posterTitle.text = youtubeVideo.channelName
            videoDesc.text = youtubeVideo.itemDesc
            duration.text = youtubeVideo.getDurationText()
            timeStamp.text = youtubeVideo.getPublishText()
            Picasso.with(context).load(youtubeVideo.itemImageURL).fit().centerCrop().into(videoImage)
            Picasso.with(context).load(youtubeVideo.itemImageURL).fit().centerCrop().into(accountThumbnail)
            itemView.setOnClickListener({
                view ->
                videoClickListener.onVideoClicked(youtubeVideo, data)
            })
        }


    }
}