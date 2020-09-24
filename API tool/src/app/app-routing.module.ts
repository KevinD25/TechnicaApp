import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {PraesidiumComponent} from './praesidium/praesidium.component'
import {AppComponent} from './app.component'
import { EventsComponent } from './events/events.component';
import { PartnersComponent } from './partners/partners.component';
import { VacaturesComponent } from './vacatures/vacatures.component';
import { InfoComponent } from './info/info.component';

const routes: Routes = [
  {path: 'home', component:AppComponent},
  {path: 'praesidium', component:PraesidiumComponent},
  {path: 'events', component:EventsComponent},
  {path: 'partners', component:PartnersComponent},
  {path: 'vacatures', component:VacaturesComponent},
  {path: 'info', component:InfoComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
