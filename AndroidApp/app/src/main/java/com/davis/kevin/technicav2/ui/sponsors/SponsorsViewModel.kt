package com.davis.kevin.technicav2.ui.sponsors

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.net.Uri
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.davis.kevin.technicav2.models.Partner
import com.davis.kevin.technicav2.networking.FirebaseHandler
import com.davis.kevin.technicav2.repository.Repository

class SponsorsViewModel : ViewModel {

    var id: String? = ""
    var name: String? = ""
    var description: String? = ""
    var website: String? = ""
    var imagelink: Bitmap? = null
    private lateinit var mContext: Context

    constructor() : super()
    constructor(
        partner: Partner
    ) : super() {
        this.id = partner.id
        this.name = partner.name
        this.description = partner.description
        this.website = partner.website
        this.imagelink = partner.imageLink
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

    fun getArray(): MutableLiveData<List<Partner>> {
        return FirebaseHandler.sponsorList
    }

}