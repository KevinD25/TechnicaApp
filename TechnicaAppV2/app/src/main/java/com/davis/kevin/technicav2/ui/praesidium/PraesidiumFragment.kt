package com.davis.kevin.technicav2.ui.praesidium

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
import com.davis.kevin.technicav2.adapters.CustomPraesidiumAdapter
import kotlinx.android.synthetic.main.fragment_praesidium.*
import me.relex.circleindicator.CircleIndicator3

class PraesidiumFragment : Fragment() {

    private lateinit var praesidiumViewModel: PraesidiumViewModel
    private var PraesidiumVP : ViewPager2? = null
    private lateinit var viewOfLayout : View
    private var customPraesidiumAdapter : CustomPraesidiumAdapter? = null
    private lateinit var ctx : Context

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        viewOfLayout = inflater!!.inflate(R.layout.fragment_praesidium, container, false)

        PraesidiumVP = viewOfLayout.findViewById(R.id.praesidiumVP)
        ctx = activity!!.applicationContext

        praesidiumViewModel =
            ViewModelProviders.of(this).get(PraesidiumViewModel::class.java)

        praesidiumViewModel.getArrayList().observe(this, Observer { praesidiumViewModels ->
            customPraesidiumAdapter = CustomPraesidiumAdapter(ctx, praesidiumViewModels!!)
            praesidiumVP!!.adapter= customPraesidiumAdapter
            val indicator = viewOfLayout.findViewById<CircleIndicator3>(R.id.indicator)
            indicator.setViewPager(praesidiumVP)
        })




       /* val textView: TextView = root.findViewById(R.id.text_share)
        praesidiumViewModel.text.observe(this, Observer {
            textView.text = it
        })*/
        return viewOfLayout
    }
}