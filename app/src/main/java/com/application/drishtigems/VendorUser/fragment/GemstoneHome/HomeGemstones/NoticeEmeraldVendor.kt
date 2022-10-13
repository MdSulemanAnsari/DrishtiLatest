package com.application.drishtigems.VendorUser.fragment.GemstoneHome.HomeGemstones

import android.graphics.BitmapFactory
import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.application.drishtigems.R
import com.application.drishtigems.databinding.FragmentNoticeEmeraldVendorBinding
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import com.application.drishtigems.VendorUser.VendorAdapter.BringOnlineStockAdapter.NoticeAdapter
import com.application.drishtigems.VendorUser.VendorModel.BringOnlineStockModel.NoticeModel
import kotlinx.android.synthetic.main.fragment_notice_emerald_vendor.*


class NoticeEmeraldVendor : Fragment() {
lateinit var binding:FragmentNoticeEmeraldVendorBinding
var noticeAdapter:NoticeAdapter?=null
var listNoticeAdd:ArrayList<NoticeModel> = ArrayList()
var strNotice="\"Natural Emerald\""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding=DataBindingUtil.inflate(inflater,R.layout.fragment_notice_emerald_vendor, container, false)
        binding.tvCheckOutJewellery.text="Checkout $strNotice Jewellery Designs"
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        spannableString()
        listAddNoticeItem()
        recyclerNoticeItem()
        onClick()

    }

    private fun onClick() {
        binding.backPressedNoticeVendor.setOnClickListener {
            requireActivity().onBackPressed()
        }
    }

    private fun listAddNoticeItem() {
        listNoticeAdd.clear()
        listNoticeAdd.add(NoticeModel(BitmapFactory.decodeResource(this.resources,R.drawable.explore_image),"RINGS"))
        listNoticeAdd.add(NoticeModel(BitmapFactory.decodeResource(this.resources,R.drawable.explore_image),"RINGS"))
        listNoticeAdd.add(NoticeModel(BitmapFactory.decodeResource(this.resources,R.drawable.explore_image),"RINGS"))
        listNoticeAdd.add(NoticeModel(BitmapFactory.decodeResource(this.resources,R.drawable.explore_image),"RINGS"))
    }

    private fun spannableString() {
        val spannable = SpannableString("Checkout $strNotice Jewellery Designs")
        spannable.setSpan(
            ForegroundColorSpan(resources.getColor(R.color.amountcolor)),
            9, // start
            26, // end
            Spannable.SPAN_EXCLUSIVE_INCLUSIVE
        )
        tvCheckOutJewellery.text = spannable
    }
    private fun recyclerNoticeItem() {
        noticeAdapter= NoticeAdapter(listNoticeAdd)
        binding.rvNotice.adapter=noticeAdapter
    }

}