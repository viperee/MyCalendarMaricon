export class Couleur {
  private id: number;
  private primary: string;
  private secondary: string;

  constructor(primary: string, secondary: string
  ) {
    this.primary = primary;
    this.secondary = secondary;
  }

  public get _id(): number {
    return this.id;
  }

  public set _id(id: number) {
    this.id = id;
  }

  public get _primary(): string {
    return this.primary;
  }

  public set _primary(primary: string) {
    this.primary = primary;
  }

  public get _secondary(): string {
    return this.secondary;
  }

  public set _secondary(secondary: string) {
    this.secondary = secondary;
  }
}
