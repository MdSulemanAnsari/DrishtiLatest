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
import com.application.drishtigems.databinding.FragmentContactUsBinding
import com.application.drishtigems.prefs.AppPrefs
import com.application.drishtigems.utils.CommonUtils
import com.google.gson.JsonObject
import kotlinx.android.synthetic.main.fragment_contact_us.*
import kotlinx.android.synthetic.main.fragment_login_screen.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.HashMap

class ContactUs : Fragment() {
lateinit var binding:FragmentContactUsBinding

    var hashMap: HashMap<String, String?> = HashMap()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState) }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
      binding=DataBindingUtil.inflate(inflater,R.layout.fragment_contact_us, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        onClick()
    }

    private fun onClick() {
        binding.backPressedCu.setOnClickListener {
           requireActivity().onBackPressed()
        }
        binding.buttonSubmitCu.setOnClickListener {
            EmailValidation()

        }

    }

    private fun EmailValidation() {
        val emailId = etEmailContactUs.text.toString()
        if ((TextUtils.isEmpty(emailId) || !CommonUtils.isValidEmail(emailId))) {
            etEmailContactUs.requestFocus();
            etEmailContactUs.error = "Enter valid Email Address"
            return
        }
        apiCallContactUs()
    }

    private fun apiCallContactUs() {
        hashMapContactUs()
        val appPrefs = AppPrefs(requireContext())
        val token = "Token" + " " + appPrefs.getToken("token")
        val retrofitData = RetrofitInstance().retrofitBuilder.contactUs(token, hashMap)
        //enqueue method
        retrofitData.enqueue(object : Callback<JsonObject> {
            override fun onResponse(call: Call<JsonObject>, response: Response<JsonObject>) {
                if (response.code() == 201) {
                    Toast.makeText(requireActivity(),  "Submitted", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(requireActivity(), "Error 400", Toast.LENGTH_SHORT).show()
                }
            }
            override fun onFailure(call: Call<JsonObject>, t: Throwable) {

                Toast.makeText(requireActivity(), "Error!", Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun hashMapContactUs() {
        hashMap["name"] =etNameContact.text.toString()
        hashMap["email"] = etEmailContactUs.text.toString()
        hashMap["subject"] = etSubjectContactUs.text.toString()
        hashMap["message"] = etMessageContactUs.text.toString()
    }


}