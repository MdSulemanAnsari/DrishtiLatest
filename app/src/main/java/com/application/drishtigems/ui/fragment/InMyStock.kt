package com.application.drishtigems.ui.fragment

import android.graphics.BitmapFactory
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.SearchView
import androidx.databinding.DataBindingUtil
import androidx.drawerlayout.widget.DrawerLayout
import com.application.drishtigems.R
import com.application.drishtigems.StaffAdapter.adapter.AdapterStockItem
import com.application.drishtigems.databinding.FragmentInMyStockBinding
import com.application.drishtigems.StaffAdapter.model.ModelStockItem
import com.application.drishtigems.ui.activity.MainActivity
import kotlinx.android.synthetic.main.fragment_in_my_stock.*

class InMyStock : Fragment() {
lateinit var binding:FragmentInMyStockBinding
    lateinit var stockList : ArrayList<ModelStockItem>
    var adapterStockItem: AdapterStockItem?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding=DataBindingUtil.inflate(inflater,R.layout.fragment_in_my_stock, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String): Boolean {
                return false
            }
            override fun onQueryTextChange(newText: String): Boolean {
                adapterStockItem?.filter(newText)
                return false
            }
        })

        //open drawer in MyStockFragment
        binding.buttonDrawerStock.setOnClickListener {
            (activity as MainActivity).openDrawer()
        }
        //open filter fragment
        binding.filterStock.setOnClickListener {
            fragmentManager?.beginTransaction()?.replace(R.id.mainLayout, FilterStock())?.addToBackStack("") ?.commit()
        }
        //calling spinner
        stockSpinner()
        if (!this::stockList.isInitialized){
        //call stock item list
        myStockItemList()
        }
        myStockRecyclerViewCall()
    }
    private fun myStockItemList() {
        stockList= arrayListOf()
        stockList.add(ModelStockItem(BitmapFactory.decodeResource(this.resources,R.drawable.stock_pic),"EMERALD PAIR-8.68","GP-85426","Colombia","$100/CARAT","17/09/2021",))
        stockList.add(ModelStockItem(BitmapFactory.decodeResource(this.resources,R.drawable.stock2pic),"EMERALD PAIR-5.68","GP-85445","Colombia","$100/CARAT","17/09/2021",))
        stockList.add(ModelStockItem(BitmapFactory.decodeResource(this.resources,R.drawable.stock3pic),"EMERALD PAIR-4.68","GP-85473","Colombia","$100/CARAT","17/09/2021",))
        stockList.add(ModelStockItem(BitmapFactory.decodeResource(this.resources,R.drawable.stock_pic),"EMERALD PAIR-6.68","GP-85426","Colombia","$100/CARAT","17/09/2021",))
        stockList.add(ModelStockItem(BitmapFactory.decodeResource(this.resources,R.drawable.stock_pic),"EMERALD PAIR-6.68","GP-85426","Colombia","$100/CARAT","17/09/2021",))
        stockList.add(ModelStockItem(BitmapFactory.decodeResource(this.resources,R.drawable.stock_pic),"EMERALD PAIR-6.68","GP-85426","Colombia","$100/CARAT","17/09/2021",))
        stockList.add(ModelStockItem(BitmapFactory.decodeResource(this.resources,R.drawable.stock_pic),"EMERALD PAIR-6.68","GP-85426","Colombia","$100/CARAT","17/09/2021",))
    }

    private fun myStockRecyclerViewCall() {
      adapterStockItem=AdapterStockItem(requireActivity(),stockList)
        binding.rvStock.adapter=adapterStockItem
    }

    private fun stockSpinner() {
        val list=arrayOf("All","Sold","Unsold")
        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, list)

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerSold?.adapter = adapter
    }
    override fun onStart() {
        super.onStart()
        (activity as MainActivity).binding.drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED)
    }
}