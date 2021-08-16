import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { DataService } from 'src/app/services/data-service/data.service';

@Component({
  selector: 'app-data',
  templateUrl: './data.component.html',
  styleUrls: ['./data.component.css']
})
export class DataComponent implements OnInit {

  route: string = "Everythings fine";
  items: any;

  constructor(private DataService: DataService, private activatedroute: ActivatedRoute) { }

  ngOnInit(): void {
    this.activatedroute.data.subscribe(data => { this.route = data.name; })
    console.log(this.route);
    if (this.route == "ClubText") {
      this.DataService.getClubTekst().subscribe(res => this.items = res);
    } else if (this.route == "Events") {
      this.DataService.getEvents().subscribe(res => (this.items = res));
    } else if (this.route == "Preasidium") {
      this.DataService.getPraesidium().subscribe(res => (this.items = res));
    } else if (this.route == "Sponsors") {
      this.DataService.getSponsors().subscribe(res => (this.items = res));
    } else if (this.route == "Vacatures") {
      this.DataService.getVacatures().subscribe(res => (this.items = res));
    }
    console.log(this.items)
  }
}
