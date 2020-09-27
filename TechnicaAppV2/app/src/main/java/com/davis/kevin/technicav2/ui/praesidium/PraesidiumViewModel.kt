package com.davis.kevin.technicav2.ui.praesidium

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.davis.kevin.technicav2.models.Praesidium
import com.davis.kevin.technicav2.networking.Repository
import com.davis.kevin.technicav2.networking.RetrofitManager.apiService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PraesidiumViewModel : ViewModel {


    var id = 0
    var name: String? = ""
    var surname: String? = ""
    var birthday: String? = ""
    var studies: String? = ""
    var functie: String? = ""



    constructor() : super()
    constructor(
        praesidium: Praesidium
    ) : super() {
        this.id = praesidium.id
        this.name = praesidium.name
        this.surname = praesidium.surname
        this.birthday = praesidium.birthday
        this.studies = praesidium.studies
        this.functie = praesidium.functie
    }

    var arrayListMutableLiveData = MutableLiveData<List<Praesidium>>()

    fun getPraesidiumCall() {
        apiService.getPraesidium().enqueue(object : Callback<List<Praesidium>> {
            override fun onResponse(
                call: Call<List<Praesidium>>,
                response: Response<List<Praesidium>>
            ) {
                arrayListMutableLiveData.value = response.body()
            }

            override fun onFailure(call: Call<List<Praesidium>>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })
    }

    fun getArrayList(): MutableLiveData<List<Praesidium>> {
        return arrayListMutableLiveData
    }

}