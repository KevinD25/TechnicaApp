import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-vacatures',
  templateUrl: './vacatures.component.html',
  styleUrls: ['./vacatures.component.css']
})
export class VacaturesComponent implements OnInit {

  constructor() { }

  ngOnInit(): void {
  }

  VoegNieuweVacatureToe = function(bedrijfid:string, naam:string,beschrijving:string,link:string){
    var Data={
      CompanyID:bedrijfid,
      name:naam,
      description:beschrijving,
      link:link,
    }

    this.http.post("https://localhost:44363/api/vacature",Data).toPromise().then((data:any)=>{
      console.log(data);
    })
    window.location.reload();
  }

}
