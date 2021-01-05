package com.davis.kevin.technicav2.util

import android.content.Context

/**
 * Transforms a [Float] into density pixels.
 * @param context [Context] required for display metrics
 * @return DP as [Int]
 */
fun Float.toDp(context: Context): Int {
    val metrics = context.resources.displayMetrics
    val pixels = metrics.density * this
    return (pixels + 0.5f).toInt()
}