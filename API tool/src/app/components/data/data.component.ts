import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { IClubTekst, IEvent, IPraesidium, ISponsor, IVacature } from 'src/app/interfaces/collections';
import { DataService } from 'src/app/services/data-service/data.service';

@Component({
  selector: 'app-data',
  templateUrl: './data.component.html',
  styleUrls: ['./data.component.css', '../add-item/add-item.component.css']
})
export class DataComponent implements OnInit {

  route: string = "Everythings fine";
  items: any;
  item: any;
  editState: boolean = false;
  itemToEdit: any;


  constructor(private DataService: DataService, private activatedroute: ActivatedRoute) { }

  ngOnInit(): void {
    this.activatedroute.data.subscribe(data => { this.route = data.name; })
    console.log(this.route);
    switch(this.route) {
      case "ClubText": {
        this.item = { text: "" } as IClubTekst;
        this.DataService.getClubTeksten().subscribe(res => { this.items = res; console.log(res) });
        break; 
      } case "Events": {
        this.item as IEvent;
        this.DataService.getEvents().subscribe(res => { this.items = res; console.log(res) });
        break; 
      } case "Praesidium": {
        this.item as IPraesidium;
        this.DataService.getPraesidium().subscribe(res => { this.items = res; console.log(res) });
        break; 
      } case "Sponsors": {
        this.item as ISponsor;
        this.DataService.getSponsors().subscribe(res => { this.items = res; console.log(res) });
        break; 
      } case "Vacatures": {
        this.item as IVacature;
        this.DataService.getVacatures().subscribe(res => { this.items = res; console.log(res) });
        break;
      } default: { 
        console.log("Route not found");
        break; 
      }
    }
  }

  clearState() {
    this.editState = false;
    this.itemToEdit = null;
  }

  deleteItem(item) {
    this.clearState();
    switch(this.route) {
      case "ClubText": {
        this.DataService.delClubTekst(item);
        break; 
      } case "Events": { 
        this.DataService.addEvent(item);
        break; 
      } case "Praesidium": { 
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

  editItem(item) {
    this.editState = true;
    this.itemToEdit = item;
  }

  updateItem(){
    // switch(this.route) {
    //   case "ClubText": {
    //     this.DataService.patchClubTekst(this.item);
    //     break; 
    //   } case "Events": { 
    //     this.DataService.patchEvent(this.item);
    //     break; 
    //   } case "Praesidium": { 
    //     this.DataService.patchPraesidium(this.item);
    //     break; 
    //   } case "Sponsors": { 
    //     this.DataService.patchSponsor(this.item);
    //     break; 
    //   } case "Vacatures": { 
    //     this.DataService.patchVacature(this.item);
    //     break; 
    //   } default: { 
    //     console.log("Route not found");
    //     break; 
    //   }
    // }
    this.clearState();
  }
}
