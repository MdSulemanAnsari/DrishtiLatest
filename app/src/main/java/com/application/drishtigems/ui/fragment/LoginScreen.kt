package com.application.drishtigems.ui.base.fragment

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.InputType
import android.text.TextWatcher
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.databinding.DataBindingUtil
import androidx.drawerlayout.widget.DrawerLayout
import com.application.drishtigems.R
import com.application.drishtigems.databinding.FragmentLoginScreenBinding
import com.application.drishtigems.ui.activity.MainActivity
import com.application.drishtigems.ui.fragment.homeFragment.HomeScreen
import kotlinx.android.synthetic.main.fragment_login_screen.*
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.MotionEvent
import android.view.View.OnTouchListener
import kotlinx.android.synthetic.main.fragment_my_profile.*
import android.content.SharedPreferences
import android.text.TextUtils
import android.util.Log
import com.application.drishtigems.Network.StaffNetwork.ApiModel.LoginUserModel.UserModel
import com.application.drishtigems.Network.RetrofitInstance
import com.application.drishtigems.Network.StaffNetwork.ApiModel.LoginUserModel.Group
import com.application.drishtigems.Network.StaffNetwork.ApiModel.MyProfileModel.MyProfileModel
import com.application.drishtigems.Network.StaffNetwork.ApiModel.natural.DataItem
import com.application.drishtigems.VendorUser.fragment.GemstoneHome.HomeGemstone
import com.application.drishtigems.prefs.AppPrefs
import com.application.drishtigems.utils.CommonUtils
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlinx.android.synthetic.main.fragment_dialog_emerald.*
import java.math.BigInteger


class LoginScreen : Fragment() {
    var binding: FragmentLoginScreenBinding? = null
    var isPasswordVisible: Boolean? = null
    var userModelStaff: UserModel? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_login_screen, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        onClick()
        initUI()
    }
    @SuppressLint("ClickableViewAccessibility")
    private fun initUI() {

        binding?.etEmail?.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable) {}
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                if (s.isNotEmpty()) {
                    binding!!.etPhoneNumber.isEnabled = false
                    binding!!.etPhoneNumber.inputType = InputType.TYPE_CLASS_NUMBER
                } else {
                    binding!!.etPhoneNumber.isEnabled = true
                    binding!!.etPhoneNumber.setHintTextColor(resources.getColor(R.color.adamColor))
                }
            }
        })
        binding?.etPhoneNumber?.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable) {}
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
            }
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                if (s.isNotEmpty()) {
                    binding!!.etEmail.isEnabled = false
                    binding!!.etEmail.inputType = InputType.TYPE_CLASS_TEXT
                } else {
                    binding!!.etEmail.isEnabled = true
                    binding!!.etEmail.setHintTextColor(resources.getColor(R.color.adamColor))
                }
            }
        })
        //eye icon toggle
        etPassword.setOnTouchListener(OnTouchListener { v, event ->
            val RIGHT = 2
            if (event.action == MotionEvent.ACTION_UP) {
                if (event.rawX >= etPassword.right - etPassword.compoundDrawables[RIGHT].bounds.width()) {
                    val selection: Int = etPassword.selectionEnd
                    if (isPasswordVisible == true) {
                        etPassword.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.eye, 0)
                        // hide Password
                        etPassword.transformationMethod = PasswordTransformationMethod.getInstance()
                        isPasswordVisible = false
                    } else {
                        etPassword.setCompoundDrawablesWithIntrinsicBounds(
                            0,
                            0,
                            R.drawable.eye_see,
                            0
                        )
                        // show Password
                        etPassword.transformationMethod =
                            HideReturnsTransformationMethod.getInstance()
                        isPasswordVisible = true
                    }
                    etPassword.setSelection(selection)
                    return@OnTouchListener true
                }
            }
            false
        })
    }

    private fun validation(): Boolean {

        //Email Validation
        val emailId = etEmail.text.toString()
        val phoneNumber = etPhoneNumber.text.toString()

        //Email validation pattern
        if (TextUtils.isEmpty(phoneNumber) && (TextUtils.isEmpty(emailId) || !CommonUtils.isValidEmail(emailId))) {
            etEmail.requestFocus();
            etEmail.error = "Enter valid Email Address"
            return false
        }
        //phone validation pattern
        if (TextUtils.isEmpty(emailId) && (TextUtils.isEmpty(phoneNumber) || !CommonUtils.isValidPhone(BigInteger(phoneNumber)))) {
            etPhoneNumber.requestFocus()
            etPhoneNumber.error = "Enter Correct Number"
            return false
        }
        //password validation
        if (etPassword.text.toString().isEmpty()) {
            etEmail.requestFocus()
            etPassword.error = "Enter Correct Your Password"
            return false
        }
        return true
    }
    override fun onStart() {
        super.onStart()
        (activity as MainActivity).binding.drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED)
    }
    private fun onClick() {
        binding?.buttonLogin?.setOnClickListener {
            binding!!.pbLogin.visibility=View.VISIBLE
            if (validation()) {
                apiLogin()
            } else {
                return@setOnClickListener
            }
        }
    }
    fun apiLogin() {
        val email = etEmail.text.toString()
        val password = etPassword.text.toString()
        val phone_number = etPhoneNumber.text.toString()
        val retrofitData = RetrofitInstance().retrofitBuilder.loginPost(email, password, phone_number)
        //enqueue method
        retrofitData.enqueue(object : Callback<UserModel> {
            override fun onResponse(call: Call<UserModel>, response: Response<UserModel>) {
                if (response.code() == 200) {
                    userModelStaff=response.body()
                    val appPrefs = AppPrefs(requireContext())
                    appPrefs.setModelKey("model",userModelStaff)
                    appPrefs.setToken("token",userModelStaff!!.token)
                    (activity as MainActivity).checkVendorStaff()
                    binding!!.pbLogin.visibility=View.GONE
                    Toast.makeText(requireActivity(), "Login Successfully", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(requireActivity(), "Error 400", Toast.LENGTH_SHORT).show()
                }
            }
            override fun onFailure(call: Call<UserModel>, t: Throwable) {
                Toast.makeText(requireActivity(), "Error!"+t.message, Toast.LENGTH_SHORT).show()
            }
        })
    }
}