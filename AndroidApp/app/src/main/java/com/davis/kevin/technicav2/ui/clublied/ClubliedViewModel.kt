package com.davis.kevin.technicav2.ui.clublied

import android.media.MediaPlayer
import androidx.lifecycle.ViewModel
import com.davis.kevin.technicav2.models.Liederen

class ClubliedViewModel : ViewModel {

    var id: String? = null
    var title: String? = null
    var song: MediaPlayer? = null

    constructor() : super()
    constructor(song: Liederen) : super() {
        this.title = song.title
        this.song = song.song
    }
}