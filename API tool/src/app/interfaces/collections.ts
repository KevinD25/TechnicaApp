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
    function?: number,
    birthday: string,
    studies: string,
    imageLink: string,
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
    sponsorId?: string
}