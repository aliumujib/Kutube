package com.abdulmujibaliu.koutube.fragments.childfragments

import android.content.Context
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.abdulmujibaliu.koutube.data.models.PlayListItem
import com.abdulmujibaliu.koutube.fragments.rvadapter.PlaylistRVAdapter
import kotlinx.android.synthetic.main.fragment_base.*

class PlaylistsFragment : BaseFragment() {

    var playlistRVAdapter: PlaylistRVAdapter? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (arguments != null) {

        }
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        playlistRVAdapter = PlaylistRVAdapter(context, this)
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = playlistRVAdapter
        playlistRVAdapter?.notifyDataSetChanged()


        dataSource.getPlayListObservable()?.subscribe({
            data->
                playlistRVAdapter?.addAll(data)
        }, {
            error->
            error.printStackTrace()
        })

    }


    override fun onAttach(context: Context?) {
        super.onAttach(context)

    }

    companion object {

        fun newInstance(): PlaylistsFragment {
            val fragment = PlaylistsFragment()
            val args = Bundle()
            fragment.arguments = args
            return fragment
        }
    }
}


open interface PlayListItemClickListener {
    fun onPlayListClick(playListItem: PlayListItem)
}