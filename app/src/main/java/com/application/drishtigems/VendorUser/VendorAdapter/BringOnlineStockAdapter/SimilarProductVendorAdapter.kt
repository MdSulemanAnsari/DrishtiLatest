package com.application.drishtigems.VendorUser.VendorAdapter.BringOnlineStockAdapter


import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.application.drishtigems.Network.RetrofitObject
import com.application.drishtigems.Network.StaffNetwork.ApiModel.similarProductsResponse.DataItem
import com.application.drishtigems.R
import com.application.drishtigems.VendorUser.ExoPlayer.ExoPlayerGem
import com.application.drishtigems.VendorUser.fragment.GemstoneHome.HomeGemstones.BuyNowCart
import com.application.drishtigems.databinding.SimilarProductVendorAdapterBinding
import com.application.drishtigems.ui.activity.MainActivity
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.emerald_item_adapter_vendor.view.*
import kotlinx.android.synthetic.main.emerald_item_adapter_vendor.view.tvOriginValueVendor
import kotlinx.android.synthetic.main.similar_product_vendor_adapter.view.*

class SimilarProductVendorAdapter(var fragmentHit:BuyNowCart,val context: Context,var listEmeraldVendor: ArrayList<DataItem> ): RecyclerView.Adapter<SimilarProductVendorAdapter.ViewHolder>() {
    class ViewHolder(binding: SimilarProductVendorAdapterBinding): RecyclerView.ViewHolder(binding.root){

    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding= DataBindingUtil.inflate<SimilarProductVendorAdapterBinding>(LayoutInflater.from(parent.context), R.layout.similar_product_vendor_adapter,parent,false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data=listEmeraldVendor[position]
        holder.itemView.apply {
            Glide.with(context).load(RetrofitObject.IMAGE_URL+data.category.image).into(imageStockVendorSp)
            tvEmeraldNameVendorSp.text=data.name
            tvSkuValueVendorSp.text=data.sku
            tvOriginValueVendor.text= data.origin.name
            tvCaratDollarVendorSp.text=data.price

            if (listEmeraldVendor[position].addedToWishlist){
                holder.itemView.imageHeartSoldBtn.setImageDrawable(ContextCompat.getDrawable(context,R.drawable.red_heart))
            }else{
                holder.itemView.imageHeartSoldBtn.setImageDrawable(ContextCompat.getDrawable(context,R.drawable.bg_heart_shadow))
            }
        }
        holder.itemView.imageHeartSoldBtn?.setOnClickListener {

        if (listEmeraldVendor[position].addedToWishlist){
            fragmentHit.removeFromListApiWishList(listEmeraldVendor[position].id)
            listEmeraldVendor[position].addedToWishlist = false
        }
        else{
            fragmentHit.addToWishListApiHit(listEmeraldVendor[position].id)
            listEmeraldVendor[position].addedToWishlist =true
            holder.itemView.imageHeartSoldBtn.setImageDrawable(ContextCompat.getDrawable(context,R.drawable.red_heart))
        }
        }
        holder.itemView.playIconSP.setOnClickListener {
            val exoPlayerGem= ExoPlayerGem()
            (context as MainActivity).supportFragmentManager.beginTransaction().replace(R.id.mainLayout,exoPlayerGem).addToBackStack("").commit()
        }
    }
    override fun getItemCount(): Int {
        return listEmeraldVendor.size
    }
}