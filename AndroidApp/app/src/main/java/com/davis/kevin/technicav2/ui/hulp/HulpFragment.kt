package com.davis.kevin.technicav2.ui.hulp

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.davis.kevin.technicav2.R
import com.davis.kevin.technicav2.databinding.FragmentHulpBinding
import com.davis.kevin.technicav2.ui.home.HomeFragment


class HulpFragment : Fragment() {

    private lateinit var _bindingFragment: FragmentHulpBinding
    private val bindingFragment get() = _bindingFragment!!
    private lateinit var ctx : Context
    private val hulpIntent: Intent = Intent(Intent.ACTION_VIEW)

    private lateinit var viewModel: HulpViewModel

    override fun onCreateView( inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {

        _bindingFragment = FragmentHulpBinding.inflate(inflater, container, false)
        val view = bindingFragment.root

        ctx = requireActivity().applicationContext
        // Return if no context is found
        if (!HomeFragment.isOnline(ctx)) HomeFragment.navigateHome(ctx, this.findNavController())

        bindingFragment.barMail.setOnClickListener{
            hulpIntent.data = Uri.parse("mailto:" + resources.getString(R.string.hulp_mail))
            startActivity(hulpIntent)
        }

        bindingFragment.barCall.setOnClickListener{
            val intent = Intent(Intent.ACTION_CALL)
            intent.data = Uri.parse("tel:" + resources.getString(R.string.hulp_call))
            if (ContextCompat.checkSelfPermission(ctx, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(requireActivity(), arrayOf(Manifest.permission.CALL_PHONE), 1)
            } else {
                try { startActivity(intent) } catch (e: SecurityException) { e.printStackTrace() }
            }
        }

        bindingFragment.barMaps.setOnClickListener{
            hulpIntent.data = Uri.parse("https://maps.google.co.in/maps?q=" + resources.getString(R.string.hulp_maps) + "&?z=1")
            hulpIntent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            ctx.startActivity(hulpIntent)
        }
        return view
    }

    @Deprecated("Deprecated in Java")
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this)[HulpViewModel::class.java]
    }

}