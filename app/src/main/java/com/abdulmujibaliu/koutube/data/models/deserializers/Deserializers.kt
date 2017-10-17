package com.abdulmujibaliu.koutube.data.models.deserializers

import android.util.Log
import com.abdulmujibaliu.koutube.data.KutConstants
import com.abdulmujibaliu.koutube.data.models.ChannelPlayListItems
import com.abdulmujibaliu.koutube.data.models.ChannelPlayLists
import com.abdulmujibaliu.koutube.data.models.VideoResult
import com.abdulmujibaliu.koutube.data.models.YoutubeVideo
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.google.gson.JsonObject
import java.lang.reflect.Type
import org.joda.time.format.DateTimeFormat


/**
 * Created by abdulmujibaliu on 10/16/17.
 */


class ChannelPlaylistsDeserializer : JsonDeserializer<ChannelPlayLists> {

    val TAG = javaClass.simpleName


    override fun deserialize(json: JsonElement?, typeOfT: Type?, context: JsonDeserializationContext?): ChannelPlayLists {
        val data = json?.getAsJsonObject()

        var channelPlayList: ChannelPlayLists? = null

        if (data != null && !data.isJsonNull) {
            channelPlayList = ChannelPlayLists(findIDs(data))
        }

        return channelPlayList!!
    }

    fun findIDs(jsonObject: JsonObject): MutableList<String>? {
        val jsonArray = jsonObject.get(KutConstants.KEY_ITEMS).getAsJsonArray()
        var idList: MutableList<String>? = mutableListOf()
        if (jsonArray != null && !jsonArray.isJsonNull() && !(jsonArray.size() == 0)) {

            for (jsonElement in jsonArray) {
                val itemID = jsonElement.asJsonObject.get(KutConstants.KEY_ITEM_ID).asString.replace("\"", "")
                Log.d(TAG, itemID)
                idList?.add(itemID)
            }

        }

        return idList
    }

}


class PlaylistsItemsDeserializer : JsonDeserializer<ChannelPlayListItems> {
    val TAG = javaClass.simpleName

    override fun deserialize(json: JsonElement?, typeOfT: Type?, context: JsonDeserializationContext?): ChannelPlayListItems {
        val data = json?.getAsJsonObject()

        var playListItems: ChannelPlayListItems? = null

        if (data != null && !data.isJsonNull) {
            playListItems = ChannelPlayListItems(findVideoIDs(data))
        }

        return playListItems!!
    }

    fun findVideoIDs(jsonObject: JsonObject): MutableList<String>? {
        val jsonArray = jsonObject.get(KutConstants.KEY_ITEMS).getAsJsonArray()
        var idList: MutableList<String>? = mutableListOf()
        if (jsonArray != null && !jsonArray.isJsonNull() && !(jsonArray.size() == 0)) {

            for (jsonElement in jsonArray) {
                val itemID = jsonElement.asJsonObject.get(KutConstants.KEY_ITEM_ID).asString.replace("\"", "")
                Log.d(TAG, "PLAYLISTITEMID $itemID")

                val snippet = jsonElement.asJsonObject.get(KutConstants.SNIPPET)

                if (snippet != null) {
                    val contentDetails = snippet.asJsonObject.get(KutConstants.RECOURCE_ID)
                    val videoID = contentDetails.asJsonObject.get(KutConstants.KEY_VIDEO_ID).asString
                    Log.d(TAG, "VIDEO ID $videoID")
                    idList?.add(videoID)
                }

            }

        }

        return idList
    }
}


class VideoDeserializer : JsonDeserializer<VideoResult> {
    val TAG = javaClass.simpleName

    override fun deserialize(json: JsonElement?, typeOfT: Type?, context: JsonDeserializationContext?): VideoResult? {
        val videos = mutableListOf<YoutubeVideo>()
        val data = json?.getAsJsonObject()

        val videoResult = VideoResult()

        for (jsonElement in data?.get(KutConstants.KEY_ITEMS)!!.asJsonArray) {
            var videoItem = YoutubeVideo()

            findDetails(jsonElement, videoItem)
            videos.add(videoItem)
        }

        videoResult.videos = videos

        return videoResult
    }

