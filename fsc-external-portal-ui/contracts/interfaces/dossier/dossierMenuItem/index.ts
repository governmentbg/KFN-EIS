export interface IDossierMenuItem {
  name: string;
  title: string;
  path: string | null;
  level: number;
  children: IDossierMenuItem[];
  parents: string[];
}
