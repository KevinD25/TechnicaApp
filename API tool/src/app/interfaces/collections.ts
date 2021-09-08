export interface IClubTekst {
    id?: string,
    text: string
}

export interface IEvent {
    id?: string,
    name: string,
    fbLink: string,
    formsLink: string,
    date: string,
    time: string,
    location: string,
    price?: number,
    imageLink: string,
    description: string
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