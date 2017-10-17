package com.abdulmujibaliu.koutube.data.repositories.contracts

import com.abdulmujibaliu.koutube.data.models.VideoResult
import io.reactivex.Observable
import io.reactivex.subjects.ReplaySubject

/**
 * Created by abdulmujibaliu on 10/16/17.
 */
interface RepositoryContracts {

    interface IPlaylistRepository {
        fun getPlayVideosForChannels(channelIDs: List<String>) : ReplaySubject<VideoResult>?
    }

}