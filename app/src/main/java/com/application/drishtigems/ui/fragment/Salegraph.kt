package com.application.drishtigems.ui.fragment

import android.os.Bundle
import android.text.Html
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.drawerlayout.widget.DrawerLayout
import com.application.drishtigems.R
import com.application.drishtigems.StaffAdapter.adapter.AdapterSaleGraph
import com.application.drishtigems.databinding.FragmentSalegraphBinding
import com.application.drishtigems.StaffAdapter.model.ModelSaleGraph
import com.application.drishtigems.ui.activity.MainActivity
import com.application.drishtigems.utils.MyMarkerView
import kotlinx.android.synthetic.main.fragment_salegraph.*
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter
import com.github.mikephil.charting.formatter.ValueFormatter

class Salegraph :Fragment() {
    lateinit var binding:FragmentSalegraphBinding
    var adapterSaleGraph: AdapterSaleGraph?=null
    var saleList:ArrayList<ModelSaleGraph>?= ArrayList()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
       binding=DataBindingUtil.inflate(inflater, R.layout.fragment_salegraph, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

       // call setUpGraph
        setGraphSale()
        //RecyclerView of sale graph
        listOfSaleGraph()
        recyclerViewSaleGraph()
        binding.buttonSaleGraph.setOnClickListener {
            (activity as MainActivity).openDrawer()
        }
        saleSpinner()

    }

    private fun listOfSaleGraph() {
       saleList!!.add(ModelSaleGraph("September 2021","$8,000","$5,325"))
       saleList!!.add(ModelSaleGraph("August 2021","$8,000","$15,325"))
       saleList!!.add(ModelSaleGraph("July 2021","$8,000","$10,325"))
       saleList!!.add(ModelSaleGraph("June 2021","$8,000","$4,325"))
       saleList!!.add(ModelSaleGraph("May 2021","$8,000","$5,325"))
       saleList!!.add(ModelSaleGraph("May 2021","$8,000","$5,325"))
       saleList!!.add(ModelSaleGraph("May 2021","$8,000","$5,325"))
    }
    private fun recyclerViewSaleGraph() {
        adapterSaleGraph= AdapterSaleGraph(saleList)
        binding.rvSaleGraph.adapter=adapterSaleGraph
    }

    private fun saleSpinner() {
        val list=arrayOf("Monthly","Weakly","Yearly")
        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, list)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerMonthly?.adapter = adapter
    }
 private fun setGraphSale() {

         val barDataSet = BarDataSet(barEntriesSale(), "DataSet Graph")
         barDataSet.color=resources.getColor(R.color.layoutNewColor)
         barDataSet.highLightColor=resources.getColor(R.color.black)

         val barData = BarData(barDataSet)
         barData.setDrawValues(false)
         chart.data = barData

         chart.description.isEnabled = false
         chart.legend.isEnabled = false;
         chart.setPinchZoom(false)
         chart.axisRight.isEnabled = false
         chart.isDragEnabled = true
         chart.setScaleEnabled(false)
         chart.isScaleXEnabled = false
         chart.isScaleYEnabled = false
         chart.setTouchEnabled(true)
         chart.animateY(1000);

         val days = arrayOf("","Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul","Aug","Sep","Oct","Nov","Dec")
         val xAxis = chart.xAxis
         xAxis.textSize=12f
         xAxis.valueFormatter = IndexAxisValueFormatter(days)
         xAxis.setCenterAxisLabels(false)
         xAxis.position = XAxis.XAxisPosition.BOTTOM
         xAxis.granularity = 1f
         xAxis.isGranularityEnabled = false
         xAxis.setDrawGridLines(false)
         xAxis.setDrawGridLinesBehindData(false)
         xAxis.gridColor= ContextCompat.getColor(requireActivity(),R.color.black)

         val yAxis = chart.axisLeft
         yAxis.valueFormatter = object : ValueFormatter() {
         override fun getFormattedValue(value: Float): String {
             return Html.fromHtml("<b>$${value.toString()[0].toInt()}k</b>").toString()
         }
     }
         chart.setVisibleXRangeMaximum(7f)
         barData.barWidth = 0.4f
         val markerView = MyMarkerView(requireActivity(), xAxis.valueFormatter)
          markerView.chartView = chart
         chart.marker = markerView

         chart.setFitBars(true)
         chart.invalidate()
 }
    private fun barEntriesSale(): ArrayList<BarEntry>? {
        val barEntries = ArrayList<BarEntry>()
        barEntries.add(BarEntry(1f, 10000f))
        barEntries.add(BarEntry(2f, 11000f))
        barEntries.add(BarEntry(3f, 7000f))
        barEntries.add(BarEntry(4f, 4000f))
        barEntries.add(BarEntry(5f, 12000f))
        barEntries.add(BarEntry(6f, 3000f))
        barEntries.add(BarEntry(7f, 8000f))
        barEntries.add(BarEntry(8f, 10000f))
        barEntries.add(BarEntry(9f, 11000f))
        barEntries.add(BarEntry(10f, 7000f))
        barEntries.add(BarEntry(11f, 4000f))
        barEntries.add(BarEntry(12f, 12000f))
        return barEntries
    }
    override fun onStart() {
        super.onStart()
        (activity as MainActivity).binding.drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED)
    }
}
