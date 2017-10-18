package com.abdulmujibaliu.koutube.fragments.childfragments


import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.View
import com.abdulmujibaliu.koutube.data.models.YoutubeVideo
import com.abdulmujibaliu.koutube.data.repositories.PlayListRepository
import com.abdulmujibaliu.koutube.data.repositories.contracts.RepositoryContracts
import com.abdulmujibaliu.koutube.fragments.rvadapter.VideoRVAdapter
import kotlinx.android.synthetic.main.fragment_base.*


class VideosFragment : BaseFragment() {



    var videosRVAdapter: VideoRVAdapter? = null



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null) {

        }
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        videosRVAdapter = VideoRVAdapter(context, this)
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = videosRVAdapter
        videosRVAdapter?.notifyDataSetChanged()



        //UCpEHs4jtfj1sTo1g-ubDyMg //MTANG

        dataSource.getVideosObservable()?.subscribe(
                { data ->
                    Log.d(TAG, data.toString())
                    videosRVAdapter!!.addAll(data.items as List<YoutubeVideo>)
                }, { error ->
            error.printStackTrace()
        })
    }


    companion object {

        fun newInstance(): VideosFragment {
            val fragment = VideosFragment()
            val args = Bundle()
            fragment.arguments = args
            return fragment
        }
    }

}


interface VideoClickListener {
    fun onVideoClicked(youtubeVideo: YoutubeVideo, relatedVideos: List<YoutubeVideo>)
}