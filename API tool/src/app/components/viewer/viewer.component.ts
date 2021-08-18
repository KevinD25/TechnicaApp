import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { DataService } from 'src/app/services/data-service/data.service';

@Component({
  selector: 'app-viewer',
  templateUrl: './viewer.component.html',
  styleUrls: ['./viewer.component.css', '../../app.component.css']
})
export class ViewerComponent implements OnInit {

  route: string = "Everythings fine";
  text;

  constructor(private DataService: DataService, private activatedroute: ActivatedRoute) { }

  ngOnInit(): void {
    this.activatedroute.data.subscribe(data => { this.route = data.name; })
    this.DataService.getClubTekst().subscribe(res => (this.text = res))
    //console.log(this.text)
  }
}
