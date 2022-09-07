package com.davis.kevin.technicav2

import android.content.pm.ActivityInfo
import android.os.Bundle
import android.util.Log
import android.view.Menu
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.davis.kevin.technicav2.databinding.ActivityMainBinding
import com.davis.kevin.technicav2.networking.FirebaseHandler
import com.google.android.material.navigation.NavigationView
import com.google.firebase.auth.FirebaseAuth


class MainActivity : AppCompatActivity() {

    /*  TO DO
    *   Upcomming events laten werken bij de eerste call
    *   Upcomming Events & kalender auto laten scrollen
        *   Zoek naar de onTouch methode die upcomming events laat scrollen en zet daar een time handler op
    *   Preasidium volgorde nog eens sorteren in Preasidium fragment
        *   Doe hetzelfde voor de kalender en sponsors
    *   Upcomming event onTouch naar eiegen event
    * */

    private lateinit var bindingMain: ActivityMainBinding
    private lateinit var appBarConfiguration: AppBarConfiguration
    private var mAuth = FirebaseAuth.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingMain = ActivityMainBinding.inflate(layoutInflater)
        //setContentView(R.layout.activity_main)
        setSupportActionBar(bindingMain.toolbar)

        //login()
        //Repository.getData()

        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT

        val drawerLayout: DrawerLayout = bindingMain.drawerLayout //= findViewById(R.id.drawer_layout)
        val navView: NavigationView = bindingMain.navView //= findViewById(R.id.nav_view)
        val navHostFragment: NavHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController

        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_home, R.id.nav_introductie, R.id.nav_sponsors,
                R.id.nav_kalender, R.id.nav_praesidium, R.id.nav_clublied,
                R.id.nav_vacatures, R.id.nav_hulp
            ), drawerLayout
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

        val view = bindingMain.root
        setContentView(view)
    }

    private fun login(){
        val user = mAuth.currentUser
        if (user != null) {
            // do your stuff
            FirebaseHandler.getFirebaseData()
        } else {
            signInAnonymously()
        }
    }

    private fun signInAnonymously() {
        mAuth.signInAnonymously().addOnSuccessListener(this) {
            FirebaseHandler.getFirebaseData()
        }
            .addOnFailureListener(this) { exception ->
                Log.e("SIGNING", "signInAnonymously:FAILURE", exception)
            }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }
}
