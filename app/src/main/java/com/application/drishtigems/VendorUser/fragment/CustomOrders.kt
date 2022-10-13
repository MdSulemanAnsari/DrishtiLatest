package com.application.drishtigems.VendorUser.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.databinding.DataBindingUtil
import androidx.drawerlayout.widget.DrawerLayout
import com.application.drishtigems.R
import com.application.drishtigems.VendorUser.VendorAdapter.CustomOrdersAdapter
import com.application.drishtigems.VendorUser.VendorModel.CustomOrderModel
import com.application.drishtigems.databinding.FragmentCustomOrdersBinding
import com.application.drishtigems.ui.activity.MainActivity
import kotlinx.android.synthetic.main.fragment_custom_orders.*
import android.widget.Toast

import com.application.drishtigems.Network.RetrofitInstance
import com.application.drishtigems.Network.StaffNetwork.ApiModel.CustomOrder.*
import com.application.drishtigems.prefs.AppPrefs
import com.google.gson.JsonObject
import com.rizlee.rangeseekbar.RangeSeekBar
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class CustomOrders : Fragment() {
    private var listWightId: Int? =null
    private var stoneListId: Int? =null
    lateinit var binding:FragmentCustomOrdersBinding
     var customOrderModelNew=CustomOrderModelNew()

    var jsonObject=JsonObject()
    var temList:ArrayList<String> =ArrayList()
    var temListStone:ArrayList<String> =ArrayList()
    var spinnerResponse:SpinnerCustomOrder?=null
    var listWight:ArrayList<WeightListItem> = ArrayList()
    var stoneList:ArrayList<StoneQualityListItem> = ArrayList()

    var customOrdersAdapter: CustomOrdersAdapter?=null
    var customOrderRecentModel: ArrayList<CustomOrderRecentModel> = ArrayList()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding=DataBindingUtil.inflate(inflater,R.layout.fragment_custom_orders, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onClick()
        rangeSeekBar()


        binding.pbCo.visibility=View.VISIBLE
        apiCallSpinnerWeight()
        apiCallRecentOrder()

    }
    private fun apiCallSpinnerWeight() {
        val appPrefs = AppPrefs(requireContext())
        val token = "Token" + " " + appPrefs.getToken("token")
        val retrofitData = RetrofitInstance().retrofitBuilder.getCustomOrderSpinnerData(token)
        retrofitData.enqueue(object : Callback<SpinnerCustomOrder> {
            override fun onResponse(call: Call<SpinnerCustomOrder>, response: Response<SpinnerCustomOrder>) {
                if (response.isSuccessful) {

                    spinnerResponse=response.body()
                    listWight.addAll(response.body()!!.weightList)
                    stoneList.addAll(response.body()!!.stoneQualityList)
                    for (i in listWight.indices){
                        temListStone.add(response.body()!!.weightList[i].name)
                        caratSpinner()}
                    for (i in stoneList.indices){
                        temList.add(response.body()!!.stoneQualityList[i].name)
                       qualitySpinner()}
                    binding.pbCo.visibility=View.GONE
                    Toast.makeText(requireActivity(),  "Successful", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(requireActivity(),  response.message(), Toast.LENGTH_SHORT).show()
                }
            }
            override fun onFailure(call: Call<SpinnerCustomOrder>, t: Throwable) {

                Toast.makeText(requireActivity(), "Error"+t.message, Toast.LENGTH_SHORT).show()
            }
        })
    }
    private fun apiCallPostCustomOrder() {
        hashMapPostCustomOrder()
        val appPrefs = AppPrefs(requireContext())
        val token = "Token" + " " + appPrefs.getToken("token")

        val retrofitData = RetrofitInstance().retrofitBuilder.sendCustomOrderData(token,customOrderModelNew)
        retrofitData.enqueue(object : Callback<JsonObject> {
            override fun onResponse(call: Call<JsonObject>, response: Response<JsonObject>) {
                if (response.code()==201) {
                    binding.pbCo.visibility=View.GONE
                    Toast.makeText(requireActivity(),  "Submitted", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(requireActivity(),  response.message(), Toast.LENGTH_SHORT).show()
                }
            }
            override fun onFailure(call: Call<JsonObject>, t: Throwable) {

                Toast.makeText(requireActivity(), "Error"+t.message, Toast.LENGTH_SHORT).show()
            }
        })
    }
    private fun apiCallRecentOrder() {
        val appPrefs = AppPrefs(requireContext())
        val token = "Token" + " " + appPrefs.getToken("token")

        val retrofitData = RetrofitInstance().retrofitBuilder.getCustomOrders(token)
        retrofitData.enqueue(object : Callback<ArrayList<CustomOrderRecentModel>> {
            override fun onResponse(call: Call<ArrayList<CustomOrderRecentModel>>, response: Response<ArrayList<CustomOrderRecentModel>>) {
                if (response.code()==200) {
                    customOrderRecentModel.clear()
                    customOrderRecentModel!!.addAll(response.body()!!)

                    recyclerCustomOrder()
                    binding.pbCo.visibility=View.GONE
                    Toast.makeText(requireActivity(),  "Successful", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(requireActivity(),  response.message(), Toast.LENGTH_SHORT).show()
                }
            }
            override fun onFailure(call: Call<ArrayList<CustomOrderRecentModel>>, t: Throwable) {

                Toast.makeText(requireActivity(), "Error"+t.message, Toast.LENGTH_SHORT).show()
            }
        })
    }
    private fun hashMapPostCustomOrder(){
        customOrderModelNew.gem_name=etGemName.text.toString()
        customOrderModelNew.weight_unit=listWightId.toString()
        customOrderModelNew.weight=textWeight.text.toString()
        customOrderModelNew.quantity=textQuantity.text.toString()
        customOrderModelNew.stone_quality=stoneListId.toString()
        customOrderModelNew.min_price=tvPriceStart.text.toString().replace("k","")
        customOrderModelNew.max_price=tvPriceEnd.text.toString().replace("k","")
    }
    private fun rangeSeekBar() {
        binding.rangeSeekBar.listenerRealTime = object : RangeSeekBar.OnRangeSeekBarRealTimeListener {
            override fun onValuesChanging(minValue: Float, maxValue: Float) { }

            override fun onValuesChanging(minValue: Int, maxValue: Int) {
                tvPriceStart.text = "${minValue.toString().replace("100000","")}k"
                tvPriceEnd.text = "${maxValue.toString().replace("100000","")}k"
            }
        }
    }

    private fun onClick() {
        binding.buttonCustomOder.setOnClickListener {
            (activity as MainActivity).openDrawer()
        }
        binding.picScCo.setOnClickListener {
            fragmentManager?.beginTransaction()?.replace(R.id.mainLayout, ShoppingCartVendor())?.addToBackStack("") ?.commit()
        }
        binding.buttonOrderSubmit.setOnClickListener {
            apiCallPostCustomOrder()
        }
    }

    private fun caratSpinner() {
        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, temListStone)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerCarat?.adapter = adapter
        spinnerCarat.onItemSelectedListener=object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                listWightId = listWight[p2].id
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {

            }

        }
    }
    private fun qualitySpinner() {
        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, temList)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerQuality?.adapter = adapter
        spinnerQuality.onItemSelectedListener=object :AdapterView.OnItemSelectedListener{
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                stoneListId=stoneList[p2].id
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {

            }

        }
    }
    private fun recyclerCustomOrder() {
        customOrdersAdapter= CustomOrdersAdapter(customOrderRecentModel)
        binding.rvCustomOrder.adapter=customOrdersAdapter
    }
    override fun onStart() {
        super.onStart()
        (activity as MainActivity).binding.drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED)
    }

}