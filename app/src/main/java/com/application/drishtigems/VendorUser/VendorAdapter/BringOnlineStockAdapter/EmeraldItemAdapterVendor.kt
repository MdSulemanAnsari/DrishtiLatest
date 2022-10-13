package com.application.drishtigems.VendorUser.VendorAdapter.BringOnlineStockAdapter

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.application.drishtigems.Network.RetrofitObject
import com.application.drishtigems.Network.StaffNetwork.ApiModel.natural.DataItem
import com.application.drishtigems.R
import com.application.drishtigems.VendorUser.ExoPlayer.ExoPlayerGem
import com.application.drishtigems.VendorUser.fragment.GemstoneHome.HomeGemstones.BuyNowCart
import com.application.drishtigems.VendorUser.fragment.GemstoneHome.HomeGemstones.NaturalEmeraldStockVendor
import com.application.drishtigems.databinding.EmeraldItemAdapterVendorBinding
import com.application.drishtigems.ui.activity.MainActivity
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.adapter_data_bring_online.view.*
import kotlinx.android.synthetic.main.emerald_item_adapter_vendor.view.*
import kotlinx.android.synthetic.main.emerald_item_adapter_vendor.view.tvOriginValueVendor
import kotlinx.android.synthetic.main.gemstone_adapter.view.*

class EmeraldItemAdapterVendor(var fragmentNEV:NaturalEmeraldStockVendor,var context: Context, var listEmeraldVendor: ArrayList<DataItem>): RecyclerView.Adapter<EmeraldItemAdapterVendor.ViewHolder>() {
    class ViewHolder(binding: EmeraldItemAdapterVendorBinding): RecyclerView.ViewHolder(binding.root){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding= DataBindingUtil.inflate<EmeraldItemAdapterVendorBinding>(LayoutInflater.from(parent.context), R.layout.emerald_item_adapter_vendor,parent,false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data=listEmeraldVendor[position]
        holder.itemView.apply {
            Glide.with(context).load(RetrofitObject.IMAGE_URL+data.category.image).into(imageStockVendor)
            tvEmeraldNameVendor.text=data.name
            tvSkuValueVendor.text=data.sku
            tvOriginValueVendor.text= data.origin.name
            tvCaratDollarVendor.text=data.price

            if (listEmeraldVendor[position].addedToWishlist){
                holder.itemView.imageHeartSold.setImageDrawable(ContextCompat.getDrawable(context,R.drawable.red_heart))
            }else{
                holder.itemView.imageHeartSold.setImageDrawable(ContextCompat.getDrawable(context,R.drawable.heart))
            }
        }
        holder.itemView.setOnClickListener {
            holder.itemView.constAnimate.setBackgroundResource(R.color.amountcolor)
            val buyNowCart= BuyNowCart()
            val bundle= Bundle()
            bundle.putString("id", data.id)
            buyNowCart.arguments=bundle
            Handler().postDelayed({
                (context as MainActivity).supportFragmentManager.beginTransaction().replace(R.id.mainLayout,buyNowCart).addToBackStack("").commit()
                true
                true
            },200)

        }
        holder.itemView.playIconNE.setOnClickListener {
             val exoPlayerGem= ExoPlayerGem()
            (context as MainActivity).supportFragmentManager.beginTransaction().replace(R.id.mainLayout,exoPlayerGem).addToBackStack("").commit()
        }
        holder.itemView.imageHeartSold.setOnClickListener {

            if (listEmeraldVendor[position].addedToWishlist){
                fragmentNEV.removeFromListApiWishList(listEmeraldVendor[position].id.toInt(),position)
                listEmeraldVendor[position].addedToWishlist = false

            }
            else{
                fragmentNEV.addToWishListApiHit(listEmeraldVendor[position].id.toInt(),position)
                listEmeraldVendor[position].addedToWishlist =true
                holder.itemView.imageHeartSold.setImageDrawable(ContextCompat.getDrawable(context,R.drawable.red_heart))

            }
        }
    }
    override fun getItemCount(): Int {
        return listEmeraldVendor.size
    }
}