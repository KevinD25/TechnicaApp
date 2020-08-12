package com.davis.kevin.technicav2.adapters

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.cardview.widget.CardView
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.davis.kevin.technicav2.DOM.Vacature
import com.davis.kevin.technicav2.R
import com.davis.kevin.technicav2.databinding.VacatureBinding
import com.davis.kevin.technicav2.ui.vacatures.VacaturesViewModel


class CustomVacatureAdapter(private val ctx: Context, private val arrayList:ArrayList<VacaturesViewModel>):RecyclerView.Adapter<CustomVacatureAdapter.CustomView>() {





    class CustomView( val vacatureBinding: VacatureBinding): RecyclerView.ViewHolder(vacatureBinding.root){


        fun bind(vacaturesViewModel: VacaturesViewModel){
            this.vacatureBinding.vacaturemodel = vacaturesViewModel
            vacatureBinding.executePendingBindings()
        }



    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomView {
        val layoutInflater = LayoutInflater.from(parent.context)

        val vacatureBinding:VacatureBinding = DataBindingUtil.inflate(layoutInflater, R.layout.innervacaturelayout, parent, false)
        val vacatureView = layoutInflater.inflate(R.layout.innervacaturelayout, parent, false)

        return CustomView(vacatureBinding)
    }

    override fun getItemCount(): Int {
        return arrayList.size
    }

    override fun onBindViewHolder(holder: CustomView, position: Int) {

        val vacaturesViewModel = arrayList[position]
        holder.bind(vacaturesViewModel)
    }



}
