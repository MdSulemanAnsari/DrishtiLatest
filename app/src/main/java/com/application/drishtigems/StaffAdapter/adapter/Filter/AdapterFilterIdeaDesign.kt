package com.application.drishtigems.StaffAdapter.adapter.Filter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.application.drishtigems.R
import com.application.drishtigems.databinding.AdapterFilterIdeaDesignBinding
import com.application.drishtigems.StaffAdapter.model.Filter.ModelFilterIdeaDesign
import kotlinx.android.synthetic.main.adapter_filter_idea_design.view.*

class AdapterFilterIdeaDesign(var filterIdeaDesignList: ArrayList<ModelFilterIdeaDesign>?): RecyclerView.Adapter<AdapterFilterIdeaDesign.ViewHolder>() {
    class ViewHolder(binding: AdapterFilterIdeaDesignBinding): RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding= DataBindingUtil.inflate<AdapterFilterIdeaDesignBinding>(LayoutInflater.from(parent.context), R.layout.adapter_filter_idea_design,parent,false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data=filterIdeaDesignList!![position]
        holder.itemView.apply {
            tvFilterIdeaDesign.text=data.filterIdeaDesign
        }

    }
    override fun getItemCount(): Int {
        return filterIdeaDesignList!!.size
    }

}