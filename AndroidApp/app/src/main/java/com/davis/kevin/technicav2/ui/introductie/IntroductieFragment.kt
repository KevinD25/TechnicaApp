package com.davis.kevin.technicav2.ui.introductie

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.davis.kevin.technicav2.databinding.FragmentIntroductieBinding
import com.davis.kevin.technicav2.ui.home.HomeFragment

class IntroductieFragment : Fragment() {

    private lateinit var _bindingFragment: FragmentIntroductieBinding
    private val bindingFragment get() = _bindingFragment!!
    private lateinit var introductieViewModel: IntroductieViewModel
    private lateinit var ctx : Context

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {

        _bindingFragment = FragmentIntroductieBinding.inflate(inflater, container, false)
        val view = bindingFragment.root

        ctx = requireActivity().applicationContext
        // Return if no context is found
        if (!HomeFragment.isOnline(ctx)) HomeFragment.navigateHome(ctx, this.findNavController())

        introductieViewModel = ViewModelProviders.of(this)[IntroductieViewModel::class.java]
        introductieViewModel.getArray().observe(viewLifecycleOwner) { text ->
            val introductieViewModel = IntroductieViewModel(text)
            introductieViewModel.text = introductieViewModel.text?.replace("\\n", "\n\n")
            bindingFragment.txtIntroductie.text = introductieViewModel.text
        }
        return view
    }
}