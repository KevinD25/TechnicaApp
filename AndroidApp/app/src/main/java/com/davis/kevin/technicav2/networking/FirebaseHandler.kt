package com.davis.kevin.technicav2.networking

import android.graphics.BitmapFactory
import androidx.lifecycle.MutableLiveData
import com.davis.kevin.technicav2.models.*
import com.davis.kevin.technicav2.ui.kalender.KalenderFragment
import com.davis.kevin.technicav2.ui.praesidium.Functie
import com.davis.kevin.technicav2.ui.praesidium.PraesidiumFragment
import com.davis.kevin.technicav2.ui.sponsors.SponsorsFragment
import com.davis.kevin.technicav2.ui.vacatures.VacaturesFragment
import com.google.firebase.crashlytics.FirebaseCrashlytics
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter


object FirebaseHandler {
    private val storage = Firebase.storage
    private val db = FirebaseFirestore.getInstance()
    val clubText = MutableLiveData<Introductie>()
    val praesidiumList = MutableLiveData<List<Praesidium>>()
    val eventList = MutableLiveData<List<Evenement>>()
    val sponsorList = MutableLiveData<List<Partner>>()
    val vacancieList = MutableLiveData<List<Vacature>>()

    fun getFirebaseData() {
        getLengths()
        getClubtext()
        getLiederen()
        getEvents()
        getPartners()
        getPraesidium()
        getVacancies()
    }

    private fun getLengths() {
        db.collection("Lengths").get().addOnSuccessListener{ result ->
            for (document in result) {
                val length = Lengths(
                    id = document.id,
                    collection = document.data["Collection"] as String?,
                    length = document.data["Length"] as Long?
                )
                when (length.collection) {
                    "Events" -> KalenderFragment.ObjectAmount = length.length
                    "Preasidium" -> PraesidiumFragment.ObjectAmount = length.length
                    "Sponsors" -> SponsorsFragment.ObjectAmount = length.length
                    "Vacatures" -> VacaturesFragment.ObjectAmount = length.length
                    else -> { }
                }
            }
        }.addOnFailureListener { exception ->
            FirebaseCrashlytics.getInstance().recordException(exception)
        }
    }

    private fun getClubtext() {
        db.collection("ClubTekst").get().addOnSuccessListener{ result ->
            for (document in result) {
                val clubtext = Introductie(
                    id = document.id,
                    clubText = document.data["text"] as String?
                )
                clubText.value = clubtext
            }
        }.addOnFailureListener { exception ->
            FirebaseCrashlytics.getInstance().recordException(exception)
        }
    }

    private fun getLiederen() {
        storage.reference.child("\\Liederen\\technica_clublied.mp3")
    }

    private fun getEvents() {
        val events = mutableListOf<Evenement>()
        db.collection("Events").get().addOnSuccessListener { result ->
            for (document in result) {
                val ONE_MEGABYTE: Long = 1048576 // = 1024 * 1024
                storage.reference.child(document["imageLink"] as String)
                    .getBytes(ONE_MEGABYTE).addOnSuccessListener { image ->
                        val dateString = document["date"] as String?
                        val timeString = document["time"] as String?
                        val event = Evenement(
                            id = document.id,
                            name = document["name"] as String?,
                            fbLink = document["fbLink"] as String?,
                            formsLink = document["formsLink"] as String?,
                            location = document["location"] as String?,
                            price = document["price"] as Long?,
                            //Double werken niet
                            description = document["description"] as String?,
                            date = LocalDateTime.parse("$dateString $timeString",
                                DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")),
                            image = BitmapFactory.decodeByteArray(image, 0, image.size)
                        )
                        events.add(event)
                        if (document.id == result.last().id) events.sortBy { event -> event.date }
                    }.addOnFailureListener { exception ->
                        FirebaseCrashlytics.getInstance().recordException(exception)
                    }
                eventList.value = events
            }
        }
    }

    private fun getPartners() {
        val partners = mutableListOf<Partner>()
        db.collection("Sponsors").get().addOnSuccessListener { result ->
            for (document in result) {
                val ONE_MEGABYTE: Long = 1048576 // = 1024 * 1024
                storage.reference.child(document["imageLink"] as String)
                    .getBytes(ONE_MEGABYTE).addOnSuccessListener { image ->
                        val partner = Partner(
                            id = document.id,
                            name = document["name"] as String?,
                            description = document["about"] as String?,
                            website = document["website"] as String?,
                            priority = document["priority"] as Long?,
                            image = BitmapFactory.decodeByteArray(image, 0, image.size)
                        )
                        partners.add(partner)
                        if (document.id == result.last().id) partners.sortBy { partner -> partner.priority }
                    }.addOnFailureListener { exception ->
                        FirebaseCrashlytics.getInstance().recordException(exception)
                    }
            }
        }
        sponsorList.value = partners
    }

    private fun getPraesidium() {
        // Make a List
        val praesidia = mutableListOf<Praesidium>()
        // The data from the database
        db.collection("Praesidium").get().addOnSuccessListener{ result ->
            // Go through all data objects (document = 1 object)
            for (document in result) {
                // Create an image size
                val ONE_MEGABYTE: Long = 1048576 // = 1024 * 1024
                // Use the imageLink variable to find the image in the FireBase Storage
                storage.reference.child(document["imageLink"] as String).getBytes(ONE_MEGABYTE).addOnSuccessListener { image ->
                        val birthdayString = document["birthday"] as String?
                        // Create a Praesidium-Object with the data
                        val praesidium = Praesidium(
                            id = document.id,
                            name = document["name"] as String?,
                            surname = document["surName"] as String?,
                            studies = document["studies"] as String?,
                            functie = Functie.longToEnum(document["function"] as Long?),
                            birthday = LocalDate.parse(birthdayString, DateTimeFormatter.ofPattern("yyyy-MM-dd")),
                            image = BitmapFactory.decodeByteArray(image, 0, image.size)
                        )
                        // Add the Praesidium-Object to the List
                        praesidia.add(praesidium)
                        // Sort the List when al items are loaded
                        if (document.id == result.last().id) praesidia.sortBy { praesidium -> praesidium.functie!!.value }
                    }.addOnFailureListener { exception ->
                        FirebaseCrashlytics.getInstance().recordException(exception)
                    }
                praesidiumList.value = praesidia
            }
        }
    }

    private fun getVacancies() {
        val vacancies = mutableListOf<Vacature>()
        db.collection("Vacatures").get().addOnSuccessListener { result ->
            for (document in result) {
                val vacature = Vacature(
                    id = document.id,
                    companyId = document["sponsorid"] as String?,
                    name = document["name"] as String?,
                    description = document["description"] as String?,
                    link = document["link"] as String?
                )
                vacancies.add(vacature)
            }
            vacancieList.value = vacancies
        }.addOnFailureListener { exception ->
            FirebaseCrashlytics.getInstance().recordException(exception)
        }
    }
}