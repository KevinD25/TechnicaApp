package com.davis.kevin.technicav2.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.davis.kevin.technicav2.R
import com.davis.kevin.technicav2.databinding.KalenderBinding
import com.davis.kevin.technicav2.ui.kalender.KalenderViewModel

class CustomKalenderAdapter(private val ctx: Context, private val arrayList:ArrayList<KalenderViewModel>):RecyclerView.Adapter<CustomKalenderAdapter.CustomView>(){


    class CustomView( val kalenderBinding: KalenderBinding): RecyclerView.ViewHolder(kalenderBinding.root){


        fun bind(kalenderViewModel: KalenderViewModel){
            this.kalenderBinding.kalendermodel = kalenderViewModel
            kalenderBinding.executePendingBindings()
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomView {
        val layoutInflater = LayoutInflater.from(parent.context)

        val kalenderBinding: KalenderBinding = DataBindingUtil.inflate(layoutInflater, R.layout.innerkalenderlayout, parent, false)

        return CustomView(kalenderBinding)
    }

    override fun getItemCount(): Int {
        return arrayList.size
    }

    override fun onBindViewHolder(holder: CustomView, position: Int) {

        val kalenderViewModel = arrayList[position]
        holder.bind(kalenderViewModel)
    }
}