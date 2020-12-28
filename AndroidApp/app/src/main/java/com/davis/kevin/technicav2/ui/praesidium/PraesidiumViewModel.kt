package com.davis.kevin.technicav2.ui.praesidium

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.davis.kevin.technicav2.models.Praesidium
import com.davis.kevin.technicav2.networking.RetrofitManager.apiService
import com.davis.kevin.technicav2.repository.Repository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PraesidiumViewModel : ViewModel {


    var id : String? = ""
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

    fun getArrayList(): MutableLiveData<List<Praesidium>> {
        return Repository.praesidiumMutableLiveData
    }

}