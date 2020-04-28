package com.example.riskyarea_test1.ui.fragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import com.example.riskyarea_test1.data.controller.UpdatesByLocationController
import com.example.riskyarea_test1.data.model.response.UpdateResponse

class DashboardViewModel : ViewModel() {
    var updatesByLocationController = UpdatesByLocationController.getController()
    var _city: MutableLiveData<String> = MutableLiveData()

    public fun setCity(city: String) {
        _city.value = city
    }

    public fun getCity(): LiveData<String> {
        return _city
    }

    public fun updates(): LiveData<UpdateResponse>? {
        return _city.switchMap {
            updatesByLocationController.fetchUpdatesByLocation(it.trim().toLowerCase())
        }
    }
}