package com.davis.kevin.technicav2.models

import android.graphics.Bitmap
import com.davis.kevin.technicav2.ui.praesidium.Functie
import java.time.LocalDate

class Praesidium(
    val id: String = "",
    val name: String?  = null,
    val surname: String? = null,
    val studies: String? = null,
    val image : Bitmap? = null,
    val functie: Functie? = null,
    val birthday: LocalDate? = null
    /*, var images: MutableMap<String, Drawable>?*/)