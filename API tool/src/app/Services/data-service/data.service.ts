import { Injectable } from '@angular/core';
import { AngularFirestore, AngularFirestoreCollection, AngularFirestoreDocument } from '@angular/fire/firestore';
import { FormControl, FormGroup } from "@angular/forms";
import { Observable } from 'rxjs/Observable'; import 'rxjs/Rx';

import { IClubText, IEvent, IPraesidium, ISponsor, IVacature } from '../../interfaces/collections';

@Injectable({
  providedIn: 'root'
})
export class DataService {
  collections: {
    clubtext: AngularFirestoreCollection<IClubText>;
    event: AngularFirestoreCollection<IEvent>;
    praesidium: AngularFirestoreCollection<IPraesidium>;
    sponsor: AngularFirestoreCollection<ISponsor>;
    vacature: AngularFirestoreCollection<IVacature>;
  } = {
    clubtext: null,
    event: null,
    praesidium: null,
    sponsor: null,
    vacature: null
  }

  observables: {
    clubtext: Observable<IClubText[]>;
    event: Observable<IEvent[]>;
    praesidium: Observable<IPraesidium[]>;
    sponsor: Observable<ISponsor[]>;
    vacature: Observable<IVacature[]>;
  } = {
    clubtext: null,
    event: null,
    praesidium: null,
    sponsor: null,
    vacature: null,
  }
  
  constructor(private afs : AngularFirestore) {
    // Clubtext Setup
    this.collections.clubtext = this.afs.collection<IClubText>("ClubTekst");
    this.observables.clubtext = this.getClubTekst();

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
  getClubTekst() {
    return this.collections.clubtext.snapshotChanges().map(changes => {
      return changes.map(a => {
        const data = a.payload.doc.data() as IClubText
        data.id = a.payload.doc.id;
        return data
      })
    });
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

  ////////      PRAESIDIUM     ////////
  getPraesidium() {
    return this.collections.praesidium.snapshotChanges().map(changes => {
      return changes.map(a => {
        const data = a.payload.doc.data() as IPraesidium
        data.id = a.payload.doc.id;
        return data
      })
    });
  }

  ////////      Sponsor     ////////
  getSponsors() { 
    return this.collections.sponsor.snapshotChanges().map(changes => {
      return changes.map(a => {
        const data = a.payload.doc.data() as ISponsor
        data.id = a.payload.doc.id;
        return data
      })
    });
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

  

  ////////      CLUBTEKST     ////////
  // getClubTekst() { 
  //   return this.afs.collection("ClubTekst").snapshotChanges();
  // }

  // CreateClubtekst(data) {
  //   return new Promise<any>((resolve, reject) =>{
  //       this.afs
  //           .collection("ClubTekst")
  //           .add(data)
  //           .then(res => {}, err => reject(err));
  //   });
  // }

  // DeleteClubTekst(data) {
  //   return this.afs.collection("ClubTekst").doc(data).delete();
  // }

  // ////////      PRAESIDIUM     ////////
  // getPraesidium() { 
  //   return this.afs.collection("Praesidium").snapshotChanges();
  // }

  // CreatePraesidiumLid(data) {
  //   return new Promise<any>((resolve, reject) =>{
  //       this.afs
  //           .collection("Praesidium")
  //           .add(data)
  //           .then(res => {}, err => reject(err));
  //   });
  // }

  // DeletePraesidiumlid(data) {
  //   return this.afs.collection("Praesidium").doc(data).delete();
  // }

  // ////////      Home     ////////
  // getHome() { 
  //   return this.afs.collection("Home").snapshotChanges();
  // }

  // CreateNewEvent(data) {
  //   return new Promise<any>((resolve, reject) =>{
  //       this.afs
  //           .collection("Home")
  //           .add(data)
  //           .then(res => {}, err => reject(err));
  //   });
  // }

  // DeleteEvent(data) {
  //   return this.afs.collection("Home").doc(data).delete();
  // }
  
  // ////////      Sponsor     ////////
  // GetSponsors() { 
  //   return this.afs.collection("Sponsors").snapshotChanges();
  // }

  // CreateSponsor(data) {
  //   return new Promise<any>((resolve, reject) =>{
  //       this.afs
  //           .collection("Sponsors")
  //           .add(data)
  //           .then(res => {}, err => reject(err));
  //   });
  // }

  // DeleteSponsor(data) {
  //   return this.afs.collection("Sponsors").doc(data).delete();
  // }
  
  // ////////      Vacatures     ////////
  // getVacatures() { 
  //   return this.afs.collection("Vacatures").snapshotChanges();
  // }

  // CreateNewVacature(data) {
  //   return new Promise<any>((resolve, reject) =>{
  //       this.afs
  //           .collection("Vacatures")
  //           .add(data)
  //           .then(res => {}, err => reject(err));
  //   });
  // }

  // DeleteVacature(data) {
  //   return this.afs.collection("Vacatures").doc(data).delete();
  // }

  SponsorForm = new FormGroup ({
    name : new FormControl(''),
    about : new FormControl(''),
    website : new FormControl(''),
    imageLink : new FormControl(''),
  })

  VacatureForm = new FormGroup ({
    name : new FormControl(''),
    description : new FormControl(''),
    link : new FormControl(''),
    sponsorid : new FormControl(''),
    sponsor : new FormControl('')
  })

  HomeForm = new FormGroup ({
    imageLink: new FormControl(''),
    date : new FormControl(''),
    fbLink : new FormControl(''),
  })

  PraesidiumForm = new FormGroup ({
    name: new FormControl(''),
    surName: new FormControl(''),
    function : new FormControl(''),
    birthday : new FormControl(''),
    studies : new FormControl(''),
    imageLink: new FormControl(''),
  })

  ClubTekstForm = new FormGroup ({
    text : new FormControl(''),
  })
}