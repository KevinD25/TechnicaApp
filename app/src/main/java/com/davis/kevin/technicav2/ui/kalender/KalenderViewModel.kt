package com.davis.kevin.technicav2.ui.kalender

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.davis.kevin.technicav2.models.Event
import kotlin.collections.ArrayList

class KalenderViewModel : ViewModel {

    var id = ""
    var name = ""
    var date   = ""
    var location = ""
    var description = ""


    private val _text = MutableLiveData<String>().apply {
        value = "This is tools Fragment"
    }
    val text: LiveData<String> = _text


    constructor() : super(){}
    constructor(
        event: Event
    ): super(){
        this.id = event.id
        this.name = event.name
        this.date = event.date
        this.location = event.location
        this.description = event.description
    }

    var arraylistMutableLiveData = MutableLiveData<ArrayList<KalenderViewModel>>()

    var arrayList = ArrayList<KalenderViewModel>()

    fun getArrayList() : MutableLiveData<ArrayList<KalenderViewModel>> {
        val event1 = Event(
            id = "1",
            name = "testevent1",
            date = "11/08/2020",
            location = "Hier",
            description = "testDescr"
        )
        val event2 = Event(
            id = "2",
            name = "testevent2",
            date = "12/08/2020",
            location = "Daar",
            description = "testDescr"
        )

        val kalenderViewModel1: KalenderViewModel = KalenderViewModel(event1)
        val kalenderViewModel2: KalenderViewModel = KalenderViewModel(event2)

        arrayList!!.add(kalenderViewModel1)
        arrayList!!.add(kalenderViewModel2)

        arraylistMutableLiveData.value = arrayList

        return arraylistMutableLiveData
    }
}