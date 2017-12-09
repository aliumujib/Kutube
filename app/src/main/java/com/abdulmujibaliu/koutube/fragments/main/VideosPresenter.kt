package com.abdulmujibaliu.koutube.fragments.main

import android.widget.VideoView
import com.abdulmujibaliu.koutube.data.models.YoutubeVideo
import com.abdulmujibaliu.koutube.fragments.mvp.VideosContract
import com.abdulmujibaliu.koutube.fragments.rvadapter.VideoAction

/**
 * Created by ABDUL-MUJEEB ALIU on 09/12/2017.
 */
class VideosPresenter(videosView: VideosContract.VideosView) : VideosContract.VideosPresenter {


    override fun searchVideos(query: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun clickVideo(video: YoutubeVideo) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun clickVideoAction(video: YoutubeVideo, videoAction: VideoAction) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun loadMoreVideos() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}