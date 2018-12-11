export class Event {
    private _id: number;
    private _titre: string;
    private _dateDebut: string;
    private _dateFin: string;
    private _couleur: string;

    /**
     * Getter id
     * @return {number}
     */
    public get id(): number {
        return this._id;
    }

    /**
     * Setter id
     * @param {number} value
     */
    public set id(value: number) {
        this._id = value;
    }

    /**
     * Getter titre
     * @return {string}
     */
    public get titre(): string {
        return this._titre;
    }

    /**
     * Setter titre
     * @param {string} value
     */
    public set titre(value: string) {
        this._titre = value;
    }

    /**
     * Getter dateDebut
     * @return {string}
     */
    public get dateDebut(): string {
        return this._dateDebut;
    }

    /**
     * Setter dateDebut
     * @param {string} value
     */
    public set dateDebut(value: string) {
        this._dateDebut = value;
    }

    /**
     * Getter dateFin
     * @return {string}
     */
    public get dateFin(): string {
        return this._dateFin;
    }

    /**
     * Setter dateFin
     * @param {string} value
     */
    public set dateFin(value: string) {
        this._dateFin = value;
    }

    /**
     * Getter couleur
     * @return {string}
     */
    public get couleur(): string {
        return this._couleur;
    }

    /**
     * Setter couleur
     * @param {string} value
     */
    public set couleur(value: string) {
        this._couleur = value;
    }
}
