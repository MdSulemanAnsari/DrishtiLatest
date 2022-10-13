package com.application.drishtigems.VendorUser.NewViewPagerDialog

import android.content.Context
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.DataBindingUtil
import androidx.viewpager.widget.PagerAdapter
import com.application.drishtigems.Network.RetrofitObject
import com.application.drishtigems.Network.jawellerNetwork.ApiModelJeweller.GemVideosItem
import com.application.drishtigems.R
import com.application.drishtigems.databinding.AdapterViewPagerBinding
import com.application.drishtigems.ui.activity.MainActivity
import com.bumptech.glide.Glide
import com.google.android.exoplayer2.PlaybackException
import com.google.android.exoplayer2.Player
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.source.MediaSource
import com.google.android.exoplayer2.source.ProgressiveMediaSource
import com.google.android.exoplayer2.source.dash.DashMediaSource
import com.google.android.exoplayer2.upstream.DataSource
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory
import kotlinx.android.synthetic.main.adapter_view_pager.*


class NewViewPager( val context: Context, var list: ArrayList<GemVideosItem>) : PagerAdapter(), Player.Listener {
    private lateinit var simpleExoplayer: SimpleExoPlayer
    private var playbackPosition: Long = 0

    private val dataSourceFactory: DataSource.Factory by lazy {
        DefaultDataSourceFactory(context, "exoplayer-sample")
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val binding= DataBindingUtil.inflate<AdapterViewPagerBinding>(LayoutInflater.from(context), R.layout.adapter_view_pager,container,false)


        if (list[position].imagePath!=null) {
            Glide.with(context).load(RetrofitObject.IMAGE_URL + list[position].imagePath).into(binding.viewPageImage)
            binding.iconPlayVp.visibility = View.GONE


        }else{
            binding.iconPlayVp.visibility= View.VISIBLE
            Glide.with(context).load(RetrofitObject.IMAGE_URL + list[position].videoThumbnailPath).into(binding.viewPageImage)

            binding.viewPageImage.setOnClickListener{
                //  ExoDialog().show((context as MainActivity).supportFragmentManager, "MyCustomDialogFragment")
                binding.exoplayerView.visibility=View.VISIBLE
                binding.progressBars.visibility=View.VISIBLE
                initializePlayer(binding,position)
                binding.progressBars.visibility=View.GONE
            }
        }

        container.addView(binding.root)
        return binding.root

    }

    private fun initializePlayer(binding: AdapterViewPagerBinding, position: Int) {

        simpleExoplayer = SimpleExoPlayer.Builder(context).build()
        val urlList = listOf( RetrofitObject.IMAGE_URL+list[position].videoPath to "default")
        val randomUrl = urlList.random()
        preparePlayer(randomUrl.first, randomUrl.second)
        binding.exoplayerView.player = simpleExoplayer
        simpleExoplayer.seekTo(playbackPosition)
        simpleExoplayer.playWhenReady = true
        simpleExoplayer.addListener(this)
    }
    private fun buildMediaSource(uri: Uri?, type: String): MediaSource {
        return if (type == "dash") {
            DashMediaSource.Factory(dataSourceFactory)
                .createMediaSource(uri!!)
        } else {
            ProgressiveMediaSource.Factory(dataSourceFactory)
                .createMediaSource(uri!!)
        }
    }
    private fun preparePlayer(videoUrl: String, type: String) {
        val uri = Uri.parse(videoUrl)
        val mediaSource = buildMediaSource(uri, type)
        simpleExoplayer.prepare(mediaSource)
    }
    fun releasePlayer() {
        if (this::simpleExoplayer.isInitialized) {
            if (simpleExoplayer != null) {
                simpleExoplayer.playWhenReady = false
                simpleExoplayer.repeatMode
            } else {
                simpleExoplayer.isPlaying
            }
        }
    }
    override fun onPlayerError(error: PlaybackException) {
        // handle error
    }
    override fun onPlayerStateChanged(playWhenReady: Boolean, playbackState: Int) {
        if (playbackState == Player.STATE_BUFFERING)
            (context as MainActivity).progressBars?.visibility = View.VISIBLE
        else if (playbackState == Player.STATE_READY || playbackState == Player.STATE_ENDED)
            (context as MainActivity).progressBars?.visibility = View.INVISIBLE
    }
    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view == `object`
    }
    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        //cast to LinearLayout
        container.removeView(`object` as ConstraintLayout)


    }
    override fun getCount(): Int {
        return list.size
    }

}