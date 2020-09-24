import { Injectable, EventEmitter } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class DatawisselenService {

  constructor() { }

  public change: EventEmitter<any> = new EventEmitter();

    public setdata(value) {

        this.change.emit(value);
    }
}
