import { Component, OnInit } from '@angular/core';
import { DataserviceService } from "../Services/dataservice.service";

@Component({
  selector: 'app-praesidium',
  templateUrl: './praesidium.component.html',
  styleUrls: ['./praesidium.component.css']
})
export class PraesidiumComponent implements OnInit {


  constructor(public DataserviceService:DataserviceService) { }

  PRAESIDIUM;
  ngOnInit(): void 
  {
    this.getPraesidium();
    
    
  }

  getPraesidium = () => this.DataserviceService.getPraesidium().subscribe(res => (this.PRAESIDIUM = res))

  CREATE() 
  {
    

    let data = this.DataserviceService.PraesidiumForm.value;
   this.DataserviceService.CreatePraesidiumLid(data)
       .then(res => {
           prompt("succes!")
       });
  }

  
  DELETE(a : string)
  {
    this.DataserviceService.DeletePraesidiumlid(a);

  }
// TODO :: IMPLEMENT UPDATE 
  UPDATE()
  {

  }

}
