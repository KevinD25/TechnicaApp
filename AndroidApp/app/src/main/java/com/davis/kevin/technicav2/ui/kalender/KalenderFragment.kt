package com.davis.kevin.technicav2.ui.kalender

import android.content.Context
import android.content.Intent
import android.graphics.drawable.BitmapDrawable
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.davis.kevin.technicav2.R
import com.davis.kevin.technicav2.adapters.CustomKalenderAdapter
import kotlinx.android.synthetic.main.fragment_kalender.*
import java.time.LocalDate


class KalenderFragment : Fragment() {

    private lateinit var kalenderViewModel: KalenderViewModel
    private var kalenderRV: RecyclerView? = null
    private lateinit var viewOfLayout: View
    private var customKalenderAdapter: CustomKalenderAdapter? = null
    private lateinit var ctx: Context
    private var arrayList = ArrayList<KalenderViewModel>()
    private var firstEventIsSet: Boolean = false

    companion object {
        private var eventImage: ImageView? = null
        private var eventName: TextView? = null
        private var eventLink: ImageView? = null
        private var eventDate: TextView? = null

        private fun setUpViews(viewOfLayout: View) {
            eventImage = viewOfLayout.findViewById(R.id.img_event)
            eventName = viewOfLayout.findViewById(R.id.txt_name)
            eventLink = viewOfLayout.findViewById(R.id.img_fc_link)
            eventDate = viewOfLayout.findViewById(R.id.txt_date)
        }

        fun setUpcomingEvent(upcomingEvent: KalenderViewModel?, ctx: Context) {
            eventImage!!.setImageDrawable(BitmapDrawable(upcomingEvent!!.image))
            eventName!!.text = upcomingEvent.name
            eventLink!!.setOnClickListener {
                val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(upcomingEvent.fbLink))
                ctx.startActivity(browserIntent)
            }
            eventDate!!.text = upcomingEvent.getViewDate()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        ctx = requireActivity().applicationContext

        viewOfLayout = inflater.inflate(R.layout.fragment_kalender, container, false)
        kalenderRV = viewOfLayout.findViewById(R.id.kalender_RV)
        setUpViews(viewOfLayout)

        var currentDate: LocalDate = LocalDate.now()
        kalenderViewModel = ViewModelProviders.of(this).get(KalenderViewModel::class.java)
        kalenderViewModel.getArrayList().observe(viewLifecycleOwner, Observer { events ->
            for(event in events){
                val kalenderViewModel = KalenderViewModel(event)
                arrayList.add(kalenderViewModel)
                // Get First Upcoming Event
                if (!firstEventIsSet && (kalenderViewModel.date!!.year > currentDate.year ||
                            (kalenderViewModel.date!!.year == currentDate.year && kalenderViewModel.date!!.dayOfYear >= currentDate.dayOfYear))) {
                    setUpcomingEvent(kalenderViewModel, ctx)
                    firstEventIsSet = true
                }
            }

            customKalenderAdapter = CustomKalenderAdapter(ctx, arrayList)
            kalenderRV!!.layoutManager = LinearLayoutManager(ctx, LinearLayoutManager.HORIZONTAL, false)
            kalenderRV!!.adapter = customKalenderAdapter
        })

        return viewOfLayout
    }
}