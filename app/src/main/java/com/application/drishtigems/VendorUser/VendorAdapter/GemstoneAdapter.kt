package com.application.drishtigems.VendorUser.VendorAdapter

import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.application.drishtigems.Network.RetrofitObject
import com.application.drishtigems.Network.StaffNetwork.ApiModel.CategoryOnlineBringModel.CategoryOnlineBringModelItem
import com.application.drishtigems.R
import com.application.drishtigems.VendorUser.fragment.GemstoneHome.HomeGemstones.NaturalEmeraldStockVendor
import com.application.drishtigems.databinding.GemstoneAdapterBinding
import com.application.drishtigems.ui.activity.MainActivity
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.gemstone_adapter.view.*

class GemstoneAdapter(var context: Context, var listGemstone: ArrayList<CategoryOnlineBringModelItem>?):RecyclerView.Adapter<GemstoneAdapter.ViewHolder>() {
    class ViewHolder(binding: GemstoneAdapterBinding):RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding= DataBindingUtil.inflate<GemstoneAdapterBinding>(LayoutInflater.from(parent.context), R.layout.gemstone_adapter,parent,false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data=listGemstone!![position]
        holder.itemView.apply {
            tvGemstone.text=data.title
            Glide.with(context).load(RetrofitObject.IMAGE_URL+data.image).into(imageGemstone)

        }
        holder.itemView.setOnClickListener {
            holder.itemView.firstCL.setBackgroundResource(R.color.amountcolor)
            val naturalEmeraldStockVendor= NaturalEmeraldStockVendor()
            val bundle = Bundle()
            bundle.putString("tittle", data.title)
            bundle.putInt("id", data.id)
            naturalEmeraldStockVendor.arguments = bundle
            Handler().postDelayed({
                (context as MainActivity).supportFragmentManager.beginTransaction().replace(R.id.mainLayout,naturalEmeraldStockVendor).addToBackStack("").commit()
                true
                true
            },200)
        }

    }

    override fun getItemCount(): Int {
        return listGemstone!!.size
    }
}