package com.davis.kevin.technicav2.networking

import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.davis.kevin.technicav2.models.Praesidium
import com.davis.kevin.technicav2.networking.RetrofitManager.apiService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

class Repository() {

    init {
        getData()
    }

    var praesidium: List<Praesidium>
        get() = praesidium
        set(value) {}


    private fun getData() {
        CoroutineScope(Dispatchers.IO).launch {
            praesidium = apiService.getPraesidium()
        }
    }
}