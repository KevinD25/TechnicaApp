package com.davis.kevin.technicav2.ui.kalender

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.davis.kevin.technicav2.models.Event
import com.davis.kevin.technicav2.networking.RetrofitManager.apiService
import com.davis.kevin.technicav2.repository.Repository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.collections.ArrayList

class KalenderViewModel : ViewModel {

    var id = 0
    var idString: String? = ""
    var name: String? = ""
    var date: String? = ""
    var location: String? = ""
    var description: String? = ""


    constructor() : super() {}
    constructor(
        event: Event
    ) : super() {
        this.id = event.id
        this.idString = event.id.toString()
        this.name = event.name
        this.date = event.date
        this.location = event.location
        this.description = event.description
    }

    var arraylistMutableLiveData = MutableLiveData<List<Event>>()


    fun getArrayList(): MutableLiveData<List<Event>> {
        return Repository.eventMutableLiveData
    }
}