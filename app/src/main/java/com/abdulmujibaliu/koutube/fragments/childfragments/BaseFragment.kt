package com.abdulmujibaliu.koutube.fragments.childfragments


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.abdulmujibaliu.koutube.R
import com.abdulmujibaliu.koutube.fragments.MainContract


/**
 * A simple [Fragment] subclass.
 */
abstract class BaseFragment : Fragment() {

    protected val parentView: MainContract.View ? = null

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater!!.inflate(R.layout.fragment_base, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


    }


}// Required empty public constructor
