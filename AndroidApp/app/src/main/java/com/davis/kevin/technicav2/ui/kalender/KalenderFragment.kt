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
    private var firstEventIsSet: Boolean = false

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
                if (!firstEventIsSet && (kalenderViewModel.date!!.year > currentDate.year ||
                            (kalenderViewModel.date!!.year == currentDate.year && kalenderViewModel.date!!.dayOfYear >= currentDate.dayOfYear))) {
                    setUpcomingEvent(kalenderViewModel)
                    firstEventIsSet = true
                }
            }

            customKalenderAdapter = CustomKalenderAdapter(arrayList, viewOfLayout)
            kalenderRV!!.layoutManager = LinearLayoutManager(ctx, LinearLayoutManager.HORIZONTAL, false)
            kalenderRV!!.adapter = customKalenderAdapter
        })

        return viewOfLayout
    }

    private fun setUpcomingEvent(upcomingEvent: KalenderViewModel?) {
        viewOfLayout.img_event.setImageDrawable(BitmapDrawable(upcomingEvent!!.image))
        viewOfLayout.txt_name.text = upcomingEvent.name
        viewOfLayout.img_fb_link.setOnClickListener {
            val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(upcomingEvent.fbLink))
            ctx.startActivity(browserIntent)
        }
        viewOfLayout.txt_date.text = upcomingEvent.getViewDate()
    }
}