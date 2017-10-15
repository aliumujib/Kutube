package com.abdulmujibaliu.koutube.tabsadapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.abdulmujibaliu.koutube.fragments.childfragments.PlaylistsFragment
import com.abdulmujibaliu.koutube.fragments.childfragments.VideosFragment

/**
 * Created by abdulmujibaliu on 10/15/17.
 */

class VideoTabsAdapter(fragmentManager: FragmentManager) : FragmentPagerAdapter(fragmentManager) {
    override fun getCount(): Int {
        return 2
    }

    val fragments : List<Fragment> = listOf(VideosFragment.newInstance(), PlaylistsFragment.newInstance())

    override fun getItem(position: Int): Fragment {
        return if (position == 0) {
            fragments[0]
        } else {
            fragments[1]
        }
    }

    override fun getPageTitle(position: Int): String {
        return if(position == 0){
            "VIDEOS"
        }else{
            "PLAYLISTS"
        }
    }

}