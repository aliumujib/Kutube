package com.abdulmujibaliu.koutube.fragments.childfragments


import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.View
import com.abdulmujibaliu.koutube.data.PlaylistGetterInterface
import com.abdulmujibaliu.koutube.data.RetrofitFactory
import com.abdulmujibaliu.koutube.fragments.rvadapter.VideoRVAdapter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.Consumer
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_base.*


class VideosFragment : BaseFragment() {

    var videosRVAdapter : VideoRVAdapter? = null


    val TAG: String = javaClass.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null) {

        }
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        videosRVAdapter = VideoRVAdapter(context)
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = videosRVAdapter
        videosRVAdapter?.notifyDataSetChanged()


      var serviceInstance =  RetrofitFactory.getInstance().create(PlaylistGetterInterface::class.java)

        serviceInstance.getPlaylistItems("PLP2VuCguZpsnJadOEvHTONtjpSdKMykRp")
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe({
                    data->

                    Log.d(TAG, data)
                }, {
                    error->

                    error.printStackTrace()
                })


        serviceInstance.getPlaylistsForChannel("UC_x5XG1OV2P6uZZ5FSM9Ttw")
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe({
                    data ->
                    run {
                        Log.d(TAG, data.ids.size.toString())
                    }
                }, {
                    t: Throwable? ->
                    t?.printStackTrace()
                })
    }


    companion object {

        fun newInstance(): VideosFragment {
            val fragment = VideosFragment()
            val args = Bundle()
            fragment.arguments = args
            return fragment
        }
    }

}
