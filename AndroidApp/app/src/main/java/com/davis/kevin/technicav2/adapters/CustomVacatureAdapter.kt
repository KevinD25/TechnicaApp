package com.davis.kevin.technicav2.adapters

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.davis.kevin.technicav2.R
import com.davis.kevin.technicav2.databinding.VacatureBinding
import com.davis.kevin.technicav2.ui.sponsors.SponsorsViewModel
import com.davis.kevin.technicav2.ui.vacatures.VacaturesViewModel
import kotlinx.android.synthetic.main.innerpartnerlayout.view.*
import kotlinx.android.synthetic.main.innervacaturelayout.view.*


class CustomVacatureAdapter(
    private val ctx: Context,
    private val arrayList: ArrayList<VacaturesViewModel>
) : RecyclerView.Adapter<CustomVacatureAdapter.CustomView>() {


    class CustomView(val vacatureBinding: VacatureBinding, val parent: ViewGroup) : RecyclerView.ViewHolder(
        vacatureBinding.root
    ) {

        //val itemLink : TextView = v.findViewById(R.id.txtLink)

        /*init{
            itemLink.setOnClickListener {v: View ->
                val position = adapterPosition
                val link = arrayList[position].link
                val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(link)) //Not sure wether this one works
                ctx.startActivity(browserIntent)
            }
        }*/

        fun bind(vacaturesViewModel: VacaturesViewModel) {
            this.vacatureBinding.vacaturemodel = vacaturesViewModel
            itemView.iv_link.setOnClickListener {
                val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(vacaturesViewModel.link))
                parent.context.startActivity(browserIntent)
            }
            vacatureBinding.executePendingBindings()
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomView {
        val layoutInflater = LayoutInflater.from(parent.context)
        val vacatureBinding: VacatureBinding = DataBindingUtil.inflate(
            layoutInflater,
            R.layout.innervacaturelayout,
            parent,
            false
        )
        return CustomView(vacatureBinding, parent)
    }

    override fun getItemCount(): Int {
        return arrayList.size
    }

    override fun onBindViewHolder(holder: CustomView, position: Int) {

        val vacaturesViewModel = arrayList[position]

        /*holder.itemView.setOnClickListener(View.OnClickListener {
            val browserIntent = Intent(
                Intent.ACTION_VIEW,
                Uri.parse(arrayList[position].link)
            ) //Not sure wether this one works
            ctx.startActivity(browserIntent)
        })*/

        holder.bind(vacaturesViewModel)
    }


}
