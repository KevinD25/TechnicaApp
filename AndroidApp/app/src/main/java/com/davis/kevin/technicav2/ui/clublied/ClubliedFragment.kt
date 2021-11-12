package com.davis.kevin.technicav2.ui.clublied

import android.content.Context
import android.media.MediaPlayer
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.davis.kevin.technicav2.R

class ClubliedFragment : Fragment() {

    private lateinit var clubliedViewModel: ClubliedViewModel
    private lateinit var viewOfLayout: View
    private lateinit var ctx: Context
    private lateinit var player: MediaPlayer


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {

        ctx = requireActivity().applicationContext
        player = MediaPlayer.create(ctx, R.raw.technica_clublied)

        viewOfLayout = inflater.inflate(R.layout.fragment_clublied, container, false)
        val button: Button? = viewOfLayout.findViewById(R.id.btn_clublied)
        button?.setOnClickListener{ player.start() }

        clubliedViewModel = ViewModelProviders.of(this)[ClubliedViewModel::class.java]
        /* clubliedViewModel.text.observe(this, Observer { })*/
        return viewOfLayout
    }
}