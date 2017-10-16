package com.abdulmujibaliu.koutube.data.models

/**
 * Created by abdulmujibaliu on 10/16/17.
 */

class ChannelPlayLists(list: MutableList<String>?) {

    val ids: MutableList<String> = list!!

}


class ChannelPlayListItems(videoIds: MutableList<String>?) {

    val videoIds: MutableList<String> = videoIds!!

}