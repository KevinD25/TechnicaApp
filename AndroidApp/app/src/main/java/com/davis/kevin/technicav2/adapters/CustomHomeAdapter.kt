package com.davis.kevin.technicav2.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.davis.kevin.technicav2.databinding.InnerHomeBinding
import com.davis.kevin.technicav2.ui.home.HomeViewModel
import java.time.format.DateTimeFormatter

class CustomHomeAdapter(var arrayList: ArrayList<HomeViewModel>): RecyclerView.Adapter<CustomHomeAdapter.CostumView>() {

    private lateinit var _bindingInner: InnerHomeBinding
    private val bindingInner get() = _bindingInner!!

    class CostumView(private val bindingInner: InnerHomeBinding): RecyclerView.ViewHolder(bindingInner.root)  {
        fun bind(homeViewModel: HomeViewModel) {
            bindingInner.imgCurrentEvent.setImageBitmap(homeViewModel.image)
            bindingInner.txtHeader.text = homeViewModel.name
            bindingInner.txtDate.text = homeViewModel.date!!.format(DateTimeFormatter.ofPattern("dd/MM/yyyy - HH:mm"))
            if(homeViewModel.getViewDate().isBlank()) bindingInner.txtDate.visibility = View.GONE
            else bindingInner.txtDate.visibility = View.VISIBLE
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CostumView {
        _bindingInner = InnerHomeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        //val homeBinding: HomeBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.inner_home, parent, false)
        return CostumView(bindingInner)
    }

    override fun getItemCount(): Int {
        return arrayList.size
    }

    override fun onBindViewHolder(holder: CostumView, position: Int) {
        holder.bind(arrayList[position])
    }
}