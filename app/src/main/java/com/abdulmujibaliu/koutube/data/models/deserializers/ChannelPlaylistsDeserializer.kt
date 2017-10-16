package com.abdulmujibaliu.koutube.data.models.deserializers

import android.util.Log
import com.abdulmujibaliu.koutube.data.KutConstants
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

        var channelPlayList : ChannelPlayLists? = null

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
                val itemID = jsonElement.asJsonObject.get(KutConstants.KEY_PLAYLIST_ID).toString()
                Log.d(TAG, itemID)
                idList?.add(itemID)
            }

        }

        return idList
    }

}