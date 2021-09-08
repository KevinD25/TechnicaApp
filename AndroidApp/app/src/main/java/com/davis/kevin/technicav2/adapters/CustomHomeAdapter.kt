package com.davis.kevin.technicav2.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.davis.kevin.technicav2.R
import com.davis.kevin.technicav2.databinding.HomeBinding
import com.davis.kevin.technicav2.ui.home.HomeViewModel
import kotlinx.android.synthetic.main.inner_home.view.*

class CustomHomeAdapter(var arrayList: ArrayList<HomeViewModel>)
    : RecyclerView.Adapter<CustomHomeAdapter.CostumView>() {

    class CostumView(private val homeBinding: HomeBinding, val parent: ViewGroup): RecyclerView.ViewHolder(homeBinding.root)  {
        fun bind(homeViewModel: HomeViewModel) {
            this.homeBinding.homeModel = homeViewModel
            if(homeViewModel.getViewDate().isNullOrBlank()) {
                itemView.txt_date.visibility = View.GONE
            } else itemView.txt_date.visibility = View.VISIBLE;
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CostumView {
        val homeBinding: HomeBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context),
            R.layout.inner_home, parent, false)
        return CostumView(homeBinding, parent)
    }

    override fun getItemCount(): Int {
        return arrayList.size
    }

    override fun onBindViewHolder(holder: CostumView, position: Int) {
        holder.bind(arrayList[position])
    }
}