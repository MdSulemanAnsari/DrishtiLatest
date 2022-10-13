package com.application.drishtigems.ui.fragment.homeFragment
import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.application.drishtigems.R
import com.application.drishtigems.StaffAdapter.adapter.AdapterHome
import com.application.drishtigems.databinding.FragmentHomeScreenBinding
import com.application.drishtigems.ui.activity.MainActivity
import android.os.Build
import android.widget.SeekBar
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.drawerlayout.widget.DrawerLayout
import com.application.drishtigems.Network.RetrofitInstance
import com.application.drishtigems.Network.RetrofitObject
import com.application.drishtigems.Network.StaffNetwork.ApiModel.CategoryOnlineBringModel.CategoryOnlineBringModel
import com.application.drishtigems.Network.StaffNetwork.ApiModel.HomeModel.HomeStaff
import com.application.drishtigems.Network.StaffNetwork.ApiModel.LoginUserModel.UserModel
import com.application.drishtigems.Network.StaffNetwork.ApiModel.MyProfileModel.MyProfileModel
import com.application.drishtigems.prefs.AppPrefs
import com.application.drishtigems.ui.fragment.NotificationHome
import com.bumptech.glide.Glide
import com.google.gson.JsonObject
import com.rizlee.rangeseekbar.RangeSeekBar
import kotlinx.android.synthetic.main.activity_main.view.*
import kotlinx.android.synthetic.main.fragment_connection.view.*
import kotlinx.android.synthetic.main.fragment_custom_orders.*
import kotlinx.android.synthetic.main.fragment_custom_orders.tvPriceStart
import kotlinx.android.synthetic.main.fragment_exo_dialog.*
import kotlinx.android.synthetic.main.fragment_home_screen.*
import kotlinx.android.synthetic.main.navigation_drawer.*
import kotlinx.android.synthetic.main.navigation_drawer.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*


class HomeScreen : Fragment() {
    val str="\"9 days\""
    var adapterHome: AdapterHome?=null
    var s1: UserModel? = null
    private  var update: MyProfileModel ? =null
    private  var staffModel: HomeStaff ? =null
    lateinit var binding:FragmentHomeScreenBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_home_screen, container, false)
        binding.textView6.text = "$str left to achieve your target amount."
        return binding.root
    }
    @RequiresApi(Build.VERSION_CODES.O)
    fun decodeBase64(input: String?): Bitmap? {
        val decodedByte: ByteArray = Base64.getDecoder().decode(input)
        return BitmapFactory.decodeByteArray(decodedByte, 0, decodedByte.size)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    @SuppressLint("WrongConstant")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

/*        val appPrefs = AppPrefs(requireContext())
        s1 = appPrefs.getModelKey("model")*/
        binding.seekBar.setPadding(0, 0, 0, -60);


        apiHomeStaff()
        onClick()
        homeRecyclerCall()
        //seekBar()
        binding.seekBar.isEnabled=false
        binding.seekBar.setOnSeekBarChangeListener(object :RangeSeekBar.OnRangeSeekBarRealTimeListener,
            SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
                binding.tvSeekStartValue.text=p1.toString()
            }

            override fun onStartTrackingTouch(p0: SeekBar?) {
                TODO("Not yet implemented")
            }

            override fun onStopTrackingTouch(p0: SeekBar?) {
                TODO("Not yet implemented")
            }

            override fun onValuesChanging(minValue: Float, maxValue: Float) {
                TODO("Not yet implemented")
            }

            override fun onValuesChanging(minValue: Int, maxValue: Int) {
                TODO("Not yet implemented")
            }


        })
    }

