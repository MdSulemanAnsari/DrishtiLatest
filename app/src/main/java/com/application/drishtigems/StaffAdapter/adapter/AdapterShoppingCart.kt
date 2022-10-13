package com.application.drishtigems.StaffAdapter.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.application.drishtigems.Network.RetrofitObject
import com.application.drishtigems.Network.StaffNetwork.ApiModel.ShoppingCartModel.ShoppingCartDeleteResponse
import com.application.drishtigems.R
import com.application.drishtigems.databinding.AdapterShoppingCartBinding
import com.application.drishtigems.StaffAdapter.model.ModelShoppingCart
import com.application.drishtigems.ui.fragment.ShoppingCart
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.adapter_data_bring_online.view.*
import kotlinx.android.synthetic.main.adapter_shopping_cart.view.*

class AdapterShoppingCart(var fragmentSp:ShoppingCart,var shoppingList: ArrayList<ShoppingCartDeleteResponse>?):RecyclerView.Adapter<AdapterShoppingCart.ViewHolder>() {
    class ViewHolder(binding:AdapterShoppingCartBinding):RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
     var binding=DataBindingUtil.inflate<AdapterShoppingCartBinding>(LayoutInflater.from(parent.context), R.layout.adapter_shopping_cart,parent,false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
       val data=shoppingList!![position]
        holder.itemView.apply {
            if (!data.gem.gemImages.isNullOrEmpty()) {
                for (i in data.gem.gemImages.indices) {
                    Glide.with(context).load(RetrofitObject.IMAGE_URL + data.gem.gemImages[i].imagePath).into(imageShoppingStock)
                }
            }
            tvShoppingEmerald.text=data.gem.name
            tvShoppingDollar.text=data.gem.price
            tvSkuSp.text=data.gem.sku
            tvWeight.text= data.gem.weight.toString()

        }
        holder.itemView.buttonDelete.setOnClickListener {
                fragmentSp.removeFromListApi(data.gem.id,position)

        }
    }

    override fun getItemCount(): Int {
       return shoppingList!!.size
    }
}