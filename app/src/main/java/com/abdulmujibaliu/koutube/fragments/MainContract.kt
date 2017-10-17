package com.abdulmujibaliu.koutube.fragments

import com.abdulmujibaliu.koutube.data.models.YoutubeVideo

/**
 * Created by abdulmujibaliu on 10/15/17.
 */
interface MainContract {

    interface View{
        fun getPresenter  () : Presenter
        fun showVideoView(video: YoutubeVideo)
    }

    interface Presenter

}