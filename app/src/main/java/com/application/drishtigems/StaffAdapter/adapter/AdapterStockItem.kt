package com.application.drishtigems.StaffAdapter.adapter

import android.content.Context
import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.application.drishtigems.R
import com.application.drishtigems.databinding.AdapterStockItemBinding
import com.application.drishtigems.StaffAdapter.model.ModelStockItem
import com.application.drishtigems.ui.activity.MainActivity
import com.application.drishtigems.ui.fragment.EmeraldStock
import kotlinx.android.synthetic.main.adapter_stock_item.view.*

class AdapterStockItem(val context: Context, var stokeList: ArrayList<ModelStockItem>):RecyclerView.Adapter<AdapterStockItem.ViewHolder>() {
     var list=ArrayList<ModelStockItem>()
    init {
        list.clear()
        list.addAll(stokeList)
    }
    class ViewHolder(binding:AdapterStockItemBinding):RecyclerView.ViewHolder(binding.root){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val binding=DataBindingUtil.inflate<AdapterStockItemBinding>(LayoutInflater.from(parent.context), R.layout.adapter_stock_item,parent,false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val data=list[position]
        holder.itemView.apply {
            imageStock.setImageBitmap(data.imageMyStock)
            tvEmerald.text=data.emerald
            tvSkuValue.text=data.skuValue
            tvOriginValue.text=data.originValue
            tvCaratDollar.text=data.dollarCarat
            tvAlignedDate.text=data.alignedDate
        }
        holder.itemView.setOnClickListener{
            val dataEmeraldStock= EmeraldStock()
            val bundle= Bundle()
            bundle.putString("emerald",data.emerald)
            bundle.putString("skuValue",data.skuValue)
            bundle.putString("originValue",data.originValue)
            bundle.putString("dollarCarat",data.dollarCarat)
            bundle.putString("alignedDate",data.alignedDate)
            dataEmeraldStock.arguments=bundle
            (context as MainActivity).supportFragmentManager.beginTransaction().replace(R.id.mainLayout,dataEmeraldStock).addToBackStack("").commit()
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }
    fun filter(inputString:String){
        list.clear()
        if (TextUtils.isEmpty(inputString)){
            list.addAll(stokeList)
            notifyDataSetChanged()
            return
        }
        list.clear()
        for (item in stokeList){
            if (item.emerald.startsWith(inputString,true)){
                list.add(item)
            }
        }

        notifyDataSetChanged()
    }
}