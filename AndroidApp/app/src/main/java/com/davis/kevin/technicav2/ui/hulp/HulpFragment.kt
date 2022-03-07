package com.davis.kevin.technicav2.ui.hulp

import android.Manifest
import android.app.Activity
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
import com.davis.kevin.technicav2.R


class HulpFragment : Fragment() {

    private lateinit var viewOfLayout : View
    private lateinit var ctx : Context
    private val hulpIntent: Intent = Intent(Intent.ACTION_VIEW)

    private lateinit var viewModel: HulpViewModel

    override fun onCreateView( inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {

        ctx = requireActivity().applicationContext

        viewOfLayout = inflater.inflate(R.layout.fragment_hulp, container, false)

        val barMail: View? = viewOfLayout.findViewById(R.id.bar_mail)
        barMail?.setOnClickListener{
            hulpIntent.data = Uri.parse("mailto:" + resources.getString(R.string.hulp_mail))
            startActivity(hulpIntent)
        }

        val barCall: View? = viewOfLayout.findViewById(R.id.bar_call)
        barCall?.setOnClickListener{
            val intent = Intent(Intent.ACTION_CALL)
            intent.data = Uri.parse("tel:" + resources.getString(R.string.hulp_call))
            if (ContextCompat.checkSelfPermission(ctx, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(requireActivity(), arrayOf(Manifest.permission.CALL_PHONE), 1)
            } else {
                try { startActivity(intent) }
                catch (e: SecurityException) { e.printStackTrace() }
            }
        }

        val barMaps: View? = viewOfLayout.findViewById(R.id.bar_maps)
        barMaps?.setOnClickListener{
            hulpIntent.data = Uri.parse("https://maps.google.co.in/maps?q=" + resources.getString(R.string.hulp_maps) + "&?z=1")
            hulpIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            ctx.startActivity(hulpIntent)
        }

        return viewOfLayout
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this)[HulpViewModel::class.java]
    }

}