/*    private fun seelBar() {
        binding.rangeSeekBar.listenerRealTime = object : RangeSeekBar.OnRangeSeekBarRealTimeListener {
            override fun onValuesChanging(minValue: Float, maxValue: Float) { }

            override fun onValuesChanging(minValue: Int, maxValue: Int) {
                tvSeekStartValue.text = "${minValue.toString().replace("100000","")}k"
                tvSeekEndValue.text = "${maxValue.toString().replace("100000","")}k"
            }
        }
    }*/

    @RequiresApi(Build.VERSION_CODES.O)
    private fun onClick() {
        binding.bellIcon.setOnClickListener {
            fragmentManager?.beginTransaction()?.replace(R.id.mainLayout, NotificationHome())?.addToBackStack("") ?.commit()
        }
        binding.buttonDrawerOpen.setOnClickListener {
            val appPrefs = AppPrefs(requireContext())
            s1 = appPrefs.getModelKey("model")
           // sharePreferenceStaff()
            (activity as MainActivity).openDrawer()
            update = appPrefs.getUpdate("update")
            (activity as MainActivity).refreshDrawerData()

        }
        //bottom sheet open in home screen
        binding.buttonCheckIncentive.setOnClickListener {
            val bottomSheet = BottomSheetHome()
            bottomSheet.isCancelable=false
            bottomSheet.show(requireActivity().supportFragmentManager, "ModalBottomSheet")
        }
    }

 /*   @RequiresApi(Build.VERSION_CODES.O)
    @SuppressLint("WrongConstant")
    private fun sharePreferenceStaff() {
        val appPref = AppPrefs(requireContext())

           s1 = appPref.getModelKey("model")

*//*       // val sFirst=appPref.getModelKey()
            (activity as MainActivity).binding.drawerLayout.tvDrawerEmail.text= s1!!.email
            (activity as MainActivity).binding.drawerLayout.tvDrawerDataName.text="${s1!!.firstName} ${s1!!.lastName}"
            Glide.with(requireActivity()).load(RetrofitObject.IMAGE_URL+s1?.image).into((activity as MainActivity).binding.includeLayout.imageDrawerPerson)*//*

          *//*  Glide.with(requireActivity()).load(modelUserNew?.image).into(binding!!)
            (activity as MainActivity).binding.drawerLayout.imageDrawerPerson.setImageBitmap(decodeBase64(s1.image))
*//*
           // (activity as MainActivity).binding.drawerLayout.imageDrawerPerson.setImageBitmap(decodeBase64(s1.image))


        //store data share preference
     *//*   val sh =requireActivity(). getSharedPreferences("SaveEmailAddress", MODE_APPEND)
        val s1 = sh.getString("email", "")
        val sFirst = sh.getString("firstName", "")
        val sLast = sh.getString("lastName", "")
        val sImage = sh.getString("imagePicture", "")*//*
       *//* (activity as MainActivity).binding.drawerLayout.tvDrawerEmail.text=s1
        (activity as MainActivity).binding.drawerLayout.tvDrawerDataName.text="$sFirst $sLast"
        if (!TextUtils.isEmpty(sImage)){
            (activity as MainActivity).binding.drawerLayout.imageDrawerPerson.setImageBitmap(decodeBase64(sImage)!!)
        }*//*
    }*/

    private fun homeRecyclerCall() {
      adapterHome= AdapterHome()
        binding.rvHome.adapter=adapterHome
    }
/*    fun refreshDrawerData(){
        val appPrefs = AppPrefs(requireContext())
        update = appPrefs.getUpdate("update")
     Glide.with(this).load(RetrofitObject.IMAGE_URL+data?.image).into((activity as MainActivity).binding.includeLayout.imageDrawerPerson)
        (activity as MainActivity).binding.drawerLayout.tvDrawerEmail.text = data!!.email
        binding.drawerLayout.tvDrawerDataName.text = "${data!!.firstName} ${data!!.lastName}"
        *//*   }else
           {
               Glide.with(this).load(RetrofitObject.IMAGE_URL+data?.image).into(binding.includeLayout.imageDrawerPerson)
               binding.drawerLayout.tvDrawerEmail.text = data!!.email
               binding.drawerLayout.tvDrawerDataName.text = "${data!!.firstName} ${data!!.lastName}"
           }*//*
    }*/

    private fun apiHomeStaff() {
        val appPrefs =AppPrefs(requireContext())

        val token = "Token" + " " + appPrefs.getToken("token")
        val retrofitData = RetrofitInstance().retrofitBuilder.getTargets(token)
        //enqueue method
        retrofitData.enqueue(object : Callback<HomeStaff> {
            override fun onResponse(call: Call<HomeStaff>, response: Response<HomeStaff>) {
                if (response.code() == 200) {
                    staffModel=response.body()
                    setHomeData()
                } else {
                    Toast.makeText(requireActivity(), "Error 400", Toast.LENGTH_SHORT).show()
                }
            }
            override fun onFailure(call: Call<HomeStaff>, t: Throwable) {
                Toast.makeText(requireActivity(), "Error!", Toast.LENGTH_SHORT).show()
            }
        })
    }
    private fun setHomeData() {
        binding.tvHiName.text="${staffModel?.firstName} ${staffModel?.lastName}"
        binding.tvMonthlyAmount.text= staffModel?.achivedTarget.toString()
        binding.tvSaleAmount.text= staffModel?.givenTarget.toString()
        binding.textView6.text= "\"${staffModel?.pendingDaysInMonth.toString()} days\"left to achieve your target amount."
        binding.tvIncentiveAmount.text= staffModel?.incentiveAmount.toString()
        binding.tvSeekStartValue.text= staffModel?.achivedTarget.toString()
        binding.tvSeekEndValue.text= staffModel?.givenTarget.toString()
        seekBar.setProgress(((staffModel?.achivedTarget!!/ staffModel!!.givenTarget!!) * 100).toInt())
    }
    override fun onStart() {
        super.onStart()
        (activity as MainActivity).binding.drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED)
    }

}