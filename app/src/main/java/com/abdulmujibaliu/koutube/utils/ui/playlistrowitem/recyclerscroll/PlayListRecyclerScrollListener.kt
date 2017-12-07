package com.abdulmujibaliu.koutube.utils.ui.playlistrowitem.recyclerscroll

import android.support.v7.widget.RecyclerView

/**
 * Created by ABDUL-MUJEEB ALIU on 07/12/2017.
 */
abstract class PlayListRecyclerScrollListener : RecyclerView.OnScrollListener() {

    private val HIDE_THRESHOLD = 100f
    private val SHOW_THRESHOLD = 50f

    var scrollDist = 0
    var isVisible = true

    override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)

        //  Check scrolled distance against the minimum
        if (isVisible && scrollDist > HIDE_THRESHOLD) {
            //  Hide fab & reset scrollDist
            hide();
            scrollDist = 0
            isVisible = false
        }
        //  -MINIMUM because scrolling up gives - dy values
        else if (!isVisible && scrollDist < -SHOW_THRESHOLD) {
            //  Show fab & reset scrollDist
            show()

            scrollDist = 0
            isVisible = true
        }

        //  Whether we scroll up or down, calculate scroll distance
        if ((isVisible && dx > 0) || (!isVisible && dx < 0)) {
            scrollDist += dx
        }
    }

    abstract fun show()
    abstract fun hide()
}