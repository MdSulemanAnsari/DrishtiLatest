package com.application.drishtigems.VendorUser.fragment.MyProfileVendor

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.application.drishtigems.Network.RetrofitInstance
import com.application.drishtigems.Network.RetrofitObject
import com.application.drishtigems.Network.StaffNetwork.ApiModel.MyProfileModel.MyProfileModel
import com.application.drishtigems.R
import com.application.drishtigems.VendorUser.fragment.GemstoneHome.HomeGemstone
import com.application.drishtigems.VendorUser.fragment.MyProfileVendor.MyProfileTabLayout.MyAddressList
import com.application.drishtigems.VendorUser.fragment.MyProfileVendor.MyProfileTabLayout.MyWishList

import com.application.drishtigems.databinding.FragmentMyProfileVendorBinding
import com.application.drishtigems.prefs.AppPrefs
import com.application.drishtigems.ui.activity.MainActivity
import com.application.drishtigems.ui.fragment.EditProfile
import com.bumptech.glide.Glide
import com.google.android.material.tabs.TabLayoutMediator

import kotlinx.android.synthetic.main.fragment_my_profile_vendor.*

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MyProfileVendor : Fragment() {
lateinit var binding:FragmentMyProfileVendorBinding
    var passDataModelJeweller: MyProfileModel? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding=DataBindingUtil.inflate(inflater,R.layout.fragment_my_profile_vendor, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.progressBarProfileJeweller.visibility = View.VISIBLE
        onClick()
        apiHitMyProfileJeweller()
        val appPref = AppPrefs(requireContext())
        val s1 = appPref.getModelKey("model")
        tvEmailProfileVendor.text = s1!!.email

        setupViewPager(viewPagerMyProfileVendor)

        //view pager2
        TabLayoutMediator(tabLayoutMyProfileVendor, viewPagerMyProfileVendor) { tab, position ->
            when (position) {
                0 -> tab.text = "My WishList"
                1 ->tab.text = "My Address"
            }
        }.attach()
    }



    private fun setupViewPager(viewPager: ViewPager2) {
        val adapter = ViewPagerAdapter(requireActivity())
        viewPager.adapter = adapter
    }
    internal class ViewPagerAdapter(manager: FragmentActivity?) :
        FragmentStateAdapter(manager!!) {

        override fun getItemCount(): Int {
            return 2
        }

        override fun createFragment(position: Int): Fragment {
            var frag = Fragment()
            when (position) {
                0 -> frag = MyWishList()
                1 -> frag = MyAddressList()

            }
            return frag
        }
    }


    private fun onClick() {
     binding.editProfileVendor.setOnClickListener {
         setDataBundleJeweller()
     }
        binding.backPressedMyProfileVendor.setOnClickListener {
            fragmentManager?.beginTransaction()?.replace(R.id.mainLayout, HomeGemstone())?.addToBackStack("") ?.commit()
        }
    }

    private fun setDataBundleJeweller() {
        val bundle=Bundle()
        val editProfile=EditProfile()
        bundle.putString("image",RetrofitObject.IMAGE_URL+passDataModelJeweller!!.image)
        bundle.putString("firstNameProfile", passDataModelJeweller?.firstName)
        bundle.putString("lastNameProfile", passDataModelJeweller?.lastName)
        bundle.putString("phoneNumberProfile",passDataModelJeweller?.phoneNumber)
        bundle.putString("addressState_id", passDataModelJeweller?.addressState?.id.toString())
        bundle.putString("addressCity_id", passDataModelJeweller?.addressCity?.id.toString())
        bundle.putString("addressProfile",passDataModelJeweller?.address)
        bundle.putString("dobProfile",passDataModelJeweller?.dob)
        bundle.putString("pinCode", passDataModelJeweller?.addressPinCode.toString())
        editProfile.arguments=bundle

        fragmentManager?.beginTransaction()?.replace(R.id.mainLayout, editProfile)?.addToBackStack("") ?.commit()
    }

    private fun apiHitMyProfileJeweller() {
        val appPrefs = AppPrefs(requireContext())

        val token = "Token" + " " + appPrefs.getToken("token")

        val retrofitData = RetrofitInstance().retrofitBuilder.myProfileGet(token)
        //enqueue method
        retrofitData.enqueue(object : Callback<MyProfileModel> {
            override fun onResponse(call: Call<MyProfileModel>, response: Response<MyProfileModel>) {
                if (response.code() == 200) {
                    passDataModelJeweller=response.body()
                    appPrefs.setUpdate("update",passDataModelJeweller)
                    setDataProfileJeweller()
                    binding!!.progressBarProfileJeweller.visibility =View.GONE
                  //  Toast.makeText(requireActivity(), "Successful", Toast.LENGTH_SHORT).show()

                } else {
                    binding!!.progressBarProfileJeweller.visibility =View.GONE
                    Toast.makeText(requireActivity(), "Error 400", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<MyProfileModel>, t: Throwable) {
                Toast.makeText(requireActivity(), "Error!", Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun setDataProfileJeweller() {
        Glide.with(requireActivity()).load(RetrofitObject.IMAGE_URL+passDataModelJeweller?.image).into(binding!!.profilePicVendor)
        binding.tvProfileNameVendor.text="${passDataModelJeweller?.firstName} ${passDataModelJeweller?.lastName}"
        binding.tvJewellersName.text=passDataModelJeweller?.firstName
        binding.tvPhoneProfileVendor.text=passDataModelJeweller?.phoneNumber
        binding.tvDateOfBirth.text=passDataModelJeweller?.dob
        binding.tvAddressProfileVendor.text="${passDataModelJeweller?.address} ${passDataModelJeweller?.addressCity!!.name} ${passDataModelJeweller?.addressState!!.name}${passDataModelJeweller?.addressPinCode}"
        binding.tvGst.text= passDataModelJeweller?.gstNumber
    }
    override fun onStart() {
        super.onStart()
        (activity as MainActivity).binding!!.drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED)
    }

}