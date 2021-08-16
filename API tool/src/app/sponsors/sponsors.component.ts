import { Component, OnInit } from '@angular/core';
import { DataService } from "../services/data-service/data.service";
import { AngularFireStorage } from "@angular/fire/storage";
import { map, finalize } from "rxjs/operators";
import { Observable } from "rxjs";

@Component({
  selector: 'app-sponsors',
  templateUrl: './sponsors.component.html',
  styleUrls: ['./sponsors.component.css']
})
export class SponsorsComponent implements OnInit {

  
  constructor(public DataserviceService:DataService, private storage: AngularFireStorage) { }

  SPONSORS;
  selectedFile: File = null;
  fb;
  downloadURL: Observable<string>;
  
  n;
  url : string;
  file:File;
  ngOnInit(): void 
  {
    //this.getSponsors();
    
    
  }

  // getSponsors = () => this.DataserviceService.GetSponsors().subscribe(res => (this.SPONSORS = res))

  // async CREATE() 
  // {
  //   this.onUpload();    
  //   await this.delay(5000);

  //   this.DataserviceService.SponsorForm.patchValue({
  //     imageLink: `/Sponsors/${this.n}`

  //   });
  //   let data = this.DataserviceService.SponsorForm.value;
  //  this.DataserviceService.CreateSponsor(data)
  //      .then(res => {
  //          prompt("succes!")
  //      });
  // }

  
  // DELETE(a : string)
  // {
  //   this.DataserviceService.DeleteSponsor(a);

  // }
// TODO :: IMPLEMENT UPDATE 
  UPDATE()
  {

  }
  delay(ms: number) {
    return new Promise( resolve => setTimeout(resolve, ms) );
}

  onFileSelected(event) 
  {
    this.n = Date.now();
    this.file = event.target.files[0];
  }
  onUpload()
  {
    const filePath = `/Sponsors/${this.n}`;
    const fileRef = this.storage.ref(filePath);
    const task = this.storage.upload(`/Sponsors/${this.n}`, this.file);
    task
      .snapshotChanges()
      .pipe(
        finalize(() => {
          this.downloadURL = fileRef.getDownloadURL();
          this.downloadURL.subscribe(url => {
            if (url) {
              this.fb = url;
              this.url = this.fb;
            }

          });
        })
      )
      .subscribe(url => {
        if (url) 
        {
          console.log(url);
        }
      });
  
    }
  }
