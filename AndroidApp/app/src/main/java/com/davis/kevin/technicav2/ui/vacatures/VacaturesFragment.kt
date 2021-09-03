package com.davis.kevin.technicav2.ui.vacatures

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.davis.kevin.technicav2.R
import com.davis.kevin.technicav2.adapters.CustomVacatureAdapter
import com.davis.kevin.technicav2.ui.sponsors.SponsorsViewModel


class VacaturesFragment : Fragment() {

    private lateinit var vacaturesViewModel: VacaturesViewModel
    private var VacatureRV: RecyclerView? = null
    private lateinit var viewOfLayout: View
    private var customVacatureAdapter: CustomVacatureAdapter? = null
    private lateinit var ctx: Context
    private var arrayList = ArrayList<VacaturesViewModel>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        ctx = requireActivity().applicationContext

        viewOfLayout = inflater!!.inflate(R.layout.fragment_vacatures, container, false)
        VacatureRV = viewOfLayout.findViewById(R.id.vacature_RV)

        vacaturesViewModel = ViewModelProviders.of(this).get(VacaturesViewModel::class.java)
        // Map partner id and name
        vacaturesViewModel.getPartners().observe(viewLifecycleOwner, Observer { partners ->
            for (partner in partners) {
                // Adds only new values
                if (!VacaturesViewModel.partnerMap.containsKey(partner.id)){
                    val sponsorsViewModel = SponsorsViewModel(partner)
                    VacaturesViewModel.partnerMap.put(sponsorsViewModel.id!!, sponsorsViewModel.name!!)
                }
            }
        })
        // Use the name and id for vacature use
        vacaturesViewModel.getArray().observe(viewLifecycleOwner, Observer { vacatures ->
            for (vacature in vacatures) {
                val vacaturesViewModel = VacaturesViewModel(vacature)
                arrayList.add(vacaturesViewModel)
                customVacatureAdapter = CustomVacatureAdapter(ctx, arrayList)
                VacatureRV!!.layoutManager = LinearLayoutManager(ctx)
                VacatureRV!!.adapter = customVacatureAdapter
            }
        })

        return viewOfLayout
    }
}