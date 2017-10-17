package com.abdulmujibaliu.koutube.data

import com.abdulmujibaliu.koutube.data.models.PlayListItem
import com.abdulmujibaliu.koutube.data.models.PlayList
import com.abdulmujibaliu.koutube.data.models.PlayListItemsResult
import com.abdulmujibaliu.koutube.data.models.PlayListsResult
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by abdulmujibaliu on 10/15/17.
 */
interface PlaylistGetterInterface {

    @GET("playlistItems")
    fun getPlaylistItems(@Query("playlistId") playlistID :String) : Observable<PlayListItemsResult>


    @GET("playlists")
    fun getPlaylistsForChannel(@Query("channelId") channelID :String) : Observable<PlayListsResult>

}