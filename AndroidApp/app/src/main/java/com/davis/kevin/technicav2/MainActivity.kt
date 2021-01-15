package com.davis.kevin.technicav2

import android.content.pm.ActivityInfo
import android.os.Bundle
import android.util.Log
import android.view.Menu
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.davis.kevin.technicav2.networking.FirebaseHandler
import com.davis.kevin.technicav2.repository.Repository
import com.google.android.gms.tasks.OnFailureListener
import com.google.android.gms.tasks.OnSuccessListener
import com.google.android.material.navigation.NavigationView
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    var mAuth = FirebaseAuth.getInstance()



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

       // login()
        //Repository.getData()


        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT

        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        val navView: NavigationView = findViewById(R.id.nav_view)
        val navController = findNavController(R.id.nav_host_fragment)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_home, R.id.nav_onzeclub, R.id.nav_sponsors,
                R.id.nav_kalender, R.id.nav_praesidium, R.id.nav_clublied,
                R.id.nav_vacatures
            ), drawerLayout
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
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
        mAuth.signInAnonymously().addOnSuccessListener(this, OnSuccessListener<AuthResult?> {
            FirebaseHandler.getFirebaseData()
        })
            .addOnFailureListener(this,
                OnFailureListener { exception ->
                    Log.e(
                        "SIGNIN",
                        "signInAnonymously:FAILURE",
                        exception
                    )
                })
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
