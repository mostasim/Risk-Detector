package com.example.riskyarea_test1.ui.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.AdapterView.OnItemSelectedListener
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.Spinner
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.riskyarea_test1.R
import com.google.android.material.textview.MaterialTextView

class DashboardFragment : Fragment() {
    private lateinit var ivSpinnerLocation: ImageView
    private var mViewModel: DashboardViewModel? = null
    private lateinit var spinnerLocation: Spinner
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
        // TODO: Use the ViewModel
    }

    companion object {
        fun newInstance(): DashboardFragment {
            return DashboardFragment()
        }
    }
}