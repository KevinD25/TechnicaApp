import { Injectable } from '@angular/core';
import { AngularFireStorage, AngularFireStorageReference, AngularFireUploadTask } from '@angular/fire/storage'

@Injectable({
  providedIn: 'root'
})
export class FileService {

  // https://medium.com/codingthesmartway-com-blog/firebase-cloud-storage-with-angular-394566fd529
  // https://stackoverflow.com/questions/54149972/convert-file-object-to-img-angular

  ref: AngularFireStorageReference;
  task: AngularFireUploadTask;
  fileToUpload: File = null;

  constructor(private afs: AngularFireStorage) { }

  getFile(imageLink: string) {
    this.ref = this.afs.ref(imageLink);
    return this.ref.getDownloadURL();
  }

  addFile(route: string, file: File) {
    this.ref = this.afs.ref(route).child(file.name);
    this.task = this.ref.put(file);
  }

  addImage(route: string) {
    this.addFile(route, this.fileToUpload);
    let imageLink = `/${route}/${this.fileToUpload.name}`;
    this.fileToUpload = null;
    return imageLink;
  }
}
