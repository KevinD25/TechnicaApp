import { Injectable } from '@angular/core';
import { AngularFireStorage, AngularFireStorageReference, AngularFireUploadTask } from '@angular/fire/storage'

@Injectable({
  providedIn: 'root'
})
export class FileService {

  // https://medium.com/codingthesmartway-com-blog/firebase-cloud-storage-with-angular-394566fd529
  // https://stackoverflow.com/questions/54149972/convert-file-object-to-img-angular
  fileToUpload: File = null;

  constructor(private afs: AngularFireStorage) { }

  getFile(imageLink: string) {
    return this.afs.ref(imageLink).getDownloadURL();
  }

  delFile(imageLink: string) {
    console.log(imageLink)
    this.afs.ref(imageLink).delete();
  }

  addFile(route: string, file: File) {
    this.afs.ref(route).child(file.name).put(file);
  }

  addImage(route: string) {
    this.addFile(route, this.fileToUpload);
    let imageLink = `/${route}/${this.fileToUpload.name}`;
    this.fileToUpload = null;
    return imageLink;
  }
}
