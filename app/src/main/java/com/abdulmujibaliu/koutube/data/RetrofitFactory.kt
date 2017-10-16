package com.abdulmujibaliu.koutube.data

import com.abdulmujibaliu.koutube.BuildConfig
import com.abdulmujibaliu.koutube.data.models.ChannelPlayLists
import com.abdulmujibaliu.koutube.data.models.deserializers.ChannelPlaylistsDeserializer
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
                        //.registerTypeAdapter(Playlist::class.java, PlaylistDeserializer())
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
                            .addQueryParameter("part", "snippet")
                            .addQueryParameter("maxresults", 50.toString())
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
                interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
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
        val KEY_PLAYLIST_ID: String = "id"
    }
}