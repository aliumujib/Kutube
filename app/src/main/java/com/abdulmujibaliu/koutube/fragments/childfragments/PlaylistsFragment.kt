package com.abdulmujibaliu.koutube.fragments.childfragments

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.abdulmujibaliu.koutube.R

class PlaylistsFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (arguments != null) {

        }
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater!!.inflate(R.layout.fragment_playlists, container, false)
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
