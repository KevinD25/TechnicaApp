package com.davis.kevin.technicav2.ui.vacatures

import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.davis.kevin.technicav2.MainActivity
import com.davis.kevin.technicav2.models.Vacature
import com.davis.kevin.technicav2.networking.FirebaseHandler
import com.davis.kevin.technicav2.networking.RetrofitManager.apiService
import com.davis.kevin.technicav2.repository.Repository
import com.google.firebase.ktx.Firebase
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class VacaturesViewModel : ViewModel {

    var id : String? = ""
    var companyID : String? = ""
    var name : String? = ""
    var description : String? = ""
    var link : String? = ""
    var imagelink : Bitmap? = null
    private lateinit var mContext: Context

    constructor() : super() {}
    constructor(
        vacature: Vacature
    ) : super() {
        this.id = vacature.id
        this.companyID = vacature.companyID
        this.name = vacature.name
        this.description = vacature.description
        this.link = vacature.link
        this.imagelink = vacature.imageLink
    }

    var arraylist = ArrayList<VacaturesViewModel>()

    /*fun getArrayList() : MutableLiveData<List<Vacature>>{
        return Repository.vacatureMutableLiveData
    }*/

    fun getArray() : MutableLiveData<List<Vacature>>{
        return FirebaseHandler.vacancieList
    }

    fun getImage(): BitmapDrawable {
        var image = imagelink?.let { scale(it) }
        return BitmapDrawable(image)
    }

    fun getContext(context: Context) {
        mContext = context
    }

    private fun scale(realImage: Bitmap): Bitmap? {
        return Bitmap.createScaledBitmap(realImage, 200, 200, false)
    }

}