package com.application.drishtigems.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.drawerlayout.widget.DrawerLayout
import com.application.drishtigems.Network.StaffNetwork.ApiModel.AddTocartModel.AddCartNew
import com.application.drishtigems.Network.StaffNetwork.ApiModel.AddTocartModel.GemImagesItem
import com.application.drishtigems.Network.StaffNetwork.ApiModel.similarProductsResponse.DataItem

import com.application.drishtigems.Network.StaffNetwork.ApiModel.similarProductsResponse.SimilarProductsResponse
import com.application.drishtigems.Network.RetrofitInstance
import com.application.drishtigems.Network.jawellerNetwork.ApiModelJeweller.BuyNowCartModel
import com.application.drishtigems.Network.jawellerNetwork.ApiModelJeweller.GemVideosItem
import com.application.drishtigems.R
import com.application.drishtigems.StaffAdapter.adapter.AdapterSimilarProduct
import com.application.drishtigems.StaffAdapter.adapter.AdapterViewPagerCarat
import com.application.drishtigems.databinding.FragmentEmeraldCartAddBinding
import com.application.drishtigems.prefs.AppPrefs
import com.application.drishtigems.ui.activity.MainActivity
import com.application.drishtigems.ui.fragment.NaturalEmeraldBringOnlineStock.Companion.category_id
import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.collections.ArrayList

class EmeraldCartAdd : Fragment() {
    lateinit var binding: FragmentEmeraldCartAddBinding
    var adapterViewPagerCarat:AdapterViewPagerCarat?=null
    var viewCaratList: AddCartNew?=null
    var mergeListStaff:ArrayList<GemVideosItem> = ArrayList()
    var imageListGemsStaff: ArrayList<GemVideosItem> = ArrayList()
    var videoListGemsStaff: ArrayList<GemVideosItem> = ArrayList()
    var buyNowCartModel= BuyNowCartModel()

    var adapterSimilarProduct:AdapterSimilarProduct?=null
    private var dataSimilarList :ArrayList<DataItem> = ArrayList()
    var hashMap:HashMap<String,String> = HashMap()
    private var id: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
       binding=DataBindingUtil.inflate(inflater,R.layout.fragment_emerald_cart_add, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.progressBarAddCart.visibility=view.visibility
        (context as MainActivity).closedDrawer()
        if (arguments!=null){
            id=requireArguments().getString("id")
        }
        apiHitAddCart()
        apiHitSimilarProduct()

        binding.backPressedCaratImage.setOnClickListener {
              requireActivity().onBackPressed()
        }
        binding.buttonAddCarat.setOnClickListener {
            fragmentManager?.beginTransaction()?.replace(R.id.mainLayout, ShoppingCart())?.addToBackStack("") ?.commit()
        }
        binding.cartIconAddToCart.setOnClickListener {
            fragmentManager?.beginTransaction()?.replace(R.id.mainLayout, ShoppingCart())?.addToBackStack("") ?.commit()
        }
    }

