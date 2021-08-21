import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { IClubTekst, IEvent, IPraesidium, ISponsor, IVacature } from 'src/app/interfaces/collections';
import { DataService } from 'src/app/services/data-service/data.service';

@Component({
  selector: 'app-add-item',
  templateUrl: './add-item.component.html',
  styleUrls: ['./add-item.component.css']
})
export class AddItemComponent implements OnInit {

  route: string = "Everythings fine";
  items: any;
  
  variables: {
    clubtekst: IClubTekst,
    event: IEvent,
    praesidium: IPraesidium,
    sponsor: ISponsor,
    vacature: IVacature
  } = {
    clubtekst: {
      text: ""
    },
    event: {
      imageLink: "",
      date: "",
      fbLink: ""
    },
    praesidium: {
      name: "",
      surName: "",
      function: "",
      birthday: "",
      studies: "",
      imageLink: ""
    },
    sponsor: {
      name: "",
      about: "",
      website: "",
      imageLink: "",
    },
    vacature: {
      name: "",
      description: "",
      link: "",
      sponsor: ""
    }
  }

  constructor(private DataService: DataService, private activatedroute: ActivatedRoute) { }

  ngOnInit(): void {
    this.activatedroute.data.subscribe(data => { this.route = data.name; })
    console.log(this.route);
  }

  addClubtekst() {
    this.DataService.addClubTekst(this.variables.clubtekst);
    this.variables.clubtekst.text = "";
  }

  addEvent() {
    this.DataService.addEvent(this.variables.event);
    this.variables.event.imageLink = "";
    this.variables.event.date = "";
    this.variables.event.fbLink = "";
  }

  addPraesidium() {
    this.DataService.addPraesidium(this.variables.praesidium);
    this.variables.praesidium.name = "";
    this.variables.praesidium.surName = "";
    this.variables.praesidium.function = "";
    this.variables.praesidium.birthday = "";
    this.variables.praesidium.studies = "";
    this.variables.praesidium.imageLink = "";
  }

  addSponsor() {
    this.DataService.addSponsor(this.variables.sponsor);
    this.variables.sponsor.name = "";
    this.variables.sponsor.about = "";
    this.variables.sponsor.website = "";
    this.variables.sponsor.imageLink = "";
  }

  addVacature() {
    this.DataService.addVacature(this.variables.vacature);
    this.variables.vacature.name = "";
    this.variables.vacature.description = "";
    this.variables.vacature.link = "";
    this.variables.vacature.sponsor = "";
  }
}
