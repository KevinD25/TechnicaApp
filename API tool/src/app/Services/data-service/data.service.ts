import { Injectable } from '@angular/core';
import { AngularFirestore, AngularFirestoreCollection, AngularFirestoreDocument } from '@angular/fire/firestore';
import { FormControl, FormGroup } from "@angular/forms";
import { Observable } from 'rxjs/Observable'; import 'rxjs/Rx';

import { IClubText } from '../../interfaces/interfaces';

@Injectable({
  providedIn: 'root'
})
export class DataService {
  clubtextCollection: AngularFirestoreCollection<IClubText>;
  clubtext: Observable<IClubText[]>
  
  constructor(private afs : AngularFirestore) {
    this.clubtextCollection = this.afs.collection<IClubText>("ClubTekst");
    this.clubtext = this.clubtextCollection.snapshotChanges().map(changes => {
      return changes.map(a => {
        const data = a.payload.doc.data() as IClubText
        data.id = a.payload.doc.id;
        return data
      })
    });
  }

  getClubTekst() {
    return this.clubtext;
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
