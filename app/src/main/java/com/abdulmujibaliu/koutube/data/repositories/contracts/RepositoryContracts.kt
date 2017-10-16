package com.abdulmujibaliu.koutube.data.repositories.contracts

import io.reactivex.Observable

/**
 * Created by abdulmujibaliu on 10/16/17.
 */
interface RepositoryContracts {

    interface IPlaylistRepository {
        fun getPlayVideosForChannels(channelIDs: List<String>) : Observable<String>
    }

}