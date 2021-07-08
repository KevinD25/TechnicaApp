import { Component, OnInit } from '@angular/core';
import { DataserviceService } from "../Services/dataservice.service";
@Component({
  selector: 'app-club-tekst',
  templateUrl: './club-tekst.component.html',
  styleUrls: ['./club-tekst.component.css']
})
export class ClubTekstComponent implements OnInit {

  constructor(public DataserviceService:DataserviceService) { }

  CLUBTEXT = [];
  TEKST;
  ngOnInit(): void 
  {
    this.getText();
    
    
  }

  getText = () => this.DataserviceService.getClubTekst().subscribe(res => (this.TEKST = res))

  CREATE() 
  {
    if(this.TEKST[0] != undefined)
    {
      this.DataserviceService.DeleteClubTekst(this.TEKST[0].payload.doc.id);

    }

    let data = this.DataserviceService.ClubTekstForm.value;
   this.DataserviceService.CreateClubtekst(data)
       .then(res => {
           prompt("succes!")
       });
  }

  
  DELETE(a : string)
  {
    this.DataserviceService.DeleteClubTekst(a);

  }
// TODO :: IMPLEMENT UPDATE 
  UPDATE()
  {

  }
}
