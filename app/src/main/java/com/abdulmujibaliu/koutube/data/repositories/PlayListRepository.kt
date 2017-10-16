package com.abdulmujibaliu.koutube.data.repositories

import android.util.Log
import com.abdulmujibaliu.koutube.data.PlaylistGetterInterface
import com.abdulmujibaliu.koutube.data.RetrofitFactory
import com.abdulmujibaliu.koutube.data.models.ChannelPlayListItems
import com.abdulmujibaliu.koutube.data.models.ChannelPlayLists
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

    var serviceInstance = RetrofitFactory.getInstance().create(PlaylistGetterInterface::class.java)
    var playListSubject = ReplaySubject.create<String>()

    override fun getPlayVideosForChannels(channelIDs: List<String>): Observable<String> {
        getChannelsPlayList(channelIDs)
        return playListSubject
    }

    fun getChannelsPlayList(channelIDs: List<String>) {
        val observablesList: MutableList<Observable<ChannelPlayLists>> = mutableListOf()

        for (channelID in channelIDs) {
            Log.d(TAG, "Getting for channelID" + channelID)
            observablesList.add(serviceInstance.getPlaylistsForChannel(channelID).observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.io()))
        }

        Observable.zip(observablesList) { dataArray ->
            var playListIDs = mutableListOf<String>()

            for (any in dataArray) {
                playListIDs.addAll((any as ChannelPlayLists).ids)
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
        val observablesList: MutableList<Observable<ChannelPlayListItems>> = mutableListOf()

        for (playListID in playListIDs) {
            observablesList.add(serviceInstance.getPlaylistItems(playListID).observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.io()))
        }

        Observable.zip(observablesList) { dataArray ->
            {
                var videoIDs = mutableListOf<String>()

                for (any in dataArray) {
                    videoIDs.addAll((any as ChannelPlayListItems).videoIds)
                }
            }
        }.subscribe({ data ->

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