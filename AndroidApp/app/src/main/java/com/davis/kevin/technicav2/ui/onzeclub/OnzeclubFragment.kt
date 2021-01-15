package com.davis.kevin.technicav2.ui.onzeclub

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.davis.kevin.technicav2.R
import com.davis.kevin.technicav2.ui.sponsors.SponsorsViewModel
import kotlinx.android.synthetic.main.fragment_onzeclub.*

class OnzeclubFragment : Fragment() {

    private lateinit var onzeclubViewModel: OnzeclubViewModel
    private lateinit var viewOfLayout : View
    private lateinit var ctx : Context

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        viewOfLayout = inflater.inflate(R.layout.fragment_onzeclub, container, false)
        onzeclubViewModel =
            ViewModelProviders.of(this).get(OnzeclubViewModel::class.java)
        ctx = requireActivity().applicationContext
        onzeclubViewModel.getContext(ctx)
        onzeclubViewModel.getArray().observe(viewLifecycleOwner, Observer { text ->
            txtClub.text = text.clubText
        })

        return viewOfLayout
    }
}