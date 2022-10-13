package com.application.drishtigems.VendorUser.fragment.ProfitGraph

import android.app.DatePickerDialog
import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.DatePicker
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.DialogFragment
import com.application.drishtigems.R
import com.application.drishtigems.VendorUser.fragment.GemstoneHome.HomeGemstone
import com.application.drishtigems.VendorUser.fragment.ShoppingCartVendor
import com.application.drishtigems.databinding.FragmentDownLoadStatementDialogBinding
import com.application.drishtigems.ui.base.fragment.LoginScreen
import kotlinx.android.synthetic.main.fragment_down_load_statement_dialog.*
import kotlinx.android.synthetic.main.fragment_edit_profile.*
import kotlinx.android.synthetic.main.fragment_edit_profile.datePicker
import java.text.SimpleDateFormat
import java.util.*

class DownLoadStatementDialog : DialogFragment() {
lateinit var binding:FragmentDownLoadStatementDialogBinding
    var textDate: TextView? = null
    var textDate2: TextView? = null
    var cal = Calendar.getInstance()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dialog!!.window?.setBackgroundDrawableResource(R.drawable.dialog_bg)
        dialog!!.setCanceledOnTouchOutside(false)
       binding=DataBindingUtil.inflate(inflater,R.layout.fragment_down_load_statement_dialog, container, false)
        return binding.root
    }
    override fun onStart() {
        super.onStart()
        val width=(resources.displayMetrics.widthPixels *0.85).toInt()
        val height=(resources.displayMetrics.heightPixels *0.40).toInt()
        dialog!!.window?.setLayout(width,ViewGroup.LayoutParams.WRAP_CONTENT)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setDatePickerFrom()
        setDatePickerTo()
        onClick()

    }

    private fun onClick() {
        binding.crossDs.setOnClickListener {
            dialog!!.dismiss()
        }
        binding.textCurrentMonth.setOnClickListener {
            binding.constraintDate.visibility=View.VISIBLE
            textCurrentMonth.text="Select Date Range"

        }
        binding.buttonSubmitDs.setOnClickListener{
            //fragmentManager?.beginTransaction()?.replace(R.id.mainLayout, HomeGemstone())?.addToBackStack("") ?.commit()
            dialog!!.dismiss()
        }
    }

    //date picker
    private fun setDatePickerFrom() {
        //set date of birth set using custom calender in dialog box
        textDate = this.datePickerFrom
        textDate!!.text = "From"
        // create an OnDateSetListener
        val dateSetListener = object : DatePickerDialog.OnDateSetListener {
            override fun onDateSet(
                view: DatePicker, year: Int, monthOfYear: Int,
                dayOfMonth: Int
            ) {
                cal.set(Calendar.YEAR, year)
                cal.set(Calendar.MONTH, monthOfYear)
                cal.set(Calendar.DAY_OF_MONTH, dayOfMonth)
                updateDateInViewFrom()
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

    private fun updateDateInViewFrom() {
        val myFormat = "yyyy-MM-dd" // mention the format you need
        val sdf = SimpleDateFormat(myFormat, Locale.US)
        textDate!!.text = sdf.format(cal.time)

    }
    private fun setDatePickerTo() {
        //set date of birth set using custom calender in dialog box
        textDate2 = this.datePickerTo
        textDate2!!.text = "To"
        // create an OnDateSetListener
        val dateSetListener = object : DatePickerDialog.OnDateSetListener {
            override fun onDateSet(
                view: DatePicker, year: Int, monthOfYear: Int,
                dayOfMonth: Int
            ) {
                cal.set(Calendar.YEAR, year)
                cal.set(Calendar.MONTH, monthOfYear)
                cal.set(Calendar.DAY_OF_MONTH, dayOfMonth)
                updateDateInViewTo()
            }
        }
        // when you click on the button, show DatePickerDialog that is set with OnDateSetListener
        textDate2!!.setOnClickListener(object : View.OnClickListener {
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
    private fun updateDateInViewTo() {
        val myFormat = "yyyy-MM-dd" // mention the format you need
        val sdf = SimpleDateFormat(myFormat, Locale.US)
        textDate2!!.text = sdf.format(cal.time)

    }
}