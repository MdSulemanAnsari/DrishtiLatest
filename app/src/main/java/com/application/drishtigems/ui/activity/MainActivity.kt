package com.application.drishtigems.ui.activity

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.content.SharedPreferences
import android.graphics.BitmapFactory
import android.graphics.Color
import android.graphics.Rect
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.core.view.GravityCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.application.drishtigems.Network.RetrofitObject
import com.application.drishtigems.Network.StaffNetwork.ApiModel.LoginUserModel.UserModel
import com.application.drishtigems.Network.StaffNetwork.ApiModel.MyProfileModel.MyProfileModel
import com.application.drishtigems.R
import com.application.drishtigems.StaffAdapter.adapter.AdapterDrawer
import com.application.drishtigems.databinding.ActivityMainBinding
import com.application.drishtigems.StaffAdapter.model.ModelDrawerItem
import com.application.drishtigems.VendorUser.fragment.GemstoneHome.HomeGemstone
import com.application.drishtigems.VendorUser.fragment.MyProfileVendor.MyProfileVendor
import com.application.drishtigems.VendorUser.fragment.Settings.LogoutDialog
import com.application.drishtigems.prefs.AppPrefs
import com.application.drishtigems.ui.base.fragment.LoginScreen
import com.application.drishtigems.ui.fragment.MyProfile
import com.application.drishtigems.ui.fragment.homeFragment.HomeScreen
import com.bumptech.glide.Glide
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_login_screen.*
import kotlinx.android.synthetic.main.fragment_my_profile.*
import kotlinx.android.synthetic.main.navigation_drawer.*
import kotlinx.android.synthetic.main.navigation_drawer.view.*
import kotlinx.coroutines.launch
import android.content.DialogInterface
import androidx.core.content.ContextCompat
import com.application.drishtigems.ConnectionFragment
import com.google.android.material.snackbar.BaseTransientBottomBar
import com.google.android.material.snackbar.Snackbar


class MainActivity : AppCompatActivity(), MyReceiver.ConnectivityReceiverListener {
    private var snackBar: Snackbar? = null
    var appPrefs = AppPrefs(this)
    private  var data: UserModel ? =null
    private  var update: MyProfileModel ? =null
    lateinit var binding: ActivityMainBinding
    private var drawerList: ArrayList<ModelDrawerItem>? = ArrayList()
    private var adapterDrawer: AdapterDrawer? = null
    private lateinit var receiver: MyReceiver

