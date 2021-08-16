import { Injectable } from '@angular/core';
import { AngularFirestore } from '@angular/fire/firestore';
import { FormControl, FormGroup } from "@angular/forms";

@Injectable({
  providedIn: 'root'
})
export class DataService {
  constructor(private firestore: AngularFirestore ) { }
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

  ////////      CLUBTEKST     ////////
  getClubTekst() { 
    return this.firestore.collection("ClubTekst").snapshotChanges();
  }

  CreateClubtekst(data) {
    return new Promise<any>((resolve, reject) =>{
        this.firestore
            .collection("ClubTekst")
            .add(data)
            .then(res => {}, err => reject(err));
    });
  }

  DeleteClubTekst(data) {
    return this.firestore.collection("ClubTekst").doc(data).delete();
  }

  ////////      PRAESIDIUM     ////////
  getPraesidium() { 
    return this.firestore.collection("Praesidium").snapshotChanges();
  }

  CreatePraesidiumLid(data) {
    return new Promise<any>((resolve, reject) =>{
        this.firestore
            .collection("Praesidium")
            .add(data)
            .then(res => {}, err => reject(err));
    });
  }

  DeletePraesidiumlid(data) {
    return this.firestore.collection("Praesidium").doc(data).delete();
  }

  ////////      Home     ////////
  getHome() { 
    return this.firestore.collection("Home").snapshotChanges();
  }

  CreateNewEvent(data) {
    return new Promise<any>((resolve, reject) =>{
        this.firestore
            .collection("Home")
            .add(data)
            .then(res => {}, err => reject(err));
    });
  }

  DeleteEvent(data) {
    return this.firestore.collection("Home").doc(data).delete();
  }
  
  ////////      Sponsor     ////////
  GetSponsors() { 
    return this.firestore.collection("Sponsors").snapshotChanges();
  }

  CreateSponsor(data) {
    return new Promise<any>((resolve, reject) =>{
        this.firestore
            .collection("Sponsors")
            .add(data)
            .then(res => {}, err => reject(err));
    });
  }

  DeleteSponsor(data) {
    return this.firestore.collection("Sponsors").doc(data).delete();
  }
  
  ////////      Vacatures     ////////
  getVacatures() { 
    return this.firestore.collection("Vacatures").snapshotChanges();
  }

  CreateNewVacature(data) {
    return new Promise<any>((resolve, reject) =>{
        this.firestore
            .collection("Vacatures")
            .add(data)
            .then(res => {}, err => reject(err));
    });
  }

  DeleteVacature(data) {
    return this.firestore.collection("Vacatures").doc(data).delete();
  }
}
