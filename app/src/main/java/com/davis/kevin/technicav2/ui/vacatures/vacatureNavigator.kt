package com.davis.kevin.technicav2.ui.vacatures

import com.davis.kevin.technicav2.DOM.Vacature

interface vacatureNavigator {
    fun onItemClick(vacature : Vacature)
}