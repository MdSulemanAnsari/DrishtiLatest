package com.application.drishtigems.VendorUser.VendorAdapter.PurchaseHistory.PurchaseHistoryAdapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.LinearLayoutCompat
import androidx.databinding.DataBindingUtil
import androidx.viewpager.widget.PagerAdapter
import com.application.drishtigems.R
import com.application.drishtigems.StaffAdapter.model.ModelViewPagerCarat
import com.application.drishtigems.databinding.AdapterViewPagerCaratBinding
import com.bumptech.glide.Glide

class PagerAdapterMyPurchase (var context:Context,var listPurchase:ArrayList<ModelViewPagerCarat>):PagerAdapter(){

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val binding= DataBindingUtil.inflate<AdapterViewPagerCaratBinding>(LayoutInflater.from(context), R.layout.adapter_view_pager_carat,container,false)
        binding.viewPageImageCarat.setImageBitmap(listPurchase[position].viewPageCaratImg)
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
      return listPurchase.size
    }
}