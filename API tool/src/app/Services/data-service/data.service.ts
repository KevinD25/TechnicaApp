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

  constructor(private afs : AngularFirestore) {
    // Clubtext Setup
    this.collections.clubtekst = this.afs.collection<IClubTekst>("ClubTekst", ref => ref.orderBy("text", "asc"));
    this.observables.clubtekst = this.getClubTeksten();

    // Event Setup
    this.collections.event = this.afs.collection<IEvent>("Events");
    this.observables.event = this.getEvents();

    // Praesidium Setup
    this.collections.praesidium = this.afs.collection<IPraesidium>("Praesidium");
    this.observables.praesidium = this.getPraesidium();

    // Sponsor Setup
    this.collections.sponsor = this.afs.collection<ISponsor>("Sponsors");
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
    this.collections.clubtekst.add(clubtext);
  }

  delClubTekst(clubtekst: IClubTekst) {
    this.collections.clubtekst.doc(clubtekst.id).delete();
  }

  patchClubTekst(clubtekst: IClubTekst) {
    this.collections.clubtekst.doc(clubtekst.id).update(clubtekst);
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
    this.collections.event.add(event);
  }
  
  delEvent(event: IEvent) {
    this.collections.event.doc(event.id).delete();
  }

  patchEvent(event: IEvent) {
    this.collections.clubtekst.doc(event.id).update(event);
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
    this.collections.praesidium.add(praesidium);
  }

  delPraesidiumt(praesidium: IPraesidium) {
    this.collections.praesidium.doc(praesidium.id).delete();
  }

  patchPraesidium(praesidium: IPraesidium) {
    this.collections.clubtekst.doc(praesidium.id).update(praesidium);
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
    this.collections.sponsor.add(sponsor);
  }

  delSponsor(sponsor: ISponsor) {
    this.collections.sponsor.doc(sponsor.id).delete();
  }

  patchSponsor(sponsor: ISponsor) {
    this.collections.clubtekst.doc(sponsor.id).update(sponsor);
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
    this.collections.vacature.add(vacature);
  }

  delVacature(vacature: IVacature) {
    this.collections.vacature.doc(vacature.id).delete();
  }

  patchVacature(vacature: IVacature) {
    this.collections.clubtekst.doc(vacature.id).update(vacature);
  }
}