import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-praesidium',
  templateUrl: './praesidium.component.html',
  styleUrls: ['./praesidium.component.css']
})
export class PraesidiumComponent implements OnInit {

  constructor() { }

  ngOnInit(): void {
  }

  VoegNieuwPraesidiumlidToe = function(naam:string, voornaam:string,geboortedatum:string,studie:string,functie:string){
      var Data={
        name:naam,
        surName:voornaam,
        function:functie,
        birthday:geboortedatum,
        studies:studie
      }
  
      this.http.post("https://localhost:44363/api/praesidium",Data).toPromise().then((data:any)=>{
        console.log(data);
      })
      window.location.reload();
    }
  }