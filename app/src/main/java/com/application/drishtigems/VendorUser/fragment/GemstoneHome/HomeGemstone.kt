package com.application.drishtigems.VendorUser.fragment.GemstoneHome

import android.animation.ArgbEvaluator
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
import com.application.drishtigems.Network.RetrofitInstance
import com.application.drishtigems.Network.RetrofitObject
import com.application.drishtigems.Network.StaffNetwork.ApiModel.CategoryOnlineBringModel.CategoryOnlineBringModel
import com.application.drishtigems.Network.StaffNetwork.ApiModel.CategoryOnlineBringModel.CategoryOnlineBringModelItem
import com.application.drishtigems.Network.StaffNetwork.ApiModel.LoginUserModel.UserModel
import com.application.drishtigems.Network.StaffNetwork.ApiModel.MyProfileModel.MyProfileModel
import com.application.drishtigems.R
import com.application.drishtigems.VendorUser.VendorAdapter.GemstoneAdapter
import com.application.drishtigems.VendorUser.fragment.ShoppingCartVendor
import com.application.drishtigems.databinding.FragmentHomeGemstoneBinding
import com.application.drishtigems.prefs.AppPrefs
import com.application.drishtigems.ui.activity.MainActivity
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.navigation_drawer.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import android.animation.ValueAnimator
import android.animation.ValueAnimator.AnimatorUpdateListener

import android.graphics.drawable.GradientDrawable




class HomeGemstone : Fragment() {
lateinit var binding:FragmentHomeGemstoneBinding
    private var setDataBringOnlineGemstone: CategoryOnlineBringModel?=null
    private var bringOnlineListGemstone:ArrayList<CategoryOnlineBringModelItem>?= ArrayList()
    var gemstoneAdapter:GemstoneAdapter?=null

    var s1: UserModel? = null
    private  var update: MyProfileModel? =null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding=DataBindingUtil.inflate(inflater,R.layout.fragment_home_gemstone, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.shimmer.shimmerExplore.startShimmer()
        Handler().postDelayed({
            apiGemstones()
        },1000)

        onClick()

/*        val valueAnimator = ValueAnimator.ofObject(ArgbEvaluator(), -0x1, -0x873a07)

        val background = view.background as GradientDrawable
        currentAnimation.addUpdateListener(AnimatorUpdateListener { animator ->
            background.setColor(
                (animator.animatedValue as Int)!!) })
        currentAnimation.setDuration(300)
        currentAnimation.start()*/
    }


    private fun apiGemstones() {
        val appPrefs = AppPrefs(requireContext())

        val token = "Token" + " " + appPrefs.getToken("token")
        val retrofitData = RetrofitInstance().retrofitBuilder.myBringStockGet(token)
        //enqueue method
        retrofitData.enqueue(object : Callback<CategoryOnlineBringModel> {
            override fun onResponse(call: Call<CategoryOnlineBringModel>, response: Response<CategoryOnlineBringModel>
            ) {
                if (response.code() == 200) {
                    setDataBringOnlineGemstone=response.body()
                    bringOnlineListGemstone!!.clear()
                    bringOnlineListGemstone!!.addAll(response.body()!!)
                    for(i in bringOnlineListGemstone!!.indices){
                        binding.tvCartCountHg.text= bringOnlineListGemstone!![i].cartCount.toString()
                    }
                    binding.shimmer.shimmerExplore.stopShimmer()
                    binding.shimmer.shimmerExplore.visibility=View.GONE
                    binding.rvGemstone.visibility=View.VISIBLE
                    recyclerGemstone()
                } else {
                    Toast.makeText(requireActivity(), "Error 400", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<CategoryOnlineBringModel>, t: Throwable) {
                Toast.makeText(requireActivity(), "Error!", Toast.LENGTH_SHORT).show()
            }
        })
    }

/*    private fun sharePreferenceJeweller() {
        val appPref = AppPrefs(requireContext())
        val s1 = appPref.getModelKey("model")
        if(s1!!.groups[0].name == "Jeweller"){
            (activity as MainActivity).binding.drawerLayout.tvDrawerEmail.text=s1.email
            (activity as MainActivity).binding.drawerLayout.tvDrawerDataName.text="${s1.firstName} ${s1.lastName}"
            Glide.with(requireActivity()).load(RetrofitObject.IMAGE_URL+s1.image).into((activity as MainActivity).binding.includeLayout.imageDrawerPerson)
        }
    }*/

    private fun onClick() {
        binding.buttonHomeGemstone.setOnClickListener {
            val appPrefs = AppPrefs(requireContext())
            s1 = appPrefs.getModelKey("model")
            // sharePreferenceStaff()
            (activity as MainActivity).openDrawer()
            update = appPrefs.getUpdate("update")
            (activity as MainActivity).refreshDrawerData()

        }
        binding.cartIconHG.setOnClickListener {
            fragmentManager?.beginTransaction()?.replace(R.id.mainLayout, ShoppingCartVendor())?.addToBackStack("") ?.commit()
        }
    }

    private fun recyclerGemstone() {
        gemstoneAdapter=GemstoneAdapter(requireContext(),bringOnlineListGemstone)
        binding.rvGemstone.adapter=gemstoneAdapter
    }
    override fun onStart() {
        super.onStart()
        (activity as MainActivity).binding.drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED)
    }

}