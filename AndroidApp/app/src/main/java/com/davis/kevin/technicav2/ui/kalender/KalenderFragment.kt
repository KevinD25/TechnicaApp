package com.davis.kevin.technicav2.ui.kalender

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ExpandableListView
import android.widget.ListView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.davis.kevin.technicav2.R
import com.davis.kevin.technicav2.adapters.CustomKalenderAdapter
import com.davis.kevin.technicav2.ui.praesidium.PraesidiumViewModel

class KalenderFragment : Fragment() {

    private lateinit var kalenderViewModel: KalenderViewModel
    private var KalenderRV : RecyclerView? = null
    private lateinit var viewOfLayout: View
    private var customKalenderAdapter : CustomKalenderAdapter? = null
    private lateinit var ctx : Context
    private var arrayList = ArrayList<KalenderViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        viewOfLayout = inflater.inflate(R.layout.fragment_kalender, container, false)

        KalenderRV = viewOfLayout.findViewById(R.id.KalenderRV)

        ctx = requireActivity().applicationContext

        kalenderViewModel =
            ViewModelProviders.of(this).get(KalenderViewModel::class.java)

        kalenderViewModel.getArrayList().observe(viewLifecycleOwner, Observer { events ->
            for(event in events){
                val kalenderViewModel = KalenderViewModel(event)
                arrayList.add(kalenderViewModel)
                customKalenderAdapter = CustomKalenderAdapter(ctx, arrayList)
                KalenderRV!!.layoutManager = LinearLayoutManager(ctx)
                KalenderRV!!.adapter = customKalenderAdapter
            }
        })

        return viewOfLayout
    }
}