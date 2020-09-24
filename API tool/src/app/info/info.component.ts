import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-info',
  templateUrl: './info.component.html',
  styleUrls: ['./info.component.css']
})
export class InfoComponent implements OnInit {

  constructor() { }

  ngOnInit(): void {
  }

  VoegInfoToe = function(info:string){
    var Data={
      info:info
    }

    this.http.post("https://localhost:44363/api/info",Data).toPromise().then((data:any)=>{
      console.log(data);
    })
    window.location.reload();
  }
}
