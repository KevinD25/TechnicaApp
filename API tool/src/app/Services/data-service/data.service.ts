import { Injectable } from '@angular/core';
import { AngularFirestore, AngularFirestoreCollection } from '@angular/fire/firestore';
import { Observable } from 'rxjs/Observable';
import 'rxjs/Rx';  // Nodig voor map functie

import { IClubTekst, IEvent, IPraesidium, ISponsor, IVacature } from '../../interfaces/collections';

@Injectable({
  providedIn: 'root'
})
export class DataService {

  /* Toturial Videos:
   * https://www.youtube.com/watch?v=gUmItHaVL2w&list=WL&index=6
   * https://www.youtube.com/watch?v=cwqeyOFcaoA&t=2s
   * https://www.youtube.com/watch?v=onVp-8BYUSM&list=WL&index=3
   */

  /* npm-Packages:
   * npm install --save materialize-css jquery font-awesome
   * npm firebase angularfire
   */

  collections: {
    clubtekst: AngularFirestoreCollection<IClubTekst>,
    event: AngularFirestoreCollection<IEvent>,
    praesidium: AngularFirestoreCollection<IPraesidium>,
    sponsor: AngularFirestoreCollection<ISponsor>,
    vacature: AngularFirestoreCollection<IVacature>,
  } = {
      clubtekst: null,
      event: null,
      praesidium: null,
      sponsor: null,
      vacature: null
    }

  observables: {
    clubtekst: Observable<IClubTekst[]>,
    event: Observable<IEvent[]>,
    praesidium: Observable<IPraesidium[]>,
    sponsor: Observable<ISponsor[]>,
    vacature: Observable<IVacature[]>,
  } = {
      clubtekst: null,
      event: null,
      praesidium: null,
      sponsor: null,
      vacature: null,
    }

  functions: string[] = [ "Ab-Actis", "Cantor", "Ere-Lid", "Ere-Praeses", "Feest", "Media", "Meter", 
                          "P.R.", "Praeses", "Peter", "Quaestor", "Redactor", "Resident DJ", 
                          "Schachtenmeester", "Schachtentemmer", "S.O.C.", "Vice-Praeses", "Webmaster", "Zedenmeester" ];

  constructor(private afs: AngularFirestore) {
    // Clubtext Setup
    this.collections.clubtekst = this.afs.collection<IClubTekst>("ClubTekst", ref => ref.orderBy("text", "asc"));
    this.observables.clubtekst = this.getClubTeksten();

    // Event Setup
    this.collections.event = this.afs.collection<IEvent>("Events", ref => ref.orderBy("date", "asc"));
    this.observables.event = this.getEvents();

    // Praesidium Setup
    this.collections.praesidium = this.afs.collection<IPraesidium>("Praesidium", ref => ref.orderBy("priority", "asc"));
    this.observables.praesidium = this.getPraesidium();

    // Sponsor Setup
    this.collections.sponsor = this.afs.collection<ISponsor>("Sponsors", ref => ref.orderBy("priority", "asc"));
    this.observables.sponsor = this.getSponsors();

    // Vacature Setup
    this.collections.vacature = this.afs.collection<IVacature>("Vacatures");
    this.observables.vacature = this.getVacatures();
  }

  ////////      CLUBTEKST     ////////
  getClubTeksten() {
    return this.collections.clubtekst.snapshotChanges().map(changes => {
      return changes.map(a => {
        const data = a.payload.doc.data() as IClubTekst
        data.id = a.payload.doc.id;
        return data
      })
    });
  }

  addClubTekst(clubtext: IClubTekst) {
    this.collections.clubtekst.add(clubtext)
      .catch(error => console.log(error));
  }

  delClubTekst(clubtekst: IClubTekst) {
    this.collections.clubtekst.doc(clubtekst.id).delete()
      .catch(error => console.log(error));
  }

