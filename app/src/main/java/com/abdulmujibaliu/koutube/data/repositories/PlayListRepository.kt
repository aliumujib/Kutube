package com.abdulmujibaliu.koutube.data.repositories

import android.util.Log
import com.abdulmujibaliu.koutube.data.PlaylistGetterInterface
import com.abdulmujibaliu.koutube.data.RetrofitFactory
import com.abdulmujibaliu.koutube.data.VideoGetterInterface
import com.abdulmujibaliu.koutube.data.models.*
import com.abdulmujibaliu.koutube.data.repositories.contracts.RepositoryContracts
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.ReplaySubject

/**
 * Created by abdulmujibaliu on 10/16/17.
 */
class PlayListRepository : RepositoryContracts.IPlaylistRepository {
    val TAG = javaClass.simpleName

    var playListserviceInstance = RetrofitFactory.getInstance().create(PlaylistGetterInterface::class.java)
    var videosServiceInstance = RetrofitFactory.getInstance().create(VideoGetterInterface::class.java)


    var playListSubject = ReplaySubject.create<VideoResult>()

    override fun getPlayVideosForChannels(channelIDs: List<String>): ReplaySubject<VideoResult>? {
        getChannelsPlayList(channelIDs)
        return playListSubject
    }

    fun getChannelsPlayList(channelIDs: List<String>) {
        val observablesList: MutableList<Observable<PlayListsResult>> = mutableListOf()

        for (channelID in channelIDs) {
            Log.d(TAG, "Getting for channelID" + channelID)
            observablesList.add(playListserviceInstance.getPlaylistsForChannel(channelID).observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.io()))
        }

        Observable.zip(observablesList) { dataArray ->
            var playListIDs = mutableListOf<String>()

            for (any in dataArray) {
                playListIDs.addAll((any as PlayListsResult).ids!!)
            }

            playListIDs
        }

                .subscribe({ data ->

                    getPlayListsItems(data)

                }, { throwable ->

                    throwable.printStackTrace()
                })
    }


    fun getPlayListsItems(playListIDs: List<String>) {
        val observablesList: MutableList<Observable<PlayListItemsResult>> = mutableListOf()

        for (playListID in playListIDs) {
            observablesList.add(playListserviceInstance.getPlaylistItems(playListID).observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.io()))
        }

        Observable.zip(observablesList) { dataArray ->
            var videoIDList = mutableListOf<String>()

            for (any in dataArray) {
                //Log.d(TAG, (any as PlayListItem).videoIds.toString())
                videoIDList.addAll((any as PlayListItemsResult).ids!!)
            }

            videoIDList

        }.subscribe({ data ->
            var videoIDs: String? = null

            for ((index, string) in (data.withIndex())) {
                if (index < 50) {
                    when (index) {
                        0 -> videoIDs = string
                        data.size - 1 -> videoIDs += string
                        49 -> videoIDs += string
                        else -> videoIDs = videoIDs + string + ","
                    }
                }
            }

            //Log.d(TAG, videoIDs)
            getVideoItems(videoIDs!!)
        }, { throwable ->

            throwable.printStackTrace()
        })

    }

    fun getVideoItems(idStrings: String) {
        videosServiceInstance.getVideoItems(idStrings)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe({ data ->
                    playListSubject.onNext(data)
                }, { throwable ->
                    throwable.printStackTrace()
                })
    }


    companion object {
        private var playListRepo: PlayListRepository? = null

        fun getInstance(): RepositoryContracts.IPlaylistRepository? {
            return if (playListRepo != null) {
                this.playListRepo
            } else {
                playListRepo = PlayListRepository()
                this.playListRepo
            }
        }
    }

}