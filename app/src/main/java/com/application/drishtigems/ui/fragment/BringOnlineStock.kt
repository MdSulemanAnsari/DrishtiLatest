package com.application.drishtigems.ui.fragment

import android.content.Context
import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.FragmentManager
import com.application.drishtigems.Network.StaffNetwork.ApiModel.CategoryOnlineBringModel.CategoryOnlineBringModel
import com.application.drishtigems.Network.StaffNetwork.ApiModel.CategoryOnlineBringModel.CategoryOnlineBringModelItem
import com.application.drishtigems.Network.RetrofitInstance
import com.application.drishtigems.R
import com.application.drishtigems.StaffAdapter.adapter.AdapterBringOnlineStock
import com.application.drishtigems.databinding.FragmentBringOnlineStockBinding
import com.application.drishtigems.prefs.AppPrefs
import com.application.drishtigems.ui.activity.MainActivity
import kotlinx.android.synthetic.main.adapter_bring_online_stock.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class BringOnlineStock : Fragment() {
    private var setDataBringOnline: CategoryOnlineBringModel?=null
    lateinit var binding:FragmentBringOnlineStockBinding
private var bringOnlineList:ArrayList<CategoryOnlineBringModelItem> = ArrayList()
    var adapterBringOnlineStock:AdapterBringOnlineStock?=null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
         binding=DataBindingUtil.inflate(inflater,R.layout.fragment_bring_online_stock, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.shimmer.shimmerExplore.startShimmer()
        (context as MainActivity).closedDrawer()
        Handler().postDelayed({
            apiHitCategory()
        },1000)

        onclick()
    }

    private fun onclick() {
        binding.buttonBringOnlineStock.setOnClickListener {
            (activity as MainActivity).openDrawer()
        }
        binding.cartBringOnline.setOnClickListener {
           fragmentManager?.beginTransaction()?.replace(R.id.mainLayout,ShoppingCart())?.addToBackStack("")?.commit()
        }
    }

    private fun bringRecyclerViewCall() {
        adapterBringOnlineStock=AdapterBringOnlineStock(requireActivity(),bringOnlineList)
        binding.rvBringStockItem.adapter=adapterBringOnlineStock
    }
    private fun apiHitCategory() {
        val appPrefs = AppPrefs(requireContext())

        val token = "Token" + " " + appPrefs.getToken("token")
        val retrofitData = RetrofitInstance().retrofitBuilder.myBringStockGet(token)
        //enqueue method
        retrofitData.enqueue(object : Callback<CategoryOnlineBringModel> {
            override fun onResponse(call: Call<CategoryOnlineBringModel>, response: Response<CategoryOnlineBringModel>
            ) {
                if (response.code() == 200) {
                    setDataBringOnline=response.body()
                    bringOnlineList.clear()
                    bringOnlineList.addAll(response.body()!!)
                    for(i in bringOnlineList.indices){
                        binding.tvBringCountIcon.text= bringOnlineList[i].cartCount.toString()

                    }
                    binding.shimmer.shimmerExplore.stopShimmer()
                    binding.shimmer.shimmerExplore.visibility=View.GONE
                    binding.rvBringStockItem.visibility=View.VISIBLE
                    bringRecyclerViewCall()

                    Toast.makeText(requireActivity(), "Successful", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(requireActivity(), "Error 400", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<CategoryOnlineBringModel>, t: Throwable) {
                Toast.makeText(requireActivity(), "Error!", Toast.LENGTH_SHORT).show()
            }
        })
    }
    override fun onStart() {
        super.onStart()
        (activity as MainActivity).binding!!.drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED)
    }

}