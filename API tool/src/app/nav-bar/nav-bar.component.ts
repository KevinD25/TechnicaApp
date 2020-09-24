import { Component, OnInit, Output } from '@angular/core';
import { AuthService } from '../auth.service';
import { Injectable, EventEmitter } from '@angular/core';


@Component({
  selector: 'app-navbar',
  templateUrl: './nav-bar.component.html',
  styleUrls: ['./nav-bar.component.css']
})
export class NavBarComponent implements OnInit {

  constructor(public auth: AuthService) { }

  ngOnInit() {
  }

  

}