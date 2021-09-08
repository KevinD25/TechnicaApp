package com.davis.kevin.technicav2.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.davis.kevin.technicav2.R
import com.davis.kevin.technicav2.databinding.PraesidiumBinding
import com.davis.kevin.technicav2.ui.praesidium.PraesidiumViewModel

class CustomPraesidiumAdapter(var arrayList: ArrayList<PraesidiumViewModel>) :
    RecyclerView.Adapter<CustomPraesidiumAdapter.CustomView>() {

    class CustomView(private val praesidiumBinding: PraesidiumBinding) : RecyclerView.ViewHolder(praesidiumBinding.root) {

        fun bind(praesidiumViewModel: PraesidiumViewModel) {
            this.praesidiumBinding.praesidiumModel = praesidiumViewModel
            praesidiumBinding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomView {
        val praesidiumBinding: PraesidiumBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context),
            R.layout.inner_praesidium, parent, false)
        return CustomView(praesidiumBinding)
    }

    override fun getItemCount(): Int {
        return arrayList.size
    }

    override fun onBindViewHolder(holder: CustomView, position: Int) {
        val praesidiumViewModel = arrayList[position]
        holder.bind(praesidiumViewModel)
    }
}