package com.application.drishtigems.VendorUser.fragment.ProfitGraph

import android.graphics.Color
import android.os.Bundle
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import com.application.drishtigems.R
import com.application.drishtigems.databinding.FragmentProfitGraphBinding
import com.application.drishtigems.ui.activity.MainActivity
import com.application.drishtigems.utils.MyMarkerView
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter
import com.github.mikephil.charting.formatter.ValueFormatter
import kotlinx.android.synthetic.main.fragment_profit_graph.*
import kotlinx.android.synthetic.main.fragment_salegraph.*


class ProfitGraph : Fragment() {
lateinit var binding:FragmentProfitGraphBinding

    //var barChart: BarChart? = null
    var barDataSet1: BarDataSet? = null
    var barDataSet2: BarDataSet? = null
    var barDataSet3: BarDataSet? = null


    var days = arrayOf("Mon", "Tue", "Wed", "Thu", "Fri", "Sat","Sun")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
      binding=DataBindingUtil.inflate(inflater, R.layout.fragment_profit_graph, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        onClick()
        profitGraphSpinner()
        groupBarCharNew()
        //groupBarChart()

    }

    private fun onClick() {
        binding.buttonDS.setOnClickListener {
            val downLoadStatementDialog=DownLoadStatementDialog()
            downLoadStatementDialog.show(requireFragmentManager(),"DownloadStatementDialog")
        }
        binding.buttonImageProfitGraph.setOnClickListener {
            (activity as MainActivity).openDrawer()
        }
    }

    private fun profitGraphSpinner() {
        val list=arrayOf("Monthly","Weakly","Yearly")
        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, list)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerMonthlyPg?.adapter = adapter
    }
  fun groupBarChart(){
/*      bar_chart.setDrawBarShadow(false)
      bar_chart.description.isEnabled = false
      bar_chart.setPinchZoom(false)
      bar_chart.setDrawGridBackground(true)

      val labels = arrayOf("","Mon", "Tue", "Wed", "Thu", "Fri", "Sat","Sun")
      val xAxis = bar_chart.xAxis
      xAxis.setCenterAxisLabels(true)
      xAxis.position = XAxis.XAxisPosition.BOTTOM
      xAxis.setDrawGridLines(true)
      xAxis.granularity = 1f // only intervals of 1 day

      xAxis.textColor = Color.BLACK
      xAxis.textSize = 12f
      xAxis.axisLineColor = Color.WHITE
      xAxis.axisMinimum = 1f
      xAxis.valueFormatter = IndexAxisValueFormatter(labels)

      val leftAxis = bar_chart.axisLeft
      leftAxis.textColor = Color.BLACK
      leftAxis.textSize = 12f
      leftAxis.axisLineColor = Color.WHITE
      leftAxis.setDrawGridLines(true)
      leftAxis.granularity = 2f
      leftAxis.setLabelCount(8, true)
      leftAxis.setPosition(YAxis.YAxisLabelPosition.OUTSIDE_CHART)

      bar_chart.axisRight.isEnabled = false
      bar_chart.legend.isEnabled = false

      val valOne = floatArrayOf(10f, 20f, 30f, 40f, 50f)
      val valTwo = floatArrayOf(60f, 50f, 40f, 30f, 20f)
      val valThree = floatArrayOf(50f, 60f, 20f, 10f, 30f)

      val barOne: ArrayList<BarEntry> = ArrayList()
      val barTwo: ArrayList<BarEntry> = ArrayList()
      val barThree: ArrayList<BarEntry> = ArrayList()
      for (i in valOne.indices) {
          barOne.add(BarEntry(valOne[i], valOne[i]))
          barTwo.add(BarEntry(valTwo[i], valTwo[i]))
          barThree.add(BarEntry(valThree[i], valThree[i]))
      }

      val set1 = BarDataSet(barOne, "barOne")
      set1.color = resources.getColor(R.color.adamColor)
      val set2 = BarDataSet(barTwo, "barTwo")
      set2.color = resources.getColor(R.color.stokDollar)
      val set3 = BarDataSet(barThree, "barThree")
      set2.color =  resources.getColor(R.color.recentRequestColor)

      set1.isHighlightEnabled = false
      set2.isHighlightEnabled = false
      set3.isHighlightEnabled = false
      set1.setDrawValues(false)
      set2.setDrawValues(false)
      set3.setDrawValues(false)

      val dataSets = ArrayList<IBarDataSet>()
      dataSets.add(set1)
      dataSets.add(set2)
      dataSets.add(set3)
      val data = BarData(dataSets)
      val groupSpace = 0.4f
      val barSpace = 0f
      val barWidth = 0.3f
      data.barWidth = barWidth
      xAxis.axisMaximum = labels.size - 1.1f

      bar_chart.data = data
      bar_chart.setScaleEnabled(false)
      bar_chart.setVisibleXRangeMaximum(6f)
      bar_chart.groupBars(1f, groupSpace, barSpace)
      bar_chart.invalidate()*/

  }
