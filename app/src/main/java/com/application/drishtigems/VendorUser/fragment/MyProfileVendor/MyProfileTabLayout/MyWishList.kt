package com.application.drishtigems.VendorUser.fragment.MyProfileVendor.MyProfileTabLayout

import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.application.drishtigems.Network.RetrofitInstance
import com.application.drishtigems.Network.jawellerNetwork.ApiModelJeweller.MyProfileVendor.DataItem
import com.application.drishtigems.Network.jawellerNetwork.ApiModelJeweller.MyProfileVendor.GetWishListNewResp
import com.application.drishtigems.Pagination.PaginationRecycler

import com.application.drishtigems.R
import com.application.drishtigems.VendorUser.VendorAdapter.MyProfileVendor.MyWishListAdapter
import com.application.drishtigems.databinding.FragmentMyWishListBinding
import com.application.drishtigems.prefs.AppPrefs
import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MyWishList : Fragment()/*, MyWishListAdapter.ItemClick*/ {
    lateinit var binding: FragmentMyWishListBinding
    var myWishListAdapter: MyWishListAdapter? = null
    var getWishListData: GetWishListNewResp? = null
    private var wishListGet: ArrayList<DataItem> = ArrayList()
    private var currentPage: Int = 1
    var isLoading = false
    var isLastPage = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_my_wish_list, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val layoutManager = LinearLayoutManager(activity, RecyclerView.VERTICAL, false)
        binding!!.rvMyWishList.layoutManager = layoutManager

        binding!!.rvMyWishList.addOnScrollListener(object : PaginationRecycler(layoutManager) {
            override fun loadMoreItems() {
                isLoading = true
                currentPage++

                binding!!.pbMyWishListAddItem.visibility = View.VISIBLE


                Handler().postDelayed(Runnable {
                    apiMyWishList(currentPage)
                    binding!!.pbMyWishListAddItem.visibility = View.GONE


                }, 2000)

            }

            override fun isLastPage(): Boolean {
                return isLastPage
            }

            override fun isLoading(): Boolean {
                return isLoading
            }
        })

        apiMyWishList(currentPage)


    }

    private fun apiMyWishList(currentPage: Int) {
        val appPrefs = AppPrefs(requireContext())
        val token = "Token" + " " + appPrefs.getToken("token")
        val retrofitData = RetrofitInstance().retrofitBuilder.getWishlist(1, token)
        //enqueue method

        retrofitData.enqueue(object : Callback<GetWishListNewResp> {
            override fun onResponse(call: Call<GetWishListNewResp>, response: Response<GetWishListNewResp>) {
                if (response.code() == 200) {
                    binding.tvShowItem.visibility=View.VISIBLE
                    getWishListData = response.body()
                    if (currentPage == 1) {
                        wishListGet.clear()
                        wishListGet.addAll(response.body()!!.data)

                    } else {
                        wishListGet.addAll(response.body()!!.data)

                    }
                    val listCount = wishListGet.size
                    isLoading = listCount == response.body()!!.totalPages

                    var list = response.body()

                    recyclerWishList()

                    myWishListAdapter?.notifyDataSetChanged()

                    binding.pbMyWishListAddItem.visibility = View.GONE

                    Toast.makeText(requireActivity(), "Success", Toast.LENGTH_SHORT).show()

                } else {

                    Toast.makeText(requireActivity(), "Error 400", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<GetWishListNewResp>, t: Throwable) {
                Toast.makeText(requireActivity(), "Ansari! " + t.message, Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun recyclerWishList() {
        myWishListAdapter = MyWishListAdapter(this, wishListGet)
        binding.rvMyWishList.adapter = myWishListAdapter
    }

    fun removeFromListApi(id: Int, position: Int) {
        val appPrefs = AppPrefs(requireContext())

        val token = "Token" + " " + appPrefs.getToken("token")
        val retrofitData = RetrofitInstance().retrofitBuilder.removeWishlist(token,id)
        //enqueue method

        retrofitData.enqueue(object : Callback<JsonObject> {

            override fun onResponse(call: Call<JsonObject>, response: Response<JsonObject>) {
                if (response.code() == 200) {
                    wishListGet.removeAt(position)
                    myWishListAdapter!!.notifyItemChanged(position)
                    myWishListAdapter!!.notifyDataSetChanged()
                    binding.tvShowItem.visibility=View.VISIBLE
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

 /*   override fun clickId(id: Int) {
        removeFromListApi(id)

    }*/
}