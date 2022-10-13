package com.application.drishtigems.VendorUser.fragment

import android.graphics.BitmapFactory
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.application.drishtigems.Network.RetrofitInstance
import com.application.drishtigems.Network.StaffNetwork.ApiModel.ShoppingCartModel.ShoppingCartDeleteResponse
import com.application.drishtigems.R
import com.application.drishtigems.VendorUser.VendorAdapter.OffersVendorAdapter
import com.application.drishtigems.VendorUser.VendorAdapter.ShoppingCartVendor.OffersScAdapter
import com.application.drishtigems.VendorUser.VendorAdapter.ShoppingCartVendor.ShoppingCartVendorAdapter
import com.application.drishtigems.VendorUser.VendorModel.OfferModelVendor
import com.application.drishtigems.VendorUser.VendorModel.ShoppingCartVendorModel
import com.application.drishtigems.VendorUser.fragment.GemstoneHome.HomeGemstones.SecurityCheckOut
import com.application.drishtigems.VendorUser.fragment.PurchaseHistory.PurchaseHistory
import com.application.drishtigems.databinding.FragmentShoppingCartVendorBinding
import com.application.drishtigems.prefs.AppPrefs
import com.google.gson.JsonArray
import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ShoppingCartVendor : Fragment() {
    lateinit var binding:FragmentShoppingCartVendorBinding
    var listScAdd:ArrayList<ShoppingCartDeleteResponse> = ArrayList()
    var shoppingCartVendorAdapter: ShoppingCartVendorAdapter?=null
    var offersScAdapter: OffersScAdapter?=null
    var addListOffer:ArrayList<OfferModelVendor> = ArrayList()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding=DataBindingUtil.inflate(inflater,R.layout.fragment_shopping_cart_vendor, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        onClick()
        apiGetShoppingCartVendor()

        offerAddListSc()
        recyclerOfferSc()
    }

    private fun onClick() {
        binding.backPressedSC.setOnClickListener {
            requireActivity().onBackPressed()
        }
        binding.buttonCheckOutSCart.setOnClickListener {
            fragmentManager?.beginTransaction()?.replace(R.id.mainLayout, SecurityCheckOut())?.commit()
        }
    }

private fun apiGetShoppingCartVendor() {
    val appPrefs = AppPrefs(requireContext())
    val token = "Token" + " " + appPrefs.getToken("token")
    val retrofitData = RetrofitInstance().retrofitBuilder.getShoppingCart(token)
    //enqueue method
    retrofitData.enqueue(object : Callback<ArrayList<ShoppingCartDeleteResponse>> {

        override fun onResponse(call: Call<ArrayList<ShoppingCartDeleteResponse>>, response: Response<ArrayList<ShoppingCartDeleteResponse>>) {
            if (response.code() == 200) {
                listScAdd.clear()
                listScAdd.addAll(response.body()!!)
                recyclerShoppingCart()
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
                    listScAdd.removeAt(position)
                    shoppingCartVendorAdapter?.notifyDataSetChanged()
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

    private fun recyclerShoppingCart() {
        shoppingCartVendorAdapter= ShoppingCartVendorAdapter(this,listScAdd)
        binding.rvScVendor.adapter=shoppingCartVendorAdapter
    }

    private fun offerAddListSc() {
        addListOffer.clear()
        addListOffer.addAll(listOf(OfferModelVendor(BitmapFactory.decodeResource(this.resources,R.drawable.offer_pic_vendor),"SIGN IN TODAY TO GET 10% \n DISCOUNT ON ALL PRODUCTS.")))
        addListOffer.addAll(listOf(OfferModelVendor(BitmapFactory.decodeResource(this.resources,R.drawable.offer_pic_vendor),"SIGN IN TODAY TO GET 10% \n DISCOUNT ON ALL PRODUCTS.")))
        addListOffer.addAll(listOf(OfferModelVendor(BitmapFactory.decodeResource(this.resources,R.drawable.offer_pic_vendor),"SIGN IN TODAY TO GET 10% \n DISCOUNT ON ALL PRODUCTS.")))
        addListOffer.addAll(listOf(OfferModelVendor(BitmapFactory.decodeResource(this.resources,R.drawable.offer_pic_vendor),"SIGN IN TODAY TO GET 10% \n DISCOUNT ON ALL PRODUCTS.")))
        addListOffer.addAll(listOf(OfferModelVendor(BitmapFactory.decodeResource(this.resources,R.drawable.offer_pic_vendor),"SIGN IN TODAY TO GET 10% \n DISCOUNT ON ALL PRODUCTS.")))
    }
    private fun recyclerOfferSc() {
        offersScAdapter= OffersScAdapter(addListOffer)
        binding.rvOfferScVendor.adapter=offersScAdapter
    }
}