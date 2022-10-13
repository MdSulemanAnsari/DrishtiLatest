package com.application.drishtigems.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.drawerlayout.widget.DrawerLayout
import com.application.drishtigems.R
import com.application.drishtigems.StaffAdapter.adapter.AdapterNotification
import com.application.drishtigems.databinding.FragmentNotificationHomeBinding
import com.application.drishtigems.StaffAdapter.model.ModelNotification
import com.application.drishtigems.ui.activity.MainActivity

class NotificationHome : Fragment() {
lateinit var binding:FragmentNotificationHomeBinding
var adapterNotification:AdapterNotification?=null
    var notificationList:ArrayList<ModelNotification>?= ArrayList()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding=DataBindingUtil.inflate(inflater,R.layout.fragment_notification_home, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.backPressedNotification.setOnClickListener {
           requireActivity().onBackPressed()
        }
        recyclerNotifiactionOne()
        notificationListOfItem()
    }

    private fun notificationListOfItem() {
       notificationList!!.add(ModelNotification("Lorem ipsum is dummy text here.","10 sep 2021","10:00 AM"))
       notificationList!!.add(ModelNotification("Lorem ipsum is dummy text here.","10 sep 2021","10:00 AM"))
       notificationList!!.add(ModelNotification("Lorem ipsum is dummy text here.","10 sep 2021","10:00 AM"))
       notificationList!!.add(ModelNotification("Lorem ipsum is dummy text here.","10 sep 2021","10:00 AM"))
       notificationList!!.add(ModelNotification("Lorem ipsum is dummy text here.","10 sep 2021","10:00 AM"))
    }

    override fun onStart() {
        super.onStart()
        (activity as MainActivity).binding!!.drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED)
    }

    private fun recyclerNotifiactionOne() {
        adapterNotification= AdapterNotification(notificationList)
        binding.rvNotification.adapter=adapterNotification
        binding.rvNotificationSecond.adapter=adapterNotification
    }
}