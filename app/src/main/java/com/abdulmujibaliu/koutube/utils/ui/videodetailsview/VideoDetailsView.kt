package com.abdulmujibaliu.koutube.utils.ui.videodetailsview

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import at.blogc.android.views.ExpandableTextView
import com.abdulmujibaliu.koutube.R
import com.abdulmujibaliu.koutube.data.models.YoutubeVideo
import com.abdulmujibaliu.koutube.utils.ui.playlistrowitem.PlayListItemRowView
import com.squareup.picasso.Picasso

/**
 * Created by abdulmujibaliu on 10/17/17.
 */

class VideoDetailsView : LinearLayout {

    private val TAG = javaClass.simpleName
    private var mView: View? = null

    private var mVideoTitle: TextView? = null
    private var mVideoStatistics: TextView? = null
    private var mVideoDesc: ExpandableTextView? = null
    private var mVideoDescExp: ImageView? = null
    private var mChannelImage: ImageView? = null
    private var mChannelName: TextView? = null
    private var mChannelDesc: TextView? = null
    private var mRelatedVideos: PlayListItemRowView? = null


    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {

        val inflater = context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

        mView = inflater.inflate(R.layout.video_details_view, this, true)

        mVideoTitle = mView!!.findViewById(R.id.video_title)
        mVideoStatistics = mView!!.findViewById(R.id.video_stats)
        mVideoDesc = mView!!.findViewById(R.id.video_desc_text)
        mChannelName = mView!!.findViewById(R.id.channel_name)
        mChannelDesc = mView!!.findViewById(R.id.channel_details)

        mRelatedVideos = mView!!.findViewById(R.id.play_list_item_row)
        mVideoDescExp = mView!!.findViewById(R.id.expanded_collpase)
        mChannelImage = mView!!.findViewById(R.id.video_channel)


    }

    fun setVideos(currentVideo: YoutubeVideo, list: List<YoutubeVideo>) {
        mVideoTitle!!.text = currentVideo.itemTitle
        mVideoDesc!!.text = currentVideo.itemDesc
        mVideoStatistics!!.text = "122200 views • ${currentVideo.getPublishText()}"
        mChannelName!!.text = currentVideo.channelName

        Picasso.with(context).load(currentVideo.itemImageURL).fit().centerCrop().into(mChannelImage)
        mVideoDescExp!!.setOnClickListener({
            if(mVideoDesc!!.isExpanded){
                mVideoDesc!!.collapse()
            }else{
                mVideoDesc!!.expand()
            }
        })

        mRelatedVideos!!.setmDataSource(list)
    }


}