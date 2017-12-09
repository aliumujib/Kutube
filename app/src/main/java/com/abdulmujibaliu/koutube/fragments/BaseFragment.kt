package com.abdulmujibaliu.koutube.fragments


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.abdulmujibaliu.koutube.R
import com.abdulmujibaliu.koutube.data.models.YoutubeVideo
import com.abdulmujibaliu.koutube.data.repositories.PlayListRepository
import com.abdulmujibaliu.koutube.data.repositories.contracts.RepositoryContracts
import com.abdulmujibaliu.koutube.fragments.main.VideoClickListener
import com.abdulmujibaliu.koutube.fragments.mvp.BaseContract
import com.abdulmujibaliu.koutube.utils.MessageUtils


/**
 * A simple [Fragment] subclass.
 */
abstract class BaseFragment : Fragment(), VideoClickListener {
    val TAG: String = javaClass.simpleName

    override fun onVideoClicked(youtubeVideo: YoutubeVideo, data: List<YoutubeVideo>) {
        parentView?.showVideoView(youtubeVideo, data)
    }

    protected var parentView: MainContract.View? = null

    val dataSource: RepositoryContracts.IDataSource = PlayListRepository.getInstance()!!

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater!!.inflate(R.layout.fragment_base, container, false)
    }


    override fun onResume() {
        super.onResume()

    }

    fun showLoading() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    fun hideLoading() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    fun showError(error: String) {
        MessageUtils.errorMessage(activity, error)
    }

    fun showEmpty() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    fun showMessage(message: String) {
        MessageUtils.warningMessage(activity, message)
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
