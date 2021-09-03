package com.davis.kevin.technicav2.models

import android.graphics.Bitmap

class Vacature(
    val id: String = "",
    val companyId: String? = null,
    val name: String?  = null,
    val description: String? = null,
    val link:String? = null,
    var image: Bitmap? = null) {
}