package com.application.drishtigems.ui.fragment

import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.application.drishtigems.Network.RetrofitInstance
import com.application.drishtigems.Network.StaffNetwork.ApiModel.RecentRequest.DataItem
import com.application.drishtigems.Network.StaffNetwork.ApiModel.RecentRequest.RecentRequestResponse
import com.application.drishtigems.Network.StaffNetwork.ApiModel.RecentRequest.TotalApprovedExpense
import com.application.drishtigems.Pagination.PaginationRecycler
import com.application.drishtigems.R
import com.application.drishtigems.StaffAdapter.adapter.AdapterRecentRequest
import com.application.drishtigems.databinding.FragmentRecentRequestsBinding
import com.application.drishtigems.prefs.AppPrefs
import com.application.drishtigems.ui.activity.MainActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RecentRequests : Fragment() {
lateinit var binding:FragmentRecentRequestsBinding
var adapterRecentRequests:AdapterRecentRequest?=null
    var recentRequest:RecentRequestResponse?=null
    var priceResponse: TotalApprovedExpense?=null
    var recentList:ArrayList<DataItem>?= ArrayList()

    private var currentPage: Int=0
    var isLoading =false
    var isLastPage =false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding=DataBindingUtil.inflate(inflater,R.layout.fragment_recent_requests, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        onClick()
        paginationRecentRes()

    }
    private fun paginationRecentRes() {
        val layoutManager = LinearLayoutManager(activity, RecyclerView.VERTICAL, false)
        binding.rvRecentRequest.layoutManager = layoutManager

        binding.rvRecentRequest.addOnScrollListener(object : PaginationRecycler(layoutManager) {
            override fun loadMoreItems() {
                isLoading = true
                currentPage++

                binding.paginationRecentRequest.visibility = View.VISIBLE

                Handler().postDelayed(Runnable {
                    apiCallRecentRequest(currentPage)
                    binding.paginationRecentRequest.visibility = View.GONE
                }, 2000)

            }

            override fun isLastPage(): Boolean {
                return isLastPage
            }

            override fun isLoading(): Boolean {
                return isLoading
            }
        })

        apiCallRecentRequest(currentPage)

    }

    private fun setDataResponse() {
        binding.totalAmountRecentRequest.text = priceResponse?.priceSum.toString()
    }

    private fun onClick() {
        binding.backPressedRecentRequest.setOnClickListener {
            requireActivity().onBackPressed()
        }
    }

    private fun apiCallRecentRequest(currentPage: Int) {
        val appPrefs = AppPrefs(requireContext())
        val token = "Token" + " " + appPrefs.getToken("token")
        val retrofitData = RetrofitInstance().retrofitBuilder.recentRequest(1,token)
        retrofitData.enqueue(object : Callback<RecentRequestResponse> {
            override fun onResponse(call: Call<RecentRequestResponse>, response: Response<RecentRequestResponse>) {
                if (response.isSuccessful) {
                    recentRequest=response.body()
                    recentList!!.addAll(response.body()!!.data)

                    if (currentPage == 1){
                        recentList!!.clear()
                        recentList!!.addAll(response.body()!!.data)
                    }
                    else
                    {
                        recentList!!.addAll(response.body()!!.data)
                    }
                    val listCount=recentList!!.size
                    isLoading=listCount==response.body()!!.totalCount

                    var list = response.body()

                    recyclerViewRecentRequest()
                    adapterRecentRequests!!.notifyDataSetChanged()
                    binding.paginationRecentRequest.visibility = View.GONE

                    setDataResponse()
                    Toast.makeText(requireActivity(),  "Successful", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(requireActivity(),  response.message(), Toast.LENGTH_SHORT).show()
                }
            }
            override fun onFailure(call: Call<RecentRequestResponse>, t: Throwable) {

                Toast.makeText(requireActivity(), "Error"+t.message, Toast.LENGTH_SHORT).show()
            }
        })
    }
    override fun onStart() {
        super.onStart()
        (activity as MainActivity).binding!!.drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED)
    }
    private fun recyclerViewRecentRequest() {
        adapterRecentRequests= AdapterRecentRequest(recentList)
        binding.rvRecentRequest.adapter=adapterRecentRequests
    }
}