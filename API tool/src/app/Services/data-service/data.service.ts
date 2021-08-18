import { Injectable } from '@angular/core';
import { AngularFirestore, AngularFirestoreCollection, AngularFirestoreDocument } from '@angular/fire/firestore';
import { Observable } from 'rxjs/Observable'; import 'rxjs/Rx';

import { IClubTekst, IEvent, IPraesidium, ISponsor, IVacature } from '../../interfaces/collections';

@Injectable({
  providedIn: 'root'
})
export class DataService {
  collections: {
    clubtekst: AngularFirestoreCollection<IClubTekst>;
    event: AngularFirestoreCollection<IEvent>;
    praesidium: AngularFirestoreCollection<IPraesidium>;
    sponsor: AngularFirestoreCollection<ISponsor>;
    vacature: AngularFirestoreCollection<IVacature>;
  } = {
    clubtekst: null,
    event: null,
    praesidium: null,
    sponsor: null,
    vacature: null
  }

  observables: {
    clubtekst: Observable<IClubTekst[]>;
    event: Observable<IEvent[]>;
    praesidium: Observable<IPraesidium[]>;
    sponsor: Observable<ISponsor[]>;
    vacature: Observable<IVacature[]>;
  } = {
    clubtekst: null,
    event: null,
    praesidium: null,
    sponsor: null,
    vacature: null,
  }

  documents: {
    clubtekst: AngularFirestoreDocument<IClubTekst>;
    event: AngularFirestoreDocument<IEvent>;
    praesidium: AngularFirestoreDocument<IPraesidium>;
    sponsor: AngularFirestoreDocument<ISponsor>;
    vacature: AngularFirestoreDocument<IVacature>;
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
    console.log(clubtekst);
    this.documents.clubtekst = this.afs.doc(`ClubTekst/${clubtekst.id}`);
    console.log(this.documents.clubtekst);
    this.documents.clubtekst.delete;
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
    this.documents.event = this.collections.event.doc(event.id);
    this.documents.event.delete;
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
    this.documents.praesidium = this.collections.praesidium.doc(praesidium.id);
    this.documents.praesidium.delete;
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
    this.documents.sponsor = this.collections.sponsor.doc(sponsor.id);
    this.documents.sponsor.delete;
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
    this.documents.vacature = this.collections.vacature.doc(vacature.id);
    this.documents.vacature.delete;
  }
}