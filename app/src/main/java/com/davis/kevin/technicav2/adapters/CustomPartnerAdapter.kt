package com.davis.kevin.technicav2.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.davis.kevin.technicav2.R
import com.davis.kevin.technicav2.databinding.PartnerBinding
import com.davis.kevin.technicav2.ui.sponsors.SponsorsViewModel

class CustomPartnerAdapter(val ctx : Context, val arrayList:ArrayList<SponsorsViewModel>) : RecyclerView.Adapter<CustomPartnerAdapter.CustomView>() {

    class CustomView( val partnerBinding: PartnerBinding): RecyclerView.ViewHolder(partnerBinding.root){


        fun bind(sponsorsViewModel: SponsorsViewModel){
            this.partnerBinding.partnermodel = sponsorsViewModel
            partnerBinding.executePendingBindings()
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomView {
        val layoutInflater = LayoutInflater.from(parent.context)

        val partnerBinding:PartnerBinding = DataBindingUtil.inflate(layoutInflater, R.layout.innerpartnerlayout, parent, false)
        val partnerView = layoutInflater.inflate(R.layout.innerpartnerlayout, parent, false)

        return CustomPartnerAdapter.CustomView(partnerBinding)
    }

    override fun onBindViewHolder(holder: CustomView, position: Int) {
        val sponsorsViewModel = arrayList[position]
        holder.bind(sponsorsViewModel)
    }

    override fun getItemCount(): Int {
        return arrayList.size
    }
}