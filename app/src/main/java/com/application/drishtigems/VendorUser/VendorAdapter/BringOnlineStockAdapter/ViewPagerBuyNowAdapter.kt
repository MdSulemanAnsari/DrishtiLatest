package com.application.drishtigems.VendorUser.VendorAdapter.BringOnlineStockAdapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.viewpager.widget.PagerAdapter
import com.application.drishtigems.Network.RetrofitObject
import com.application.drishtigems.Network.jawellerNetwork.ApiModelJeweller.GemVideosItem
import com.application.drishtigems.R
import com.application.drishtigems.VendorUser.ExoPlayer.ExoPlayerGem
import com.application.drishtigems.VendorUser.ExoPlayerDialog.ExoDialog
import com.application.drishtigems.databinding.AdapterViewPagerBinding
import com.application.drishtigems.ui.activity.MainActivity
import com.application.drishtigems.ui.fragment.DialogEmerald
import com.bumptech.glide.Glide
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import com.application.drishtigems.VendorUser.NewViewPagerDialog.NewDialogViewPager

class ViewPagerBuyNowAdapter(val context: Context, var list: ArrayList<GemVideosItem>,val id:String) : PagerAdapter() {

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val binding= DataBindingUtil.inflate<AdapterViewPagerBinding>(LayoutInflater.from(context), R.layout.adapter_view_pager,container,false)

        if (list[position].imagePath!=null) {
            Glide.with(context).load(RetrofitObject.IMAGE_URL+list[position].imagePath).into(binding.viewPageImage)
            binding.iconPlayVp.visibility=View.GONE
        }else{
            binding.iconPlayVp.visibility=View.VISIBLE
            Glide.with(context).load(RetrofitObject.IMAGE_URL + list[position].videoThumbnailPath).into(binding.viewPageImage)
        }
        binding.viewPageImage.setOnClickListener {
            NewDialogViewPager(id,position).show((context as MainActivity).supportFragmentManager, "MyCustomDialogFragment")
        }


        container.addView(binding.root)
        return binding.root
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