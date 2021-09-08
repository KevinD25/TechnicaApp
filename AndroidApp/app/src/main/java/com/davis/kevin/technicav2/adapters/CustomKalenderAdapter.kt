package com.davis.kevin.technicav2.adapters

import android.content.Intent
import android.graphics.drawable.BitmapDrawable
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.davis.kevin.technicav2.R
import com.davis.kevin.technicav2.ui.kalender.KalenderFragment
import com.davis.kevin.technicav2.ui.kalender.KalenderViewModel
import kotlinx.android.synthetic.main.fragment_kalender.view.*
import kotlinx.android.synthetic.main.inner_kalender.view.img_btn_event

class CustomKalenderAdapter(private val arrayList: ArrayList<KalenderViewModel>, private val parentView: View)
    : RecyclerView.Adapter<CustomKalenderAdapter.CustomView>() {

    class CustomView(val view: View, private val parentView: View) : RecyclerView.ViewHolder(view) {

        fun bind(kalenderViewModel: KalenderViewModel) {
            itemView.img_btn_event.setImageDrawable(kalenderViewModel.getViewImage())
            itemView.img_btn_event.setOnClickListener {
                KalenderFragment.setUpcomingEvent(kalenderViewModel, parentView)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomView {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.inner_kalender, parent, false)
        return CustomView(view, parentView)
    }

    override fun getItemCount(): Int {
        return arrayList.size
    }

    override fun onBindViewHolder(holder: CustomView, position: Int) {
        val kalenderViewModel = arrayList[position]
        holder.bind(kalenderViewModel)
    }

    fun getItemIndex(item: KalenderViewModel?): Int {
        return arrayList.indexOf(item)
    }
}