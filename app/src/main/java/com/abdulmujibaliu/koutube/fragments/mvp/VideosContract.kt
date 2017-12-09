package com.abdulmujibaliu.koutube.fragments.mvp

import com.abdulmujibaliu.koutube.data.models.VideoResult
import com.abdulmujibaliu.koutube.data.models.YoutubeVideo
import com.abdulmujibaliu.koutube.fragments.rvadapter.VideoAction

/**
 * Created by ABDUL-MUJEEB ALIU on 09/12/2017.
 */

interface VideosContract {

    interface VideosView : BaseContract.BaseView<VideoResult> {

    }

    interface VideosNavigator  {
        fun goToVideoDetails(video: YoutubeVideo)
    }

    interface VideosPresenter : BaseContract.BasePresenter{

         fun searchVideos(query: String)

         fun clickVideo(video: YoutubeVideo)

         fun clickVideoAction(video: YoutubeVideo, videoAction: VideoAction)

         fun loadMoreVideos()

    }


}