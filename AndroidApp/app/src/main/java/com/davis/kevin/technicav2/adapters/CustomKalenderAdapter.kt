package com.davis.kevin.technicav2.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.davis.kevin.technicav2.databinding.InnerKalenderBinding
import com.davis.kevin.technicav2.ui.kalender.KalenderFragment
import com.davis.kevin.technicav2.ui.kalender.KalenderViewModel

class CustomKalenderAdapter(private val arrayList: ArrayList<KalenderViewModel>)
    : RecyclerView.Adapter<CustomKalenderAdapter.CustomView>() {

    private lateinit var _bindingInner: InnerKalenderBinding
    private val bindingInner get() = _bindingInner!!

    class CustomView(private val bindingInner: InnerKalenderBinding) : RecyclerView.ViewHolder(bindingInner.root) {
        fun bind(kalenderViewModel: KalenderViewModel) {
            bindingInner.imgBtnEvent.setImageDrawable(kalenderViewModel.getViewImage())
            bindingInner.imgBtnEvent.setOnClickListener {
                KalenderFragment.setUpcomingEvent(kalenderViewModel, bindingInner)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomView {
        _bindingInner = InnerKalenderBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        //val view = LayoutInflater.from(parent.context).inflate(R.layout.inner_kalender, parent, false)
        return CustomView(bindingInner)
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