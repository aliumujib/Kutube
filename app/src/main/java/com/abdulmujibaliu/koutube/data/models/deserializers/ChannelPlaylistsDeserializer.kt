package com.abdulmujibaliu.koutube.data.models.deserializers

import android.util.Log
import com.abdulmujibaliu.koutube.data.KutConstants
import com.abdulmujibaliu.koutube.data.models.ChannelPlayListItems
import com.abdulmujibaliu.koutube.data.models.ChannelPlayLists
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.google.gson.JsonObject
import java.lang.reflect.Type

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
                val itemID = jsonElement.asJsonObject.get(KutConstants.KEY_PLAYLIST_ID).asString.replace("\"","")
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
                val itemID = jsonElement.asJsonObject.get(KutConstants.KEY_PLAYLIST_ID).asString.replace("\"","")
                Log.d(TAG, "PLAYLISTITEMID $itemID")

                val snippet = jsonElement.asJsonObject.get(KutConstants.SNIPPET)

                if(snippet!=null){
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