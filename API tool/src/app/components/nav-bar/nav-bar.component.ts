import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-nav-bar',
  templateUrl: './nav-bar.component.html',
  styleUrls: ['./nav-bar.component.css']
})
export class NavBarComponent implements OnInit {

  categories: string[] = ["Preasidium", "Vacatures", "ClubText", "Home", "Sponsors"]

  constructor() { }

  ngOnInit(): void {
  }

}
