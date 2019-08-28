package com.davis.kevin.technicav2.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class HomeViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "Het volgende evenement bij Technica:"
    }
    val text: LiveData<String> = _text
}