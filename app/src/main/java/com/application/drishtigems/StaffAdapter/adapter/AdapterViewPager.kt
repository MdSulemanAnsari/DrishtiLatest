package com.application.drishtigems.StaffAdapter.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import androidx.appcompat.widget.LinearLayoutCompat
import androidx.databinding.DataBindingUtil

import androidx.viewpager.widget.PagerAdapter
import com.application.drishtigems.R
import com.application.drishtigems.databinding.AdapterViewPagerBinding
import com.application.drishtigems.StaffAdapter.model.ModelViewPager


class AdapterViewPager(val context: Context, var list:ArrayList<ModelViewPager>) : PagerAdapter() {

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val binding=DataBindingUtil.inflate<AdapterViewPagerBinding>(LayoutInflater.from(context), R.layout.adapter_view_pager,container,false)

        binding.viewPageImage.setImageBitmap(list[position].viewPageImg)
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