fun groupBarCharNew(){
    barDataSet1 = BarDataSet(getBarEntriesOne(), "Spent")
    barDataSet1!!.color = (resources.getColor(R.color.adamColor))

    barDataSet2 = BarDataSet(getBarEntriesTwo(), "Selling")
    barDataSet2!!.color = (resources.getColor(R.color.stokDollar))

    barDataSet3 = BarDataSet(getBarEntriesThree(), "Profit")
    barDataSet3!!.color = (resources.getColor(R.color.recentRequestColor))

    val data = BarData(barDataSet1, barDataSet2,barDataSet3)
    bar_chart!!.data = data

    bar_chart!!.description.isEnabled = false

    val xAxis = bar_chart!!.xAxis
    xAxis.textSize=12f
    xAxis.position = XAxis.XAxisPosition.BOTTOM
    xAxis.granularity = 1f
    xAxis.isGranularityEnabled = false

    xAxis.setDrawGridLines(false)
    xAxis.setDrawGridLinesBehindData(false)

    xAxis.valueFormatter = IndexAxisValueFormatter(days)

    xAxis.setCenterAxisLabels(true)

    xAxis.position = XAxis.XAxisPosition.BOTTOM
    xAxis.granularity = 1f

    xAxis.isGranularityEnabled = true

    bar_chart!!.isDragEnabled = true

    bar_chart!!.setVisibleXRangeMaximum(5f)
    val barSpace = 0.1f
    val groupSpace = 0.23f
    data.barWidth = 0.15f
    bar_chart!!.xAxis.axisMinimum =0f
    bar_chart!!.animate()
    bar_chart!!.groupBars(0f, groupSpace, barSpace)
    bar_chart!!.invalidate()

    bar_chart.setPinchZoom(false)
    bar_chart.axisRight.isEnabled = false
    bar_chart.setTouchEnabled(true)
    bar_chart.animateY(1000)

    val yAxis = bar_chart.axisLeft
    yAxis.valueFormatter = object : ValueFormatter() {
        override fun getFormattedValue(value: Float): String {
            return Html.fromHtml("<b>$${value.toString()[0].toInt()}k</b>").toString()
        }
    }
}
    private fun getBarEntriesOne(): ArrayList<BarEntry> {
        val barEntries = ArrayList<BarEntry>()
        barEntries.add(BarEntry(1f, 20f))
        barEntries.add(BarEntry(2f, 10f))
        barEntries.add(BarEntry(3f, 8f))
        barEntries.add(BarEntry(4f, 20f))
        barEntries.add(BarEntry(5f, 14f))
        barEntries.add(BarEntry(7f, 10f))
        barEntries.add(BarEntry(8f, 10f))
        return barEntries
    }
    private fun getBarEntriesTwo(): ArrayList<BarEntry> {

        val barEntries = ArrayList<BarEntry>()
        barEntries.add(BarEntry(1f, 22f))
        barEntries.add(BarEntry(2f, 12f))
        barEntries.add(BarEntry(3f, 10f))
        barEntries.add(BarEntry(4f, 22f))
        barEntries.add(BarEntry(5f, 16f))
        barEntries.add(BarEntry(7f, 12f))
        barEntries.add(BarEntry(8f, 12f))
        return barEntries
    }
    private fun getBarEntriesThree(): ArrayList<BarEntry> {

        val barEntries = ArrayList<BarEntry>()
        barEntries.add(BarEntry(1f, 4f))
        barEntries.add(BarEntry(2f, 8f))
        barEntries.add(BarEntry(3f, 10f))
        barEntries.add(BarEntry(4f, 12f))
        barEntries.add(BarEntry(5f, 7f))
        barEntries.add(BarEntry(7f, 3f))
        barEntries.add(BarEntry(8f, 3f))
        return barEntries
    }
    override fun onStart() {
        super.onStart()
        (activity as MainActivity).binding!!.drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED)
    }

}