export interface IClubTekst {
    id?: string,
    text: string
}

export interface IEvent {
    id?: string,
    name: string,
    imageLink: string,
    date: string,
    fbLink: string,
}

export interface IPraesidium {
    id?: string,
    name: string,
    surName: string,
    function: string,
    birthday: string,
    studies: string,
    imageLink: string,
    priority?: number
}

export interface ISponsor {
    id?: string,
    name: string,
    about: string,
    website: string,
    imageLink: string,
    priority?: number
}

export interface IVacature {
    id?: string,
    name: string,
    description: string,
    link: string,
    sponsor: string,
    sponsorId?: string
}