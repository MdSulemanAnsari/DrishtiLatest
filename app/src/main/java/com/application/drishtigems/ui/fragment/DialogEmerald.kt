package com.application.drishtigems.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.DialogFragment
import com.application.drishtigems.R
import com.application.drishtigems.databinding.FragmentDialogEmeraldBinding
import com.application.drishtigems.ui.activity.MainActivity

class DialogEmerald : DialogFragment() {
lateinit var binding:FragmentDialogEmeraldBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dialog!!.window?.setBackgroundDrawableResource(R.drawable.dialog_bg)

        //for touch pause
        dialog!!.setCanceledOnTouchOutside(false)
        binding=DataBindingUtil.inflate(inflater,R.layout.fragment_dialog_emerald, container, false)
        return binding.root
    }
    override fun onStart() {
        super.onStart()
        val width = (resources.displayMetrics.widthPixels * 0.85).toInt()
        val height = (resources.displayMetrics.heightPixels * 0.40).toInt()
        dialog!!.window?.setLayout(width, ViewGroup.LayoutParams.WRAP_CONTENT)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.crossDialogPic.setOnClickListener{
            dialog!!.dismiss()
        }
        binding.buttonSubmitDialog.setOnClickListener {
            fragmentManager?.beginTransaction()?.replace(R.id.mainLayout, InMyStock())?.commit()
            dialog!!.dismiss()
        }
    }

}