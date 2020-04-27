package com.example.riskyarea_test1.ui.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import android.widget.AdapterView.OnItemSelectedListener
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.riskyarea_test1.R
import com.google.android.material.textview.MaterialTextView

class DashboardFragment : Fragment() {

    private lateinit var ivSpinnerLocation: ImageView
    private lateinit var mViewModel: DashboardViewModel
    private lateinit var spinnerLocation: Spinner
    private lateinit var tvInfectedToday: TextView
    private lateinit var tvInfectedTotal: TextView
    private lateinit var tvDeathToday: TextView
    private lateinit var tvDeathTotal: TextView
    private lateinit var tvRecoverToday: TextView
    private lateinit var tvRecoverTotal: TextView
    private lateinit var tvTestToday: TextView
    private lateinit var tvTestTotal: TextView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val root = inflater.inflate(R.layout.dashboard_fragment, container, false)

//        llInfected.findViewById<CardView>(R.id.cvDashboard).setCardBackgroundColor(Color.BLUE)
        spinnerLocation = root.findViewById<Spinner>(R.id.spinnerLocation)
        ivSpinnerLocation = root.findViewById<ImageView>(R.id.ivSpinnerArrowLocation)

        root.findViewById<View>(R.id.llDeath).findViewById<CardView>(R.id.cvDashboard).setCardBackgroundColor(resources.getColor(R.color.death_bg))
        root.findViewById<View>(R.id.llRecover).findViewById<CardView>(R.id.cvDashboard).setCardBackgroundColor(resources.getColor(R.color.recover_bg))
        root.findViewById<View>(R.id.llTest).findViewById<CardView>(R.id.cvDashboard).setCardBackgroundColor(resources.getColor(R.color.test_bg))

        root.findViewById<View>(R.id.llDeath).findViewById<MaterialTextView>(R.id.tvDashboardSectionTitle).text = "New Death"
        root.findViewById<View>(R.id.llRecover).findViewById<MaterialTextView>(R.id.tvDashboardSectionTitle).text = "Recover"
        root.findViewById<View>(R.id.llTest).findViewById<MaterialTextView>(R.id.tvDashboardSectionTitle).text = "Test"

        tvInfectedToday = root.findViewById<View>(R.id.llInfected).findViewById<TextView>(R.id.tv24h)
        tvDeathToday = root.findViewById<View>(R.id.llDeath).findViewById<TextView>(R.id.tv24h)
        tvRecoverToday = root.findViewById<View>(R.id.llRecover).findViewById<TextView>(R.id.tv24h)
        tvTestToday = root.findViewById<View>(R.id.llTest).findViewById<TextView>(R.id.tv24h)

        tvInfectedTotal = root.findViewById<View>(R.id.llInfected).findViewById<TextView>(R.id.tvTotal)
        tvDeathTotal = root.findViewById<View>(R.id.llDeath).findViewById<TextView>(R.id.tvTotal)
        tvRecoverTotal = root.findViewById<View>(R.id.llRecover).findViewById<TextView>(R.id.tvTotal)
        tvTestTotal = root.findViewById<View>(R.id.llTest).findViewById<TextView>(R.id.tvTotal)

        val adapter: ArrayAdapter<String> = ArrayAdapter<String>(this.requireContext(),
                android.R.layout.simple_spinner_item, resources
                .getStringArray(R.array.location_list)) //setting the country_array to spinner

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerLocation.setAdapter(adapter)
        spinnerLocation.setSelection(0)
        spinnerLocation.setOnItemSelectedListener(object : OnItemSelectedListener {
            override fun onItemSelected(arg0: AdapterView<*>?, arg1: View,
                                        position: Int, id: Long) {
                val selected = resources.getStringArray(R.array.location_list)
                Log.e("Location SPINNER ", selected[position])
                mViewModel.setCity(selected[position])
            }

            override fun onNothingSelected(arg0: AdapterView<*>?) {}
        })
        ivSpinnerLocation.setOnClickListener {
            spinnerLocation.performClick()
        }
        return root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        mViewModel = ViewModelProvider(this).get(DashboardViewModel::class.java)
        mViewModel.setCity("Dinajpur")
        mViewModel.updates()?.observe(this.viewLifecycleOwner, Observer {
            tvInfectedToday.text = it.newInfectedCount.toString()
            tvInfectedToday.text = it.totalInfectedCount.toString()
            tvDeathToday.text = it.newDeathCount.toString()
            tvDeathToday.text = it.totalDeathCount.toString()
            tvRecoverToday.text = it.newRecoverCount.toString()
            tvRecoverTotal.text = it.totalRecoverCount.toString()
            tvTestToday.text = it.newTestCount.toString()
            tvTestTotal.text = it.totalTestCount.toString()
        })
    }

    companion object {
        fun newInstance(): DashboardFragment {
            return DashboardFragment()
        }
    }
}