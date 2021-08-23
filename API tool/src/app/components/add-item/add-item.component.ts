import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { IClubTekst, IEvent, IPraesidium, ISponsor, IVacature } from 'src/app/interfaces/collections';
import { DataService } from 'src/app/services/data-service/data.service';
import { FileService } from 'src/app/services/file-service/file.service';

@Component({
  selector: 'app-add-item',
  templateUrl: './add-item.component.html',
  styleUrls: ['./add-item.component.css']
})
export class AddItemComponent implements OnInit {

  route: string = "Everythings fine";
  items: any = null;
  url: any = null;
  
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
      date: "",
      fbLink: "",
      imageLink: ""
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

  constructor(private DataService: DataService, private FileService: FileService, private activatedroute: ActivatedRoute) { }

  ngOnInit(): void {
    this.activatedroute.data.subscribe(data => { this.route = data.name; })
    //console.log(this.route);
  }

  addItem() {
    switch(this.route) {
      case "ClubText": {
        this.DataService.addClubTekst(this.variables.clubtekst);
        this.variables.clubtekst.text = "";
        break; 
      } case "Events": {
        this.url = null;
        this.variables.event.imageLink = this.FileService.addImage(this.route);
        this.DataService.addEvent(this.variables.event);
        this.variables.event.date = "";
        this.variables.event.fbLink = "";
        this.variables.event.imageLink = "";
        break; 
      } case "Praesidium": {
        this.DataService.addPraesidium(this.variables.praesidium);
        this.variables.praesidium.name = "";
        this.variables.praesidium.surName = "";
        this.variables.praesidium.function = "";
        this.variables.praesidium.birthday = "";
        this.variables.praesidium.studies = "";
        this.variables.praesidium.imageLink = "";
        break; 
      } case "Sponsors": {
        this.DataService.addSponsor(this.variables.sponsor);
        this.variables.sponsor.name = "";
        this.variables.sponsor.about = "";
        this.variables.sponsor.website = "";
        this.variables.sponsor.imageLink = "";
        break; 
      } case "Vacatures": {
        this.DataService.addVacature(this.variables.vacature);
        this.variables.vacature.name = "";
        this.variables.vacature.description = "";
        this.variables.vacature.link = "";
        this.variables.vacature.sponsor = "";
        break; 
      } default: { 
        console.log("Route not found");
        break; 
      }
    }
  }

  upload(event) {
    this.FileService.fileToUpload = event.target.files[0];
    let reader = new FileReader();
    reader.onload = (event: any) => {
      this.url = event.target.result;
    };
    reader.onerror = (event: any) => {
      console.log("File could not be read: " + event.target.error.code);
    };
    reader.readAsDataURL(event.target.files[0]);
  }
}
