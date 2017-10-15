package com.abdulmujibaliu.koutube.fragments

/**
 * Created by abdulmujibaliu on 10/15/17.
 */
interface MainContract {

    interface View{
        fun getPresenter  () : Presenter
    }

    interface Presenter

}