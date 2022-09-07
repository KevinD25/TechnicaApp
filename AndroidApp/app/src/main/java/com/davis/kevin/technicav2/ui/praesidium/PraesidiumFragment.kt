package com.davis.kevin.technicav2.ui.praesidium

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.davis.kevin.technicav2.adapters.CustomPraesidiumAdapter
import com.davis.kevin.technicav2.databinding.FragmentPraesidiumBinding
import com.davis.kevin.technicav2.ui.home.HomeFragment
import com.davis.kevin.technicav2.ui.sponsors.SponsorsFragment

class PraesidiumFragment : Fragment() {

    private lateinit var _bindingFragment: FragmentPraesidiumBinding
    private val bindingFragment get() = _bindingFragment!!
    private lateinit var praesidiumViewModel: PraesidiumViewModel
    private var customPraesidiumAdapter : CustomPraesidiumAdapter? = null
    private lateinit var ctx : Context
    var arrayList = ArrayList<PraesidiumViewModel>()

    companion object { var ObjectAmount: Long? = 1000; }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {

        _bindingFragment = FragmentPraesidiumBinding.inflate(inflater, container, false)
        val view = bindingFragment.root

        ctx = requireActivity().applicationContext
        // Return if no context is found
        if (!HomeFragment.isOnline(ctx)) HomeFragment.navigateHome(ctx, this.findNavController())

        // Get View Model --> The database class is converted to the one in that is used for the view (ViewModel)
        praesidiumViewModel = ViewModelProviders.of(this)[PraesidiumViewModel::class.java]
        // Get the data from the FirebaseHandler (getPraesidium()) and use it in the view
        praesidiumViewModel.getArray().observe(viewLifecycleOwner) { praesidium -> praesidium.let {
            for (praesidia in it) {
                val praesidiumViewModel = PraesidiumViewModel(praesidia)
                arrayList.add(praesidiumViewModel)
            }
            if (ObjectAmount != null)
                if (arrayList.size < SponsorsFragment.ObjectAmount!!)
                    HomeFragment.navigateHome(ctx, this.findNavController())

            arrayList.sortBy { praesidium -> praesidium.getPraesidiumFunctieInt() }

            customPraesidiumAdapter = CustomPraesidiumAdapter(arrayList)
            bindingFragment.praesidiumVP.adapter = customPraesidiumAdapter
            bindingFragment.indicator.setViewPager(bindingFragment.praesidiumVP)
            }
        }
        return view
    }
}