import { Injectable } from '@angular/core';
import { AngularFireStorage, AngularFireStorageReference, AngularFireUploadTask } from '@angular/fire/storage'

@Injectable({
  providedIn: 'root'
})
export class FileService {

  // https://medium.com/codingthesmartway-com-blog/firebase-cloud-storage-with-angular-394566fd529

  ref: AngularFireStorageReference;
  task: AngularFireUploadTask;

  constructor(private afs: AngularFireStorage) { }

  upload(file: File) {
    this.ref = this.afs.ref(file.name);
    this.task = this.ref.put(file);
  }
}
