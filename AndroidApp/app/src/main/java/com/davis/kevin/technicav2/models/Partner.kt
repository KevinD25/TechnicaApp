package com.davis.kevin.technicav2.models

import android.graphics.Bitmap

class Partner(
    val id: String = "",
    val name: String? = null,
    val image : Bitmap? = null,
    val description: String? = null,
    val website: String?= null,
    val priority: Long? = null)