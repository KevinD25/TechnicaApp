package com.davis.kevin.technicav2.ui.sponsors

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.viewpager2.widget.ViewPager2
import com.davis.kevin.technicav2.R
import com.davis.kevin.technicav2.adapters.CustomPartnerAdapter
import com.davis.kevin.technicav2.adapters.CustomPraesidiumAdapter
import com.davis.kevin.technicav2.ui.praesidium.PraesidiumViewModel
import kotlinx.android.synthetic.main.fragment_praesidium.*
import kotlinx.android.synthetic.main.fragment_sponsors.*
import me.relex.circleindicator.CircleIndicator3

class SponsorsFragment : Fragment() {

    private lateinit var sponsorsViewModel: SponsorsViewModel
    private var PartnerVP : ViewPager2? = null
    private lateinit var viewOfLayout : View
    private var customPartnerAdapter : CustomPartnerAdapter? = null
    private lateinit var ctx : Context

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        viewOfLayout = inflater!!.inflate(R.layout.fragment_sponsors, container, false)

        sponsorsViewModel =
            ViewModelProviders.of(this).get(SponsorsViewModel::class.java)


        PartnerVP = viewOfLayout.findViewById(R.id.partnerVP)
        ctx = activity!!.applicationContext

        sponsorsViewModel =
            ViewModelProviders.of(this).get(SponsorsViewModel::class.java)

        sponsorsViewModel.getArrayList().observe(this, Observer { sponsorsViewModels ->
            customPartnerAdapter = CustomPartnerAdapter(ctx, sponsorsViewModels!!)
            partnerVP!!.adapter= customPartnerAdapter
            val indicator = viewOfLayout.findViewById<CircleIndicator3>(R.id.indicator)
            indicator.setViewPager(partnerVP)
        })


        /* val textView: TextView = root.findViewById(R.id.text_share)
         praesidiumViewModel.text.observe(this, Observer {
             textView.text = it
         })*/
        return viewOfLayout
    }
}