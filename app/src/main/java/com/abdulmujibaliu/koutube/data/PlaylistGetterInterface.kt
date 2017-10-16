package com.abdulmujibaliu.koutube.data

import com.abdulmujibaliu.koutube.data.models.ChannelPlayListItems
import com.abdulmujibaliu.koutube.data.models.ChannelPlayLists
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by abdulmujibaliu on 10/15/17.
 */
interface PlaylistGetterInterface {

    @GET("playlistItems")
    fun getPlaylistItems(@Query("playlistId") playlistID :String) : Observable<ChannelPlayListItems>


    @GET("playlists")
    fun getPlaylistsForChannel(@Query("channelId") channelID :String) : Observable<ChannelPlayLists>

}