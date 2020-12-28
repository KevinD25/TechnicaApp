package com.davis.kevin.technicav2.ui.vacatures

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.davis.kevin.technicav2.MainActivity
import com.davis.kevin.technicav2.models.Vacature
import com.davis.kevin.technicav2.networking.RetrofitManager.apiService
import com.davis.kevin.technicav2.repository.Repository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class VacaturesViewModel : ViewModel {

    var id : String? = ""
    var companyID : String? = ""
    var name : String? = ""
    var description : String? = ""
    var link : String? = ""

    constructor() : super() {}
    constructor(
        vacature: Vacature
    ) : super() {
        this.id = vacature.id
        this.companyID = vacature.companyID
        this.name = vacature.name
        this.description = vacature.description
        this.link = vacature.link
    }

    var arraylist = ArrayList<VacaturesViewModel>()

    fun getArrayList() : MutableLiveData<List<Vacature>>{
        return Repository.vacatureMutableLiveData
    }


}