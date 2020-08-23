package com.davis.kevin.technicav2.ui.vacatures

import android.content.Context
import android.content.Intent
import android.net.Uri
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
import kotlinx.android.synthetic.main.fragment_vacatures.view.*


class VacaturesFragment : Fragment(){

    private lateinit var vacaturesViewModel: VacaturesViewModel
    private var VacatureRV: RecyclerView? = null
    private lateinit var viewOfLayout: View
    private var customVacatureAdapter : CustomVacatureAdapter? = null
    private lateinit var ctx : Context


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        viewOfLayout = inflater!!.inflate(R.layout.fragment_vacatures, container, false)

        VacatureRV = viewOfLayout.findViewById(R.id.VacatureRV)
        ctx = activity!!.applicationContext

        vacaturesViewModel =
            ViewModelProviders.of(this).get(VacaturesViewModel::class.java)

        vacaturesViewModel.getArrayList().observe(this, Observer { vacaturesViewModels ->


            customVacatureAdapter = CustomVacatureAdapter(ctx, vacaturesViewModels!!)
            VacatureRV!!.layoutManager = LinearLayoutManager(ctx)
            VacatureRV!!.adapter = customVacatureAdapter

        })

        return viewOfLayout

    }

}