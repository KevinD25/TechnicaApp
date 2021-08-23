import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import { AppComponent } from './app.component';
import { environment } from "src/environments/environment";

import { AngularFireModule } from "@angular/fire";
import { AngularFirestoreModule } from "@angular/fire/firestore";
import { AngularFireStorageModule } from "@angular/fire/storage";
import { ReactiveFormsModule, FormsModule } from "@angular/forms";

import { DataComponent } from './components/data/data.component';
import { NavBarComponent } from './components/nav-bar/nav-bar.component';
import { AddItemComponent } from './components/add-item/add-item.component';

@NgModule({
  declarations: [
    AppComponent,
    NavBarComponent,
    DataComponent,
    AddItemComponent,
  ],
  imports: [
    BrowserModule,
    AngularFireModule.initializeApp(environment.firebaseConfig, 'API tool'),
    AngularFirestoreModule,
    AngularFireStorageModule,
    ReactiveFormsModule,
    FormsModule,
    RouterModule.forRoot([
      {path: "ClubText", component: DataComponent, data :{ name: "ClubText" }},
      {path: "Events", component: DataComponent, data :{ name: "Events" }},
      {path: "Praesidium", component: DataComponent, data :{ name: "Praesidium" }},
      {path: "Sponsors", component: DataComponent, data :{ name: "Sponsors" }},
      {path: "Vacatures", component: DataComponent, data :{ name: "Vacatures" }},
      {path: "", redirectTo: "Home", pathMatch: 'full'}
    ], {useHash: true})
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }