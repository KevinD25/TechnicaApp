package com.davis.kevin.technicav2.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.davis.kevin.technicav2.R
import com.davis.kevin.technicav2.databinding.VacatureBinding
import com.davis.kevin.technicav2.ui.vacatures.VacaturesViewModel

class CustomAdapter (private val cxt: Context, private val arrayList:ArrayList<VacaturesViewModel>):RecyclerView.Adapter<CustomAdapter.CustomView>() {





    class CustomView( val vacatureBinding: VacatureBinding): RecyclerView.ViewHolder(vacatureBinding.root){


        fun bind(vacaturesViewModel: VacaturesViewModel){
            this.vacatureBinding.vacaturemodel = vacaturesViewModel
            vacatureBinding.executePendingBindings()
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomView {
        val layoutInflater = LayoutInflater.from(parent.context)

        val vacatureBinding:VacatureBinding = DataBindingUtil.inflate(layoutInflater, R.layout.fragment_vacatures, parent, false)

        return CustomView(vacatureBinding)
    }

    override fun getItemCount(): Int {
        arrayList.size
    }

    override fun onBindViewHolder(holder: CustomView, position: Int) {

        val vacaturesViewModel = arrayList[position]
        holder.bind(vacaturesViewModel)
    }
}