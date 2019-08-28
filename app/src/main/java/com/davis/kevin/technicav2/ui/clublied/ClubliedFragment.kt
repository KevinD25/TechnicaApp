package com.davis.kevin.technicav2.ui.clublied

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.davis.kevin.technicav2.R

class ClubliedFragment : Fragment() {

    private lateinit var clubliedViewModel: ClubliedViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        clubliedViewModel =
            ViewModelProviders.of(this).get(ClubliedViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_clublied, container, false)
        val textView: TextView = root.findViewById(R.id.text_send)
        clubliedViewModel.text.observe(this, Observer {
            textView.text = it
        })
        return root
    }
}