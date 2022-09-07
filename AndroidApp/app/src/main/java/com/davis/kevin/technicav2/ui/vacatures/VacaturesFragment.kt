package com.davis.kevin.technicav2.ui.vacatures

import android.content.Context
import android.graphics.Bitmap
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.davis.kevin.technicav2.MainActivity
import com.davis.kevin.technicav2.adapters.CustomVacatureAdapter
import com.davis.kevin.technicav2.databinding.FragmentVacaturesBinding
import com.davis.kevin.technicav2.models.Vacature
import com.davis.kevin.technicav2.ui.home.HomeFragment
import com.davis.kevin.technicav2.ui.praesidium.PraesidiumFragment
import com.davis.kevin.technicav2.ui.sponsors.SponsorsFragment

class VacaturesFragment : Fragment() {

    private lateinit var _bindingFragment: FragmentVacaturesBinding
    private val bindingFragment get() = _bindingFragment!!
    private lateinit var vacaturesViewModel: VacaturesViewModel
    private var customVacatureAdapter: CustomVacatureAdapter? = null
    private lateinit var ctx: Context
    private var arrayList = ArrayList<VacaturesViewModel>()

    companion object {
        var ObjectAmount: Long? = 1000
        var sponsorId: String? = null
        var sponsorImage: Bitmap? = null
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {

        _bindingFragment = FragmentVacaturesBinding.inflate(inflater, container, false)
        val view = bindingFragment.root

        ctx = requireActivity().applicationContext
        // Return if no context is found
        if (!HomeFragment.isOnline(ctx)) HomeFragment.navigateHome(ctx, this.findNavController())

        vacaturesViewModel = ViewModelProviders.of(this)[VacaturesViewModel::class.java]
        val sponsorList = ArrayList<VacatureSponsor>()
        // Get Sponsor Items
        VacatureSponsor().getArray().observe(viewLifecycleOwner) { partners ->
            for (partner in partners) {
                val vacatureSponsor = VacatureSponsor(partner)
                sponsorList.add(vacatureSponsor)
            }

            if (PraesidiumFragment.ObjectAmount != null)
                if (arrayList.size < ObjectAmount!!) HomeFragment.navigateHome(ctx, this.findNavController())

            // Use the name and id for the vacatures
            vacaturesViewModel.getArray().observe(viewLifecycleOwner) { vacatures ->
                for (vacature in vacatures) {
                    val vacaturesViewModel = VacaturesViewModel(vacature)
                    // Assign Partners
                    vacaturesViewModel.partner = sponsorList.firstOrNull { sponsor: VacatureSponsor ->
                            sponsor.id.equals(vacature.companyId)
                        }
                    if (vacaturesViewModel.partner != null) {
                        if (vacaturesViewModel.partner!!.id.equals(sponsorId) || sponsorId.isNullOrEmpty()) {
                            arrayList.add(vacaturesViewModel)
                        }
                    }
                }
                if (arrayList.size == 0) {
                    val emptyVac = Vacature("0",
                        VacaturesFragment.sponsorId,
                        "Geen Vacatures Gevonden",
                        "Er is geen vacature gegeven door dit bedrijf.",
                        "", VacaturesFragment.sponsorImage)
                    val emptyVacVM = VacaturesViewModel(emptyVac)
                    // Assign Partners
                    emptyVacVM.partner = sponsorList.firstOrNull { sponsor: VacatureSponsor ->
                        sponsor.id.equals(emptyVac.companyId)
                    }
                    arrayList.add(emptyVacVM)
                }
                customVacatureAdapter = CustomVacatureAdapter(arrayList)
                bindingFragment.vacatureRV.layoutManager = LinearLayoutManager(ctx)
                bindingFragment.vacatureRV.adapter = customVacatureAdapter
                sponsorId = null
            }
        }
        return view
    }
}