package com.application.drishtigems.StaffAdapter.adapter


import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.application.drishtigems.Network.RetrofitObject
import com.application.drishtigems.Network.StaffNetwork.ApiModel.natural.DataItem
import com.application.drishtigems.R
import com.application.drishtigems.databinding.AdapterDataBringOnlineBinding
import com.application.drishtigems.ui.activity.MainActivity
import com.application.drishtigems.ui.fragment.EmeraldCartAdd
import com.application.drishtigems.ui.fragment.NaturalEmeraldBringOnlineStock
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.adapter_bring_online_stock.view.*
import kotlinx.android.synthetic.main.adapter_data_bring_online.view.*
import kotlinx.android.synthetic.main.fragment_product_details_bottom_sheet.view.*

class AdapterDataBringOnline(var fragmentNE:NaturalEmeraldBringOnlineStock,val context: Context, var bringList: ArrayList<DataItem>):RecyclerView.Adapter<AdapterDataBringOnline.ViewHolder>() {

    var list= ArrayList<DataItem>()
    init {
        list.clear()
        list.addAll(bringList)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding=DataBindingUtil.inflate<AdapterDataBringOnlineBinding>(LayoutInflater.from(parent.context), R.layout.adapter_data_bring_online,parent,false)
        return ViewHolder(binding)
    }

    @SuppressLint("ResourceAsColor")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data=list[position]
        holder.itemView.apply {
            Glide.with(context).load(RetrofitObject.IMAGE_URL+data.category.image).into(imageBringStock)
            tvBringEmerald.text=data.name
            tvBringSkuValue.text=data.sku
            tvBringOriginValue.text= data.origin.name
            tvBringCaratDollar.text=data.price

            if (bringList[position].addedToCart){
                holder.itemView.buttonMark.text = "Remove Item"
                holder.itemView.buttonMark.setBackgroundColor(Color.parseColor("#ED9C00"));
            }else{
                holder.itemView.buttonMark.text = "Add Item"
            }
        }
        holder.itemView.setOnClickListener {
            holder.itemView.constAbo.setBackgroundResource(R.color.amountcolor)
            val emeraldCartAdd=EmeraldCartAdd()
            val bundle=Bundle()
            bundle.putString("id", data.id)
            emeraldCartAdd.arguments=bundle
            Handler().postDelayed({
                (context as MainActivity).supportFragmentManager.beginTransaction().replace(R.id.mainLayout,emeraldCartAdd).addToBackStack("").commit()
                true
                true
            },200)

        }
        holder.itemView.buttonMark.setOnClickListener {

            if (bringList[position].addedToCart){
                fragmentNE.removeFromListApi(bringList[position].id.toInt(),position)
                bringList[position].addedToCart = false

            }
            else{
                fragmentNE.apiCallAddToCartStaff(bringList[position].id,position)
                bringList[position].addedToCart =true
                holder.itemView.buttonMark.text = "Remove Item"
                holder.itemView.buttonMark.setBackgroundColor(Color.parseColor("#ED9C00"))

            }
        }

    }
    override fun getItemCount(): Int {
        return list.size
    }
    class ViewHolder(binding: AdapterDataBringOnlineBinding) :RecyclerView.ViewHolder(binding.root){

    }
    fun filter(inputString: String?){
        list.clear()
        if (TextUtils.isEmpty(inputString)){
            list.addAll(bringList)
            notifyDataSetChanged()
            return
        }
        list.clear()
        for (item in bringList){
            if (item.sku.startsWith(inputString!!,true)){
                list.add(item)
            }
            if (item.price.startsWith(inputString!!,true)){
                list.add(item)
            }
            if (item.name.startsWith(inputString!!,true)){
                list.add(item)
            }
        }
        notifyDataSetChanged()
    }
}