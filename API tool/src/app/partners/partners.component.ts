import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-partners',
  templateUrl: './partners.component.html',
  styleUrls: ['./partners.component.css']
})
export class PartnersComponent implements OnInit {

  constructor() { }

  ngOnInit(): void {
  }

  VoegNieuwePartnerToe = function(naam:string, info:string,link:string,logo:string){
    var Data={
      name:naam,
      about:info,
      website:link,
      imageLink:logo,
    }

    this.http.post("https://localhost:44363/api/sponsor",Data).toPromise().then((data:any)=>{
      console.log(data);
    })
    window.location.reload();
  }

}
