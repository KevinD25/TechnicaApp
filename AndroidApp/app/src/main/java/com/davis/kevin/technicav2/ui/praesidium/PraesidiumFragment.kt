package com.davis.kevin.technicav2.ui.praesidium

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.davis.kevin.technicav2.R
import com.davis.kevin.technicav2.adapters.CustomPraesidiumAdapter
import com.davis.kevin.technicav2.ui.home.HomeFragment
import com.davis.kevin.technicav2.ui.sponsors.SponsorsFragment
import me.relex.circleindicator.CircleIndicator3

class PraesidiumFragment : Fragment() {

    private lateinit var praesidiumViewModel: PraesidiumViewModel
    private var PraesidiumVP : ViewPager2? = null
    private lateinit var viewOfLayout : View
    private var customPraesidiumAdapter : CustomPraesidiumAdapter? = null
    private lateinit var ctx : Context
    var arrayList = ArrayList<PraesidiumViewModel>()

    companion object { var ObjectAmount: Long? = 1000; }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {

        // Get Context
        ctx = requireActivity().applicationContext

        // activity_main.include --> app_bar_main.include --> content_main.fragment
        // content_main.fragment.navGraph --> navigation (directory) --> mobile_navigation
        // mobile_navigation.fragment.fragment_praesidium
        viewOfLayout = inflater.inflate(R.layout.fragment_praesidium, container, false)
        // Get ViewPager
        PraesidiumVP = viewOfLayout.findViewById(R.id.praesidium_VP)

        if (!HomeFragment.isOnline(ctx)) HomeFragment.navigateHome(ctx, this.findNavController())

        // Get View Model --> The database class is converted to the one in that is used for the view (ViewModel)
        praesidiumViewModel = ViewModelProviders.of(this).get(PraesidiumViewModel::class.java)
        // Get the data from the FirebaseHandler (getPraesidium()) and use it in the view
        praesidiumViewModel.getArray().observe(viewLifecycleOwner) { praesidium -> praesidium.let {
                for (praesidia in it) {
                    val praesidiumViewModel = PraesidiumViewModel(praesidia)
                    arrayList.add(praesidiumViewModel)
                }

                if (ObjectAmount != null)
                    if (arrayList.size < SponsorsFragment.ObjectAmount!!)
                        HomeFragment.navigateHome(ctx, this.findNavController())

                customPraesidiumAdapter = CustomPraesidiumAdapter(arrayList)
                PraesidiumVP!!.adapter = customPraesidiumAdapter

                val indicator = viewOfLayout.findViewById<CircleIndicator3>(R.id.indicator)
                indicator.setViewPager(PraesidiumVP)
            }
        }


        return viewOfLayout
    }
}