    override fun onStart() {
        super.onStart()
        (activity as MainActivity).binding!!.drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED)
    }

    private fun recyclerSimilarProduct() {
        adapterSimilarProduct= AdapterSimilarProduct(this,dataSimilarList)
        binding.rvSimilarProductCart.adapter=adapterSimilarProduct
    }

    private fun setDataCart() {
        binding.tvDataCaratEmerald.text = viewCaratList?.name
        binding.tvDataSkuCaratValue.text = viewCaratList?.sku
        binding.tvDataCaratOriginValue.text = viewCaratList?.origin?.name
        binding.tvDataAddCaratDollar.text = viewCaratList?.price
        binding.tvCaratDataEmerald.text=viewCaratList?.name
        binding.tvCaratPriceDataEmerald.text=viewCaratList?.price
        binding.tvCaratName.text=viewCaratList?.name
        binding.tvCratValue.text=viewCaratList?.price
        binding.tvStoneCut.text=viewCaratList?.stoneCut?.name
        binding.tvStoneQuality.text=viewCaratList?.stoneQuality?.name
        binding.tvShape.text=viewCaratList?.shape?.name
        binding.tvColor.text=viewCaratList?.categoryColor?.name
        binding.tvExactDimensions.text=viewCaratList?.dimensionHeight?.toString()
        binding.tvWeight.text=viewCaratList?.weight?.toString()
    }

    private fun viewPagerCall(id:String) {
      adapterViewPagerCarat=AdapterViewPagerCarat(requireActivity(), mergeListStaff,id)
        binding.viewPagerCarat.adapter=adapterViewPagerCarat
        binding.tabLayCarat.setupWithViewPager(binding.viewPagerCarat, true)
    }
    private fun apiHitAddCart() {
        val appPrefs = AppPrefs(requireContext())

        val token = "Token" + " " + appPrefs.getToken("token")
        val retrofitData = RetrofitInstance().retrofitBuilder.getFilterData(token,requireArguments().getString("id"))
        //enqueue method
        retrofitData.enqueue(object : Callback<AddCartNew> {

            override fun onResponse(call: Call<AddCartNew>, response: Response<AddCartNew>) {
                if (response.code() == 200) {
                    mergeListStaff.clear()
                    imageListGemsStaff.clear()
                    videoListGemsStaff.clear()
                    viewCaratList = response.body()!!
                    imageListGemsStaff.addAll(response.body()!!.gemImages)
                    videoListGemsStaff.addAll(response.body()!!.gemVideos)
                    mergeListStaff.addAll(imageListGemsStaff)
                    mergeListStaff.addAll(videoListGemsStaff)
                    viewPagerCall(requireArguments().getString("id")!!)
                    setDataCart()
                    binding.progressBarAddCart.visibility=View.GONE
                    Toast.makeText(requireActivity(), "Successful", Toast.LENGTH_SHORT).show()
                } else {
                    binding.progressBarAddCart.visibility=View.GONE
                    Toast.makeText(requireActivity(), "Error 400", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<AddCartNew>, t: Throwable) {
                Toast.makeText(requireActivity(), "Error! " +t.message, Toast.LENGTH_SHORT).show()
            }
        })
    }
    private fun apiHitSimilarProduct() {
        val appPrefs = AppPrefs(requireContext())

        val token = "Token" + " " + appPrefs.getToken("token")
        hashMapData()
        val retrofitData = RetrofitInstance().retrofitBuilder.similarProduct(token,hashMap)
        //enqueue method
        retrofitData.enqueue(object : Callback<SimilarProductsResponse> {

            override fun onResponse(call: Call<SimilarProductsResponse>, response: Response<SimilarProductsResponse>) {
                if (response.code() == 200) {
                    dataSimilarList.clear()
                    dataSimilarList.addAll(response.body()!!.data)
                    recyclerSimilarProduct()
                    binding.progressBarAddCart.visibility=View.GONE
                    Toast.makeText(requireActivity(), "Successful", Toast.LENGTH_SHORT).show()
                } else {
                    binding.progressBarAddCart.visibility=View.GONE
                    Toast.makeText(requireActivity(), "Error 400", Toast.LENGTH_SHORT).show()
                }
            }
            override fun onFailure(call: Call<SimilarProductsResponse>, t: Throwable) {
                Toast.makeText(requireActivity(), "Error! " +t.message, Toast.LENGTH_SHORT).show()
            }
        })
    }
    fun apiCallAddToCartStaff(id: String, position: Int) {
        buyNowCartModel.gem=id.toInt()
        val appPrefs = AppPrefs(requireContext())
        val token = "Token" + " " + appPrefs.getToken("token")
        val retrofitData = RetrofitInstance().retrofitBuilder.addToCart(token,buyNowCartModel)
        //enqueue method
        retrofitData.enqueue(object : Callback<JsonObject> {

            override fun onResponse(call: Call<JsonObject>, response: Response<JsonObject>) {
                if (response.code() == 201) {
                    Toast.makeText(requireActivity(), "Add To Shopping Cart", Toast.LENGTH_SHORT).show()
                  binding!!.tvAddToCartCountIcon.text = response.body()?.get("user_cart_count").toString()
                } else {
                    Toast.makeText(requireActivity(), "Already Add", Toast.LENGTH_SHORT).show()
                }
            }
            override fun onFailure(call: Call<JsonObject>, t: Throwable) {
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
                    Toast.makeText(requireActivity(), "Remove Item", Toast.LENGTH_SHORT).show()
                    binding!!.tvAddToCartCountIcon.text = response.body()?.get("user_cart_count").toString()
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
    fun hashMapData(){
        hashMap["category_id"]=category_id.toString()
        hashMap["gem_id"]=id!!
    }

}