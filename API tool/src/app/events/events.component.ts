import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-events',
  templateUrl: './events.component.html',
  styleUrls: ['./events.component.css']
})
export class EventsComponent implements OnInit {

  constructor() { }

  ngOnInit(): void {
  }

  VoegNieuwEventToe = function(banner:string, poster:string,datum:string,facebooklink:string){
    var Data={
      bannerimage:banner,
      posterimage:poster,
      date:datum,
      facebooklink:facebooklink
        }

    this.http.post("https://localhost:44363/api/home",Data).toPromise().then((data:any)=>{
      console.log(data);
    })
    window.location.reload();
  }

}
