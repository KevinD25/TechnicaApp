package com.davis.kevin.technicav2.adapters

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.davis.kevin.technicav2.MainActivity
import com.davis.kevin.technicav2.R
import com.davis.kevin.technicav2.databinding.InnerPartnerBinding
import com.davis.kevin.technicav2.ui.sponsors.SponsorsViewModel
import com.davis.kevin.technicav2.ui.vacatures.VacaturesFragment

class CustomPartnerAdapter(val arrayList: ArrayList<SponsorsViewModel>): RecyclerView.Adapter<CustomPartnerAdapter.CustomView>() {

    private lateinit var _bindingInner: InnerPartnerBinding
    private val bindingInner get() = _bindingInner!!

    class CustomView(private val bindingInner: InnerPartnerBinding): RecyclerView.ViewHolder(bindingInner.root) {
        fun bind(sponsorsViewModel: SponsorsViewModel) {
            bindingInner.imgSponsor.setImageBitmap(sponsorsViewModel.image)
            bindingInner.txtInfo.text = "${sponsorsViewModel.name} \n ${sponsorsViewModel.description}"
            bindingInner.btnWebsiteSponsor.setOnClickListener {
                val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(sponsorsViewModel.website))
                bindingInner.root.context.startActivity(browserIntent)
            }
            bindingInner.btnVacatureSponsor.setOnClickListener {
                VacaturesFragment.sponsorId = sponsorsViewModel.id
                VacaturesFragment.sponsorImage = sponsorsViewModel.image
                MainActivity.navigateToFragment(bindingInner.root.findNavController(), R.id.nav_vacatures)
            }
            bindingInner.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomView {
        _bindingInner = InnerPartnerBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        //val partnerBinding: PartnerBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.inner_partner, parent, false)
        return CustomView(bindingInner)
    }

    override fun onBindViewHolder(holder: CustomView, position: Int) {
        val sponsorsViewModel = arrayList[position]
        holder.bind(sponsorsViewModel)
    }

    override fun getItemCount(): Int {
        return arrayList.size
    }
}