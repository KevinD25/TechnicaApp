import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Function2LabelMapping } from 'src/app/enums/function';
import { IClubTekst, IEvent, IPraesidium, ISponsor, IVacature } from 'src/app/interfaces/collections';
import { DataService } from 'src/app/services/data-service/data.service';
import { FileService } from 'src/app/services/file-service/file.service';

@Component({
  selector: 'app-data',
  templateUrl: './data.component.html',
  styleUrls: ['./data.component.css', '../add-item/add-item.component.css']
})
export class DataComponent implements OnInit {

  route: string = "Everythings fine";
  Function2LabelMapping = Function2LabelMapping;
  items: any[] = [];
  editState: boolean = false;
  itemToEdit: any = null;
  itemUrls: any[] = [];
  url: any = null;


  constructor(private DataService: DataService, private FileService: FileService, private activatedroute: ActivatedRoute) { }

  ngOnInit(): void {
    this.activatedroute.data.subscribe(data => { this.route = data.name; })
    //console.log(this.route);
    switch(this.route) {
      case "ClubText": {
        this.items as IClubTekst[];
        this.DataService.getClubTeksten().subscribe(res => {
          this.items = res;
          console.log(res)
        });
        break; 
      } case "Events": {
        this.items as IEvent[];
        this.DataService.getEvents().subscribe(res => { 
          this.items = res;
          console.log(res);
          this.getUrls(res);
        });
        break; 
      } case "Praesidium": {
        this.items as IPraesidium[];
        this.DataService.getPraesidium().subscribe(res => { 
          this.items = res;
          console.log(res);
          this.getUrls(res);
        });
        break; 
      } case "Sponsors": {
        this.items as ISponsor[];
        this.DataService.getSponsors().subscribe(res => { 
          this.items = res;
          console.log(res);
          this.getUrls(res);
        });
        break; 
      } case "Vacatures": {
        this.items as IVacature[];
        // Map sponsor id and name
        this.DataService.getSponsors().subscribe(res => { 
          this.DataService.mapSponsors(res);
          // Use the name and id for vacature use
          this.DataService.getVacatures().subscribe(res => { 
            this.items = res;
            console.log(this.items)
          });
        });
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
        this.FileService.delFile(item.imageLink);
        this.DataService.delEvent(item);
        break; 
      } case "Praesidium": { 
        this.DataService.delPraesidium(item);
        break; 
      } case "Sponsors": { 
        this.DataService.delSponsor(item);
        break; 
      } case "Vacatures": { 
        this.DataService.delVacature(item);
        break; 
      } default: { 
        console.log("Route not found");
        break; 
      }
    }
  }

  updateItem(item){
    switch(this.route) {
      case "ClubText": {
        this.DataService.patchClubTekst(item);
        break; 
      } case "Events": { 
        this.url = null;
        if (this.FileService.fileToUpload != null){
          this.FileService.delFile(item.imageLink); // remove old image
          item.imageLink = this.FileService.addImage(this.route); // get new imageLink
        }
        this.DataService.patchEvent(item);
        break; 
      } case "Praesidium": { 
        this.url = null;
        if (this.FileService.fileToUpload != null){
          this.FileService.delFile(item.imageLink); // remove old image
          item.imageLink = this.FileService.addImage(this.route); // get new imageLink
        }
        console.log(item)
        this.DataService.patchPraesidium(item);
        break; 
      } case "Sponsors": { 
        this.url = null;
        if (this.FileService.fileToUpload != null){
          this.FileService.delFile(item.imageLink); // remove old image
          item.imageLink = this.FileService.addImage(this.route); // get new imageLink
        }
        this.DataService.patchSponsor(item);
        break; 
      } case "Vacatures": { 
        this.DataService.patchVacature(item);
        break; 
      } default: { 
        console.log("Route not found");
        break; 
      }
    }
    this.clearState();
  }
  
  editItem(item) {
    this.editState = true;
    this.itemToEdit = item;
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

  getUrls(items: any[]){
    let imageUrls: string[] = [];
    
    // Tegen willekeurige plaatsing van foto's
    // Bestanden zijn aparte entiteiten van de data
    items.forEach(item => {
      imageUrls.push(item.imageLink.slice(this.route.length + 2, item.imageLink.indexOf('.')));
      this.itemUrls.push(null)
    });
    console.log(imageUrls)

    items.forEach(item => {
      this.FileService.getFile(item.imageLink).subscribe(res => {
        imageUrls.forEach(url => {
          // Speciale tekens kunnen prolemen veroorzaken
          //console.log(url)
          //console.log(JSON.stringify(res))
          if (JSON.stringify(res).includes(url)) {
            this.itemUrls[imageUrls.indexOf(url)] = res;
          }
        });
      });
    });
  }

  get functions() {
    return this.DataService.functions;
  }

  get sponsors() : Map<string, string> {
    return this.DataService.sponsors;
  }
}
