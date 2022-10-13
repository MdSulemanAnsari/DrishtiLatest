package com.application.drishtigems.VendorUser.VendorAdapter.PurchaseHistory

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.application.drishtigems.R
import com.application.drishtigems.VendorUser.VendorModel.PurchaseHistoryModel.MyPurchaseModel
import com.application.drishtigems.VendorUser.fragment.PurchaseHistory.PurchaseHistoryTablayout.OrderMyPurchase
import com.application.drishtigems.databinding.MyPurchaseAdapterBinding
import com.application.drishtigems.ui.activity.MainActivity
import kotlinx.android.synthetic.main.my_purchase_adapter.view.*

class MyPurchaseAdapter(var context:Context,var listPurchase:ArrayList<MyPurchaseModel>):RecyclerView.Adapter<MyPurchaseAdapter.ViewHolder>() {
    class ViewHolder(binding:MyPurchaseAdapterBinding):RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
       val binding=DataBindingUtil.inflate<MyPurchaseAdapterBinding>(LayoutInflater.from(parent.context), R.layout.my_purchase_adapter,parent,false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
       val data=listPurchase[position]
        holder.itemView.apply {
            imagePurchaseItem.setImageBitmap(data.imagePurchase)
            tvEmeraldPurchase.text=data.namePurchaseItem
            tvValueBuyingOption.text=data.buyingOption
            tvPurchasePrice.text=data.pricePurchase
            tvDateOrderOn.text=data.orderDate
        }
        holder.itemView.setOnClickListener {
            val orderMyPurchase= OrderMyPurchase()
            (context as MainActivity).supportFragmentManager.beginTransaction().replace(R.id.mainLayout,orderMyPurchase).addToBackStack("").commit()

        }
    }

    override fun getItemCount(): Int {
        return listPurchase.size
    }
}