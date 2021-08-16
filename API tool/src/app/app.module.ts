import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { ReactiveFormsModule,FormsModule } from "@angular/forms";
import { RouterModule } from '@angular/router';
import { AppComponent } from './app.component';
import { environment } from "src/environments/environment";
import { AngularFireModule } from "@angular/fire";
import { AngularFirestoreModule } from "@angular/fire/firestore";

import { DataComponent } from './components/data/data.component';
import { EditorComponent } from './components/editor/editor.component';
import { NavBarComponent } from './components/nav-bar/nav-bar.component';
import { ViewerComponent } from './components/viewer/viewer.component';

import { ClubTekstComponent } from './club-tekst/club-tekst.component';
import { HomeComponent } from './home/home.component';
import { PraesidiumComponent } from './praesidium/praesidium.component';
import { SponsorsComponent } from './sponsors/sponsors.component';
import { VacaturesComponent } from './vacatures/vacatures.component';

@NgModule({
  declarations: [
    AppComponent,
    NavBarComponent,
    EditorComponent,
    ViewerComponent,
    DataComponent,
    
    ClubTekstComponent,
    HomeComponent,
    PraesidiumComponent,
    SponsorsComponent,
    VacaturesComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    ReactiveFormsModule,
    AngularFireModule.initializeApp(environment.firebaseConfig, 'API tool'),
    AngularFirestoreModule,
    RouterModule.forRoot([
      {path: "ClubText", component: DataComponent, data :{ name: "ClubText" }},
      {path: "Events", component: DataComponent, data :{ name: "Events" }},
      {path: "Preasidium", component: DataComponent, data :{ name: "Preasidium" }},
      {path: "Sponsors", component: DataComponent, data :{ name: "Sponsors" }},
      {path: "Vacatures", component: DataComponent, data :{ name: "Vacatures" }},
      {path: "", redirectTo: "Home", pathMatch: 'full'}
    ], {useHash: true})
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
