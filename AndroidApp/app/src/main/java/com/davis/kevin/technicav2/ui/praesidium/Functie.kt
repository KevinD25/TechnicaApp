package com.davis.kevin.technicav2.ui.praesidium

import java.util.*

enum class Functie(index: Int) {
    PRAESES(0),
    VICE_PRAESES(1),
    QUAESTOR(2),
    CANTOR(3),
    AB_ACTIS(4),
    SCHACTENMEESTER(5),
    SCHACHTENTEMMER(6),
    ZEDENMEESTER(7),
    FEEST(8),
    S_O_C(9),
    P_R(10),
    REDACTOR(11),
    MEDIA(12),
    WEBMASTER(13),
    RESIDENT_DJ(14),
    METER(15),
    PETER(16),
    ERE_LID(17),
    ERE_PRAESES(18),
    OUWE_ZAK(19);

    // Read-Only Variable
    var value = index
        private set

    companion object {
        val Functies: ArrayList<String> = object : ArrayList<String>(
            listOf(
                "Praeses",
                "Vice-Praeses",
                "Quaestor",
                "Cantor",
                "Ab-Actis",
                "Schachtenmeester",
                "Schachtentemmer",
                "Zedenmeester",
                "Feest",
                "S.O.C.",
                "P.R.",
                "Redactor",
                "Media",
                "Webmaster",
                "Resident DJ",
                "Meter",
                "Peter",
                "Ere-Lid",
                "Ere-Praeses",
                "Ouwe-Zak"
            )
        ) {}

        fun longToEnum(functie: Long?): Functie {
            return Functie.values()[functie!!.toInt()]
        }

        fun integerToEnum(functie: Int?): Functie {
            return Functie.values()[functie!!]
        }

        fun stringToEnum(functie: String?): Functie {
            return integerToEnum(Functies.indexOf(functie!!))
        }

        fun enumToInt(functie: Functie?): Int {
            return functie!!.ordinal
        }

        fun enumToString(functie: Functie?): String {
            return Functies[functie!!.ordinal]
        }
    }
}