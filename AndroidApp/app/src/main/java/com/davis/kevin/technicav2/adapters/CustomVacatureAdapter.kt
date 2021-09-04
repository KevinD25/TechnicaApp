package com.davis.kevin.technicav2.adapters

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.davis.kevin.technicav2.R
import com.davis.kevin.technicav2.databinding.VacatureBinding
import com.davis.kevin.technicav2.ui.vacatures.VacaturesViewModel
import kotlinx.android.synthetic.main.inner_vacature.view.*


class CustomVacatureAdapter(private val ctx: Context, private val arrayList: ArrayList<VacaturesViewModel>)
    : RecyclerView.Adapter<CustomVacatureAdapter.CustomView>() {

    class CustomView(val vacatureBinding: VacatureBinding, val parent: ViewGroup) : RecyclerView.ViewHolder(
        vacatureBinding.root) {

        fun bind(vacaturesViewModel: VacaturesViewModel) {
            this.vacatureBinding.vacatureModel = vacaturesViewModel
            itemView.ic_weblink.setOnClickListener {
                val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(vacaturesViewModel.link))
                parent.context.startActivity(browserIntent)
            }
            vacatureBinding.executePendingBindings()
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomView {
        val layoutInflater = LayoutInflater.from(parent.context)
        val vacatureBinding: VacatureBinding = DataBindingUtil.inflate(layoutInflater,
            R.layout.inner_vacature, parent, false)
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
