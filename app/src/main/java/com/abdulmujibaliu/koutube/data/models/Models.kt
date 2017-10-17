package com.abdulmujibaliu.koutube.data.models

import android.text.format.DateUtils
import org.joda.time.DateTime
import org.joda.time.LocalTime
import org.joda.time.format.DateTimeFormat
import java.util.*

/**
 * Created by abdulmujibaliu on 10/16/17.
 */

open class BaseModel {

    var itemID: String? = null
    var datePublished: DateTime? = null
    var channelID: String? = null
    var itemTitle: String? = null
    var itemDesc: String? = null
    var itemImageURL: String? = null
    var channelName: String? = null


    public fun getPublishText(): String {
        return if (datePublished == null) {
            "N/A"
        } else {
            DateUtils.getRelativeTimeSpanString(datePublished!!.millis, Calendar.getInstance().getTime().getTime(), 0L, DateUtils.FORMAT_24HOUR).toString();
        }
    }

}

open class BaseModelWrapper(idList: MutableList<String>) {

    val ids: MutableList<String>? = idList

    var items = mutableListOf<BaseModel>()
}

class PlayList : BaseModel() {
    var itemCount: Int? = null
}

class PlayListsResult(idList: MutableList<String>) : BaseModelWrapper(idList)


class PlayListItem : BaseModel() {

    var videoId: String? = null

}


class PlayListItemsResult(idList: MutableList<String>) : BaseModelWrapper(idList)


class VideoResult : BaseModelWrapper(mutableListOf())


class YoutubeVideo : BaseModel() {

    //@SerializedName("id")
    var videoID: String? = null

    var duration: LocalTime? = null

    var numberOfViews: Int? = null


    fun getDurationText(): String {
        return if (duration == null) {
            "N/A"
        } else {
            val dtf = DateTimeFormat.forPattern("HH:mm:ss")
            dtf.print(duration)
        }
    }

}