package com.davis.kevin.technicav2.models

import android.graphics.Bitmap
import java.time.LocalDate
import java.util.*

class Evenement(
    val id: String? = null,
    val name: String? = null,
    val fbLink: String? = null,
    val image: Bitmap? = null,
    val date: LocalDate? = null,
    val location: String? = null,
    val description: String? = null) {
}