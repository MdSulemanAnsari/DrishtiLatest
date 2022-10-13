package com.application.drishtigems.ui.fragment

import android.graphics.BitmapFactory
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.drawerlayout.widget.DrawerLayout
import com.application.drishtigems.R
import com.application.drishtigems.StaffAdapter.adapter.AdapterViewPager
import com.application.drishtigems.databinding.FragmentEmeraldStockBinding
import com.application.drishtigems.StaffAdapter.model.ModelViewPager
import com.application.drishtigems.ui.activity.MainActivity
import kotlinx.android.synthetic.main.fragment_emerald_stock.*

class EmeraldStock : Fragment() {
    lateinit var binding: FragmentEmeraldStockBinding
    private var alignedOn: String? = null
    private var emerald: String? = null
    private var dollar: String? = null
    private var sku: String? = null
    private var origin: String? = null
    private var adapterViewPager: AdapterViewPager? = null
    lateinit var viewPagerList: ArrayList<ModelViewPager>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_emerald_stock, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //get data from adapter stock
        if (arguments != null) {
            alignedOn = requireArguments().getString("alignedDate")
            emerald = requireArguments().getString("emerald")
            dollar = requireArguments().getString("dollarCarat")
            sku = requireArguments().getString("skuValue")
            origin = requireArguments().getString("originValue")
            tvDataAlignedDate.text = alignedOn
            tvDataNewEmerald.text = emerald
            tvDataCaratDollar.text = dollar
            tvDataSkuValue.text = sku
            tvDataOriginValue.text = origin
        }
        //back pressed image icon
        binding.backPressedImage.setOnClickListener {
           requireActivity().onBackPressed()

            //open Emerald dialog fragment
        }
        binding.buttonMark.setOnClickListener {
            DialogEmerald().show(requireFragmentManager(), "MyCustomDialogFragment")
        }

        if (!this::viewPagerList.isInitialized) {
            viewPagerListItem()
            callViewPager()
        }
    }
    override fun onStart() {
        super.onStart()
        (activity as MainActivity).binding!!.drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED)
    }
    private fun viewPagerListItem() {
        viewPagerList= arrayListOf()
        viewPagerList.add(ModelViewPager(BitmapFactory.decodeResource(this.resources, R.drawable.view_pager_image)))
        viewPagerList.add(ModelViewPager(BitmapFactory.decodeResource(this.resources, R.drawable.view_pager_image)))
        viewPagerList.add(ModelViewPager(BitmapFactory.decodeResource(this.resources, R.drawable.view_pager_image)))
        viewPagerList.add(ModelViewPager(BitmapFactory.decodeResource(this.resources, R.drawable.view_pager_image)))
    }

    private fun callViewPager() {
        adapterViewPager = AdapterViewPager(requireActivity(), viewPagerList ?: arrayListOf())
        binding.viewPager.adapter = adapterViewPager
        binding.tabLay.setupWithViewPager(binding.viewPager, true)
    }
}