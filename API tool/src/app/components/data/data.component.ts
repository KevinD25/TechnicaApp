import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { IClubTekst, IEvent, IPraesidium, ISponsor, IVacature } from 'src/app/interfaces/collections';
import { DataService } from 'src/app/services/data-service/data.service';

@Component({
  selector: 'app-data',
  templateUrl: './data.component.html',
  styleUrls: ['./data.component.css', '../../app.component.css']
})
export class DataComponent implements OnInit {

  route: string = "Everythings fine";
  items: any;

  clubtekst: IClubTekst = {
    text: ""
  }
  event: IEvent = {
    imageLink: "",
    date: "",
    fbLink: ""
  }
  praesidium: IPraesidium = {
    name: "",
    surName: "",
    function: "",
    birthday: "",
    studies: "",
    imageLink: ""
  }
  sponsor: ISponsor = {
    name: "",
    about: "",
    website: "",
    imageLink: "",
  }
  vacature: IVacature = {
    name: "",
    description: "",
    link: "",
    sponsor: ""
  }

  constructor(private DataService: DataService, private activatedroute: ActivatedRoute) { }

  ngOnInit(): void {
    this.activatedroute.data.subscribe(data => { this.route = data.name; })
    console.log(this.route);
    switch(this.route) {
      case "ClubText": { 
        this.DataService.getClubTeksten().subscribe(res => { this.items = res; console.log(res) });
        break; 
      } case "Events": { 
        this.DataService.getEvents().subscribe(res => { this.items = res; console.log(res) });
        break; 
      } case "Preasidium": { 
        this.DataService.getPraesidium().subscribe(res => { this.items = res; console.log(res) });
        break; 
      } case "Sponsors": { 
        this.DataService.getSponsors().subscribe(res => { this.items = res; console.log(res) });
        break; 
      } case "Vacatures": { 
        this.DataService.getVacatures().subscribe(res => { this.items = res; console.log(res) });
        break;
      } default: { 
        console.log("Route not found");
        break; 
      }
    }
  }

  deleteItem(event, item) {
    switch(this.route) {
      case "ClubText": {
        this.DataService.delClubTekst(item);
        break; 
      } case "Events": { 
        this.DataService.addEvent(item);
        break; 
      } case "Preasidium": { 
        this.DataService.addPraesidium(item);
        break; 
      } case "Sponsors": { 
        this.DataService.addSponsor(item);
        break; 
      } case "Vacatures": { 
        this.DataService.addVacature(item);
        break; 
      } default: { 
        console.log("Route not found");
        break; 
      }
    }
  }
  
  addClubtekst() {
    this.DataService.addClubTekst(this.clubtekst);
    this.clubtekst.text = "";
  }

  addEvent() {
    this.DataService.addEvent(this.event);
    this.event.imageLink = "";
    this.event.date = "";
    this.event.fbLink = "";
  }

  addPraesidium() {
    this.DataService.addPraesidium(this.praesidium);
    this.praesidium.name = "";
    this.praesidium.surName = "";
    this.praesidium.function = "";
    this.praesidium.birthday = "";
    this.praesidium.studies = "";
    this.praesidium.imageLink = "";
  }

  addSponsor() {
    this.DataService.addSponsor(this.sponsor);
    this.sponsor.name = "";
    this.sponsor.about = "";
    this.sponsor.website = "";
    this.sponsor.imageLink = "";
  }

  addVacature() {
    this.DataService.addVacature(this.vacature);
    this.vacature.name = "";
    this.vacature.description = "";
    this.vacature.link = "";
    this.vacature.sponsor = "";
  }
}
