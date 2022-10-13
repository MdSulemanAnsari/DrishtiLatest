package com.application.drishtigems.ui.fragment

import android.annotation.SuppressLint
import android.content.Context.MODE_APPEND
import android.graphics.Bitmap
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.application.drishtigems.R
import com.application.drishtigems.databinding.FragmentMyProfileBinding
import com.application.drishtigems.ui.fragment.homeFragment.HomeScreen
import kotlinx.android.synthetic.main.fragment_my_profile.*

import android.graphics.BitmapFactory
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.drawerlayout.widget.DrawerLayout
import com.application.drishtigems.Network.StaffNetwork.ApiModel.MyProfileModel.MyProfileModel
import com.application.drishtigems.Network.RetrofitInstance
import com.application.drishtigems.Network.RetrofitObject.IMAGE_URL
import com.application.drishtigems.Network.StaffNetwork.ApiModel.LoginUserModel.UserModel
import com.application.drishtigems.prefs.AppPrefs
import com.application.drishtigems.ui.activity.MainActivity
import com.bumptech.glide.Glide
import com.google.gson.Gson
import com.google.gson.JsonObject
import kotlinx.android.synthetic.main.fragment_edit_profile.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

class MyProfile : Fragment() {
    lateinit var binding: FragmentMyProfileBinding
    var passDataModel: MyProfileModel? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_my_profile, container, false)
        return binding.root
    }

    @RequiresApi(Build.VERSION_CODES.O)
    @SuppressLint("WrongConstant")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.progressBarProfile.visibility = View.VISIBLE
        onClick()
        apiHitMyProfileStaff()
        val appPref = AppPrefs(requireContext())
        val s1 = appPref.getModelKey("model")
        tvEmailProfile.text = s1!!.email


        // Storing the email using share preference
   /*     val sh = requireActivity().getSharedPreferences("SaveEmailAddress", MODE_APPEND)
        val s1 = sh.getString("email", "")*/
  /*      val s1 = (activity as MainActivity).getModelKey("token")
        tvEmailProfile.text = s1.toString()*/
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun decodeBase64(input: String?): Bitmap? {
        val decodedByte: ByteArray = Base64.getDecoder().decode(input)
        return BitmapFactory
            .decodeByteArray(decodedByte, 0, decodedByte.size)
    }

    private fun apiHitMyProfileStaff() {
        val appPrefs =AppPrefs(requireContext())

        val token = "Token" + " " + appPrefs.getToken("token")

        val retrofitData = RetrofitInstance().retrofitBuilder.myProfileGet(token)
        //enqueue method
        retrofitData.enqueue(object : Callback<MyProfileModel> {
            override fun onResponse(call: Call<MyProfileModel>, response: Response<MyProfileModel>
            ) {
                if (response.code() == 200) {
                    passDataModel=response.body()
                    appPrefs.setUpdate("update",passDataModel)
                    setDataProfile()
                    binding!!.progressBarProfile.visibility =View.GONE
                    Toast.makeText(requireActivity(), "Successful", Toast.LENGTH_SHORT).show()

                } else {
                    binding!!.progressBarProfile.visibility =View.GONE
                    Toast.makeText(requireActivity(), "Error 400", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<MyProfileModel>, t: Throwable) {
                Toast.makeText(requireActivity(), "Error!", Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun setDataProfile() {
        Glide.with(requireActivity()).load(IMAGE_URL+passDataModel?.image).into(binding!!.profilePic)
        binding.tvProfile.text="${passDataModel?.firstName} ${passDataModel?.lastName}"
        binding.tvPhoneProfile.text=passDataModel?.phoneNumber
        binding.tvDateOfBirth.text=passDataModel?.dob
        binding.tvAddressProfile.text="${passDataModel?.address} ${passDataModel?.addressCity!!.name} ${passDataModel?.addressState!!.name}${passDataModel?.addressPinCode}"
    }
    override fun onStart() {
        super.onStart()
        (activity as MainActivity).binding!!.drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED)
    }

    private fun setDataBundle() {
       val bundle=Bundle()
        val editProfile=EditProfile()
        bundle.putString("image",IMAGE_URL+passDataModel?.image)
        bundle.putString("firstNameProfile", passDataModel?.firstName)
        bundle.putString("lastNameProfile", passDataModel?.lastName)
        bundle.putString("phoneNumberProfile",passDataModel?.phoneNumber)
        bundle.putString("addressState_id", passDataModel?.addressState?.id.toString())
        bundle.putString("addressCity_id", passDataModel?.addressCity?.id.toString())
        bundle.putString("addressProfile",passDataModel?.address)
        bundle.putString("dobProfile",passDataModel?.dob)
        bundle.putString("pinCode",passDataModel?.addressPinCode.toString())
        editProfile.arguments=bundle
        fragmentManager?.beginTransaction()?.replace(R.id.mainLayout, editProfile)?.addToBackStack("") ?.commit()

    }
    private fun onClick() {
        binding.editProfile.setOnClickListener {
            setDataBundle()
        }
        binding.backPressedMyProfile.setOnClickListener {
            fragmentManager?.beginTransaction()?.replace(R.id.mainLayout, HomeScreen())?.addToBackStack("") ?.commit()
        }
    }
}