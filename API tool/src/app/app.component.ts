import { Component, Input } from '@angular/core';
import { tokenNotExpired, JwtHelper } from 'angular2-jwt';
import Auth0Lock from 'auth0-lock';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { TokenInterceptorService } from './token-interceptor.service';
import { DatawisselenService } from './datawisselen.service';

// declare var Auth0Lock;

declare module '@angular/core' {
  interface ModuleWithProviders<T = any> {
    ngModule: Type<T>;
    providers?: Provider[];
  }
}

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css'],
})
export class AppComponent {
  title = 'TechnicaAngularProject';

  JuisteGegevens: any;

  constructor(private datawisselen: DatawisselenService) {
    this.getData();
  }

  private getData(): void {
    this.datawisselen.change.subscribe((value) => {
      this.JuisteGegevens = value;
      console.log(this.JuisteGegevens);
    });
  }

  /*
  formGroup:FormGroup;

  constructor(private TokenInterceptorService:TokenInterceptorService){}

  ngOnInit(){
    JuisteGegevens=false;
    this.initForm
  }

  LoginProces(){
    if(this.formGroup.valid){
      this.TokenInterceptorService.login(this.formGroup.value).subscribe(result=>{
        if(result.success){
          console.log(result);
          alert(result.message);
          JuisteGegevens=true;
        }
        else{
          alert(result.message);
          JuisteGegevens=false;
        }
      })
    }
  }

  initForm(){
    this.formGroup=new FormGroup({
      email:new FormControl('',Validators.required),
      password:new FormControl('',Validators.required)
    });
  }*/

  /*
  lock=new Auth0Lock('3V3VJhjWHGmjLaXt0EuxPMVlUCuM2xJE','dev-skr3fnrj.eu.auth0.com');
  jwthelper:JwtHelper=new JwtHelper();

  login(){
    this.lock.show((err: string, profile:string, id_token:string)=>{
      if(err){
        throw new Error(err);
      }

      localStorage.setItem('profile',JSON.stringify(profile))
      localStorage.setItem('id_token',id_token);

      console.log(
        this.jwthelper.decodeToken(id_token),
        this.jwthelper.getTokenExpirationDate(id_token),
        this.jwthelper.isTokenExpired(id_token)
      );

      this.LoggedIn();
    });
  }

  logout(){
    localStorage.removeItem('profile');
    localStorage.removeItem("id_token");

    this.LoggedIn();
  }

  LoggedIn(){
    return tokenNotExpired();
  }

  */
}
