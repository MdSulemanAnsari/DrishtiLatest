package com.application.drishtigems.VendorUser.fragment.PurchaseHistory

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.ViewPager
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.application.drishtigems.R
import com.application.drishtigems.VendorUser.fragment.PurchaseHistory.PurchaseHistoryTablayout.MyPurchase
import com.application.drishtigems.VendorUser.fragment.PurchaseHistory.PurchaseHistoryTablayout.MyStock
import com.application.drishtigems.VendorUser.fragment.ShoppingCartVendor
import com.application.drishtigems.databinding.FragmentPurchaseHistoryBinding
import com.application.drishtigems.ui.activity.MainActivity
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.fragment_emerald_stock.*
import kotlinx.android.synthetic.main.fragment_purchase_history.*

class PurchaseHistory : Fragment() {
    lateinit var binding:FragmentPurchaseHistoryBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding=DataBindingUtil.inflate(inflater,R.layout.fragment_purchase_history, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        onClick()
        setupViewPager(viewPagerPurchaseHistory)

        TabLayoutMediator(tabLayoutPurchaseHistory, viewPagerPurchaseHistory) { tab, position ->
            when (position) {
                0 -> tab.text = "My Purchase"
                1 ->tab.text = "My Stock"
            }
        }.attach()
    }
    private fun setupViewPager(viewPager: ViewPager2) {
        val adapter = ViewPagerAdapter(requireActivity())

        viewPager.adapter = adapter
    }

    internal class ViewPagerAdapter(manager: FragmentActivity?) :
        FragmentStateAdapter(manager!!) {

        override fun getItemCount(): Int {
            return 2
        }

        override fun createFragment(position: Int): Fragment {
            var frag = Fragment()
            when (position) {
                0 -> frag = MyPurchase()
                1 -> frag = MyStock()

            }
            return frag
        }
        }
    private fun onClick() {
        binding.buttonPurchaseHistory.setOnClickListener {
            (activity as MainActivity).openDrawer()
        }
        binding.picScPh.setOnClickListener {
            fragmentManager?.beginTransaction()?.replace(R.id.mainLayout, ShoppingCartVendor())?.addToBackStack("") ?.commit()
        }
    }
    }

