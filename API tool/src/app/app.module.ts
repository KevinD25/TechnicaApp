import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { ReactiveFormsModule,FormsModule } from "@angular/forms";
import { AppRoutingModule } from './app-routing.module';
import { RouterModule } from '@angular/router';
import { AppComponent } from './app.component';
import { SponsorsComponent } from './sponsors/sponsors.component';
import { VacaturesComponent } from './vacatures/vacatures.component';
import { ClubTekstComponent } from './club-tekst/club-tekst.component';
import { HomeComponent } from './home/home.component';
import { PraesidiumComponent } from './praesidium/praesidium.component';
import { environment } from "src/environments/environment";
import { AngularFireModule } from "@angular/fire";
import { AngularFirestoreModule } from "@angular/fire/firestore";
import { NavBarComponent } from './components/nav-bar/nav-bar.component';
import { EditorComponent } from './components/editor/editor.component';
import { ViewerComponent } from './components/viewer/viewer.component';
import { DataComponent } from './components/data/data.component';

@NgModule({
  declarations: [
    AppComponent,
    SponsorsComponent,
    VacaturesComponent,
    ClubTekstComponent,
    HomeComponent,
    PraesidiumComponent,
    NavBarComponent,
    EditorComponent,
    ViewerComponent,
    DataComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    ReactiveFormsModule,
    AngularFireModule.initializeApp(environment.firebaseConfig),
    AngularFirestoreModule,
    RouterModule.forRoot([
      {path: "Preasidium", component: DataComponent, data :{ name: "Preasidium" }},
      {path: "Vacatures", component: DataComponent, data :{ name: "Vacatures" }},
      {path: "ClubText", component: DataComponent, data :{ name: "ClubText" }},
      {path: "Home", component: DataComponent, data :{ name: "Home" }},
      {path: "Sponsors", component: DataComponent, data :{ name: "Sponsors" }},
      {path: "", redirectTo: "Home", pathMatch: 'full'}
    ], {useHash: true})
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
