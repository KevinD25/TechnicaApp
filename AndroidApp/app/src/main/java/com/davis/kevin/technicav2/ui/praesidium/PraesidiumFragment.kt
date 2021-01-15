package com.davis.kevin.technicav2.ui.praesidium

import android.content.Context
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.viewpager2.widget.ViewPager2
import com.davis.kevin.technicav2.MainActivity
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
    var arrayList = ArrayList<PraesidiumViewModel>()
    private var images : MutableMap<String, Drawable>? = HashMap()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        viewOfLayout = inflater!!.inflate(R.layout.fragment_praesidium, container, false)
        praesidiumViewModel = ViewModelProviders.of(this).get(PraesidiumViewModel::class.java)
        ctx = requireActivity().applicationContext
        PraesidiumVP = viewOfLayout.findViewById(R.id.praesidiumVP)




        praesidiumViewModel.getArray().observe(viewLifecycleOwner, Observer { praesidium ->
            praesidium.let {
                for (praesidia in it) {
                    val praesidiumViewModel = PraesidiumViewModel(praesidia)
                    arrayList.add(praesidiumViewModel)
                }
                customPraesidiumAdapter = CustomPraesidiumAdapter(ctx, arrayList)
                PraesidiumVP!!.adapter = customPraesidiumAdapter

                val indicator = viewOfLayout.findViewById<CircleIndicator3>(R.id.indicator)
                indicator.setViewPager(PraesidiumVP)
            }
        })

        return viewOfLayout
    }

   /* private fun getAllImages(){
        images?.set("praeses", getDrawable(ctx, R.drawable.praeses)!!)
        images?.set("vicepraeses", getDrawable(ctx, R.drawable.vicepraeses)!!)
        images?.set("soc", getDrawable(ctx, R.drawable.soc)!!)
        images?.set("quaestor", getDrawable(ctx, R.drawable.quaestor)!!)
        images?.set("erelid", getDrawable(ctx, R.drawable.erelid)!!)
        images?.set("peter", getDrawable(ctx, R.drawable.peter)!!)
        images?.set("redactor", getDrawable(ctx, R.drawable.redactor)!!)
        images?.set("media", getDrawable(ctx, R.drawable.media)!!)
        images?.set("feest", getDrawable(ctx, R.drawable.feest)!!)
        images?.set("zeden", getDrawable(ctx, R.drawable.zeden)!!)
        images?.set("meester", getDrawable(ctx, R.drawable.meester)!!)
        images?.set("meter", getDrawable(ctx, R.drawable.meter)!!)
        images?.set("meterke", getDrawable(ctx, R.drawable.meterke)!!)
        images?.set("temmer", getDrawable(ctx, R.drawable.temmer)!!)
        images?.set("pr", getDrawable(ctx, R.drawable.pr)!!)
        images?.set("abactis", getDrawable(ctx, R.drawable.abactis)!!)
        images?.set("erepraeses", getDrawable(ctx, R.drawable.erepraeses)!!)
        images?.set("cantor", getDrawable(ctx, R.drawable.cantor)!!)
    }*/
}