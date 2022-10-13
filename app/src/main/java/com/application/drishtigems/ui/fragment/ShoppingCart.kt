package com.application.drishtigems.ui.fragment

import android.graphics.BitmapFactory
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.drawerlayout.widget.DrawerLayout
import com.application.drishtigems.Network.RetrofitInstance
import com.application.drishtigems.Network.StaffNetwork.ApiModel.RecentRequest.DataItem
import com.application.drishtigems.Network.StaffNetwork.ApiModel.RecentRequest.User
import com.application.drishtigems.Network.StaffNetwork.ApiModel.ShoppingCartModel.Gem
import com.application.drishtigems.Network.StaffNetwork.ApiModel.ShoppingCartModel.ShoppingCartDeleteResponse
import com.application.drishtigems.R
import com.application.drishtigems.StaffAdapter.adapter.AdapterShoppingCart
import com.application.drishtigems.databinding.FragmentShoppingCartBinding
import com.application.drishtigems.StaffAdapter.model.ModelShoppingCart
import com.application.drishtigems.prefs.AppPrefs
import com.application.drishtigems.ui.activity.MainActivity
import com.google.gson.JsonArray
import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ShoppingCart : Fragment() {
    lateinit var binding:FragmentShoppingCartBinding
    private var adapterShoppingCart:AdapterShoppingCart?=null
    var shoppingList:ArrayList<ShoppingCartDeleteResponse> = ArrayList()
    var shoppingCartDeleteResponse:ShoppingCartDeleteResponse?=null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding=DataBindingUtil.inflate(inflater,R.layout.fragment_shopping_cart, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.messageText.visibility=View.VISIBLE
        apiGetShoppingCart()
        binding.buttonAddCarat.setOnClickListener {
            fragmentManager?.beginTransaction()?.replace(R.id.mainLayout, BillGenerated())?.addToBackStack("") ?.commit()
        }
        binding.backPressedShopping.setOnClickListener {
            requireActivity().onBackPressed()
        }
    }
    override fun onStart() {
        super.onStart()
        (activity as MainActivity).binding.drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED)
    }
    private fun apiGetShoppingCart() {
        val appPrefs = AppPrefs(requireContext())
        val token = "Token" + " " + appPrefs.getToken("token")
        val retrofitData = RetrofitInstance().retrofitBuilder.getShoppingCart(token)
        //enqueue method
        retrofitData.enqueue(object : Callback<ArrayList<ShoppingCartDeleteResponse>> {

            override fun onResponse(call: Call<ArrayList<ShoppingCartDeleteResponse>>, response: Response<ArrayList<ShoppingCartDeleteResponse>>) {
                if (response.code() == 200) {
                    binding.messageText.visibility=View.GONE
                    shoppingList.clear()
                    shoppingList.addAll(response.body()!!)
                    recyclerViewShopping()
                    Toast.makeText(requireActivity(), "Successful", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(requireActivity(), "Not Successful", Toast.LENGTH_SHORT).show()
                }
            }
            override fun onFailure(call: Call<ArrayList<ShoppingCartDeleteResponse>>, t: Throwable) {
                Toast.makeText(requireActivity(), "Error! " + t.message, Toast.LENGTH_SHORT).show()
            }
        })
    }
    fun removeFromListApi(gem_id: Int, position: Int) {
        val appPrefs = AppPrefs(requireContext())

        val token = "Token" + " " + appPrefs.getToken("token")
        val retrofitData = RetrofitInstance().retrofitBuilder.removeFromCart(token,gem_id)
        //enqueue method

        retrofitData.enqueue(object : Callback<JsonObject> {

            override fun onResponse(call: Call<JsonObject>, response: Response<JsonObject>) {
                if (response.code() == 200) {
                    shoppingList.removeAt(position)
                    adapterShoppingCart?.notifyDataSetChanged()
                    binding.messageText.visibility=View.VISIBLE
                    Toast.makeText(requireActivity(), "Remove Item", Toast.LENGTH_SHORT).show()
                } else {
                    //  binding.progressBarBringOnline.visibility=View.GONE
                    Toast.makeText(requireActivity(), response.body().toString(), Toast.LENGTH_SHORT).show()
                }
            }
            override fun onFailure(call: Call<JsonObject>, t: Throwable) {
                Toast.makeText(requireActivity(), "Error! " + t.message, Toast.LENGTH_SHORT).show()
            }
        })
    }
    private fun recyclerViewShopping() {
        adapterShoppingCart=AdapterShoppingCart(this,shoppingList)
        binding.rvShoppingCart.adapter=adapterShoppingCart
    }
}