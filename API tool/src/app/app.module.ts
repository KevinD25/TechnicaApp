import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { PraesidiumComponent } from './praesidium/praesidium.component';
import { InfoComponent } from './info/info.component';
import { EventsComponent } from './events/events.component';
import { PartnersComponent } from './partners/partners.component';
import { VacaturesComponent } from './vacatures/vacatures.component';
import {HTTP_INTERCEPTORS, HttpClientModule} from '@angular/common/http';
import {TokenInterceptorService} from './token-interceptor.service';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import { NavBarComponent } from './nav-bar/nav-bar.component';
import {AngularFireModule} from 'angularfire2';
import {environment} from '../environments/environment';

export const firebaseConfig = environment.firebaseConfig;
import {AngularFirestoreModule} from 'angularfire2/firestore';
import { FirestoreService } from './services/firestore.service';


@NgModule({
  declarations: [
    AppComponent,
    PraesidiumComponent,
    InfoComponent,
    EventsComponent,
    PartnersComponent,
    VacaturesComponent,
    NavBarComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    ReactiveFormsModule,
    AngularFireModule.initializeApp(firebaseConfig),
    AngularFirestoreModule
  ],
  providers: [
    FirestoreService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
