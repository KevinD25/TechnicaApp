package com.davis.kevin.technicav2.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.davis.kevin.technicav2.R
import com.davis.kevin.technicav2.databinding.PraesidiumBinding
import com.davis.kevin.technicav2.databinding.VacatureBinding
import com.davis.kevin.technicav2.models.Praesidium
import com.davis.kevin.technicav2.ui.praesidium.PraesidiumViewModel
import com.davis.kevin.technicav2.ui.vacatures.VacaturesViewModel

class CustomPraesidiumAdapter(val ctx: Context, var arrayList: ArrayList<PraesidiumViewModel>) :
    RecyclerView.Adapter<CustomPraesidiumAdapter.CustomView>() {

    class CustomView(val praesidiumBinding: PraesidiumBinding) :
        RecyclerView.ViewHolder(praesidiumBinding.root) {
        fun bind(praesidiumViewModel: PraesidiumViewModel) {
            this.praesidiumBinding.praesidiummodel = praesidiumViewModel
            praesidiumBinding.executePendingBindings()

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomView {

        val layoutInflater = LayoutInflater.from(parent.context)

        val praesidiumBinding: PraesidiumBinding =
            DataBindingUtil.inflate(layoutInflater, R.layout.innerpraesidiumlayout, parent, false)

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