package com.application.drishtigems.StaffAdapter.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.application.drishtigems.R
import com.application.drishtigems.databinding.AdapterOffersBinding
import com.application.drishtigems.StaffAdapter.model.ModelOffers
import kotlinx.android.synthetic.main.adapter_offers.view.*

class AdapterOffers(var offersList: ArrayList<ModelOffers>?):RecyclerView.Adapter<AdapterOffers.ViewHolder>() {
    class ViewHolder(binding:AdapterOffersBinding):RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var binding=DataBindingUtil.inflate<AdapterOffersBinding>(LayoutInflater.from(parent.context), R.layout.adapter_offers,parent,false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
      var data=offersList!![position]
        holder.itemView.apply {
            tvMonthlyOffer.text=data.monthlyOffers
            tvIncentiveOffers.text=data.incentiveOffers
        }
    }

    override fun getItemCount(): Int {
      return offersList!!.size
    }
}