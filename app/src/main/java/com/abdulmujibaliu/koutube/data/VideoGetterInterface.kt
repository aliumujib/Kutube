package com.abdulmujibaliu.koutube.data

import com.abdulmujibaliu.koutube.data.models.VideoResult
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by abdulmujibaliu on 10/16/17.
 */

interface VideoGetterInterface{

    @GET("videos")
    fun getVideoItems(@Query("id") videoIDs :String) : Observable<VideoResult>

}