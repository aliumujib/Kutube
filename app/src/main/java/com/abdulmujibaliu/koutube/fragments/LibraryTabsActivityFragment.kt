package com.abdulmujibaliu.koutube.fragments

import android.support.v4.app.Fragment
import android.os.Bundle
import android.support.design.widget.CoordinatorLayout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import com.abdulmujibaliu.koutube.R
import com.abdulmujibaliu.koutube.data.models.YoutubeVideo
import com.abdulmujibaliu.koutube.tabsadapter.VideoTabsAdapter
import com.abdulmujibaliu.koutube.utils.FullScreenManager
import com.pierfrancescosoffritti.youtubeplayer.player.YouTubePlayerView
import jp.bglb.bonboru.behaviors.YoutubeLikeBehavior
import kotlinx.android.synthetic.main.fragment_library_tabs.*
import kotlinx.android.synthetic.main.video_description.*
import kotlinx.android.synthetic.main.youtubevideo_player.*
import kotterknife.bindView
import com.pierfrancescosoffritti.youtubeplayer.player.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.youtubeplayer.player.YouTubePlayer
import com.pierfrancescosoffritti.youtubeplayer.player.YouTubePlayerInitListener
import android.content.pm.ActivityInfo
import android.support.v4.content.ContextCompat
import android.util.Log
import com.pierfrancescosoffritti.youtubeplayer.player.YouTubePlayerFullScreenListener




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
        toolbar.setTitle("Kutube")
        container.adapter = videoTabsAdapter

        tabs.setupWithViewPager(container)

    }



    override fun showVideoView(video: YoutubeVideo) {
        media = layoutInflater.inflate(R.layout.youtubevideo_player, rootCordinator, false) as YouTubePlayerView
        description = layoutInflater.inflate(R.layout.video_description, rootCordinator, false)

        val fullScreenManager = FullScreenManager(activity)

        rootCordinator!!.addView(media)
        rootCordinator!!.addView(description)

        val behavior = YoutubeLikeBehavior.from(media)
        behavior?.listener = this

        media!!.initialize({ initializedYouTubePlayer ->
            player = initializedYouTubePlayer

            initializedYouTubePlayer.addListener(object : AbstractYouTubePlayerListener() {
                override fun onReady() {
                    Log.d(TAG, video.videoID)
                    initializedYouTubePlayer.loadVideo("g_hg9dbCQy8", 0f)
                }

            })
        }, true)


        media!!.addFullScreenListener(object : YouTubePlayerFullScreenListener {
            override fun onYouTubePlayerEnterFullScreen() {
                activity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE)
                fullScreenManager.enterFullScreen()

                media!!.getPlayerUIController().setCustomAction1(ContextCompat.getDrawable(activity, R.drawable.ic_pause_36dp),
                        { if (player != null) player?.pause() })
            }

            override fun onYouTubePlayerExitFullScreen() {
                activity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE)
                fullScreenManager.exitFullScreen()

                media!!.getPlayerUIController().showCustomAction1(false)
            }
        })

    }


    override fun onBehaviorStateChanged(newState: Long) {
        if (newState == YoutubeLikeBehavior.STATE_TO_RIGHT || newState == YoutubeLikeBehavior.STATE_TO_LEFT) {
            root_view.removeView(media)
            root_view.removeView(video_desc_text)
        }
    }
}
