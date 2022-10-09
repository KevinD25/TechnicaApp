import { IClubTekst, IEvent, IPraesidium, ISponsor, IVacature } from '../interfaces/collections';
import { AngularFirestoreCollection, } from '@angular/fire/firestore';

export interface ICollection{
    clubtext: AngularFirestoreCollection<IClubTekst>,
    event: AngularFirestoreCollection<IEvent>,
    praesidium: AngularFirestoreCollection<IPraesidium>,
    sponsor: AngularFirestoreCollection<ISponsor>,
    vacature: AngularFirestoreCollection<IVacature>
}