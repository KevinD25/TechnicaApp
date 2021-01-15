package com.davis.kevin.technicav2

import android.content.Intent
import android.content.pm.ActivityInfo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import com.davis.kevin.technicav2.networking.FirebaseHandler
import com.google.android.gms.tasks.OnFailureListener
import com.google.android.gms.tasks.OnSuccessListener
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth

class LoadingActivity : AppCompatActivity() {

    private var mDelayHandler: Handler? = null
    private val SPLASH_DELAY: Long = 3000 //3 seconds
    var mAuth = FirebaseAuth.getInstance()


    private val mRunnable: Runnable = Runnable {
        if (!isFinishing) {

            val intent = Intent(applicationContext, MainActivity::class.java)
            startActivity(intent)
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            finish()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_loading)

        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT

        login()

        //Initialize the Handler
        mDelayHandler = Handler()

        //Navigate with delay
        mDelayHandler!!.postDelayed(mRunnable, SPLASH_DELAY)

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

    public override fun onDestroy() {

        if (mDelayHandler != null) {
            mDelayHandler!!.removeCallbacks(mRunnable)
        }

        super.onDestroy()
    }
}
