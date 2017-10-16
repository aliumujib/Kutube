package com.abdulmujibaliu.koutube.data

import com.abdulmujibaliu.koutube.data.models.ChannelPlayLists
import io.reactivex.Flowable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Created by abdulmujibaliu on 10/15/17.
 */
interface PlaylistGetterInterface {

    @GET("playlistItems")
    fun getPlaylistItems(@Query("playlistId") playlistID :String) : Flowable<String>


    @GET("playlists")
    fun getPlaylistsForChannel(@Query("channelId") channelID :String) : Flowable<ChannelPlayLists>

}