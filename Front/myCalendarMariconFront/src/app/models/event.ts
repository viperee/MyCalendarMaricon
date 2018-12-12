import { Couleur } from "./couleur";

export class Event {
  private id: number;
  private titre: string;
  private dateDebut: string;
  private dateFin: string;
  private couleurs: Couleur;
  private journeeEntiere: boolean;

  constructor(
    titre: string,
    dateDebut: string,
    dateFin: string,
    couleur: Couleur, journeeEntiere: boolean
  ) {
    this.titre = titre;
    this.dateDebut = dateDebut;
    this.dateFin = dateFin;
    this.couleurs = new Couleur(couleur._primary, couleur._secondary);
    this.journeeEntiere = journeeEntiere;
  }

  /**
   * Getter id
   * @return {number}
   */
  public get _id(): number {
    return this.id;
  }

  /**
   * Setter id
   * @param {number} value
   */
  public set _id(value: number) {
    this.id = value;
  }

  /**
   * Getter titre
   * @return {string}
   */
  public get _titre(): string {
    return this.titre;
  }

  /**
   * Setter titre
   * @param {string} value
   */
  public set _titre(value: string) {
    this.titre = value;
  }

  /**
   * Getter dateDebut
   * @return {string}
   */
  public get _dateDebut(): string {
    return this.dateDebut;
  }

  /**
   * Setter dateDebut
   * @param {string} value
   */
  public set _dateDebut(value: string) {
    this.dateDebut = value;
  }

  /**
   * Getter dateFin
   * @return {string}
   */
  public get _dateFin(): string {
    return this.dateFin;
  }

  /**
   * Setter dateFin
   * @param {string} value
   */
  public set _dateFin(value: string) {
    this.dateFin = value;
  }

  /**
   * Getter Couleur
   * @return {Couleur}
   */
  public get _couleurs(): Couleur {
    return this.couleurs;
  }

  /**
   * Setter couleur
   * @param {string} value
   */
  public set _couleurs(value: Couleur) {
    this.couleurs = value;
  }

  /**
   * Getter journeeEntiere
   * @return {boolean}
   */
  public get _journeeEntiere(): boolean {
    return this.journeeEntiere;
  }

  /**
   * Setter journeeEntiere
   * @param {boolean} value
   */
  public set _journeeEntiere(value: boolean) {
    this.journeeEntiere = value;
  }
}
