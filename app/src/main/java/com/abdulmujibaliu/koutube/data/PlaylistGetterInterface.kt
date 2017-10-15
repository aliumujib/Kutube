package com.abdulmujibaliu.koutube.data

import io.reactivex.Flowable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Created by abdulmujibaliu on 10/15/17.
 */
interface PlaylistGetterInterface {

    @GET("playlistItems")
    fun getPlaylists(@Query("playlistId") playlistID :String) : Flowable<String>
}