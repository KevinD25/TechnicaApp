package com.davis.kevin.technicav2.adapters

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.davis.kevin.technicav2.MainActivity
import com.davis.kevin.technicav2.R
import com.davis.kevin.technicav2.databinding.PartnerBinding
import com.davis.kevin.technicav2.ui.sponsors.SponsorsFragment
import com.davis.kevin.technicav2.ui.sponsors.SponsorsViewModel
import kotlinx.android.synthetic.main.inner_partner.view.*

class CustomPartnerAdapter(val ctx: Context, val arrayList: ArrayList<SponsorsViewModel>, private val navController: NavController)
    : RecyclerView.Adapter<CustomPartnerAdapter.CustomView>() {


    class CustomView(val partnerBinding: PartnerBinding, val parent: ViewGroup, private val navController: NavController)
        : RecyclerView.ViewHolder(partnerBinding.root) {

        fun bind(sponsorsViewModel: SponsorsViewModel) {
            this.partnerBinding.partnerModel = sponsorsViewModel

            itemView.btn_website_sponsor.setOnClickListener {
                val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(sponsorsViewModel.website))
                parent.context.startActivity(browserIntent)
            }

            itemView.btn_vacature_sponsor.setOnClickListener {
                SponsorsFragment.navigateToVacatures(navController, R.id.nav_vacatures, sponsorsViewModel.id)
            }

            partnerBinding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomView {
        val layoutInflater = LayoutInflater.from(parent.context)

        val partnerBinding: PartnerBinding = DataBindingUtil.inflate(layoutInflater,
            R.layout.inner_partner, parent, false)
        val partnerView = layoutInflater.inflate(R.layout.inner_partner, parent, false)

        return CustomView(partnerBinding, parent, navController)
    }

    override fun onBindViewHolder(holder: CustomView, position: Int) {
        val sponsorsViewModel = arrayList[position]
        holder.bind(sponsorsViewModel)
    }

    override fun getItemCount(): Int {
        return arrayList.size
    }
}