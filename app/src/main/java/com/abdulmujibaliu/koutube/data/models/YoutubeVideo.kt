package com.abdulmujibaliu.koutube.data.models

import android.text.format.DateUtils
import com.google.gson.annotations.SerializedName
import org.joda.time.DateTime
import org.joda.time.LocalTime
import org.joda.time.format.DateTimeFormat
import java.util.*

/**
 * Created by abdulmujibaliu on 10/16/17.
 */


class VideoResult {
    var videos = mutableListOf<YoutubeVideo>()
}


class YoutubeVideo {

    @SerializedName("id")
    var videoID: String? = null

    var datePublished: DateTime? = null

    var duration: LocalTime? = null

    var videoTitle: String? = null

    var channelName: String? = null

    var description: String? = null

    var videoThumbnailURL: String? = null

    var numberOfViews: Int? = null

    fun getPublishText():String{
        return if(datePublished == null){
            "N/A"
        }else{
            DateUtils.getRelativeTimeSpanString(datePublished!!.millis , Calendar.getInstance().getTime().getTime(), 0L, DateUtils.FORMAT_24HOUR).toString();
        }
    }

    fun getDurationText():String{
        return if(duration == null){
            "N/A"
        }else{
            val dtf = DateTimeFormat.forPattern("HH:mm")
            dtf.print(duration)
        }
    }

}