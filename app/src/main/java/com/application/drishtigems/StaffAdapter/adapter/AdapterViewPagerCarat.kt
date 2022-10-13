package com.application.drishtigems.StaffAdapter.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.LinearLayoutCompat
import androidx.databinding.DataBindingUtil
import androidx.viewpager.widget.PagerAdapter
import com.application.drishtigems.Network.RetrofitObject
import com.application.drishtigems.Network.StaffNetwork.ApiModel.AddTocartModel.GemImagesItem
import com.application.drishtigems.Network.jawellerNetwork.ApiModelJeweller.GemVideosItem
import com.application.drishtigems.R
import com.application.drishtigems.VendorUser.ExoPlayer.ExoPlayerGem
import com.application.drishtigems.VendorUser.NewViewPagerDialog.NewDialogViewPager
import com.application.drishtigems.databinding.AdapterViewPagerCaratBinding
import com.application.drishtigems.ui.activity.MainActivity
import com.bumptech.glide.Glide

class AdapterViewPagerCarat(val context: Context, var list: ArrayList<GemVideosItem>,val id:String):PagerAdapter() {

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val binding=DataBindingUtil.inflate<AdapterViewPagerCaratBinding>(LayoutInflater.from(context), R.layout.adapter_view_pager_carat,container,false)
        if (list[position].imagePath!=null) {
            Glide.with(context).load(RetrofitObject.IMAGE_URL+list[position].imagePath).into(binding.viewPageImageCarat)
            binding.iconPlayVpS.visibility=View.GONE
        }else{
            binding.iconPlayVpS.visibility=View.VISIBLE
            Glide.with(context).load(RetrofitObject.IMAGE_URL + list[position].videoThumbnailPath).into(binding.viewPageImageCarat)
        }
        binding.viewPageImageCarat.setOnClickListener {
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
        container.removeView(`object` as LinearLayoutCompat)
    }
    override fun getCount(): Int {
       return list.size
    }
}