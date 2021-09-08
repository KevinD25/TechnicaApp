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
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.davis.kevin.technicav2.R
import com.davis.kevin.technicav2.adapters.CustomKalenderAdapter
import kotlinx.android.synthetic.main.fragment_kalender.view.*
import java.time.LocalDate


class KalenderFragment : Fragment() {

    private lateinit var kalenderViewModel: KalenderViewModel
    private var kalenderRV: RecyclerView? = null
    private lateinit var viewOfLayout: View
    private var customKalenderAdapter: CustomKalenderAdapter? = null
    private lateinit var ctx: Context
    private var arrayList = ArrayList<KalenderViewModel>()
    private var upcomingEvent: KalenderViewModel? = null

    companion object {
        fun setUpcomingEvent(upcomingEvent: KalenderViewModel?, view: View) {

            // Image
            view.img_event.setImageDrawable(BitmapDrawable(upcomingEvent!!.image))

            // Name
            view.txt_name.text = upcomingEvent.name

            // FaceBook Link
            view.img_fb_link.setOnClickListener {
                val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(upcomingEvent.fbLink))
                view.context.startActivity(browserIntent)
            }

            // Google Forms Link
            if (upcomingEvent.formsLink.isNullOrBlank()) {
                view.img_forms_link.visibility = View.GONE
            } else {
                view.img_forms_link.visibility = View.VISIBLE
                view.img_forms_link.setOnClickListener {
                    val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(upcomingEvent.formsLink))
                    view.context.startActivity(browserIntent)
                }
            }

            // Date
            view.txt_date.text = upcomingEvent.getViewDate()

            // Location
            if (upcomingEvent.location.isNullOrBlank()) {
                view.bar_location.visibility = View.GONE
            } else {
                view.bar_location.visibility = View.VISIBLE
                view.txt_location.text = upcomingEvent.location
                view.img_location.setOnClickListener {
                    val browserIntent = Intent(Intent.ACTION_VIEW,
                        Uri.parse("https://maps.google.co.in/maps?q=\"${upcomingEvent.location} Belgium\"&?z=1"))
                    view.context.startActivity(browserIntent)
                }
            }

            // Price
            if (upcomingEvent.price == null || upcomingEvent.price == 0) {
                view.txt_price.visibility = View.GONE
            } else {
                view.txt_price.visibility = View.VISIBLE
                view.txt_price.text = upcomingEvent.getViewPrice()
            }

            view.txt_description.text = upcomingEvent.description
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        ctx = requireActivity().applicationContext

        viewOfLayout = inflater.inflate(R.layout.fragment_kalender, container, false)
        kalenderRV = viewOfLayout.findViewById(R.id.kalender_RV)

        val currentDate: LocalDate = LocalDate.now()
        kalenderViewModel = ViewModelProviders.of(this).get(KalenderViewModel::class.java)
        kalenderViewModel.getArrayList().observe(viewLifecycleOwner, Observer { events ->
            for(event in events){
                val kalenderViewModel = KalenderViewModel(event)
                arrayList.add(kalenderViewModel)
                // Get First Upcoming Event
                if (upcomingEvent == null && (kalenderViewModel.date!!.year > currentDate.year ||
                            (kalenderViewModel.date!!.year == currentDate.year && kalenderViewModel.date!!.dayOfYear >= currentDate.dayOfYear))) {
                    upcomingEvent = kalenderViewModel
                }
            }

            customKalenderAdapter = CustomKalenderAdapter(arrayList, viewOfLayout)
            kalenderRV!!.layoutManager = LinearLayoutManager(ctx, LinearLayoutManager.HORIZONTAL, false)
            kalenderRV!!.adapter = customKalenderAdapter
            kalenderRV!!.scrollToPosition(customKalenderAdapter!!.getItemIndex(upcomingEvent))
            setUpcomingEvent(upcomingEvent, viewOfLayout)
            upcomingEvent = null
        })

        return viewOfLayout
    }
}