    @SuppressLint("WrongConstant")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        receiver= MyReceiver(this@MainActivity)
        registerReceiver(receiver, IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION))

         data = appPrefs.getModelKey("model")

        if (data==null){
            supportFragmentManager.beginTransaction().replace(R.id.mainLayout, LoginScreen()).commit()
        }
        else if (data!!.groups[0].name == "Sales Executive"){
            checkVendorStaff()
        }else if(data!!.groups[0].name == "Jeweller"){
            checkVendorStaff()
        }

        binding.includeLayout.imageDrawerPerson.setOnClickListener {
            if (data!!.groups[0].name == "Sales Executive"){
                supportFragmentManager.beginTransaction().replace(R.id.mainLayout, MyProfile()).addToBackStack("").commit()
            }
            else if(data!!.groups[0].name == "Jeweller"){
            supportFragmentManager.beginTransaction().replace(R.id.mainLayout, MyProfileVendor()).addToBackStack("").commit()
            }
            closedDrawer()
        }
        //logout of drawer Navigation to login screen using custom dialog
        binding.buttonDrawerLogout.setOnClickListener {
   /*         var logoutDialog=LogoutDialog()
            logoutDialog.show(supportFragmentManager,"MyLogoutDialog")*/
            AlertDialog.Builder(this)
                .setMessage(R.string.select_logout)
                .setPositiveButton("Yes") { dialogInterface, which ->
                     appPrefs.delete()
                    val intent = intent
                    finish()
                    startActivity(intent)
                }.setNegativeButton("No"
                ) { p0, p1 -> p0?.dismiss() }.create().show()
        }
    }

    fun checkVendorStaff() {
         data = appPrefs.getModelKey("model")
        if (data!!.groups[0].name == "Sales Executive"){
            drawerStaff()
            supportFragmentManager.beginTransaction().replace(R.id.mainLayout, HomeScreen()).commit()
        }else if(data!!.groups[0].name == "Jeweller"){
            drawerVendor()
            supportFragmentManager.beginTransaction().replace(R.id.mainLayout, HomeGemstone()).addToBackStack(HomeGemstone::class.java.name).commit()
        }
        drawerRecyclerView()
    }
    fun closedDrawer() {
        binding.drawerLayout.closeDrawer(GravityCompat.START)
    }

    fun openDrawer() {
        binding.drawerLayout.openDrawer(GravityCompat.START)
    }

    private fun drawerStaff() {
        drawerList!!.add(ModelDrawerItem(BitmapFactory.decodeResource(this.resources, R.drawable.home_3x), "Home"))
        drawerList!!.add(ModelDrawerItem(BitmapFactory.decodeResource(this.resources, R.drawable.stock_3x), "In My Stock"))
        drawerList!!.add(ModelDrawerItem(BitmapFactory.decodeResource(this.resources, R.drawable.bring_online_3x), "Bring Online Stock"))
        drawerList!!.add(ModelDrawerItem(BitmapFactory.decodeResource(this.resources, R.drawable.bill_icon_3x), "Generate Bill"))
        drawerList!!.add(ModelDrawerItem(BitmapFactory.decodeResource(this.resources, R.drawable.graph_icon3x), "Sale Graph"))
        drawerList!!.add(ModelDrawerItem(BitmapFactory.decodeResource(this.resources, R.drawable.form_icon_3x), "TA/DA Form"))
        drawerList!!.add(ModelDrawerItem(BitmapFactory.decodeResource(this.resources, R.drawable.offer_icon_3x), "Offers"))
        drawerList!!.add(ModelDrawerItem(BitmapFactory.decodeResource(this.resources, R.drawable.settings_icon3x), "Settings"))
    }
    private fun drawerVendor() {
        drawerList!!.add(ModelDrawerItem(BitmapFactory.decodeResource(this.resources, R.drawable.home_3x), "Home"))
        drawerList!!.add(ModelDrawerItem(BitmapFactory.decodeResource(this.resources, R.drawable.offer_icon_3x), "Offers"))
        drawerList!!.add(ModelDrawerItem(BitmapFactory.decodeResource(this.resources, R.drawable.custom_order_icon3x), "Custom Orders"))
        drawerList!!.add(ModelDrawerItem(BitmapFactory.decodeResource(this.resources, R.drawable.purchase_icon3x), "Purchase History"))
        drawerList!!.add(ModelDrawerItem(BitmapFactory.decodeResource(this.resources, R.drawable.graph_icon3x), "Profit Graph"))
        drawerList!!.add(ModelDrawerItem(BitmapFactory.decodeResource(this.resources, R.drawable.commission_icon3x), "Commission Earned"))
        drawerList!!.add(ModelDrawerItem(BitmapFactory.decodeResource(this.resources, R.drawable.notification_icon3x), "Notification"))
        drawerList!!.add(ModelDrawerItem(BitmapFactory.decodeResource(this.resources, R.drawable.settings_icon3x), "Settings"))
    }

    private fun drawerRecyclerView() {
        adapterDrawer = AdapterDrawer(this@MainActivity, drawerList,data)
        binding.rvDrawerItem.adapter = adapterDrawer
    }

    override fun dispatchTouchEvent(event: MotionEvent): Boolean {
        if (event.action == MotionEvent.ACTION_DOWN) {
            val v = currentFocus
            if (v is EditText) {
                val outRect = Rect()
                v.getGlobalVisibleRect(outRect)
                if (!outRect.contains(event.rawX.toInt(), event.rawY.toInt())) {
                    Log.d("focus", "touchevent")
                    v.clearFocus()
                    val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                    imm.hideSoftInputFromWindow(v.windowToken, 0)
                }
            }
        }
        return super.dispatchTouchEvent(event)
    }

    //on back press drawer closed
    override fun onBackPressed() {
        if (binding.drawerLayout.isDrawerOpen(GravityCompat.START)) {
            binding.drawerLayout.closeDrawers()
            return
        }
        else{
            onBackStackChanged()
        }
    }

    private fun getVisibleFragment():Fragment{
        return supportFragmentManager.findFragmentById(R.id.mainLayout)!!
    }

    fun onBackStackChanged() {
        val localFragmentManager = supportFragmentManager
        val i = localFragmentManager.backStackEntryCount
        if (i == 1 || i == 0) {
            if (getVisibleFragment() is HomeScreen || getVisibleFragment() is HomeGemstone){
                finish()
            }
            if (data!!.groups[0].name == "Sales Executive"){
                localFragmentManager.beginTransaction().replace(R.id.mainLayout, HomeScreen()).commit()
            }else if(data!!.groups[0].name == "Jeweller"){
                localFragmentManager.beginTransaction().replace(R.id.mainLayout, HomeGemstone()).commit()
            }
        } else {
            localFragmentManager.popBackStack()
        }
    }
    fun refreshDrawerData(){
        data = appPrefs.getModelKey("model")
        update = appPrefs.getUpdate("update")
        if (update!=null){
            Glide.with(this).load(RetrofitObject.IMAGE_URL+update?.image).into(binding.includeLayout.imageDrawerPerson)
            binding.drawerLayout.tvDrawerEmail.text = update!!.email
            binding.drawerLayout.tvDrawerDataName.text = "${update!!.firstName} ${update!!.lastName}"
        }
        else
        {
            Glide.with(this).load(RetrofitObject.IMAGE_URL+data?.image).into(binding.includeLayout.imageDrawerPerson)
            binding.drawerLayout.tvDrawerEmail.text = data!!.email
            binding.drawerLayout.tvDrawerDataName.text = "${data!!.firstName} ${data!!.lastName}"
        }
    }

    val connectionFragment =  ConnectionFragment()

    private fun showNetworkMessage(isConnected: Boolean) {
        if (!isConnected) {
            if (connectionFragment.isAdded){
                return
            }
            connectionFragment.show(supportFragmentManager,"ConnectingFragment")
            showSnakBar("You are Offline")

        } else {
            connectionFragment.dialog?.dismiss()
            showSnakBar("You are Online")
            snackBar?.dismiss()
        }
    }
    fun showSnakBar(string: String) {
        try {
            val snackBar: Snackbar = Snackbar.make(findViewById(android.R.id.content), string, Snackbar.LENGTH_SHORT)
            val snackBarView = snackBar.view
            snackBarView.setBackgroundColor(ContextCompat.getColor(this, R.color.amountcolor))
            snackBar.show()
        } catch (e: java.lang.Exception) {

        }

    }


    override fun onNetworkConnectionEnabled() {
        showNetworkMessage(true)
    }
    override fun onNetworkConnectionDisabled() {
        showNetworkMessage(false)
    }
    override fun onStop() {
        super.onStop()
        unregisterReceiver(receiver)
    }
}