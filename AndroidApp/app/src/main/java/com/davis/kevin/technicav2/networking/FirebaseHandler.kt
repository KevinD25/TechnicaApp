package com.davis.kevin.technicav2.networking

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Log
import androidx.appcompat.content.res.AppCompatResources
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.davis.kevin.technicav2.R
import com.davis.kevin.technicav2.models.*
import com.davis.kevin.technicav2.ui.praesidium.PraesidiumViewModel
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
import java.util.*
import kotlin.collections.ArrayList


object FirebaseHandler {
    private val storage = Firebase.storage
    val homeList = mutableListOf<Home>()
    val sponsorList = MutableLiveData<List<Partner>>()
    val vacancieList = MutableLiveData<List<Vacature>>()
    var clubText = MutableLiveData<Clubtext>()
    val praesidiumList = MutableLiveData<List<Praesidium>>()
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
                    val clubtext = Clubtext(
                        id = document.id,
                        clubText = document.data["text"] as String?
                    )
                    clubText.value = clubtext
                }
            }
            .addOnFailureListener { exception ->
            }
    }

    private fun getPraesidiumTest() {
        val praesidia = mutableListOf<Praesidium>()
        db.collection("Praesidium").addSnapshotListener { documents, _ ->
            documents?.forEach {
                val ONE_MEGABYTE: Long = 1024 * 1024
                storage.reference.child(it["imageLink"] as String)
                    .getBytes(ONE_MEGABYTE).addOnSuccessListener { image ->
                        val praesidium = Praesidium(
                            id = it.id,
                            name = it["name"] as String?,
                            surname = it["surName"] as String?,
                            birthday = it["birthday"] as String?,
                            studies = it["studies"] as String?,
                            functie = it["function"] as String?,
                            imageLink = BitmapFactory.decodeByteArray(image, 0, image.size)/*,
                            images = null*/
                        )
                        praesidia.add(praesidium)
                    }
            }
        }
        praesidiumList.postValue(praesidia)
    }

    private fun getPraesidium() {
        val praesidia = mutableListOf<Praesidium>()
        db.collection("Praesidium").get().addOnSuccessListener { result ->
            for (document in result) {
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
                        praesidia.add(praesidium)
                    }.addOnFailureListener { exception ->
                        FirebaseCrashlytics.getInstance()
                            .recordException(exception)
                    }
                praesidiumList.value = praesidia
            }
        }
    }

    private fun getHome() {
        db.collection("Home").get().addOnSuccessListener { result ->
            for (document in result) {
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
    }

    private fun getPartners() {
        val partners = mutableListOf<Partner>()
        db.collection("Sponsors").get().addOnSuccessListener { result ->
            for (document in result) {
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
                        partners.add(partner)
                    }.addOnFailureListener { exception ->
                        FirebaseCrashlytics.getInstance().recordException(exception)
                    }
            }
        }
        sponsorList.value = partners
    }

    private fun getVacancies() {
        val vacancies = mutableListOf<Vacature>()
        db.collection("Vacatures").get().addOnSuccessListener { result ->
            for (document in result) {
                val vacature = Vacature(
                    id = document.id,
                    companyID = document["sponsorid"] as String?,
                    name = document["name"] as String?,
                    description = document["description"] as String?,
                    link = document["link"] as String?
                )
                vacancies.add(vacature)
            }
            vacancieList.value = vacancies
        }
    }
}