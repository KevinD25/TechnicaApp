package com.davis.kevin.technicav2.ui.clublied

import android.content.Context
import android.media.MediaPlayer
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.davis.kevin.technicav2.R
import com.davis.kevin.technicav2.databinding.FragmentClubliedBinding
import com.davis.kevin.technicav2.ui.home.HomeFragment

class ClubliedFragment : Fragment() {

    private lateinit var _bindingFragment: FragmentClubliedBinding
    private val bindingFragment get() = _bindingFragment!!
    private lateinit var clubliedViewModel: ClubliedViewModel
    private lateinit var ctx: Context
    private lateinit var player: MediaPlayer


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {

        _bindingFragment = FragmentClubliedBinding.inflate(inflater, container, false)
        val view = bindingFragment.root

        ctx = requireActivity().applicationContext
        // Return if no context is found
        if (!HomeFragment.isOnline(ctx)) HomeFragment.navigateHome(ctx, this.findNavController())

        player = MediaPlayer.create(ctx, R.raw.technica_clublied)
        bindingFragment.btnClublied.setOnClickListener{ player.start() }

        clubliedViewModel = ViewModelProviders.of(this)[ClubliedViewModel::class.java]
        /* clubliedViewModel.text.observe(this, Observer { })*/
        return view
    }
}