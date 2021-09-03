package com.davis.kevin.technicav2.models

import android.graphics.Bitmap
import com.davis.kevin.technicav2.ui.praesidium.Functie

class Praesidium(
    val id: String = "",
    val name: String?  = null,
    val surname: String? = null,
    val birthday: String? = null,
    val image : Bitmap? = null,
    val studies: String? = null,
    val functie: Functie? = null
    /*, var images: MutableMap<String, Drawable>?*/) {
}