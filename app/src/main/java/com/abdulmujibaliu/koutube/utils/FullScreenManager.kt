package com.abdulmujibaliu.koutube.utils

import android.view.View.SYSTEM_UI_FLAG_LAYOUT_STABLE
import android.view.View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
import android.view.View.SYSTEM_UI_FLAG_FULLSCREEN
import android.view.View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
import android.view.View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
import android.view.View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
import android.app.Activity
import android.view.View


/**
 * Created by abdulmujibaliu on 10/17/17.
 */
class FullScreenManager
/**
 * @param context
 * @param views to hide/show
 */
(private val mContext: Activity, vararg views: View) {
    private val views: Array<View>

    init {
        this.views = views as Array<View>
    }

    /**
     * call this method to enter full screen
     */
    fun enterFullScreen() {
        val decorView = mContext.window.decorView

        hideSystemUI(decorView)

        for (view in views) {
            view.setVisibility(View.GONE)
            view.invalidate()
        }
    }

    /**
     * call this method to exit full screen
     */
    fun exitFullScreen() {
        val decorView = mContext.window.decorView

        showSystemUI(decorView)

        for (view in views) {
            view.setVisibility(View.VISIBLE)
            view.invalidate()
        }
    }

    // hides the system bars.
    private fun hideSystemUI(mDecorView: View) {
        mDecorView.setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        or View.SYSTEM_UI_FLAG_FULLSCREEN
                        or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY)
    }

    // This snippet shows the system bars.
    private fun showSystemUI(mDecorView: View) {
        mDecorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE)
        //        mDecorView.setSystemUiVisibility(
        //                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
        //                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
        //                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
    }
}