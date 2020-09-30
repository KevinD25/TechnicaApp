package com.davis.kevin.technicav2.adapters

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.davis.kevin.technicav2.R
import com.davis.kevin.technicav2.databinding.PartnerBinding
import com.davis.kevin.technicav2.ui.sponsors.SponsorsViewModel

class CustomPartnerAdapter(val ctx : Context, val arrayList:ArrayList<SponsorsViewModel>) : RecyclerView.Adapter<CustomPartnerAdapter.CustomView>() {

    class CustomView(val partnerBinding: PartnerBinding): RecyclerView.ViewHolder(partnerBinding.root){

        //val btnWebsite : Button = v.findViewById<Button>(R.id.btnWebsite)

        /*init {
            btnWebsite.setOnClickListener{ v: View ->
                val position = adapterPosition
                val link = arrayList[position].website
                val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(link)) //Not sure wether this one works
                ctx.startActivity(browserIntent)
            }
        }*/

        fun bind(sponsorsViewModel: SponsorsViewModel){
            this.partnerBinding.partnermodel = sponsorsViewModel
            partnerBinding.executePendingBindings()
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomView {
        val layoutInflater = LayoutInflater.from(parent.context)

        val partnerBinding:PartnerBinding = DataBindingUtil.inflate(layoutInflater, R.layout.innerpartnerlayout, parent, false)
        val partnerView = layoutInflater.inflate(R.layout.innerpartnerlayout, parent, false)

        return CustomView(partnerBinding)
    }

    override fun onBindViewHolder(holder: CustomView, position: Int) {
        val sponsorsViewModel = arrayList[position]
        holder.bind(sponsorsViewModel)
    }

    override fun getItemCount(): Int {
        return arrayList.size
    }
}