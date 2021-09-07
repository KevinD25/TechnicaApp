package com.davis.kevin.technicav2.adapters

import android.content.Intent
import android.graphics.drawable.BitmapDrawable
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.davis.kevin.technicav2.R
import com.davis.kevin.technicav2.ui.kalender.KalenderViewModel
import kotlinx.android.synthetic.main.fragment_kalender.view.*
import kotlinx.android.synthetic.main.inner_kalender.view.img_btn_event

class CustomKalenderAdapter(private val arrayList: ArrayList<KalenderViewModel>, private val parentView: View)
    : RecyclerView.Adapter<CustomKalenderAdapter.CustomView>() {

    class CustomView(val view: View, val parentView: View) : RecyclerView.ViewHolder(view) {

        fun bind(kalenderViewModel: KalenderViewModel) {
            itemView.img_btn_event.setImageDrawable(kalenderViewModel.getViewImage())
            itemView.img_btn_event.setOnClickListener {
                this.parentView.img_event.setImageDrawable(BitmapDrawable(kalenderViewModel.image))
                this.parentView.txt_name.text = kalenderViewModel.name
                this.parentView.img_fb_link.setOnClickListener {
                    val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(kalenderViewModel.fbLink))
                    view.context.startActivity(browserIntent)
                }
                this.parentView.txt_date.text = kalenderViewModel.getViewDate()
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
}