package com.application.drishtigems.StaffAdapter.adapter

import android.annotation.SuppressLint
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.application.drishtigems.Network.RetrofitObject
import com.application.drishtigems.R
import com.application.drishtigems.databinding.AdapterSimilarProductBinding
import com.application.drishtigems.ui.fragment.EmeraldCartAdd
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.adapter_data_bring_online.view.*
import kotlinx.android.synthetic.main.adapter_similar_product.view.*
import kotlinx.android.synthetic.main.adapter_similar_product.view.tvBringOriginValue

class AdapterSimilarProduct(var fragmentSp:EmeraldCartAdd,var listProduct: ArrayList<com.application.drishtigems.Network.StaffNetwork.ApiModel.similarProductsResponse.DataItem>):RecyclerView.Adapter<AdapterSimilarProduct.ViewHolder>(){
    class ViewHolder(binding: AdapterSimilarProductBinding):RecyclerView.ViewHolder(binding.root){

    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding=DataBindingUtil.inflate<AdapterSimilarProductBinding>(LayoutInflater.from(parent.context), R.layout.adapter_similar_product,parent,false)
        return ViewHolder(binding)
    }
    @SuppressLint("ResourceAsColor")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
 val data=listProduct[position]
        holder.itemView.apply {
            Glide.with(context).load(RetrofitObject.IMAGE_URL+data.category.image).into(imageBringCaratStock)
            tvBringCaratEmerald.text=data.name
            tvBringCaratSkuValue.text=data.sku
            tvBringOriginValue.text= data.origin.name
            tvBringAddCartCaratDollar.text=data.price

            if (listProduct[position].addedToCart){
                holder.itemView.buttonMarkSp.text = "Remove Item"
                holder.itemView.buttonMarkSp.setBackgroundColor(Color.parseColor("#ED9C00"));
            }else{
                holder.itemView.buttonMarkSp.text = "Add Item"
            }
        }
        holder.itemView.buttonMarkSp.setOnClickListener {
            if (listProduct[position].addedToCart){
                fragmentSp.removeFromListApi(listProduct[position].id,position)
                listProduct[position].addedToCart = false
            }
            else{
                fragmentSp.apiCallAddToCartStaff(listProduct[position].id.toString(),position)
                listProduct[position].addedToCart =true
                holder.itemView.buttonMarkSp.text = "Remove Item"
                holder.itemView.buttonMarkSp.setBackgroundColor(Color.parseColor("#ED9C00"))
            }
        }
    }
    override fun getItemCount(): Int {
        return listProduct.size
    }
}