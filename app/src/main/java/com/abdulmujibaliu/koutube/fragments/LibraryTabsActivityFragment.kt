package com.abdulmujibaliu.koutube.fragments

import android.os.Bundle
import android.support.design.widget.CoordinatorLayout
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.abdulmujibaliu.koutube.R
import com.abdulmujibaliu.koutube.data.models.YoutubeVideo
import com.abdulmujibaliu.koutube.data.repositories.PlayListRepository
import com.abdulmujibaliu.koutube.data.repositories.contracts.RepositoryContracts
import com.abdulmujibaliu.koutube.tabsadapter.VideoTabsAdapter
import com.abdulmujibaliu.koutube.utils.ui.videodetailsview.VideoDetailsView
import com.pierfrancescosoffritti.youtubeplayer.player.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.youtubeplayer.player.YouTubePlayer
import com.pierfrancescosoffritti.youtubeplayer.player.YouTubePlayerView
import jp.bglb.bonboru.behaviors.YoutubeLikeBehavior
import kotlinx.android.synthetic.main.fragment_library_tabs.*


/**
 * A placeholder fragment containing a simple view.
 */
class LibraryTabsActivityFragment : Fragment(), MainContract.View, YoutubeLikeBehavior.OnBehaviorStateListener {

    var media: YouTubePlayerView? = null
    var description: View? = null
    val TAG = javaClass.simpleName
    var rootCordinator: CoordinatorLayout? = null
    private var player: YouTubePlayer? = null

    override fun getPresenter(): MainContract.Presenter {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_library_tabs, container, false)
    }


    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        rootCordinator = view!!.findViewById(R.id.root_view)

        val videoTabsAdapter = VideoTabsAdapter(childFragmentManager)
        toolbar.setTitle("Koutube")
        toolbar.setTitleTextColor(ContextCompat.getColor(context, R.color.colorAccent))
        container.adapter = videoTabsAdapter

        tabs.setupWithViewPager(container)


        val playListRepo: RepositoryContracts.IPlaylistRepository = PlayListRepository.getInstance()!!

        playListRepo.getPlayListsAndVideosForChannels(listOf("UCsooa4yRKGN_zEE8iknghZA"))
    }


    override fun showVideoView(video: YoutubeVideo, data: List<YoutubeVideo>) {

        media = layoutInflater.inflate(R.layout.youtubevideo_player, rootCordinator, false) as YouTubePlayerView
        description = layoutInflater.inflate(R.layout.video_description, rootCordinator, false)

        if (media?.parent != null) {
            rootCordinator?.removeView(media)
        }

        if (description?.parent != null) {
            rootCordinator?.removeView(description)
        }

        rootCordinator!!.addView(media)
        rootCordinator!!.addView(description)

        val behavior = YoutubeLikeBehavior.from(media)
        behavior?.listener = this

        val videoDetailsView: VideoDetailsView = description!!.findViewById(R.id.video_details)
        videoDetailsView.setVideos(video, data)

        Log.d(TAG, "VID ID: ${video.videoID}")
        //behavior?.state = YoutubeLikeBehavior.STATE_HIDDEN

        media!!.initialize({ initializedYouTubePlayer ->
            player = initializedYouTubePlayer

            initializedYouTubePlayer.addListener(object : AbstractYouTubePlayerListener() {
                override fun onReady() {
                    Log.d(TAG, video.videoID)
                    initializedYouTubePlayer.loadVideo(video.videoID, 0f)
                }
            })
        }, true)

        //behavior?.state = YoutubeLikeBehavior.STATE_EXPANDED
    }


    override fun onBehaviorStateChanged(newState: Long) {
        if (newState == YoutubeLikeBehavior.STATE_TO_RIGHT || newState == YoutubeLikeBehavior.STATE_TO_LEFT) {
            rootCordinator?.removeView(media)
            rootCordinator?.removeView(description)
            val behavior = YoutubeLikeBehavior.from(media)
        }
    }
}
