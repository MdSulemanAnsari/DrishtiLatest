package com.application.drishtigems.StaffAdapter.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.application.drishtigems.Network.StaffNetwork.ApiModel.LoginUserModel.UserModel
import com.application.drishtigems.R
import com.application.drishtigems.databinding.AdapterDrawerBinding
import com.application.drishtigems.StaffAdapter.model.ModelDrawerItem
import com.application.drishtigems.VendorUser.fragment.*
import com.application.drishtigems.VendorUser.fragment.ComissionEarned.CommissionEarned
import com.application.drishtigems.VendorUser.fragment.GemstoneHome.HomeGemstone
import com.application.drishtigems.VendorUser.fragment.ProfitGraph.ProfitGraph
import com.application.drishtigems.VendorUser.fragment.PurchaseHistory.PurchaseHistory
import com.application.drishtigems.VendorUser.fragment.Settings.Settings
import com.application.drishtigems.ui.activity.MainActivity
import com.application.drishtigems.ui.fragment.*
import com.application.drishtigems.ui.fragment.homeFragment.HomeScreen
import kotlinx.android.synthetic.main.adapter_drawer.view.*

class AdapterDrawer(val context: Context, var drawerList: ArrayList<ModelDrawerItem>?, var data: UserModel?):RecyclerView.Adapter<AdapterDrawer.ViewHolder>() {
    class ViewHolder(binding:AdapterDrawerBinding):RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdapterDrawer.ViewHolder {
       val binding=DataBindingUtil.inflate<AdapterDrawerBinding>(LayoutInflater.from(parent.context), R.layout.adapter_drawer,parent,false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AdapterDrawer.ViewHolder, position: Int) {
      val datalist=drawerList!![position]

        holder.itemView.apply {
            imageDrawerNavigation.setImageBitmap(datalist.drawerImage)
            tvdrawerName.text=datalist.drawerName
        }
        holder.itemView.setOnClickListener {
            (context as MainActivity).closedDrawer()
            if (data!!.groups[0].name == "Sales Executive"){
            when(position){
                0->(context as MainActivity).supportFragmentManager.beginTransaction().replace(R.id.mainLayout, HomeScreen()).commit()
                1->(context as MainActivity).supportFragmentManager.beginTransaction().replace(R.id.mainLayout, InMyStock()).commit()
                2->(context as MainActivity).supportFragmentManager.beginTransaction().replace(R.id.mainLayout, BringOnlineStock()).commit()
                3->(context as MainActivity).supportFragmentManager.beginTransaction().replace(R.id.mainLayout, BillGenerated()).commit()
                4->(context as MainActivity).supportFragmentManager.beginTransaction().replace(R.id.mainLayout, Salegraph()).commit()
                5->(context as MainActivity).supportFragmentManager.beginTransaction().replace(R.id.mainLayout, TaDaForm()).commit()
                6->(context as MainActivity).supportFragmentManager.beginTransaction().replace(R.id.mainLayout,Offers()).commit()
                7->(context as MainActivity).supportFragmentManager.beginTransaction().replace(R.id.mainLayout, Settings()).commit()
            }}else if(data!!.groups[0].name == "Jeweller"){
            when(position){
                0->(context as MainActivity).supportFragmentManager.beginTransaction().replace(R.id.mainLayout, HomeGemstone()).commit()
                1->(context as MainActivity).supportFragmentManager.beginTransaction().replace(R.id.mainLayout,OffersVendor()).commit()
                2->(context as MainActivity).supportFragmentManager.beginTransaction().replace(R.id.mainLayout,CustomOrders()).commit()
                3->(context as MainActivity).supportFragmentManager.beginTransaction().replace(R.id.mainLayout,PurchaseHistory()).commit()
                4->(context as MainActivity).supportFragmentManager.beginTransaction().replace(R.id.mainLayout, ProfitGraph()).commit()
                5->(context as MainActivity).supportFragmentManager.beginTransaction().replace(R.id.mainLayout,CommissionEarned()).commit()
                6->(context as MainActivity).supportFragmentManager.beginTransaction().replace(R.id.mainLayout,NotificationVendor()).commit()
                7->(context as MainActivity).supportFragmentManager.beginTransaction().replace(R.id.mainLayout, Settings()).commit()
            } }
        }
    }
    override fun getItemCount(): Int {
       return drawerList!!.size
    }
}