package com.davis.kevin.technicav2.adapters

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.davis.kevin.technicav2.R
import com.davis.kevin.technicav2.databinding.VacatureBinding
import com.davis.kevin.technicav2.ui.vacatures.VacaturesViewModel


class CustomVacatureAdapter(
    private val ctx: Context,
    private val arrayList: ArrayList<VacaturesViewModel>
):RecyclerView.Adapter<CustomVacatureAdapter.CustomView>() {

    private val activity: Activity? = null

    class CustomView(val vacatureBinding: VacatureBinding, v: View, ctx: Context, arrayList: ArrayList<VacaturesViewModel>): RecyclerView.ViewHolder(
        vacatureBinding.root
    ) {

        val itemLink : TextView = v.findViewById(R.id.txtLink)

        init{
            itemLink.setOnClickListener {v: View ->
                val position = adapterPosition
                val link = arrayList[position].link
                val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(link)) //Not sure wether this one works
                ctx.startActivity(browserIntent)
            }
        }

        fun bind(vacaturesViewModel: VacaturesViewModel){
            this.vacatureBinding.vacaturemodel = vacaturesViewModel
            vacatureBinding.executePendingBindings()
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomView {
        val layoutInflater = LayoutInflater.from(parent.context)

        val vacatureBinding:VacatureBinding = DataBindingUtil.inflate(
            layoutInflater,
            R.layout.innervacaturelayout,
            parent,
            false
        )
        val vacatureView = layoutInflater.inflate(R.layout.innervacaturelayout, parent, false)

        return CustomView(vacatureBinding, vacatureView, ctx, arrayList)
    }

    override fun getItemCount(): Int {
        return arrayList.size
    }

    override fun onBindViewHolder(holder: CustomView, position: Int) {

        val vacaturesViewModel = arrayList[position]

        holder.itemView.setOnClickListener(View.OnClickListener {
            val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(arrayList[position].link)) //Not sure wether this one works
            ctx.startActivity(browserIntent)
        })

        holder.bind(vacaturesViewModel)
    }


}
