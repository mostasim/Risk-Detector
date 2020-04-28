package com.example.riskyarea_test1.ui.fragment

import android.content.IntentSender
import android.location.Address
import android.location.Geocoder
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import android.widget.AdapterView.OnItemSelectedListener
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.riskyarea_test1.R
import com.example.riskyarea_test1.utils.Utils
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.common.api.ResolvableApiException
import com.google.android.gms.location.*
import com.google.android.material.textview.MaterialTextView
import java.io.IOException
import java.util.*
import kotlin.collections.ArrayList

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
    private lateinit var adapter: ArrayAdapter<String>
    private lateinit var locationArrayList: ArrayList<String>
    private lateinit var locationMutableLiveData: MutableLiveData<ArrayList<String>>

    private lateinit var fusedLocationProviderClient: FusedLocationProviderClient

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

        locationArrayList = ArrayList();
        locationArrayList.add("Bangladesh")
        adapter = ArrayAdapter<String>(this.requireContext(),
                R.layout.spinner_item, locationArrayList) //setting the country_array to spinner

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerLocation.setAdapter(adapter)
        spinnerLocation.setSelection(0)
        spinnerLocation.setOnItemSelectedListener(object : OnItemSelectedListener {
            override fun onItemSelected(arg0: AdapterView<*>?, arg1: View,
                                        position: Int, id: Long) {
                val selected = locationArrayList
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
        fusedLocationProviderClient = FusedLocationProviderClient(this.requireActivity())

        getCurrentLocation()
        /* mViewModel.getCity().observe(this.viewLifecycleOwner, Observer {
             Log.e("getCity",": " +it)
             var city = it
             if (city.contains("District")) {
                 city = it.replace("District", "")
             }
             locationArrayList.clear()
             locationArrayList.add(city)
             locationArrayList.add("Bangladesh")
             adapter.notifyDataSetChanged()
             spinnerLocation.setSelection(0)
         })*/
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
        Log.e("IDS", "UID : ${Utils().getDeviceUniqueID(requireActivity())}")
    }

    private fun getCurrentLocation() {

        val locationRequest = LocationRequest()
        locationRequest.priority = LocationRequest.PRIORITY_HIGH_ACCURACY
        locationRequest.interval = (10 * 1000).toLong()
        locationRequest.fastestInterval = 2000

        val builder = LocationSettingsRequest.Builder()
        builder.addLocationRequest(locationRequest)
        val locationSettingsRequest = builder.build()

        val result = LocationServices.getSettingsClient(this.requireActivity()).checkLocationSettings(locationSettingsRequest)
        result.addOnCompleteListener { task ->
            try {
                val response = task.getResult(ApiException::class.java)
                if (response!!.locationSettingsStates.isLocationPresent) {
                    getLastLocation()
                }
            } catch (exception: ApiException) {
                when (exception.statusCode) {
                    LocationSettingsStatusCodes.RESOLUTION_REQUIRED -> try {
                        val resolvable = exception as ResolvableApiException
                        resolvable.startResolutionForResult(this.requireActivity(), REQUEST_CHECK_SETTINGS)
                    } catch (e: IntentSender.SendIntentException) {
                    } catch (e: ClassCastException) {
                    }

                    LocationSettingsStatusCodes.SETTINGS_CHANGE_UNAVAILABLE -> {
                    }
                }
            }
        }
    }

    private fun getLastLocation() {
        fusedLocationProviderClient.lastLocation
                .addOnCompleteListener(this.requireActivity()) { task ->
                    if (task.isSuccessful && task.result != null) {
                        val mLastLocation = task.result

                        var address = "No known address"

                        val gcd = Geocoder(this.requireContext(), Locale.getDefault())
                        val addresses: List<Address>
                        try {
                            addresses = gcd.getFromLocation(mLastLocation!!.latitude, mLastLocation.longitude, 1)
                            if (addresses.isNotEmpty()) {
                                address = addresses[0].subAdminArea
                                Log.e("TAG FUSION", "address " + address)
                                addMyCityToSpinner(address)
                            }
                        } catch (e: IOException) {
                            e.printStackTrace()
                        }

                    } else {
//                        Log.e("TAGFUSION","address "+address)
                        Toast.makeText(this.requireContext(), "No current location found", Toast.LENGTH_LONG).show()
                    }
                }
    }

    private fun addMyCityToSpinner(cityName: String) {
        var city = cityName
        if (city.contains("District")) {
            city = cityName.replace("District", "")
        }
        locationArrayList.clear()
        locationArrayList.add(city)
        locationArrayList.add("Bangladesh")
        adapter.notifyDataSetChanged()
        spinnerLocation.setSelection(0)
        mViewModel.setCity(city)
    }

    companion object {
        const val REQUEST_CHECK_SETTINGS = 43
        fun newInstance(): DashboardFragment {
            return DashboardFragment()
        }
    }
}