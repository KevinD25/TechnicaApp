package com.davis.kevin.technicav2

import android.content.Intent
import android.content.pm.ActivityInfo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import com.davis.kevin.technicav2.networking.FirebaseHandler
import com.google.firebase.auth.FirebaseAuth

class LoadingActivity : AppCompatActivity() {

    private var mDelayHandler: Handler? = null
    private val mSplashDelay: Long = 3000 //3 seconds
    private var mAuth = FirebaseAuth.getInstance()

    private val mRunnable: Runnable = Runnable {
        if (!isFinishing) {
            val intent = Intent(applicationContext, MainActivity::class.java)
            startActivity(intent)
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
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
        mDelayHandler!!.postDelayed(mRunnable, mSplashDelay)
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

    public override fun onDestroy() {

        if (mDelayHandler != null) {
            mDelayHandler!!.removeCallbacks(mRunnable)
        }

        super.onDestroy()
    }
}