  patchClubTekst(clubtekst: IClubTekst) {
    this.collections.clubtekst.doc(clubtekst.id).update(clubtekst)
      .catch(error => console.log(error));
  }

  ////////      Event     ////////
  getEvents() {
    return this.collections.event.snapshotChanges().map(changes => {
      return changes.map(a => {
        const data = a.payload.doc.data() as IEvent
        data.id = a.payload.doc.id;
        return data
      })
    });
  }

  addEvent(event: IEvent) {
    this.collections.event.add(event)
      .catch(error => console.log(error));
  }

  delEvent(event: IEvent) {
    this.collections.event.doc(event.id).delete()
      .catch(error => console.log(error));
  }

  patchEvent(event: IEvent) {
    this.collections.event.doc(event.id).update(event)
      .catch(error => console.log(error));
  }

  ////////      Praesidium     ////////
  getPraesidium() {
    return this.collections.praesidium.snapshotChanges().map(changes => {
      return changes.map(a => {
        const data = a.payload.doc.data() as IPraesidium
        data.id = a.payload.doc.id;
        return data
      })
    });
  }

  addPraesidium(praesidium: IPraesidium) {
    this.collections.praesidium.add(praesidium)
      .catch(error => console.log(error));
  }

  delPraesidium(praesidium: IPraesidium) {
    this.collections.praesidium.doc(praesidium.id).delete()
      .catch(error => console.log(error));
  }

  patchPraesidium(praesidium: IPraesidium) {
    this.collections.praesidium.doc(praesidium.id).update(praesidium)
      .catch(error => console.log(error));
  }

  setPriotity(functie: string) {
    switch (functie) {
      case "Praeses":
        return 0;
      case "Vice-Praeses":
        return 1;
      case "Quaestor":
        return 2;
      case "Ab-Actis":
        return 3;
      case "Redactor":
        return 4;
      case "Schachtenmeester":
        return 5;
      case "Schachtenmeester":
        return 6;
      case "P.R.":
        return 6;
      case "Feest":
        return 6;
      case "S.O.C.":
        return 6;
      case "Cantor":
        return 7;
      case "Media":
        return 7;
      case "Zedenmeester":
        return 7;
      case "Webmaster":
        return 7;
      case "Resident DJ":
        return 7;
      case "Peter":
        return 8;
      case "Meter":
        return 8;
      case "Ere-Praeses":
        return 9
      case "Ere-Lid":
        return 10;
    }
  }

  ////////      Sponsors     ////////
  getSponsors() {
    return this.collections.sponsor.snapshotChanges().map(changes => {
      return changes.map(a => {
        const data = a.payload.doc.data() as ISponsor
        data.id = a.payload.doc.id;
        return data
      })
    });
  }

  addSponsor(sponsor: ISponsor) {
    this.collections.sponsor.add(sponsor)
      .catch(error => console.log(error));
  }

  delSponsor(sponsor: ISponsor) {
    this.collections.sponsor.doc(sponsor.id).delete()
      .catch(error => console.log(error));
  }

  patchSponsor(sponsor: ISponsor) {
    this.collections.sponsor.doc(sponsor.id).update(sponsor)
      .catch(error => console.log(error));
  }

  ////////      Vacatures     ////////
  getVacatures() {
    return this.collections.vacature.snapshotChanges().map(changes => {
      return changes.map(a => {
        const data = a.payload.doc.data() as IVacature
        data.id = a.payload.doc.id;
        return data
      })
    });
  }

  addVacature(vacature: IVacature) {
    this.collections.vacature.add(vacature)
      .catch(error => console.log(error));
  }

  delVacature(vacature: IVacature) {
    this.collections.vacature.doc(vacature.id).delete()
      .catch(error => console.log(error));
  }

  patchVacature(vacature: IVacature) {
    this.collections.vacature.doc(vacature.id).update(vacature)
      .catch(error => console.log(error));
  }
}