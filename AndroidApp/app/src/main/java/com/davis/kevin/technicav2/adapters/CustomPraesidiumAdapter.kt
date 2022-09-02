package com.davis.kevin.technicav2.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.davis.kevin.technicav2.databinding.InnerPraesidiumBinding
import com.davis.kevin.technicav2.ui.praesidium.PraesidiumViewModel

class CustomPraesidiumAdapter(var arrayList: ArrayList<PraesidiumViewModel>) :
    RecyclerView.Adapter<CustomPraesidiumAdapter.CustomView>() {

    private lateinit var _bindingInner: InnerPraesidiumBinding
    private val bindingInner get() = _bindingInner!!

    class CustomView(private val bindingInner: InnerPraesidiumBinding) : RecyclerView.ViewHolder(bindingInner.root) {
        fun bind(praesidiumViewModel: PraesidiumViewModel) {
            bindingInner.imgPerson.setImageBitmap(praesidiumViewModel.image)
            bindingInner.txtPerson.text = praesidiumViewModel.name  + ' ' + praesidiumViewModel.surname + '\n' + praesidiumViewModel.getPraesidiumFunctie()
            bindingInner.txtStudie.text = praesidiumViewModel.studies
            bindingInner.txtBday.text = praesidiumViewModel.getViewBirthday()
            bindingInner.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomView {
        _bindingInner = InnerPraesidiumBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        // val praesidiumBinding: PraesidiumBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.inner_praesidium, parent, false)
        return CustomView(bindingInner)
    }

    override fun getItemCount(): Int {
        return arrayList.size
    }

    override fun onBindViewHolder(holder: CustomView, position: Int) {
        val praesidiumViewModel = arrayList[position]
        holder.bind(praesidiumViewModel)
    }
}