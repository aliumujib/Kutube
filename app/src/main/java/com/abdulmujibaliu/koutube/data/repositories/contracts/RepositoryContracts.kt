package com.abdulmujibaliu.koutube.data.repositories.contracts

import com.abdulmujibaliu.koutube.data.models.BaseModel
import com.abdulmujibaliu.koutube.data.models.PlayListItemsResult
import com.abdulmujibaliu.koutube.data.models.PlayListsResult
import com.abdulmujibaliu.koutube.data.models.VideoResult
import io.reactivex.Observable
import io.reactivex.subjects.ReplaySubject

/**
 * Created by abdulmujibaliu on 10/16/17.
 */
interface RepositoryContracts {

    interface IDataSource {
        fun getVideosObservable() : Observable<VideoResult>?

        fun getPlayListObservable() : Observable<List<PlayListItemsResult>>?

    }

    interface IPlaylistRepository: IDataSource {
        fun getPlayListsAndVideosForChannels(channelIDs: List<String>)
    }

    interface IVideoSearchRepository {

        fun searchForVideo(filters: HashMap<String, Any>)

        fun getResultObservable() : Observable<VideoResult>?

    }
}