package com.davis.kevin.technicav2.ui.home

import android.content.Context
import android.graphics.BitmapFactory
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.NavController
import com.davis.kevin.technicav2.R
import com.davis.kevin.technicav2.adapters.CustomHomeAdapter
import com.davis.kevin.technicav2.models.Evenement
import com.yarolegovich.discretescrollview.DiscreteScrollView
import com.yarolegovich.discretescrollview.InfiniteScrollAdapter
import java.time.LocalDate

class HomeFragment : Fragment(), DiscreteScrollView.OnItemChangedListener<CustomHomeAdapter.CostumView> {

    private lateinit var homeViewModel: HomeViewModel
    private var homeDSV: DiscreteScrollView? = null
    private lateinit var viewOfLayout: View
    private var customHomeAdapter: CustomHomeAdapter? = null
    private lateinit var infiniteScrollWrapper: InfiniteScrollAdapter<*>
    private lateinit var ctx: Context
    private var arrayList: ArrayList<HomeViewModel> = arrayListOf()

    companion object {
        fun navigateHome(context: Context, navController: NavController) {
            Toast.makeText(context, "Couldn't load all files! \nCheck your wifi connection.", Toast.LENGTH_SHORT).show()
            navController.navigate(R.id.nav_home)
        }

        fun isOnline(context: Context): Boolean {
            val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val capabilities =
                connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
            if (capabilities != null) {
                when {
                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> {
                        Log.i("Internet", "NetworkCapabilities.TRANSPORT_CELLULAR")
                        return true
                    }
                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> {
                        Log.i("Internet", "NetworkCapabilities.TRANSPORT_WIFI")
                        return true
                    }
                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> {
                        Log.i("Internet", "NetworkCapabilities.TRANSPORT_ETHERNET")
                        return true
                    }
                }
            }
            return false
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {

        ctx = requireActivity().applicationContext

        viewOfLayout = inflater.inflate(R.layout.fragment_home, container, false)
        homeDSV = viewOfLayout.findViewById(R.id.carousel_event)

        val currentDate: LocalDate = LocalDate.now()
        homeViewModel = ViewModelProviders.of(this)[HomeViewModel::class.java]
        homeViewModel.getArrayList().observe(viewLifecycleOwner) { events ->
            for (event in events) {
                val homeViewModel = HomeViewModel(event)
                // Only new events
                if (homeViewModel.date!!.year > currentDate.year ||
                    (homeViewModel.date!!.year == currentDate.year && homeViewModel.date!!.dayOfYear >= currentDate.dayOfYear)
                ) {
                    // Max show 3
                    if (arrayList.size < 3) arrayList.add(homeViewModel)
                    else break
                }
            }
            while (arrayList.size < 3) {
                arrayList.add(HomeViewModel(
                    Evenement(name = "No Upcoming Events Planned",
                        image = BitmapFactory.decodeResource(ctx.resources,
                            R.drawable.technica_schild_laad_pagina)
                    )
                ))
            }
            customHomeAdapter = CustomHomeAdapter(arrayList)
            infiniteScrollWrapper = InfiniteScrollAdapter.wrap(customHomeAdapter!!)
            homeDSV!!.adapter = infiniteScrollWrapper
            homeDSV!!.setItemTransformer(CarouselTransformer())
            homeDSV!!.addOnItemChangedListener(this)
            homeDSV!!.adapter!!.notifyDataSetChanged()
        }

        //Thread.sleep(5000);

        return viewOfLayout
    }

    override fun onCurrentItemChanged(viewHolder: CustomHomeAdapter.CostumView?, adapterPosition: Int) {
        val realPosition = infiniteScrollWrapper.realCurrentPosition
        //log("onCurrentItemChanged $realPosition")
    }
}

private fun Handler.postDelayed(runnable: Runnable) {

}
