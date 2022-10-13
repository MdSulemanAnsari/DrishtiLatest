package com.application.drishtigems.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.databinding.DataBindingUtil
import androidx.drawerlayout.widget.DrawerLayout
import com.application.drishtigems.R
import com.application.drishtigems.StaffAdapter.adapter.Filter.AdapterFilterIdeaDesign
import com.application.drishtigems.StaffAdapter.adapter.Filter.AdapterFilterOrigin
import com.application.drishtigems.StaffAdapter.adapter.Filter.AdapterFilterShape
import com.application.drishtigems.StaffAdapter.adapter.Filter.AdapterTreatment
import com.application.drishtigems.databinding.FragmentFilterStockBinding
import com.application.drishtigems.StaffAdapter.model.Filter.ModelFilterIdeaDesign
import com.application.drishtigems.StaffAdapter.model.Filter.ModelFilterOrigin
import com.application.drishtigems.StaffAdapter.model.Filter.ModelFilterShape
import com.application.drishtigems.StaffAdapter.model.Filter.ModelTreatment
import com.application.drishtigems.ui.activity.MainActivity
import kotlinx.android.synthetic.main.fragment_filter_stock.*

class FilterStock : Fragment() {
    lateinit var binding: FragmentFilterStockBinding
    private var filterShapeList: ArrayList<ModelFilterShape>? = ArrayList()
    private var filterOriginList: ArrayList<ModelFilterOrigin>? = ArrayList()
    private var filterIdeaDesignList: ArrayList<ModelFilterIdeaDesign>? = ArrayList()
    private var filterTreatmentList: ArrayList<ModelTreatment>? = ArrayList()
    var adapterFilterShape: AdapterFilterShape? = null
    var adapterFilterOrigin: AdapterFilterOrigin? = null
    var adapterFilterIdeaDesign: AdapterFilterIdeaDesign? = null
    var adapterFilterTreatment: AdapterTreatment? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_filter_stock, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //cross button call back to InMyStock fragment
        binding.crossFilter.setOnClickListener {
        requireActivity().onBackPressed()
        }
        //spinner Call
        filterEmeraldSpinner()
        filterSortSpinner()

        //Recycler view Adapter Call
        filterStockShapeRecyclerViewCall()
        filterStockOriginRecyclerViewCall()
        filterStockIdeaDesignRecyclerViewCall()
        filterStockTreatmentRecyclerViewCall()

        //list function call
        filterShapeListCall()
        filterOriginListCall()
        filterIdeaDesignListCall()
        filterTreatmentListCall()

    }
    override fun onStart() {
        super.onStart()
        (activity as MainActivity).binding!!.drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED)
    }
    private fun filterShapeListCall() {
        filterShapeList!!.add(ModelFilterShape("Octogonal(324)"))
        filterShapeList!!.add(ModelFilterShape("Oval(131)"))
        filterShapeList!!.add(ModelFilterShape("Cushion(34)"))
        filterShapeList!!.add(ModelFilterShape("Pear(19)"))
        filterShapeList!!.add(ModelFilterShape("Round(5)"))
        filterShapeList!!.add(ModelFilterShape("Heart(1)"))
    }
    private fun filterOriginListCall() {
        filterOriginList!!.add(ModelFilterOrigin("Zambia(343"))
        filterOriginList!!.add(ModelFilterOrigin("Colombia(138"))
        filterOriginList!!.add(ModelFilterOrigin("Ethiopia(5"))
        filterOriginList!!.add(ModelFilterOrigin("america(3"))
        filterOriginList!!.add(ModelFilterOrigin("Russia(6"))
        filterOriginList!!.add(ModelFilterOrigin("brazil(2"))
    }
    private fun filterIdeaDesignListCall() {
        filterIdeaDesignList!!.add(ModelFilterIdeaDesign("Men"))
        filterIdeaDesignList!!.add(ModelFilterIdeaDesign("Women"))
    }
    private fun filterTreatmentListCall() {
        filterTreatmentList!!.add(ModelTreatment("Unheated and Untreated(Oiling Only)(480)"))
        filterTreatmentList!!.add(ModelTreatment("Minor Oil(Certified)"))
    }


    private fun filterStockShapeRecyclerViewCall() {
        adapterFilterShape = AdapterFilterShape(filterShapeList)
        binding.rvFilterShape.adapter = adapterFilterShape
    }

    private fun filterStockOriginRecyclerViewCall() {
        adapterFilterOrigin = AdapterFilterOrigin(filterOriginList)
        binding.rvFilterOrigin.adapter = adapterFilterOrigin

    }
    private fun filterStockIdeaDesignRecyclerViewCall() {
       adapterFilterIdeaDesign= AdapterFilterIdeaDesign(filterIdeaDesignList)
        binding.rvIdeaDesign.adapter=adapterFilterIdeaDesign
    }
    private fun filterStockTreatmentRecyclerViewCall() {
      adapterFilterTreatment= AdapterTreatment(filterTreatmentList)
        binding.rvTreatment.adapter=adapterFilterTreatment
    }

    private fun filterEmeraldSpinner() {
        val list = arrayOf("Natural Emerald", "Emerald", "Gold Emerald")
        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, list)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerEmeraldFilter?.adapter = adapter
    }

    private fun filterSortSpinner() {
        val list = arrayOf("Natural Emerald", "Emerald", "Gold Emerald")
        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, list)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerSortFilter?.adapter = adapter
    }
}