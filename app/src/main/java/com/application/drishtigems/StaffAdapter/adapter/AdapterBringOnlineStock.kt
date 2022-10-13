package com.application.drishtigems.StaffAdapter.adapter

import android.app.ProgressDialog
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
import com.application.drishtigems.databinding.AdapterBringOnlineStockBinding
import com.application.drishtigems.ui.activity.MainActivity
import com.application.drishtigems.ui.fragment.NaturalEmeraldBringOnlineStock
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.adapter_bring_online_stock.view.*
import kotlinx.android.synthetic.main.emerald_item_adapter_vendor.view.*

class AdapterBringOnlineStock(var context: Context, var bringOnlineList: ArrayList<CategoryOnlineBringModelItem>?) : RecyclerView.Adapter<AdapterBringOnlineStock.ViewHolder>() {
    class ViewHolder(binding: AdapterBringOnlineStockBinding) : RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = DataBindingUtil.inflate<AdapterBringOnlineStockBinding>(LayoutInflater.from(parent.context), R.layout.adapter_bring_online_stock, parent, false)
        return ViewHolder(binding)
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = bringOnlineList!![position]
        holder.itemView.apply {
            tvBring.text = data.title
            Glide.with(context).load(RetrofitObject.IMAGE_URL+data.image).into(buttonBringOnlineStock)
        }
        //passing data to fragment using bundle
        holder.itemView.setOnClickListener {
            holder.itemView.cons.setBackgroundResource(R.color.amountcolor)
            val naturalEmeraldBringOnlineStock = NaturalEmeraldBringOnlineStock()
            val bundle = Bundle()
            bundle.putString("tittle", data.title)
            bundle.putInt("id", data.id)
            naturalEmeraldBringOnlineStock.arguments = bundle
            Handler().postDelayed({
                (context as MainActivity).supportFragmentManager.beginTransaction().replace(R.id.mainLayout, naturalEmeraldBringOnlineStock).addToBackStack("").commit()
                true
                true
            },200)


        }
    }

    override fun getItemCount(): Int {
        return bringOnlineList!!.size
    }
}