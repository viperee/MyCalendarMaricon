import { Couleur } from "./couleur";

export class Event {
  id: number;
  titre: string;
  dateDebut: string;
  dateFin: string;
  couleurs: Couleur;
  journeeEntiere: boolean;

  constructor(
    id: number,
    titre: string,
    dateDebut: string,
    dateFin: string,
    couleur: Couleur, journeeEntiere: boolean
  ) {
    this.id = id;
    this.titre = titre;
    this.dateDebut = dateDebut;
    this.dateFin = dateFin;
    this.couleurs = new Couleur(couleur.primary, couleur.secondary);
    this.journeeEntiere = journeeEntiere;
  }

}
