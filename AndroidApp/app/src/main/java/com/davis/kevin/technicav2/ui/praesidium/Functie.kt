package com.davis.kevin.technicav2.ui.praesidium

import java.util.*

enum class Functie(index: Int) {
    PRAESES(0),
    VICE_PRAESES(1),
    QUAESTOR(2),
    AB_ACTIS(3),
    REDACTOR(4),
    SCHACTENMEESTER(5),
    SCHACHTENTEMMER(6),
    ZEDENMEESTER(7),
    P_R(8),
    FEEST(9),
    S_O_C(10),
    MEDIA(11),
    CANTOR(12),
    WEBMASTER(13),
    RESIDENT_DJ(14),
    METER(15),
    PETER(16),
    ERE_PRAESES(17),
    ERE_LID(18),
    OUWE_ZAK(19);

    // Read-Only Variable
    var value = index
        private set

    companion object {
        val Functies: ArrayList<String> = object : ArrayList<String>(
            Arrays.asList(
                "Praeses",
                "Vice-Praeses",
                "Quaestor",
                "Ab-Actis",
                "Redactor",
                "Schachtenmeester",
                "Schachtentemmer",
                "Zedenmeester",
                "P.R.",
                "Feest",
                "S.O.C.",
                "Media",
                "Cantor",
                "Webmaster",
                "Resident DJ",
                "Meter",
                "Peter",
                "Ere-Praeses",
                "Ere-Lid",
                "Ouwe-Zak"
            )
        ) {}

        fun LongToEnum(functie: Long?): Functie? {
            return Functie.values().get(functie!!.toInt())
        }

        fun IntegerToEnum(functie: Int?): Functie? {
            return Functie.values().get(functie!!)
        }

        fun StringToEnum(functie: String?): Functie? {
            return IntegerToEnum(Functies.indexOf(functie!!))
        }

        fun EnumToInt(functie: Functie?): Int? {
            return functie!!.ordinal
        }

        fun EnumToString(functie: Functie?): String? {
            return Companion.Functies[functie!!.ordinal]
        }
    }
}