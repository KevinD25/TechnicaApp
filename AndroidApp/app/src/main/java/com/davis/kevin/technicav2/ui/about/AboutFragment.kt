package com.davis.kevin.technicav2.ui.about

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.davis.kevin.technicav2.R

class AboutFragment : Fragment() {

    private lateinit var viewOfLayout : View
    private lateinit var ctx : Context

    private lateinit var viewModel: AboutViewModel

    override fun onCreateView( inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        ctx = requireActivity().applicationContext

        viewOfLayout = inflater.inflate(R.layout.fragment_about, container, false)
        return viewOfLayout
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(AboutViewModel::class.java)
    }

}