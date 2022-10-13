package com.application.drishtigems.VendorUser.VendorAdapter.ShoppingCartVendor

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.application.drishtigems.Network.RetrofitObject
import com.application.drishtigems.Network.StaffNetwork.ApiModel.ShoppingCartModel.ShoppingCartDeleteResponse
import com.application.drishtigems.R
import com.application.drishtigems.VendorUser.VendorModel.ShoppingCartVendorModel
import com.application.drishtigems.VendorUser.fragment.ShoppingCartVendor
import com.application.drishtigems.databinding.ShoppingCartVendorAdapterBinding
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.adapter_shopping_cart.view.*
import kotlinx.android.synthetic.main.shopping_cart_vendor_adapter.view.*
import kotlinx.android.synthetic.main.similar_product_vendor_adapter.view.*

class ShoppingCartVendorAdapter(var fragmentSc:ShoppingCartVendor,var listSc:ArrayList<ShoppingCartDeleteResponse>):RecyclerView.Adapter<ShoppingCartVendorAdapter.ViewHolder>() {
    class ViewHolder(binding:ShoppingCartVendorAdapterBinding):RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
       val binding=DataBindingUtil.inflate<ShoppingCartVendorAdapterBinding>(LayoutInflater.from(parent.context), R.layout.shopping_cart_vendor_adapter,parent,false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

      /*    holder.itemView.moreInfoSc.setOnClickListener {
              holder.itemView.moreInfoSc.visibility=View.GONE
              holder.itemView.constraintVisible.visibility =View.VISIBLE
        }*/
/*
        holder.itemView.lesInfo.setOnClickListener {
            holder.itemView.moreInfoSc.visibility=View.VISIBLE
            holder.itemView.constraintVisible.visibility =View.GONE
        }*/

       val data=listSc[position]
        holder.itemView.apply {
            if (!data.gem.gemImages.isNullOrEmpty()) {
                for (i in data.gem.gemImages.indices) {
                    Glide.with(context).load(RetrofitObject.IMAGE_URL + data.gem.gemImages[i].imagePath).into(imageShoppingStockVendor)
                }
            }
            tvShoppingEmeraldVendor.text=data.gem.name
            tvShoppingDollarVendor.text=data.gem.price
            tvDispatchData.text=data.expectedDeliveryTime
        }
        holder.itemView.buttonDeleteVendor.setOnClickListener {
            fragmentSc.removeFromListApi(data.gem.id,position)
        }

    }


    override fun getItemCount(): Int {
        return listSc.size
    }
}