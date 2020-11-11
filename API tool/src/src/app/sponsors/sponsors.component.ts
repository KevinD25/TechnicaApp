import { Component, OnInit } from '@angular/core';
import { DataserviceService } from "../Services/dataservice.service";

@Component({
  selector: 'app-sponsors',
  templateUrl: './sponsors.component.html',
  styleUrls: ['./sponsors.component.css']
})
export class SponsorsComponent implements OnInit {

  
  constructor(public DataserviceService:DataserviceService) { }

  SPONSORS;
  ngOnInit(): void 
  {
    this.getSponsors();
    
    
  }

  getSponsors = () => this.DataserviceService.GetSponsors().subscribe(res => (this.SPONSORS = res))

  CREATE() 
  {
    

    let data = this.DataserviceService.SponsorForm.value;
   this.DataserviceService.CreateSponsor(data)
       .then(res => {
           prompt("succes!")
       });
  }

  
  DELETE(a : string)
  {
    this.DataserviceService.DeleteSponsor(a);

  }
// TODO :: IMPLEMENT UPDATE 
  UPDATE()
  {

  }
}
