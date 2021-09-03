package com.davis.kevin.technicav2.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.davis.kevin.technicav2.models.Evenement
import com.davis.kevin.technicav2.models.Partner
import com.davis.kevin.technicav2.models.Praesidium
import com.davis.kevin.technicav2.models.Vacature
import com.davis.kevin.technicav2.networking.RetrofitManager.apiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


object Repository {
    var eventMutableLiveData = MutableLiveData<List<Evenement>>()
    var vacatureMutableLiveData = MutableLiveData<List<Vacature>>()
    var praesidiumMutableLiveData = MutableLiveData<List<Praesidium>>()
    var partnerMutableLiveData = MutableLiveData<List<Partner>>()

    init{
       // getData()
    }

    fun getData(){
        getVacatures()
        getPraesidium()
        getEvents()
        getPartners()
    }

    private fun getPartners() {
        apiService.getPartners().enqueue(object : Callback<List<Partner>> {
            override fun onResponse(call: Call<List<Partner>>, response: Response<List<Partner>>) {
                partnerMutableLiveData.value = response.body()
                Log.d("PARTNER", response.body().toString())
            } override fun onFailure(call: Call<List<Partner>>, t: Throwable) {
                Log.d("PARTNER", t.toString())
            }

        })
    }

    private fun getVacatures(){
        apiService.getVacatures().enqueue(object : Callback<List<Vacature>> {
            override fun onResponse(call: Call<List<Vacature>>, response: Response<List<Vacature>>) {
                vacatureMutableLiveData.value = response.body()
            } override fun onFailure(call: Call<List<Vacature>>, t: Throwable) {

            }

        })
    }

    private fun getPraesidium(){
        apiService.getPraesidium().enqueue(object : Callback<List<Praesidium>> {
            override fun onResponse(call: Call<List<Praesidium>>, response: Response<List<Praesidium>> ) {
                praesidiumMutableLiveData.value = response.body()
            } override fun onFailure(call: Call<List<Praesidium>>, t: Throwable) {

            }
        })
    }

    private fun getEvents(){
        apiService.getEvents().enqueue(object : Callback<List<Evenement>> {
            override fun onResponse(call: Call<List<Evenement>>, response: Response<List<Evenement>>) {
                eventMutableLiveData.value = response.body()
            } override fun onFailure(call: Call<List<Evenement>>, t: Throwable) {

            }
        })
    }
}