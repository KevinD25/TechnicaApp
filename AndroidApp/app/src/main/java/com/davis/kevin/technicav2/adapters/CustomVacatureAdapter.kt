package com.davis.kevin.technicav2.adapters

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.davis.kevin.technicav2.databinding.InnerVacatureBinding
import com.davis.kevin.technicav2.ui.vacatures.VacaturesViewModel


class CustomVacatureAdapter(private val arrayList: ArrayList<VacaturesViewModel>)
    : RecyclerView.Adapter<CustomVacatureAdapter.CustomView>() {

    private lateinit var _bindingInner: InnerVacatureBinding
    private val bindingInner get() = _bindingInner!!

    class CustomView(private val bindingInner: InnerVacatureBinding): RecyclerView.ViewHolder(bindingInner.root) {
        fun bind(vacaturesViewModel: VacaturesViewModel) {
            bindingInner.txtName.text = vacaturesViewModel.name
            bindingInner.imgLogo.setImageDrawable(vacaturesViewModel.getViewImage())
            bindingInner.txtDescription.text = vacaturesViewModel.description
            bindingInner.icWeblink.setOnClickListener {
                val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(vacaturesViewModel.link))
                bindingInner.root.context.startActivity(browserIntent)
            }
            bindingInner.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomView {
        _bindingInner = InnerVacatureBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        // val vacatureBinding: VacatureBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.inner_vacature, parent, false)
        return CustomView(bindingInner)
    }

    override fun getItemCount(): Int {
        return arrayList.size
    }

    override fun onBindViewHolder(holder: CustomView, position: Int) {
        val vacaturesViewModel = arrayList[position]
        holder.bind(vacaturesViewModel)
    }
}
