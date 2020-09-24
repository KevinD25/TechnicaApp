package com.davis.kevin.technicav2.ui.vacatures

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.davis.kevin.technicav2.models.Vacature

class VacaturesViewModel : ViewModel {

    var id  = ""
    var companyID  = ""
    var name =  ""
    var description = ""
    var link = ""

    /*private val _text = MutableLiveData<String>().apply {
        value = "This is slideshow Fragment"
    }
    val text: LiveData<String> = _text*/

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


    var arraylistMutableLiveData = MutableLiveData<ArrayList<VacaturesViewModel>>()

    var arraylist = ArrayList<VacaturesViewModel>()


    fun getArrayList() : MutableLiveData<ArrayList<VacaturesViewModel>>{

        val vacature1= Vacature(id="1", companyID = "1", name="TestVacature", description = "testDescription", link = "www.google.com")
        val vacature2= Vacature(id="2", companyID = "2", name="TestVacature2", description = "testDescription", link = "www.google.com")

        val vacaturesViewModel1 : VacaturesViewModel = VacaturesViewModel(vacature1)
        val vacaturesViewModel2 : VacaturesViewModel = VacaturesViewModel(vacature2)

        arraylist!!.add(vacaturesViewModel1)
        arraylist!!.add(vacaturesViewModel2)

        arraylistMutableLiveData.value = arraylist

        return arraylistMutableLiveData
    }


}