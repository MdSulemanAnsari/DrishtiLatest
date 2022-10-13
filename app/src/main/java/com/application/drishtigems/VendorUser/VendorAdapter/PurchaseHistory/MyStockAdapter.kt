package com.application.drishtigems.VendorUser.VendorAdapter.PurchaseHistory

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.application.drishtigems.R
import com.application.drishtigems.VendorUser.VendorModel.PurchaseHistoryModel.MyStockModel
import com.application.drishtigems.VendorUser.fragment.PurchaseHistory.PurchaseHistoryTablayout.SoldMyStock
import com.application.drishtigems.databinding.MyStockAdapterBinding
import com.application.drishtigems.ui.activity.MainActivity
import kotlinx.android.synthetic.main.my_stock_adapter.view.*

class MyStockAdapter(var context: Context,var listMyStock:ArrayList<MyStockModel>):RecyclerView.Adapter<MyStockAdapter.ViewHolder>() {
    class ViewHolder(binding:MyStockAdapterBinding):RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
  val binding=DataBindingUtil.inflate<MyStockAdapterBinding>(LayoutInflater.from(parent.context), R.layout.my_stock_adapter,parent,false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
      val data =listMyStock[position]
        holder.itemView.apply {
            imageMyStock.setImageBitmap(data.imageMyStock)
            tvEmeraldMyStock.text=data.nameMyStock
            tvValueBuyingMyStock.text=data.buyingMyStock
            tvPriceMyStock.text=data.priceMyStock
            tvDataMyStock.text=data.orderDateMyStock
        }
        holder.itemView.setOnClickListener {

            val soldMyStock=SoldMyStock()
            (context as MainActivity).supportFragmentManager.beginTransaction().replace(R.id.mainLayout,soldMyStock).addToBackStack("").commit()
        }
    }

    override fun getItemCount(): Int {
       return listMyStock.size
    }
}