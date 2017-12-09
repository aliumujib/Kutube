package com.abdulmujibaliu.koutube.fragments.mvp

/**
 * Created by ABDUL-MUJEEB ALIU on 09/12/2017.
 */
interface BaseContract {

    interface BaseView<T> {

        fun setData(data: T)

        fun showLoading()

        fun hideLoading()

        fun showError(error: String)

        fun showEmpty()

        fun showMessage(message: String)

    }

    interface BasePresenter {

    }

}