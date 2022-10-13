package com.application.drishtigems.VendorUser.fragment.Settings

import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.application.drishtigems.Network.RetrofitInstance
import com.application.drishtigems.R
import com.application.drishtigems.databinding.FragmentChangePasswordBinding
import com.application.drishtigems.prefs.AppPrefs
import com.google.gson.JsonObject
import kotlinx.android.synthetic.main.fragment_change_password.*
import kotlinx.android.synthetic.main.fragment_contact_us.*
import kotlinx.android.synthetic.main.fragment_contact_us.etNameContact
import kotlinx.android.synthetic.main.fragment_login_screen.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.HashMap

class ChangePassword : Fragment() {
lateinit var binding:FragmentChangePasswordBinding
    var hashMap: HashMap<String, String?> = HashMap()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
       binding=DataBindingUtil.inflate(inflater,R.layout.fragment_change_password, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onClick()
    }

    private fun onClick() {
        binding.backPressedCp.setOnClickListener {
            requireActivity().onBackPressed()
        }
        binding.buttonUpdateCp.setOnClickListener {

            if (validation()){
                apiCallChangePassword()
            }
        }
    }

    private fun validation(): Boolean {

        binding.apply {
            when {
                etCurrentPassword.text.toString().isNullOrEmpty() -> showToast("Please Enter Current Password...")
                etNewPassword.text.toString()
                    .isNullOrEmpty() -> showToast("Please Enter  New Password...")
                etConfirmPassword.text.toString()
                    .isNullOrEmpty() -> showToast("Please Enter Confirm Password...")
                (etNewPassword.text.toString() != etConfirmPassword.text.toString()) -> showToast("Please Enter correct Password")
                else -> return true
            }
            return false
        }
    }

    private fun showToast(s: String) {
        Toast.makeText(requireActivity(),s, Toast.LENGTH_SHORT).show()
    }

    private fun apiCallChangePassword() {
        hashMapChangePassword()
        val appPrefs = AppPrefs(requireContext())
        val token = "Token" + " " + appPrefs.getToken("token")
        val retrofitData = RetrofitInstance().retrofitBuilder.changePassword(token, hashMap)
        retrofitData.enqueue(object : Callback<JsonObject> {
            override fun onResponse(call: Call<JsonObject>, response: Response<JsonObject>) {
                if (response.isSuccessful) {
                    Toast.makeText(requireActivity(),  "Updated", Toast.LENGTH_SHORT).show()
                    etCurrentPassword.setText("")
                    etNewPassword.setText("")
                    etConfirmPassword.setText("")

                }else if(response.code() == 400){
                    Toast.makeText(requireActivity(),  "current password is wrong", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(requireActivity(),  response.message(), Toast.LENGTH_SHORT).show()
                }
            }
            override fun onFailure(call: Call<JsonObject>, t: Throwable) {

                Toast.makeText(requireActivity(), "Error!", Toast.LENGTH_SHORT).show()
            }
        })
    }
    private fun hashMapChangePassword() {
        hashMap["old_password"]=etCurrentPassword.text.toString()
        hashMap["new_password"]=etNewPassword.text.toString()
    }
}