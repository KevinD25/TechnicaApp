import { Component, OnInit } from '@angular/core';
import { DataserviceService } from "../Services/dataservice.service";

@Component({
  selector: 'app-vacatures',
  templateUrl: './vacatures.component.html',
  styleUrls: ['./vacatures.component.css']
})
export class VacaturesComponent implements OnInit {

  constructor(public DataserviceService:DataserviceService) { }

  VACATURES;
  ngOnInit(): void 
  {
    this.getVacatures();
    
    
  }

  getVacatures = () => this.DataserviceService.getVacatures().subscribe(res => (this.VACATURES = res))

  CREATE() 
  {
    

    let data = this.DataserviceService.VacatureForm.value;
   this.DataserviceService.CreateNewVacature(data)
       .then(res => {
           prompt("succes!")
       });
  }

  
  DELETE(a : string)
  {
    this.DataserviceService.DeleteVacature(a);

  }
// TODO :: IMPLEMENT UPDATE 
  UPDATE()
  {

  }
}
