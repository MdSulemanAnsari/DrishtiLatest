package com.application.drishtigems.VendorUser.NewViewPagerDialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.DialogFragment
import com.application.drishtigems.Network.RetrofitInstance
import com.application.drishtigems.Network.StaffNetwork.ApiModel.AddTocartModel.AddCartNew
import com.application.drishtigems.Network.jawellerNetwork.ApiModelJeweller.GemVideosItem
import com.application.drishtigems.R
import com.application.drishtigems.databinding.FragmentNewDialogViewPagerBinding
import com.application.drishtigems.prefs.AppPrefs
import com.google.android.exoplayer2.Player

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class NewDialogViewPager (private  var id:String,private  var position:Int): DialogFragment(),
    Player.Listener {
    private var newViewPager: NewViewPager? = null
    var viewCaratListVendor: AddCartNew? = null
    var imageListGemsVendor: ArrayList<GemVideosItem> = ArrayList()
    var videoListGemsVendor: ArrayList<GemVideosItem> = ArrayList()
    var mergeListJeweller: ArrayList<GemVideosItem> = ArrayList()

/*
    private lateinit var simpleExoplayer: SimpleExoPlayer
    private var playbackPosition: Long = 0
    private val mp4Url = RetrofitObject.IMAGE_URL +"/media/gem_videos/baf4e15f-f8c2-49ca-bbed-ff3db9ce53bf.mp4"
    //  private val dashUrl = "https://storage.googleapis.com/wvmedia/clear/vp9/tears/tears_uhd.mpd"
    private val urlList = listOf(mp4Url to "default")

    private val dataSourceFactory: DataSource.Factory by lazy {
        DefaultDataSourceFactory(requireActivity(), "exoplayer-sample")
    }*/

lateinit var binding:FragmentNewDialogViewPagerBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
         dialog!!.setCanceledOnTouchOutside(true)
        binding=DataBindingUtil.inflate(inflater,R.layout.fragment_new_dialog_view_pager, container, false)
        return binding.root
    }

    override fun onStart() {
        super.onStart()
      // initializePlayer()
        val width = ViewGroup.LayoutParams.MATCH_PARENT
        val height = (resources.displayMetrics.heightPixels * 0.40).toInt()
        dialog!!.window?.setLayout(width, ViewGroup.LayoutParams.WRAP_CONTENT)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
       
    /*    binding.dismissExoPlayer.setOnClickListener {
            dialog!!.dismiss()
        }*/
        apiBuyCartGemItem()
    }
    private fun apiBuyCartGemItem() {
        val appPrefs = AppPrefs(requireContext())

        val token = "Token" + " " + appPrefs.getToken("token")
        val retrofitData = RetrofitInstance().retrofitBuilder.getFilterData(token,id)
        //enqueue method
        retrofitData.enqueue(object : Callback<AddCartNew> {

            override fun onResponse(call: Call<AddCartNew>, response: Response<AddCartNew>) {
                if (response.code() == 200) {
                    mergeListJeweller.clear()
                    imageListGemsVendor.clear()
                    videoListGemsVendor.clear()
                    viewCaratListVendor = response.body()!!
                    imageListGemsVendor.addAll(response.body()!!.gemImages)
                    videoListGemsVendor.addAll(response.body()!!.gemVideos)
                    mergeListJeweller.addAll(imageListGemsVendor)
                    mergeListJeweller.addAll(videoListGemsVendor)
                    callViewPagerBuyNow()
                    //  binding.progressBarAddCart.visibility=View.GONE
                    Toast.makeText(requireActivity(), "Successful", Toast.LENGTH_SHORT).show()
                } else {
                    //  binding.progressBarAddCart.visibility=View.GONE
                    Toast.makeText(requireActivity(), "Error 400", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<AddCartNew>, t: Throwable) {
                Toast.makeText(requireActivity(), "Error! " + t.message, Toast.LENGTH_SHORT).show()
            }
        })
    }
    private fun callViewPagerBuyNow() {
        newViewPager = NewViewPager(requireActivity(), (mergeListJeweller ?: arrayListOf()) as ArrayList<GemVideosItem>)
        binding.newViewPagerView.post {
            binding.newViewPagerView.setCurrentItem(position, true)
        }
        binding.newViewPagerView.adapter = newViewPager

        binding.newViewPagerView.setOnScrollChangeListener(object:View.OnScrollChangeListener{
            override fun onScrollChange(p0: View?, p1: Int, p2: Int, p3: Int, p4: Int) {
                newViewPager?.releasePlayer()
            }

        })
    }
/*    override fun onStop() {
        super.onStop()
        releasePlayer()
    }

    fun initializePlayer() {
        simpleExoplayer = SimpleExoPlayer.Builder(requireContext()).build()
        val randomUrl = urlList.random()
        preparePlayer(randomUrl.first, randomUrl.second)
        exoplayerView?.player = simpleExoplayer
        simpleExoplayer.seekTo(playbackPosition)
        simpleExoplayer.playWhenReady = true
        simpleExoplayer.addListener(this)
    }

    private fun buildMediaSource(uri: Uri, type: String): MediaSource {
        return if (type == "dash") {
            DashMediaSource.Factory(dataSourceFactory)
                .createMediaSource(uri)
        } else {
            ProgressiveMediaSource.Factory(dataSourceFactory)
                .createMediaSource(uri)
        }
    }

    private fun preparePlayer(videoUrl: String, type: String) {
        val uri = Uri.parse(videoUrl)
        val mediaSource = buildMediaSource(uri, type)
        simpleExoplayer.prepare(mediaSource)
    }

    private fun releasePlayer() {
        playbackPosition = simpleExoplayer.currentPosition
        simpleExoplayer.release()
    }

    override fun onPlayerError(error: PlaybackException) {
        // handle error
    }

    override fun onPlayerStateChanged(playWhenReady: Boolean, playbackState: Int) {
        if (playbackState == Player.STATE_BUFFERING){}
         //   binding.progressBar.visibility = View.VISIBLE
        else if (playbackState == Player.STATE_READY || playbackState == Player.STATE_ENDED){}
        //    binding.progressBar.visibility = View.INVISIBLE
    }*/
}