package com.abdulmujibaliu.koutube.fragments.rvadapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.abdulmujibaliu.koutube.R
import com.abdulmujibaliu.koutube.data.models.PlayListItemsResult
import com.abdulmujibaliu.koutube.fragments.childfragments.VideoClickListener
import com.abdulmujibaliu.koutube.utils.ui.playlistrowitem.PlayListItemRowView
import kotlinx.android.synthetic.main.playlist_item_row_view.view.*

/**
 * Created by abdulmujibaliu on 10/18/17.
 */
class PlaylistRVAdapter (context: Context, videoClickListener: VideoClickListener) : RecyclerView.Adapter<PlaylistRVAdapter.ViewHolder>() {

    val context = context
    val videoClickListener = videoClickListener

    private var data: MutableList<PlayListItemsResult> = mutableListOf()

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        holder?.bindItem(data[position], context, videoClickListener)
    }


    fun addAll(data: List<PlayListItemsResult>) {
        this.data.addAll(data)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.playlist_item_row_view, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return this.data.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val playListItemRowView: PlayListItemRowView = itemView.play_list_rv_item

        fun bindItem(playListItem: PlayListItemsResult, context: Context, videoClickListener: VideoClickListener) {
            playListItemRowView.setPlayListItemResult(playListItem)
        }


    }
}