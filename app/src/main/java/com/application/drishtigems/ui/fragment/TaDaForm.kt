package com.application.drishtigems.ui.fragment

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.DatePicker
import android.widget.TextView
import android.widget.TimePicker
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.drawerlayout.widget.DrawerLayout
import com.application.drishtigems.Network.RetrofitInstance
import com.application.drishtigems.R
import com.application.drishtigems.databinding.FragmentTaDaFormBinding
import com.application.drishtigems.prefs.AppPrefs
import com.application.drishtigems.ui.activity.MainActivity
import com.google.gson.JsonObject
import com.ikovac.timepickerwithseconds.MyTimePickerDialog
import kotlinx.android.synthetic.main.fragment_change_password.*
import kotlinx.android.synthetic.main.fragment_change_password.etCurrentPassword
import kotlinx.android.synthetic.main.fragment_change_password.etNewPassword
import kotlinx.android.synthetic.main.fragment_edit_profile.*
import kotlinx.android.synthetic.main.fragment_sold_dialog.*
import kotlinx.android.synthetic.main.fragment_ta_da_form.*
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.*

class TaDaForm : Fragment() {
  lateinit var binding:FragmentTaDaFormBinding

    var textDate: TextView? = null
    var cal = Calendar.getInstance()


    val mcurrentTime = Calendar.getInstance()
    val hour = mcurrentTime.get(Calendar.HOUR_OF_DAY)
    val minute = mcurrentTime.get(Calendar.MINUTE)
    val second = mcurrentTime.get(Calendar.SECOND)

    var hashMap: HashMap<String, RequestBody?> = HashMap()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
       binding=DataBindingUtil.inflate(inflater,R.layout.fragment_ta_da_form, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        onClick()
        datePickerTaDa()
        timeSecondPicker()
    }
    private fun onClick() {
        binding.buttonTaDa.setOnClickListener {
            (activity as MainActivity).openDrawer()
        }
        binding.constraintLayout2.setOnClickListener {
            fragmentManager?.beginTransaction()?.replace(R.id.mainLayout, RecentRequests())?.addToBackStack("") ?.commit()
        }
        binding.buttonSubmitTaDa.setOnClickListener {
            apiCallTaDa()
        }
    }

    private fun apiCallTaDa() {
        hashMapTaDaForm()
        val appPrefs = AppPrefs(requireContext())
        val token = "Token" + " " + appPrefs.getToken("token")
        val retrofitData = RetrofitInstance().retrofitBuilder.taDaForm(token,hashMap)
        retrofitData.enqueue(object : Callback<JsonObject> {
            override fun onResponse(call: Call<JsonObject>, response: Response<JsonObject>) {
                if (response.isSuccessful) {
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

    private fun hashMapTaDaForm() {
        val dateTimeNew=etDateForm.text.toString()+" "+etTimeForm.text.toString()
        hashMap["type_of_expense"]= etExpense.text.toString().toRequestBody("text/form-data".toMediaTypeOrNull())
        hashMap["description"] = etDescription.text.toString().toRequestBody("text/form-data".toMediaTypeOrNull())
        hashMap["On_Date_time"] =dateTimeNew.toRequestBody("text/form-data".toMediaTypeOrNull())
        hashMap["price"] = etMoneyOffer.text.toString().toRequestBody("text/form-data".toMediaTypeOrNull())
    }
    private fun datePickerTaDa() {
        //set date of birth set using custom calender in dialog box
        textDate = this.etDateForm
        textDate!!.text = "Select Time"
        // create an OnDateSetListener
        val dateSetListener = object : DatePickerDialog.OnDateSetListener {
            override fun onDateSet(
                view: DatePicker, year: Int, monthOfYear: Int,
                dayOfMonth: Int
            ) {
                cal.set(Calendar.YEAR, year)
                cal.set(Calendar.MONTH, monthOfYear)
                cal.set(Calendar.DAY_OF_MONTH, dayOfMonth)
                updateDateInView()
            }
        }
        // when you click on the button, show DatePickerDialog that is set with OnDateSetListener
        textDate!!.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View) {
                // set DatePickerDialog to point to today's date when it loads up
                DatePickerDialog(
                    requireContext(),
                    dateSetListener,
                    cal.get(Calendar.YEAR),
                    cal.get(Calendar.MONTH),
                    cal.get(Calendar.DAY_OF_MONTH)
                ).show()
            }
        })
    }

    private fun updateDateInView() {
        val myFormat = "yyyy-MM-dd" // mention the format you need
        val sdf = SimpleDateFormat(myFormat, Locale.US)
        textDate!!.text = sdf.format(cal.time)
    }
    private fun timeSecondPicker() {
        val mTimePicker = MyTimePickerDialog(requireContext(), object : TimePickerDialog.OnTimeSetListener,
            MyTimePickerDialog.OnTimeSetListener {
            @SuppressLint("SetTextI18n")
            override fun onTimeSet(view: com.ikovac.timepickerwithseconds.TimePicker?, hourOfDay: Int, minute: Int, seconds: Int
            ) {
                binding.etTimeForm.text = String.format("%d", hourOfDay)+
                        ":" + String.format("%d", minute) +
                        ":" + String.format("%d", seconds)
            }

            override fun onTimeSet(p0: TimePicker?, p1: Int, p2: Int) {
              return onTimeSet(p0, p1, p2)
            }
        }, hour, minute, second, true)
        binding.etTimeForm.setOnClickListener {
            mTimePicker!!.show()
        }


    }
    override fun onStart() {
        super.onStart()
        (activity as MainActivity).binding.drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED)
    }


/*    private fun timePicker() {
        mTimePicker = TimePickerDialog(requireContext(), object : TimePickerDialog.OnTimeSetListener {
            override fun onTimeSet(view: TimePicker?, hourOfDay: Int, minute: Int, second:Int) {
                binding.etTimeForm.text = String.format("%d : %d :%d", hourOfDay, minute ,second)
            }
        }, hour, minute,second, false)
        binding.etTimeForm.setOnClickListener {
            mTimePicker!!.show()
        }
    }*/


}