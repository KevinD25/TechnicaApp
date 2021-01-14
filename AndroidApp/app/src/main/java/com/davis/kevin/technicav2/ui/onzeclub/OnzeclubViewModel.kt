package com.davis.kevin.technicav2.ui.onzeclub

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.davis.kevin.technicav2.models.Clubtext
import com.davis.kevin.technicav2.networking.FirebaseHandler
import com.google.firebase.ktx.Firebase

class OnzeclubViewModel : ViewModel {

    var id : String? = ""
    var text : String? = ""
    private lateinit var mContext: Context

    constructor() : super()
    constructor(clubtext: Clubtext): super() {
        this.id = clubtext.id
        this.text = clubtext.clubText
    }

    fun getContext(context: Context) {
        mContext = context
    }

    fun getArray() : MutableLiveData<Clubtext>{
        return FirebaseHandler.clubText
    }

}