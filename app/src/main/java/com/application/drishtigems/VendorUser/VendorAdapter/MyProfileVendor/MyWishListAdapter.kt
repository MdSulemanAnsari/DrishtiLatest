package com.application.drishtigems.VendorUser.VendorAdapter.MyProfileVendor

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.application.drishtigems.Network.RetrofitObject
import com.application.drishtigems.Network.jawellerNetwork.ApiModelJeweller.MyProfileVendor.DataItem

import com.application.drishtigems.R
import com.application.drishtigems.VendorUser.fragment.MyProfileVendor.MyProfileTabLayout.MyWishList
import com.application.drishtigems.databinding.MyWishlistAdapterBinding
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.my_wishlist_adapter.view.*

class MyWishListAdapter(var fragmentRemoveApiHit:MyWishList, var listWishList: ArrayList<DataItem>):RecyclerView.Adapter<MyWishListAdapter.ViewHolder>() {
    class ViewHolder(binding:MyWishlistAdapterBinding):RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
      val binding=DataBindingUtil.inflate<MyWishlistAdapterBinding>(LayoutInflater.from(parent.context), R.layout.my_wishlist_adapter,parent,false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data=listWishList[position]
        holder.itemView.apply {
            tvEmeraldWishList.text = data.gem.name
            tvCaratDollarWishList.text = data.gem.price
            Glide.with(context).load(RetrofitObject.IMAGE_URL+data.gem.gemImages).into(imageStockWishList)
        }
        holder.itemView.tvRemoverList.setOnClickListener {
          fragmentRemoveApiHit.removeFromListApi(data.gem.id,position)


        //interface using    abc.clickId(data.id)
        }
    }

    override fun getItemCount(): Int {
        return listWishList.size
    }
   /* interface ItemClick{
        fun clickId(id:Int)
    }*/
}