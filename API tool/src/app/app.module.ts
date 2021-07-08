import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { ReactiveFormsModule,FormsModule } from "@angular/forms";
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { SponsorsComponent } from './sponsors/sponsors.component';
import { VacaturesComponent } from './vacatures/vacatures.component';
import { ClubTekstComponent } from './club-tekst/club-tekst.component';
import { HomeComponent } from './home/home.component';
import { PraesidiumComponent } from './praesidium/praesidium.component';
import { environment } from "src/environments/environment";
import { AngularFireModule } from "@angular/fire";
import { AngularFirestoreModule } from "@angular/fire/firestore";

@NgModule({
  declarations: [
    AppComponent,
    SponsorsComponent,
    VacaturesComponent,
    ClubTekstComponent,
    HomeComponent,
    PraesidiumComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    ReactiveFormsModule,
    AngularFireModule.initializeApp(environment.firebaseConfig),
    AngularFirestoreModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
