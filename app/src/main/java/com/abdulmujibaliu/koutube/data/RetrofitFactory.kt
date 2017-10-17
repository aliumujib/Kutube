package com.abdulmujibaliu.koutube.data

import com.abdulmujibaliu.koutube.BuildConfig
import com.abdulmujibaliu.koutube.data.models.ChannelPlayListItems
import com.abdulmujibaliu.koutube.data.models.ChannelPlayLists
import com.abdulmujibaliu.koutube.data.models.VideoResult
import com.abdulmujibaliu.koutube.data.models.YoutubeVideo
import com.abdulmujibaliu.koutube.data.models.deserializers.ChannelPlaylistsDeserializer
import com.abdulmujibaliu.koutube.data.models.deserializers.PlaylistsItemsDeserializer
import com.abdulmujibaliu.koutube.data.models.deserializers.VideoDeserializer
import com.google.gson.GsonBuilder
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException
import java.util.concurrent.TimeUnit

/**
 * Created by abdulmujibaliu on 10/15/17.
 */

class RetrofitFactory {

    companion object RetrofitInstance {
       private var retrofit: Retrofit? = null

        fun getInstance(): Retrofit {

            return if (retrofit != null) {


                retrofit as Retrofit
            } else {

                val client = OkHttpClient.Builder()
                        .addInterceptor(createApiKeyQueryInterceptor())
                        .addInterceptor(createLoggingInterceptor())
                        .readTimeout(1, TimeUnit.MINUTES)
                        .connectTimeout(1, TimeUnit.MINUTES)
                        .build()

                val gson = GsonBuilder()
                        .registerTypeAdapter(ChannelPlayLists::class.java, ChannelPlaylistsDeserializer())
                        .registerTypeAdapter(ChannelPlayListItems::class.java, PlaylistsItemsDeserializer())
                        .registerTypeAdapter(VideoResult::class.java, VideoDeserializer())
                        .create()

                retrofit = retrofit2.Retrofit.Builder()
                        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                        .addConverterFactory(GsonConverterFactory.create(gson))
                        .client(client)
                        .baseUrl("https://www.googleapis.com/youtube/v3/")
                        .build()
                retrofit as Retrofit
            }
        }



        private fun createApiKeyQueryInterceptor(): Interceptor {
            return object : Interceptor {
                @Throws(IOException::class)
                override fun intercept(chain: Interceptor.Chain): Response {
                    val original = chain.request()
                    val originalHttpUrl = original.url()
                    val url = originalHttpUrl.newBuilder()
                            .addQueryParameter("part", "snippet,contentDetails")
                            .addQueryParameter("maxResults", 50.toString())
                            .addQueryParameter(KutConstants.API_KEY,
                                    KutConstants.API_KEY_VAL)
                            //.addQueryParameter(WSConstants.PAGE_LIMIT, 200.toString())
                            .build()
                    return chain.proceed(original.newBuilder().url(url).build())
                }


            }
        }

        private fun createLoggingInterceptor(): HttpLoggingInterceptor {
            val interceptor = HttpLoggingInterceptor()
            if (BuildConfig.DEBUG) {
                interceptor.setLevel(HttpLoggingInterceptor.Level.HEADERS)
            } else {
                interceptor.setLevel(HttpLoggingInterceptor.Level.NONE)
            }
            return interceptor
        }
    }
}


class KutConstants {
    companion object {
        val API_KEY = "key"
        val API_KEY_VAL = "AIzaSyCzTQAdni52z7AR6vLPBVoM75FES9BIUTw"
        val KEY_ITEMS = "items"
        val KEY_ITEM_ID: String = "id"

        val KEY_CONTENT_DETAILS: String = "contentDetails"

        val KEY_VIDEO_ID: String = "videoId"
        val SNIPPET: String = "snippet"
        val RECOURCE_ID: String = "resourceId"

        val VIDEO_PUBSLISHED_AT: String = "publishedAt"
        val VIDEO_TITLE: String = "title"
        val VIDEO_DESCRIPTION: String = "description"
        val VIDEO_THUMBNAILS: String = "thumbnails"
        val VIDEO_MAXRES_IMG: String = "standard"
        val VIDEO_STANDARD: String = "standard"
        val VIDEO_MEDIUM_IMG: String = "medium"
        val VIDEO_HIGH_IMG: String = "high"
        val VIDEO_DEFAULT_IMG: String = "default"

        val VIDEO_THUMB_URL: String = "url"
        val VIDEO_CHANNEL_TITLE: String = "channelTitle"

        val VIDEO_VIDEO_DURATION: String = "duration"
        val VIDEO_VIDEO_STATISTICS: String = "statistics"

        val VIDEO_VIDEO_VIEW_COUNT: String = "viewCount"


    }
}