    fun findDetails(json: JsonElement?, youtubeVideo: YoutubeVideo) {

        youtubeVideo.videoID = json!!.asJsonObject.get(KutConstants.KEY_ITEM_ID).asString

        if (json.asJsonObject?.getAsJsonObject(KutConstants.SNIPPET) != null && !json.asJsonObject.getAsJsonObject(KutConstants.SNIPPET).isJsonNull) {
            val snippetObject: JsonElement? = json.asJsonObject?.getAsJsonObject(KutConstants.SNIPPET)
            var dtf = DateTimeFormat.forPattern("yyyy-MM-dd'T'HH:mm:ss.SSSZ")

            val dateTime = dtf.parseDateTime(snippetObject?.asJsonObject?.get(KutConstants.VIDEO_PUBSLISHED_AT)?.asString)
            val title = snippetObject?.asJsonObject?.get(KutConstants.VIDEO_TITLE)?.asString
            val desc = snippetObject?.asJsonObject?.get(KutConstants.VIDEO_DESCRIPTION)?.asString

            //Log.d(TAG, "VIDEO DATETIME $dateTime VIDNAME: $title DESC: $desc")

            youtubeVideo.datePublished = dateTime
            youtubeVideo.videoTitle = title
            youtubeVideo.description = desc

            if (snippetObject?.asJsonObject?.getAsJsonObject(KutConstants.VIDEO_THUMBNAILS) != null &&
                    !snippetObject.asJsonObject?.getAsJsonObject(KutConstants.VIDEO_THUMBNAILS)!!.isJsonNull) {

                val thumbNailsObj = snippetObject.asJsonObject?.get(KutConstants.VIDEO_THUMBNAILS)
                val channelName = snippetObject.asJsonObject.get(KutConstants.VIDEO_CHANNEL_TITLE).asString

                youtubeVideo.channelName = channelName

                if (thumbNailsObj != null) {

                    val standard = thumbNailsObj.asJsonObject?.get(KutConstants.VIDEO_STANDARD)
                    val default = thumbNailsObj.asJsonObject?.get(KutConstants.VIDEO_DEFAULT_IMG)
                    val maxRes = thumbNailsObj.asJsonObject?.get(KutConstants.VIDEO_MAXRES_IMG)
                    val medium = thumbNailsObj.asJsonObject?.get(KutConstants.VIDEO_MEDIUM_IMG)
                    val high = thumbNailsObj.asJsonObject?.get(KutConstants.VIDEO_HIGH_IMG)

                    //Log.d(TAG, standard.toString())

                    var resURL: String? = null

                    when {
                        maxRes != null -> resURL = maxRes.asJsonObject.get(KutConstants.VIDEO_THUMB_URL).asString
                        high != null -> resURL = high.asJsonObject.get(KutConstants.VIDEO_THUMB_URL).asString
                        medium != null -> resURL = medium.asJsonObject.get(KutConstants.VIDEO_THUMB_URL).asString
                        standard != null -> resURL = standard.asJsonObject.get(KutConstants.VIDEO_THUMB_URL).asString
                        default != null -> resURL = default.asJsonObject.get(KutConstants.VIDEO_THUMB_URL).asString
                    }

                    youtubeVideo.videoThumbnailURL = resURL
                }
            }


            dtf = DateTimeFormat.forPattern("'PT'mm'M'ss'S'")

            if (json.asJsonObject.get(KutConstants.KEY_CONTENT_DETAILS) != null) {
                try {
                    val duration = dtf.parseLocalTime(json.asJsonObject.
                            get(KutConstants.KEY_CONTENT_DETAILS).asJsonObject.get(KutConstants.VIDEO_VIDEO_DURATION).asString)
                    youtubeVideo.duration = duration
                }catch (e: Exception){
                    e.printStackTrace()
                }
            }

            if (json.asJsonObject.get(KutConstants.VIDEO_VIDEO_STATISTICS) != null) {
                youtubeVideo.numberOfViews = json.asJsonObject.get(KutConstants.VIDEO_VIDEO_STATISTICS)
                        .asJsonObject.get(KutConstants.VIDEO_VIDEO_VIEW_COUNT).asInt
            }

        }
    }


}