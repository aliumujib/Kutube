package com.abdulmujibaliu.koutube.utils.ui.playlistrowitem.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.abdulmujibaliu.koutube.R
import com.abdulmujibaliu.koutube.data.models.BaseModel
import com.abdulmujibaliu.koutube.fragments.playlists.PlayListItemClickListener
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.play_list_item.view.*

/**
 * Created by abdulmujibaliu on 10/17/17.
 */

class PlayListRowRVAdapter(context: Context, playListItemClickListener: PlayListItemClickListener?) : RecyclerView.Adapter<PlayListRowRVAdapter.ViewHolder>() {

    val context = context;
    val videoClickListener = playListItemClickListener

    private var data: MutableList<BaseModel> = mutableListOf()

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        holder?.bindItem(data[position], context, videoClickListener)
    }


    fun addAll(data: List<BaseModel>) {
        this.data.addAll(data)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.play_list_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        if(data.isEmpty()){
            return 0
        }else{
            return this.data.size
        }
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val videoTitle: TextView = itemView.item_title
        val videoImage: ImageView = itemView.item_image


        fun bindItem(playListItem: BaseModel, context: Context, playListItemClickListener: PlayListItemClickListener?) {
            videoTitle.text = playListItem.itemTitle
            Picasso.with(context).load(playListItem.itemImageURL).fit().centerCrop().into(videoImage)
            itemView.setOnClickListener({ view ->
                //playListItemClickListener?.onPlayListClick(playListItem as PlayListItem)
            })
        }


    }
}