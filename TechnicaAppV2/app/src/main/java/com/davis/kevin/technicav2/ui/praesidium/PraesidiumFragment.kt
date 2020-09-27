package com.davis.kevin.technicav2.ui.praesidium

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.viewpager2.widget.ViewPager2
import com.davis.kevin.technicav2.R
import com.davis.kevin.technicav2.adapters.CustomPraesidiumAdapter
import com.davis.kevin.technicav2.networking.Repository
import kotlinx.android.synthetic.main.fragment_praesidium.*
import kotlinx.coroutines.CoroutineScope
import me.relex.circleindicator.CircleIndicator3

class PraesidiumFragment : Fragment() {

    private lateinit var praesidiumViewModel: PraesidiumViewModel
    private var PraesidiumVP : ViewPager2? = null
    private lateinit var viewOfLayout : View
    private var customPraesidiumAdapter : CustomPraesidiumAdapter? = null
    private lateinit var ctx : Context
    var arrayList = ArrayList<PraesidiumViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        viewOfLayout = inflater!!.inflate(R.layout.fragment_praesidium, container, false)

        PraesidiumVP = viewOfLayout.findViewById(R.id.praesidiumVP)
        ctx = requireActivity().applicationContext

        praesidiumViewModel =
            ViewModelProviders.of(this).get(PraesidiumViewModel::class.java)

        praesidiumViewModel.getPraesidiumCall()

        praesidiumViewModel.getArrayList().observe(viewLifecycleOwner, Observer { praesidium ->
            for (praesidi in praesidium) {
                val praesidiumViewModel = PraesidiumViewModel(praesidi)
                arrayList!!.add(praesidiumViewModel)
                customPraesidiumAdapter = CustomPraesidiumAdapter(ctx, arrayList!!)
                praesidiumVP!!.adapter= customPraesidiumAdapter
                val indicator = viewOfLayout.findViewById<CircleIndicator3>(R.id.indicator)
                indicator.setViewPager(praesidiumVP)
            }
        })


        return viewOfLayout
    }

}