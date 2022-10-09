package com.davis.kevin.technicav2.ui.home

import android.view.View
import com.yarolegovich.discretescrollview.transform.DiscreteScrollItemTransformer
import kotlin.math.abs

class CarouselTransformer : DiscreteScrollItemTransformer {
    // https://github.com/PuniCharana/Carousel-Demo

    override fun transformItem(item: View?, position: Float) {

        item?.let { view ->
            val height = view.height
            val width = view.width
            val scale: Float = if (position < 0) {
                min(if (position > 0) 1f else abs(1f + position), 0.9f)
            } else {
                min(if (position < 0) 1f else abs(1f - position), 0.9f)
            }

            view.scaleX = scale
            view.scaleY = scale
            view.pivotX = width * 0.5f
            view.pivotY = height * 0.5f
        }
    }

    private fun min(pos: Float, min: Float): Float {
        return if (pos < min) min else pos
    }
}