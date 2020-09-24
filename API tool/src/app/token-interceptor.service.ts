import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http'
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class TokenInterceptorService{

  constructor(private http:HttpClient) { }

  login(data):Observable<any>{
    return this.http.post('http://localhost:4200/',data)
  }
}
