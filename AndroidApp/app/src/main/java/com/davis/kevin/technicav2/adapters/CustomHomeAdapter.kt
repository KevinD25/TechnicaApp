package com.davis.kevin.technicav2.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.davis.kevin.technicav2.MainActivity
import com.davis.kevin.technicav2.R
import com.davis.kevin.technicav2.databinding.InnerHomeBinding
import com.davis.kevin.technicav2.ui.home.HomeViewModel
import com.davis.kevin.technicav2.ui.kalender.KalenderFragment


class CustomHomeAdapter(var arrayList: ArrayList<HomeViewModel>): RecyclerView.Adapter<CustomHomeAdapter.CostumView>() {

    private lateinit var _bindingInner: InnerHomeBinding
    private val bindingInner get() = _bindingInner!!

    class CostumView(private val bindingInner: InnerHomeBinding): RecyclerView.ViewHolder(bindingInner.root)  {
        fun bind(homeViewModel: HomeViewModel) {
            bindingInner.eventCard.setOnLongClickListener {
                KalenderFragment.showEventId = homeViewModel.id
                MainActivity.navigateToFragment(bindingInner.root.findNavController(), R.id.nav_kalender)
                true // <- OnLongClickListener need a true on the end
            }
            bindingInner.imgCurrentEvent.setImageBitmap(homeViewModel.image)
            bindingInner.txtHeader.text = homeViewModel.name
            if (homeViewModel.getViewDate().isBlank()) bindingInner.txtDate.visibility = View.GONE
            else {
                bindingInner.txtDate.visibility = View.VISIBLE
                bindingInner.txtDate.text = homeViewModel.getViewDate()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CostumView {
        _bindingInner = InnerHomeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CostumView(bindingInner)
    }

    override fun getItemCount(): Int {
        return arrayList.size
    }

    override fun onBindViewHolder(holder: CostumView, position: Int) {
        holder.bind(arrayList[position])
    }
}
