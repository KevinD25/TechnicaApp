package com.davis.kevin.technicav2.ui.sponsors

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.davis.kevin.technicav2.models.Partner
import com.davis.kevin.technicav2.models.Vacature

class SponsorsViewModel : ViewModel {

    var id = 0
    var name : String? = ""
    var description : String? = ""
    var website : String? = ""
    var vacancies : List<Vacature>? = null

   /* private val _text = MutableLiveData<String>().apply {
        value = "This is slideshow Fragment"
    }
    val text: LiveData<String> = _text*/

    constructor() : super()
    constructor(
        partner: Partner
    ) : super() {
        this.id = partner.id
        this.name = partner.name
        this.description = partner.description
        this.website = partner.website
        this.vacancies = partner.vacancies
    }


    var arrayListMutableLiveData = MutableLiveData<ArrayList<SponsorsViewModel>>()

    var arrayList = ArrayList<SponsorsViewModel>()

    fun getArrayList() : MutableLiveData<ArrayList<SponsorsViewModel>>{

        var emptyVacanciesList : List<Vacature> = emptyList()

        val partner1 = Partner(id=1, name="3IT", description = "Sponsor description van 3 lijnen max", website = "www.3it.be", vacancies = emptyVacanciesList )
        val partner2 = Partner(id=2, name="Induver", description = "Sponsor description van 3 lijnen max", website = "www.induver.be", vacancies = emptyVacanciesList)

        val sponsorsViewModel1 : SponsorsViewModel = SponsorsViewModel(partner1)
        val sponsorsViewModel2 : SponsorsViewModel = SponsorsViewModel(partner2)

        arrayList!!.add(sponsorsViewModel1)
        arrayList!!.add(sponsorsViewModel2)

        arrayListMutableLiveData.value = arrayList
        return arrayListMutableLiveData
    }

}