import { Component, OnInit } from '@angular/core';
import { DataserviceService } from "../Services/dataservice.service";
import { AngularFireStorage } from "@angular/fire/storage";
import { finalize } from "rxjs/operators";
import { Observable } from "rxjs";

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  constructor(public DataserviceService:DataserviceService, private storage: AngularFireStorage) { }

  title = "cloudsSorage";
  selectedFile: File = null;
  fb;
  downloadURL: Observable<string>;
  HOME;
  n;
  url : string;
  file:File;

  ngOnInit(): void 
  {
    this.getHome();
    
    
  }

  getHome = () => this.DataserviceService.getHome().subscribe(res => (this.HOME = res))

  async CREATE() 
  {
    this.onUpload();    
    await this.delay(5000);

    this.DataserviceService.HomeForm.patchValue({
      imageLink: `/HomeImages/${this.n}`

    });
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
    const filePath = `/HomeImages/${this.n}`;
    const fileRef = this.storage.ref(filePath);
    const task = this.storage.upload(`/HomeImages/${this.n}`, this.file);
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


