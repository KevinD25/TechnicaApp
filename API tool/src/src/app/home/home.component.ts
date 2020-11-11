import { Component, OnInit } from '@angular/core';
import { DataserviceService } from "../Services/dataservice.service";

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  constructor(public DataserviceService:DataserviceService) { }

  HOME;
  ngOnInit(): void 
  {
    this.getHome();
    
    
  }

  getHome = () => this.DataserviceService.getHome().subscribe(res => (this.HOME = res))

  CREATE() 
  {
    

    let data = this.DataserviceService.HomeForm.value;
   this.DataserviceService.CreateNewEvent(data)
       .then(res => {
           prompt("succes!")
       });
  }

  
  DELETE(a : string)
  {
    this.DataserviceService.DeleteEvent(a);

  }
// TODO :: IMPLEMENT UPDATE 
  UPDATE()
  {

  }
}
