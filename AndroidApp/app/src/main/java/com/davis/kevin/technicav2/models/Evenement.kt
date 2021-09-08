package com.davis.kevin.technicav2.models

import android.graphics.Bitmap
import java.time.LocalDateTime
import java.util.*

class Evenement(
    val id: String? = null,
    val name: String? = null,
    val fbLink: String? = null,
    val formsLink: String? = null,
    val location: String? = null,
    val price: Long? = null,
    val description: String? = null,
    val image: Bitmap? = null,
    val date: LocalDateTime? = null
) {
}