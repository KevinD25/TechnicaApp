package com.davis.kevin.technicav2.DOM

class Repository() {

    var events: List<Event>
        get() {
            return events
        }
        set(_events:List<Event>){
            events = _events;
        }

    var partners: List<Partner>
        get() {
            return partners
        }
        set(_partners:List<Partner>){
            partners = _partners
        }

    var praesidium: List<Praesidium>
        get() {
            return praesidium
        }
        set(_praesidium:List<Praesidium>){
            praesidium = _praesidium
        }

    var vacancies : List<Vacature>
        get() {
            return vacancies
        }
        set(_vacancies:List<Vacature>){
            vacancies = _vacancies
        }

    var clubtext : Clubtext
        get() {
            return clubtext
        }
        set(_clubText:Clubtext){
            clubtext  = _clubText
        }

}