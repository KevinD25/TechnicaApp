package com.davis.kevin.technicav2.ui.sponsors

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.davis.kevin.technicav2.models.Partner
import com.davis.kevin.technicav2.models.Vacature
import com.davis.kevin.technicav2.networking.RetrofitManager.apiService
import com.davis.kevin.technicav2.repository.Repository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SponsorsViewModel : ViewModel {

    var id : String? = ""
    var name: String? = ""
    var description: String? = ""
    var website: String? = ""

    constructor() : super()
    constructor(
        partner: Partner
    ) : super() {
        this.id = partner.id
        this.name = partner.name
        this.description = partner.description
        this.website = partner.website
    }

    fun getArrayList(): MutableLiveData<List<Partner>> {
        return Repository.partnerMutableLiveData
    }

}