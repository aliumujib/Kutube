package com.abdulmujibaliu.koutube.utils.ui.playlistrowitem

import android.content.Context
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.TextView
import android.view.LayoutInflater
import android.util.AttributeSet
import android.view.View
import android.widget.LinearLayout
import com.abdulmujibaliu.koutube.R
import com.abdulmujibaliu.koutube.data.models.PlayListItem
import com.abdulmujibaliu.koutube.data.models.YoutubeVideo


/**
 * Created by abdulmujibaliu on 10/17/17.
 */

/*
internal class MicrositeRowView : LinearLayout {


    private var mSectionMoreClickListener: MicrositeRecyclerAdapter.SectionMoreClickListener? = null
    private var sectionItem: YoutubeVideo? = null


    private val TAG = javaClass.simpleName
    private var mView: View? = null
    private var mSectionTitleView: TextView? = null
    private var mSectionDescription: TextView? = null
    private var mSeeMoreBtn: TextView? = null
    private var mYoutubeVideosRV: RecyclerView? = null

    protected var mAttachmentsAdapter: ProductItemRecyclerViewAdapter? = null
    protected var mLayoutManager: RecyclerView.LayoutManager? = null

    private var sectionTitle: String? = null
    protected var mDataSource: List<YoutubeVideo> = ArrayList()

    constructor(context: Context) : super(context) {}

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {

        val a = context.obtainStyledAttributes(attrs,
                R.styleable.PlayListRowView, 0, 0)


        sectionTitle = a.getString(R.styleable.PlayListRowView_section_title)

        a.recycle()

        val inflater = context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

        mView = inflater.inflate(R.layout.playlist_item_row, this, true)

        mSectionTitleView = mView.findViewById(R.id.section_title)
        mYoutubeVideosRV = mView.findViewById(R.id.section_items_recycler)

        initRV()

        mSeeMoreBtn.setOnClickListener { v -> mSectionMoreClickListener!!.onSectionItemClick(sectionItem) }

        if (sectionTitle != null && !sectionTitle!!.matches("".toRegex()))
            mSectionTitleView.text = sectionTitle

        if (sectionDescription != null && !sectionDescription!!.matches("".toRegex()))
            mSectionDescription.text = sectionDescription


    }

    fun getSectionDescription(): String? {
        return sectionDescription
    }

    fun setSectionDescription(sectionDescription: String) {
        this.sectionDescription = sectionDescription
        mSectionDescription.text = sectionDescription
    }

    fun setOnMoreClickListener(sectionMoreClickListener: MicrositeRecyclerAdapter.SectionMoreClickListener) {

        mSectionMoreClickListener = sectionMoreClickListener
    }

    fun getSectionTitle(): String? {
        return sectionTitle
    }

    fun setSectionTitle(sectionTitle: String) {
        this.sectionTitle = sectionTitle
        mSectionTitleView.text = sectionTitle
    }

    fun getmDataSource(): List<YoutubeVideo> {
        return mDataSource
    }

    fun setmDataSource(youtubeVideos: MutableList<YoutubeVideo>) {
        this.mDataSource = youtubeVideos
        this.mAttachmentsAdapter.addNewData(youtubeVideos)
        mAttachmentsAdapter.reloadData()
    }

    protected fun initRV() {
        mLayoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        mProductItemsRV.layoutManager = mLayoutManager
        mProductItemsRV.setHasFixedSize(true)
        mProductItemsRV.isNestedScrollingEnabled = false
        mAttachmentsAdapter = ProductItemRecyclerViewAdapter(context, mDataSource) { productItem -> }
        mProductItemsRV.adapter = mAttachmentsAdapter
        mAttachmentsAdapter.reloadData()
    }

    fun setSectionItem(sectionItem: PlayListItem) {
        this.sectionItem = sectionItem
        //this.setmDataSource(sectionItem.getProductItems())
        //this.setSectionTitle(sectionItem.get())
        //this.setSectionDescription(sectionItem.getSectionDesc())
    }

    companion object {
        var INVALID_REQUEST_CODE_NUMBER = -2
    }
}*/
