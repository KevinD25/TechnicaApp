package com.davis.kevin.technicav2.adapters

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.davis.kevin.technicav2.R
import com.davis.kevin.technicav2.databinding.PartnerBinding
import com.davis.kevin.technicav2.ui.sponsors.SponsorsViewModel
import kotlinx.android.synthetic.main.inner_partner.view.*

class CustomPartnerAdapter(
    val ctx: Context,
    val arrayList: ArrayList<SponsorsViewModel>,
   /* val onClickListener: OnClickListener*/
) : RecyclerView.Adapter<CustomPartnerAdapter.CustomView>() {


    class CustomView(val partnerBinding: PartnerBinding, val parent: ViewGroup) :
        RecyclerView.ViewHolder(partnerBinding.root) {

        fun bind(sponsorsViewModel: SponsorsViewModel) {
            this.partnerBinding.partnermodel = sponsorsViewModel

            itemView.btnWebsite.setOnClickListener {
                val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(sponsorsViewModel.website))
                parent.context.startActivity(browserIntent)
            }
            partnerBinding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomView {
        val layoutInflater = LayoutInflater.from(parent.context)

        val partnerBinding: PartnerBinding =
            DataBindingUtil.inflate(layoutInflater, R.layout.inner_partner, parent, false)
        val partnerView = layoutInflater.inflate(R.layout.inner_partner, parent, false)

        return CustomView(partnerBinding, parent)
    }

    override fun onBindViewHolder(holder: CustomView, position: Int) {
        val sponsorsViewModel = arrayList[position]
        holder.bind(sponsorsViewModel)
    }

    override fun getItemCount(): Int {
        return arrayList.size
    }
}