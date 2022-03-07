package com.davis.kevin.technicav2.ui.introductie

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.davis.kevin.technicav2.R
import kotlinx.android.synthetic.main.fragment_introductie.*

class IntroductieFragment : Fragment() {

    private lateinit var introductieViewModel: IntroductieViewModel
    private lateinit var viewOfLayout : View
    private lateinit var ctx : Context

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {

        ctx = requireActivity().applicationContext

        viewOfLayout = inflater.inflate(R.layout.fragment_introductie, container, false)
        introductieViewModel = ViewModelProviders.of(this)[IntroductieViewModel::class.java]

        introductieViewModel.getArray().observe(viewLifecycleOwner) { text ->
            val introductieViewModel = IntroductieViewModel(text)
            introductieViewModel.text = introductieViewModel.text?.replace("\\n", "\n\n")
            txt_introductie.text = introductieViewModel.text
        }

        return viewOfLayout
    }
}