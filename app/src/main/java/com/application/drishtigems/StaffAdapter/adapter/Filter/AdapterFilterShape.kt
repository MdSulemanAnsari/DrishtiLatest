package com.application.drishtigems.StaffAdapter.adapter.Filter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.application.drishtigems.R
import com.application.drishtigems.databinding.AdapterFilterShapeBinding
import com.application.drishtigems.StaffAdapter.model.Filter.ModelFilterShape
import kotlinx.android.synthetic.main.adapter_filter_shape.view.*

class AdapterFilterShape(var filterShapeList: ArrayList<ModelFilterShape>?):RecyclerView.Adapter<AdapterFilterShape.ViewHolder>() {
    class ViewHolder(binding:AdapterFilterShapeBinding):RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
       val binding=DataBindingUtil.inflate<AdapterFilterShapeBinding>(LayoutInflater.from(parent.context), R.layout.adapter_filter_shape,parent,false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
      val data=filterShapeList!![position]
        holder.itemView.apply {
            tvFilterShape.text=data.filterShape
        }
    }

    override fun getItemCount(): Int {
       return filterShapeList!!.size
    }
}