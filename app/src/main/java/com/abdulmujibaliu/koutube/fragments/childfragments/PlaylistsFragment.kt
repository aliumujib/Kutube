package com.abdulmujibaliu.koutube.fragments.childfragments

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.abdulmujibaliu.koutube.R

class PlaylistsFragment : BaseFragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (arguments != null) {

        }
    }


    override fun onAttach(context: Context?) {
        super.onAttach(context)

    }

    companion object {

        fun newInstance(): PlaylistsFragment {
            val fragment = PlaylistsFragment()
            val args = Bundle()
            fragment.arguments = args
            return fragment
        }
    }
}
