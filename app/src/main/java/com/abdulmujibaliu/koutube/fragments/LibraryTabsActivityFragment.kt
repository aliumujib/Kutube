package com.abdulmujibaliu.koutube.fragments

import android.content.Context
import android.os.Bundle
import android.support.design.widget.CoordinatorLayout
import android.support.design.widget.NavigationView
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.content.ContextCompat
import android.support.v4.view.GravityCompat
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.abdulmujibaliu.koutube.R
import com.abdulmujibaliu.koutube.data.KutConstants
import com.abdulmujibaliu.koutube.data.models.YoutubeVideo
import com.abdulmujibaliu.koutube.data.repositories.PlayListRepository
import com.abdulmujibaliu.koutube.data.repositories.contracts.RepositoryContracts
import com.abdulmujibaliu.koutube.fragments.childfragments.PlaylistsFragment
import com.abdulmujibaliu.koutube.fragments.childfragments.VideosFragment
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
        toolbar.setTitle("Search")
        var appCompatactivity: AppCompatActivity? = activity as AppCompatActivity
        appCompatactivity!!.setSupportActionBar(toolbar)

        val playListRepo: RepositoryContracts.IPlaylistRepository = PlayListRepository.getInstance()!!
        playListRepo.getPlayListsAndVideosForChannels(listOf("UCsooa4yRKGN_zEE8iknghZA"))

        val drawer = view.findViewById<DrawerLayout>(R.id.drawer_layout)
        val toggle = ActionBarDrawerToggle(
                activity, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer.setDrawerListener(toggle)
        toggle.syncState()

        nav_view.setNavigationItemSelectedListener { item ->

            if (item.itemId == R.id.nav_home) {
                replaceFragmentWithorWithoutBackState(VideosFragment.newInstance(), context, KutConstants.REPLACE_WITH_BACK_STATE, R.id.container_frame)
            } else if (item.itemId == R.id.nav_playlist) {
                replaceFragmentWithorWithoutBackState(PlaylistsFragment.newInstance(), context, KutConstants.REPLACE_WITH_BACK_STATE, R.id.container_frame)
            } else if (item.itemId == R.id.nav_downloads) {

            }

            if (drawer.isDrawerOpen(GravityCompat.START)) run { drawer.closeDrawer(GravityCompat.START) }

            false
        }

        replaceFragmentWithorWithoutBackState(VideosFragment.newInstance(), context, KutConstants.REPLACE_WITH_BACK_STATE, R.id.container_frame)


    }


    fun replaceFragmentWithorWithoutBackState(fragment: Fragment, context: Context, mode: Int, resID: Int) {
        val backStateName = fragment.javaClass.name

        val manager = (context as AppCompatActivity).supportFragmentManager
        val fragmentPopped = manager.popBackStackImmediate(backStateName, FragmentManager.POP_BACK_STACK_INCLUSIVE)

        val ft = manager.beginTransaction()
        ft.replace(resID, fragment, getTagForFragment(fragment))
        if (mode == KutConstants.REPLACE_WITH_BACK_STATE) {
            ft.addToBackStack(backStateName)
        } else {

        }
        ft.commit()
    }


    fun getTagForFragment(frag: Fragment): String {
        return frag.javaClass.simpleName
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
