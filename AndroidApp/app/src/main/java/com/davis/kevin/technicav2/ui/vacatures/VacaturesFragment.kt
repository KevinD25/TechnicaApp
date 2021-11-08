package com.davis.kevin.technicav2.ui.vacatures

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.davis.kevin.technicav2.R
import com.davis.kevin.technicav2.adapters.CustomVacatureAdapter
import com.davis.kevin.technicav2.ui.home.HomeFragment
import com.davis.kevin.technicav2.ui.sponsors.SponsorsFragment

class VacaturesFragment : Fragment() {

    private lateinit var vacaturesViewModel: VacaturesViewModel
    private var VacatureRV: RecyclerView? = null
    private lateinit var viewOfLayout: View
    private var customVacatureAdapter: CustomVacatureAdapter? = null
    private lateinit var ctx: Context
    private var arrayList = ArrayList<VacaturesViewModel>()

    companion object { var ObjectAmount: Long? = 1000; }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        ctx = requireActivity().applicationContext

        viewOfLayout = inflater.inflate(R.layout.fragment_vacatures, container, false)
        VacatureRV = viewOfLayout.findViewById(R.id.vacature_RV)

        if (!HomeFragment.isOnline(ctx)) HomeFragment.navigateHome(ctx, this.findNavController())

        vacaturesViewModel = ViewModelProviders.of(this).get(VacaturesViewModel::class.java)
        val sponsorList = ArrayList<VacatureSponsor>()
        // Get Sponsor Items
        VacatureSponsor().getArray().observe(viewLifecycleOwner, Observer { partners ->
            for (partner in partners) {
                val vacatureSponsor = VacatureSponsor(partner)
                sponsorList.add(vacatureSponsor)
            }

            if (ObjectAmount != null)
                if (arrayList.size < SponsorsFragment.ObjectAmount!!)
                    HomeFragment.navigateHome(ctx, this.findNavController())

            // Use the name and id for the vacatures
            vacaturesViewModel.getArray().observe(viewLifecycleOwner, Observer { vacatures ->
                for (vacature in vacatures) {
                    val vacaturesViewModel = VacaturesViewModel(vacature)
                    vacaturesViewModel.partner = sponsorList.firstOrNull { sponsor: VacatureSponsor -> sponsor.id.equals(vacature.companyId) }
                    if (vacaturesViewModel.partner != null){
                        if (vacaturesViewModel.partner!!.id.equals(SponsorsFragment.sponsorId)
                            || SponsorsFragment.sponsorId.isNullOrEmpty()) {
                            arrayList.add(vacaturesViewModel)
                        }
                    }
                }
                customVacatureAdapter = CustomVacatureAdapter(arrayList)
                VacatureRV!!.layoutManager = LinearLayoutManager(ctx)
                VacatureRV!!.adapter = customVacatureAdapter
                SponsorsFragment.sponsorId = null
            })
        })

        return viewOfLayout
    }
}