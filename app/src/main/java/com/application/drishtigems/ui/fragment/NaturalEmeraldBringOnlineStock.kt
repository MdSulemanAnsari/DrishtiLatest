package com.application.drishtigems.ui.fragment

import android.os.Bundle
import android.os.Handler
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.application.drishtigems.Network.StaffNetwork.ApiModel.natural.DataItem
import com.application.drishtigems.Network.StaffNetwork.ApiModel.natural.NaturalResponse
import com.application.drishtigems.Network.RetrofitInstance
import com.application.drishtigems.Network.jawellerNetwork.ApiModelJeweller.BuyNowCartModel
import com.application.drishtigems.Pagination.PaginationRecycler
import com.application.drishtigems.R
import com.application.drishtigems.StaffAdapter.adapter.AdapterDataBringOnline
import com.application.drishtigems.databinding.FragmentNaturalEmeraldBringOnlineStockBinding
import com.application.drishtigems.prefs.AppPrefs
import com.application.drishtigems.ui.activity.MainActivity
import com.google.gson.JsonObject
import kotlinx.android.synthetic.main.fragment_natural_emerald_bring_online_stock.*
import kotlinx.android.synthetic.main.fragment_shopping_cart.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NaturalEmeraldBringOnlineStock : Fragment() {
    lateinit var binding:FragmentNaturalEmeraldBringOnlineStockBinding
    private var  bringList:ArrayList<DataItem> = ArrayList()
    var dataItemCount:DataItem?=null
    var adapterDataBringOnline:AdapterDataBringOnline?=null
    var hashMap:HashMap<String,Any>?= HashMap()
    var buyNowCartModel= BuyNowCartModel()
    private var emeraldStockData: String? = null
    private var currentPage: Int=0
    var isLoading =false
    var isLastPage =false
    companion object{
        var  category_id: String? = null
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding=DataBindingUtil.inflate(inflater,R.layout.fragment_natural_emerald_bring_online_stock, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.shimmerBOS.shimmerExploreNO.startShimmer()



        if (arguments!=null){
            emeraldStockData=requireArguments().getString("tittle")
            category_id= requireArguments().getInt("id").toString()
            tvOnlineStockData.text=emeraldStockData
        }
        val layoutManager = LinearLayoutManager(activity, RecyclerView.VERTICAL, false)
        binding!!.rvOnlineBringStock.layoutManager = layoutManager
        binding!!.rvOnlineBringStock.addOnScrollListener(object : PaginationRecycler(layoutManager) {
            override fun loadMoreItems() {
                isLoading = true
                currentPage++
                binding.tvNoItemFound.visibility=View.GONE
                binding!!.pbNE.visibility = View.VISIBLE

                Handler().postDelayed(Runnable {
                    apiHitGems(currentPage)
                    binding.tvNoItemFound.visibility=View.GONE
                    binding!!.pbNE.visibility = View.GONE
                }, 1000)

            }
            override fun isLastPage(): Boolean {
                return isLastPage
            }
            override fun isLoading(): Boolean {
                return isLoading
            }
        })

        Handler().postDelayed({
            apiHitGems(currentPage)
        },1000)

        onClick()
        //search view


    }

    private fun onClick() {
        searchViewSecond.addTextChangedListener(object :TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }
            override fun onTextChanged(nextText: CharSequence?, p1: Int, p2: Int, p3: Int) {
                adapterDataBringOnline?.filter(nextText.toString())

          /*      if (!searchViewSecond.performClick()) {
                    binding.tvNoItemFound.visibility=View.VISIBLE
                    return
                }*/
            }
            override fun afterTextChanged(p0: Editable?) {
            }
        })
        binding.buttonBackOnlineStock.setOnClickListener {
            requireActivity().onBackPressed()
        }
        binding.cartIconNBOStock.setOnClickListener {
            fragmentManager?.beginTransaction()?.replace(R.id.mainLayout,ShoppingCart())?.addToBackStack("")?.commit()
        }
        binding.filterStockTwo.setOnClickListener {
            fragmentManager?.beginTransaction()?.replace(R.id.mainLayout,FilterStock())?.addToBackStack("")?.commit()
        }

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
                    binding!!.tvNBringOnlineCountIcon.text = response.body()?.get("user_cart_count").toString()
                    adapterDataBringOnline?.notifyDataSetChanged()
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
                    binding!!.tvNBringOnlineCountIcon.text = response.body()?.get("user_cart_count").toString()
                    adapterDataBringOnline?.notifyDataSetChanged()
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

    override fun onStart() {
        super.onStart()
        (activity as MainActivity).binding!!.drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED)
    }

    private fun recyclerBringStockCall() {
        adapterDataBringOnline= AdapterDataBringOnline(this,requireActivity(),bringList)
        binding.rvOnlineBringStock.adapter=adapterDataBringOnline
    }
    private fun apiHitGems(currentPage: Int) {
        val appPrefs = AppPrefs(requireContext())

        val token = "Token" + " " + appPrefs.getToken("token")
        hashMapSetData()
        val retrofitData = RetrofitInstance().retrofitBuilder.gemsGetList(token,hashMap!!)
        //enqueue method
        retrofitData.enqueue(object : Callback<NaturalResponse> {

            override fun onResponse(call: Call<NaturalResponse>, response: Response<NaturalResponse>) {
                if (response.code() == 200) {
                    binding.tvNoItemFound.visibility=View.GONE
                    if (currentPage==1){
                        bringList.clear()
                        bringList.addAll(response.body()!!.data)
                    }
                    else{
                        bringList.addAll(response.body()!!.data)
                    }
                    for (i in bringList.indices){
                        binding.tvOnlineStockData.text = bringList[i].category.title
                    }
                    val listCount=bringList.size
                    isLoading=listCount==response.body()!!.totalCount
                    var list = response.body()
                    for (i in bringList.indices){
                       binding!!.tvNBringOnlineCountIcon.text =bringList[i].cartCount.toString()
                    }

                    binding.shimmerBOS.shimmerExploreNO.stopShimmer()
                    binding.shimmerBOS.shimmerExploreNO.visibility=View.GONE
                    binding.rvOnlineBringStock.visibility=View.VISIBLE
                    binding!!.pbNE.visibility = View.GONE
                    Toast.makeText(requireActivity(), "Successful", Toast.LENGTH_SHORT).show()


                    recyclerBringStockCall()
                   // adapterDataBringOnline?.notifyDataSetChanged()


                } else {

                    Toast.makeText(requireActivity(), "Error 400", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<NaturalResponse>, t: Throwable) {
                Toast.makeText(requireActivity(), "Error! " +t.message, Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun hashMapSetData(){
        hashMap!!["category_id"]=category_id!!
        hashMap!!["page"] = "1"

    }
}