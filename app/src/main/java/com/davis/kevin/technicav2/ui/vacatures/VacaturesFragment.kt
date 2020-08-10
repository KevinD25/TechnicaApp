package com.davis.kevin.technicav2.ui.vacatures

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.RecyclerView
import com.davis.kevin.technicav2.R
import kotlinx.android.synthetic.main.fragment_vacatures.view.*

class VacaturesFragment : Fragment() {

    private lateinit var vacaturesViewModel: VacaturesViewModel
    private var VacatureRV: RecyclerView? = null
    private lateinit var viewOfLayout: View

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        viewOfLayout = inflater!!.inflate(R.layout.fragment_vacatures, container, false)

        VacatureRV = viewOfLayout.findViewById(R.id.VacatureRV)

        vacaturesViewModel =
            ViewModelProviders.of(this).get(VacaturesViewModel::class.java)

        vacaturesViewModel.getArrayList().observe(this, Observer {

        })

        //viewOfLayout.textView.text = "hello"   //add your view before id else will get nullpointer exception
        return viewOfLayout

    }
}