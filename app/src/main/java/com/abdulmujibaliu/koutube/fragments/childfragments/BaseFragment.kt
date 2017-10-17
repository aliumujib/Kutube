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

    protected var parentView: MainContract.View ? = null

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater!!.inflate(R.layout.fragment_base, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        onAttachToParentFragment(parentFragment)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


    }

    // In the child fragment.
    private fun onAttachToParentFragment(fragment: Fragment) {
        try {
            parentView = fragment as MainContract.View

        } catch (e: ClassCastException) {
            throw ClassCastException(
                    fragment.toString() + " must implement MainContract.View")
        }

    }


}// Required empty public constructor
