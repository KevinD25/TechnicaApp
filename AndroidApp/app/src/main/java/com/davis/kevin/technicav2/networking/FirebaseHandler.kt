package com.davis.kevin.technicav2.networking

import android.content.Context
import android.graphics.BitmapFactory
import android.util.Log
import androidx.appcompat.content.res.AppCompatResources
import com.davis.kevin.technicav2.R
import com.davis.kevin.technicav2.models.*
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.crashlytics.FirebaseCrashlytics
import com.google.firebase.firestore.EventListener
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ListenerRegistration
import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObjects
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import java.time.LocalDate
import java.time.format.DateTimeFormatter


object FirebaseHandler {
    private val storage = Firebase.storage
    val homeList = mutableListOf<Home>()
    val sponsorList = mutableListOf<Partner>()
    val vacancieList = mutableListOf<Vacature>()
    var clubTextList = mutableListOf<Clubtext>()
    val praesidiumList = mutableListOf<Praesidium>()
    private val db = FirebaseFirestore.getInstance()


    fun getFirebaseData() {
        getHome()
        getPartners()
        getVacancies()
        getClubtext()
        getPraesidium()
    }


    private fun getClubtext() {
        db.collection("ClubTekst")
            .get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    Log.d("INCOMING", "${document.id} => ${document.data}")
                    val clubtext = Clubtext(
                        id = document.id,
                        clubText = document.data["text"] as String?
                    )
                    clubTextList.add(clubtext)
                }
            }
            .addOnFailureListener { exception ->
                Log.d("INCOMING", "Error getting documents: ", exception)
            }
    }

    private fun getPraesidium() {
        db.collection("Praesidium").get().addOnSuccessListener { result ->
            for(document in result){
                Log.d("INCOMING", "${document.id} => ${document.data}")
                val ONE_MEGABYTE: Long = 1024 * 1024
                storage.reference.child(document["imageLink"] as String)
                    .getBytes(ONE_MEGABYTE).addOnSuccessListener { image ->
                        val praesidium = Praesidium(
                            id = document.id,
                            name = document["name"] as String?,
                            surname = document["surName"] as String?,
                            birthday = document["birthday"] as String?,
                            studies = document["studies"] as String?,
                            functie = document["function"] as String?,
                            imageLink = BitmapFactory.decodeByteArray(image, 0, image.size)/*,
                            images = null*/
                        )
                        praesidiumList.add(praesidium)
                    }.addOnFailureListener { exception ->
                        FirebaseCrashlytics.getInstance()
                            .recordException(exception)
                    }
            }
        }
        Log.d("INCDATA", praesidiumList.toString())
    }


    private fun getHome() {
        db.collection("Home").get().addOnSuccessListener {  result ->
            for(document in result){
                val ONE_MEGABYTE: Long = 1024 * 1024
                storage.reference.child(document["imageLink"] as String)
                    .getBytes(ONE_MEGABYTE).addOnSuccessListener { image ->
                        val dateString = document["date"] as String?
                        val date =
                            LocalDate.parse(dateString, DateTimeFormatter.ofPattern("yyyy-MM-dd"))

                        val home = Home(
                            id = document.id,
                            imageLink = BitmapFactory.decodeByteArray(image, 0, image.size),
                            fbLink = document["fbLink"] as String?,
                            date = date
                        )
                        homeList.add(home)
                        Log.d("Home", homeList.toString())
                    }.addOnFailureListener { exception ->
                        FirebaseCrashlytics.getInstance()
                            .recordException(exception)
                    }
            }
        }

        Log.d("INCDATA", homeList.toString())
    }

    private fun getPartners() {
        db.collection("Sponsors").get().addOnSuccessListener { result ->
            for(document in result){
                val ONE_MEGABYTE: Long = 1024 * 1024
                storage.reference.child(document["imageLink"] as String)
                    .getBytes(ONE_MEGABYTE).addOnSuccessListener { image ->
                        val partner = Partner(
                            id = document.id,
                            imageLink = BitmapFactory.decodeByteArray(image, 0, image.size),
                            name = document["name"] as String?,
                            description = document["about"] as String?,
                            website = document["website"] as String?
                        )
                        sponsorList.add(partner)
                        Log.d("Partner", sponsorList.toString())
                    }.addOnFailureListener { exception ->
                        FirebaseCrashlytics.getInstance().recordException(exception)
                    }
            }
        }
        Log.d("INCDATA", sponsorList.toString())
    }


    private fun getVacancies() {
        db.collection("Vacatures").get().addOnSuccessListener { result ->
            for(document in result){
                val vacature = Vacature(
                    id = document.id,
                    companyID = document["sponsorid"] as String?,
                    name = document["name"] as String?,
                    description = document["description"] as String?,
                    link = document["link"] as String?
                )
                vacancieList.add(vacature)
            }
        }
        Log.d("INCDATA", vacancieList.toString())
    }

}