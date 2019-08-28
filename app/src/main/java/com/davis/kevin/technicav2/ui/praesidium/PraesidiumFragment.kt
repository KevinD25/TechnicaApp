package com.davis.kevin.technicav2.ui.praesidium

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.davis.kevin.technicav2.R

class PraesidiumFragment : Fragment() {

    private lateinit var praesidiumViewModel: PraesidiumViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        praesidiumViewModel =
            ViewModelProviders.of(this).get(PraesidiumViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_praesidium, container, false)
        val textView: TextView = root.findViewById(R.id.text_share)
        praesidiumViewModel.text.observe(this, Observer {
            textView.text = it
        })
        return root
    }
}