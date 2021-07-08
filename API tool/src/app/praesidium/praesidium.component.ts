import { Component, OnInit } from '@angular/core';
import { DataserviceService } from "../Services/dataservice.service";

import { AngularFireStorage } from "@angular/fire/storage";
import { map, finalize } from "rxjs/operators";
import { Observable } from "rxjs";

@Component({
  selector: 'app-praesidium',
  templateUrl: './praesidium.component.html',
  styleUrls: ['./praesidium.component.css']
})
export class PraesidiumComponent implements OnInit {


  constructor(public DataserviceService: DataserviceService, private storage: AngularFireStorage) { }

  PRAESIDIUM;
  selectedFile: File = null;
  n;
  fb;
  downloadURL: Observable<string>;
  url: string;
  file: File;
  count: number=0;
  urls:string[]=[""];
  ngOnInit(): void {
    this.getPraesidium();

  }

  getPraesidium = () => this.DataserviceService.getPraesidium().subscribe(res => (this.PRAESIDIUM = res))

  async CREATE() {
    this.onUpload();
    await this.delay(5000);

    this.DataserviceService.PraesidiumForm.patchValue({
      imageLink: `/Praesidium/${this.n}`
    });

    let data = this.DataserviceService.PraesidiumForm.value;
    this.DataserviceService.CreatePraesidiumLid(data)
      .then(res => {
        prompt("succes!")
      });
  }


  DELETE(a: string) {
    this.DataserviceService.DeletePraesidiumlid(a);

  }
  // TODO :: IMPLEMENT UPDATE 
  UPDATE() {

  }
  delay(ms: number) {
    return new Promise(resolve => setTimeout(resolve, ms));
  }

  onFileSelected(event) {
    this.n = Date.now();
    this.file = event.target.files[0];
  }

  onUpload() {
    const filePath = `/Praesidium/${this.n}`;
    const fileRef = this.storage.ref(filePath);
    const task = this.storage.upload(`/Praesidium/${this.n}`, this.file);
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
        if (url) {
          console.log(url);
        }
      });

  }

  //method to retrieve download url
  private async  getUrl(linkske) {
    this.count++;
    if (this.count <= this.PRAESIDIUM.length) {
      const ref = this.storage.ref(linkske)
      await ref.getDownloadURL()
      .subscribe(url => {
          this.urls.push(url)
        })
    }

  }
}
