package com.application.drishtigems.VendorUser.fragment.GemstoneHome.HomeGemstones

import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.drawerlayout.widget.DrawerLayout
import com.application.drishtigems.Network.RetrofitInstance
import com.application.drishtigems.Network.StaffNetwork.ApiModel.natural.DataItem
import com.application.drishtigems.Network.StaffNetwork.ApiModel.natural.NaturalResponse
import com.application.drishtigems.R
import com.application.drishtigems.VendorUser.VendorAdapter.BringOnlineStockAdapter.EmeraldItemAdapterVendor
import com.application.drishtigems.databinding.FragmentNaturalEmeraldStockVendorBinding
import com.application.drishtigems.ui.activity.MainActivity
import kotlinx.android.synthetic.main.fragment_natural_emerald_stock_vendor.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.application.drishtigems.Network.jawellerNetwork.ApiModelJeweller.AddtoWishListModel

import com.application.drishtigems.Pagination.PaginationRecycler
import com.application.drishtigems.VendorUser.fragment.ShoppingCartVendor
import com.application.drishtigems.prefs.AppPrefs
import com.google.gson.JsonObject


class NaturalEmeraldStockVendor : Fragment() {
    lateinit var binding:FragmentNaturalEmeraldStockVendorBinding
    var emeraldItemAdapterVendor: EmeraldItemAdapterVendor?=null
    private var  bringListJeweller:ArrayList<DataItem> = ArrayList()
    var hashMap:HashMap<String,Any>?= HashMap()


    private var getIdTittle: String? = null
    private var currentPage: Int=1
    var isLoading =false
    var isLastPage =false

    companion object{
        var  category_id_vendor: String? = null
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
      binding=DataBindingUtil.inflate(inflater,R.layout.fragment_natural_emerald_stock_vendor, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.shimmerBOS.shimmerExploreNO.startShimmer()
        if (arguments!=null){
            getIdTittle=requireArguments().getString("tittle")
            category_id_vendor = requireArguments().getInt("id").toString()
            tvDataItemName.text=getIdTittle
        }
        val layoutManager = LinearLayoutManager(activity, RecyclerView.VERTICAL, false)
        binding!!.rvEmeraldItemVendor.layoutManager = layoutManager

        binding!!.rvEmeraldItemVendor.addOnScrollListener(object : PaginationRecycler(layoutManager) {
            override fun loadMoreItems() {
                isLoading = true
                currentPage++

                binding!!.paginationJewellerOne.visibility = View.VISIBLE

                Handler().postDelayed(Runnable {
                    apiHitGemsJeweller(currentPage)
                    binding!!.paginationJewellerOne.visibility = View.GONE
                }, 2000)

            }

            override fun isLastPage(): Boolean {
                return isLastPage
            }

            override fun isLoading(): Boolean {
                return isLoading
            }
        })

        Handler().postDelayed({
            apiHitGemsJeweller(currentPage)
        },1000)

        onClick()
    }
    private fun apiHitGemsJeweller(currentPage: Int?) {
        val appPrefs = AppPrefs(requireContext())

        val token = "Token" + " " + appPrefs.getToken("token")
        hashMapSetDataJeweller()
        val retrofitData = RetrofitInstance().retrofitBuilder.gemsGetList(token,hashMap!!)
        //enqueue method
        retrofitData.enqueue(object : Callback<NaturalResponse> {

            override fun onResponse(call: Call<NaturalResponse>, response: Response<NaturalResponse>) {
                if (response.code() == 200) {
                    if (currentPage == 1){
                        bringListJeweller.clear()
                        bringListJeweller.addAll(response.body()!!.data)
                    }
                    else
                    {
                        bringListJeweller.addAll(response.body()!!.data)
                    }
                    for (i in bringListJeweller.indices){
                        binding.tvDataItemName.text = bringListJeweller[i].category.title
                    }
                    val listCount=bringListJeweller.size
                    isLoading=listCount==response.body()!!.totalCount

                    var list = response.body()
                    binding.shimmerBOS.shimmerExploreNO.stopShimmer()
                    binding.shimmerBOS.shimmerExploreNO.visibility=View.GONE
                    binding.rvEmeraldItemVendor.visibility=View.VISIBLE
                    binding.paginationJewellerOne.visibility = View.GONE
                    recyclerEmerald()

                    for(i in bringListJeweller!!.indices){
                        binding.tvBringStockCountIcon.text= bringListJeweller!![i].cartCount.toString()
                    }
                    emeraldItemAdapterVendor?.notifyDataSetChanged()

                    Toast.makeText(requireActivity(), "Successful", Toast.LENGTH_SHORT).show()
                } else {
                  //  binding.paginationOne.visibility=View.VISIBLE
                    Toast.makeText(requireActivity(), "Error 400", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<NaturalResponse>, t: Throwable) {
                Toast.makeText(requireActivity(), "Error! " +t.message, Toast.LENGTH_SHORT).show()
            }
        })
    }
    fun removeFromListApiWishList(id: Int, position: Int) {
        val appPrefs = AppPrefs(requireContext())

        val token = "Token" + " " + appPrefs.getToken("token")
        val retrofitData = RetrofitInstance().retrofitBuilder.removeWishlist(token,id)
        //enqueue method
        retrofitData.enqueue(object : Callback<JsonObject> {

            override fun onResponse(call: Call<JsonObject>, response: Response<JsonObject>) {
                if (response.code() == 200) {

                    emeraldItemAdapterVendor!!.notifyDataSetChanged()

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
    fun addToWishListApiHit(id: Int, position: Int) {
        val appPrefs = AppPrefs(requireContext())
        val token = "Token" + " " + appPrefs.getToken("token")
        val retrofitData = RetrofitInstance().retrofitBuilder.addToWishlist(token,id)
        //enqueue method
        retrofitData.enqueue(object : Callback<AddtoWishListModel> {

            override fun onResponse(call: Call<AddtoWishListModel>, response: Response<AddtoWishListModel>) {
                if (response.code() == 201) {

                    Toast.makeText(requireActivity(), "Add To WishList", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(requireActivity(), "Already Add To WishList", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<AddtoWishListModel>, t: Throwable) {
                Toast.makeText(requireActivity(), "Error! " + t.message, Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun onClick() {
    /*    binding.noticeIconVendor.setOnClickListener {
            fragmentManager?.beginTransaction()?.replace(R.id.mainLayout, NoticeEmeraldVendor())?.addToBackStack("") ?.commit()
        }*/
        binding.cartBringOnline.setOnClickListener {
            fragmentManager?.beginTransaction()?.replace(R.id.mainLayout, ShoppingCartVendor())?.addToBackStack("") ?.commit()
        }
        binding.filterStockVendor.setOnClickListener {
            fragmentManager?.beginTransaction()?.replace(R.id.mainLayout, FilterStockVendor())?.addToBackStack("") ?.commit()
        }
        binding.backPressedNeVendorXYZ.setOnClickListener {
            requireActivity().onBackPressed()
        }
    }

    private fun recyclerEmerald() {
        emeraldItemAdapterVendor= EmeraldItemAdapterVendor(this,requireActivity(),bringListJeweller)
        binding.rvEmeraldItemVendor.adapter=emeraldItemAdapterVendor
    }
    private fun hashMapSetDataJeweller(){
        hashMap!!["category_id"]=category_id_vendor!!
        hashMap!!["page"] = "1"

    }
    override fun onStart() {
        super.onStart()
        (activity as MainActivity).binding!!.drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED)
    }


}