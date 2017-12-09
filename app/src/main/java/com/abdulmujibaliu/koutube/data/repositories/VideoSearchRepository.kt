package com.abdulmujibaliu.koutube.data.repositories

import com.abdulmujibaliu.koutube.data.models.VideoResult
import com.abdulmujibaliu.koutube.data.repositories.contracts.RepositoryContracts
import io.reactivex.Observable
import io.reactivex.subjects.ReplaySubject

/**
 * Created by ABDUL-MUJEEB ALIU on 09/12/2017.
 */
class VideoSearchRepository: RepositoryContracts.IVideoSearchRepository {

    var videosListSubject = ReplaySubject.create<VideoResult>()

    override fun getResultObservable(): Observable<VideoResult>? {
        return videosListSubject
    }

    override fun searchForVideo(filters: HashMap<String, Any>) {

    }

}