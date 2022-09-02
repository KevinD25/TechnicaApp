package com.davis.kevin.technicav2.ui.kalender

import android.content.Context
import android.content.Intent
import android.graphics.drawable.BitmapDrawable
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.davis.kevin.technicav2.adapters.CustomKalenderAdapter
import com.davis.kevin.technicav2.databinding.FragmentKalenderBinding
import com.davis.kevin.technicav2.ui.home.HomeFragment
import java.time.LocalDate


class KalenderFragment : Fragment() {

    private lateinit var _bindingFragment: FragmentKalenderBinding
    private val bindingFragment get() = _bindingFragment!!
    private lateinit var kalenderViewModel: KalenderViewModel
    private var customKalenderAdapter: CustomKalenderAdapter? = null
    private lateinit var ctx: Context
    private var arrayList = ArrayList<KalenderViewModel>()

    companion object {
        var ObjectAmount: Long? = 1000;
        fun setUpcomingEvent(upcomingEvent: KalenderViewModel?, view: FragmentKalenderBinding) {
            // Image
            view.imgEvent.setImageDrawable(BitmapDrawable(upcomingEvent!!.image))
            // Name
            view.txtName.text = upcomingEvent.name
            // FaceBook Link
            view.imgFbLink.setOnClickListener {
                val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(upcomingEvent.fbLink))
                view.root.context.startActivity(browserIntent)
            }
            // Google Forms Link
            if (upcomingEvent.formsLink.isNullOrBlank()) {
                view.imgFormsLink.visibility = View.GONE
            } else {
                view.imgFormsLink.visibility = View.VISIBLE
                view.imgFormsLink.setOnClickListener {
                    val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(upcomingEvent.formsLink))
                    view.root.context.startActivity(browserIntent)
                }
            }
            // Date
            view.txtDate.text = upcomingEvent.getViewDate()
            // Location
            if (upcomingEvent.location.isNullOrBlank()) {
                view.barLocation.visibility = View.GONE
            } else {
                view.barLocation.visibility = View.VISIBLE
                view.txtLocation.text = upcomingEvent.location
                view.imgLocation.setOnClickListener {
                    val browserIntent = Intent(Intent.ACTION_VIEW,
                        Uri.parse("https://maps.google.co.in/maps?q=\"${upcomingEvent.location} Belgium\"&?z=1"))
                    view.root.context.startActivity(browserIntent)
                }
            }
            // Price
            if (upcomingEvent.price == null || upcomingEvent.price == 0.toLong()) {
                view.txtPrice.visibility = View.GONE
            } else {
                view.txtPrice.visibility = View.VISIBLE
                view.txtPrice.text = upcomingEvent.getViewPrice()
            }
            view.txtDescription.text = upcomingEvent.description
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {

        _bindingFragment = FragmentKalenderBinding.inflate(inflater, container, false)
        val view = bindingFragment.root

        ctx = requireActivity().applicationContext
        // Return if no context is found
        if (!HomeFragment.isOnline(ctx)) HomeFragment.navigateHome(ctx, this.findNavController())

        val currentDate: LocalDate = LocalDate.now()
        kalenderViewModel = ViewModelProviders.of(this)[KalenderViewModel::class.java]
        kalenderViewModel.getArrayList().observe(viewLifecycleOwner) { events ->
            for (event in events) {
                val kalenderViewModel = KalenderViewModel(event)
                arrayList.add(kalenderViewModel)
            }
            if (arrayList.size < 4) HomeFragment.navigateHome(ctx, this.findNavController())
            customKalenderAdapter = CustomKalenderAdapter(arrayList, bindingFragment)
            bindingFragment.kalenderRV.layoutManager = LinearLayoutManager(ctx, LinearLayoutManager.HORIZONTAL, false)
            bindingFragment.kalenderRV.adapter = customKalenderAdapter
            bindingFragment.kalenderRV.scrollToPosition(0)
            setUpcomingEvent(arrayList.first(), bindingFragment)
        }
        return view
    }
}