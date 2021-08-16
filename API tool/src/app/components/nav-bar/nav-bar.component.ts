import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-nav-bar',
  templateUrl: './nav-bar.component.html',
  styleUrls: ['./nav-bar.component.css']
})
export class NavBarComponent implements OnInit {

  categories: string[] = [ "ClubText", "Events", "Preasidium", "Sponsors", "Vacatures"]

  constructor() { }

  ngOnInit(): void {
  }

}
