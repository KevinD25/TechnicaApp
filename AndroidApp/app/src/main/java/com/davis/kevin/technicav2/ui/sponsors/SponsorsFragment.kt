package com.davis.kevin.technicav2.ui.sponsors

import android.content.Context
import android.graphics.Bitmap
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.davis.kevin.technicav2.MainActivity
import com.davis.kevin.technicav2.adapters.CustomPartnerAdapter
import com.davis.kevin.technicav2.databinding.FragmentSponsorsBinding
import com.davis.kevin.technicav2.ui.home.HomeFragment
import com.davis.kevin.technicav2.ui.praesidium.PraesidiumFragment

class SponsorsFragment : Fragment() {

    private lateinit var _bindingFragment: FragmentSponsorsBinding
    private val bindingFragment get() = _bindingFragment!!
    private lateinit var sponsorsViewModel: SponsorsViewModel
    private var customPartnerAdapter: CustomPartnerAdapter? = null
    private lateinit var ctx: Context
    private var arrayList = ArrayList<SponsorsViewModel>()

    companion object { var ObjectAmount: Long? = 1000 }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {

        _bindingFragment = FragmentSponsorsBinding.inflate(inflater, container, false)
        val view = bindingFragment.root

        ctx = requireActivity().applicationContext
        // Return if no context is found
        if (!HomeFragment.isOnline(ctx)) HomeFragment.navigateHome(ctx, this.findNavController())

        sponsorsViewModel = ViewModelProviders.of(this)[SponsorsViewModel::class.java]
        sponsorsViewModel.getArray().observe(viewLifecycleOwner) { partners ->
            if (arrayList.size < partners.size){ // Without this line the array grows with the vacatures button press
                for (partner in partners) {
                    val partnerViewModel = SponsorsViewModel(partner)
                    arrayList.add(partnerViewModel)
                }
            }
            if (PraesidiumFragment.ObjectAmount != null)
                if (arrayList.size < ObjectAmount!!) HomeFragment.navigateHome(ctx, this.findNavController())

            arrayList.sortBy { partner -> partner.getPriority() }

            customPartnerAdapter = CustomPartnerAdapter(arrayList)
            bindingFragment.partnerVP.adapter = customPartnerAdapter
            bindingFragment.indicator.setViewPager(bindingFragment.partnerVP)
        }
        return view
    }
}