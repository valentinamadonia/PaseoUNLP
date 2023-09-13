import { Estado } from "app/utils/estado";

export class PuntoDeRetiro {
     id: number;
	 direccion: string;
	 nombre: string;
     estado:Estado;

	constructor(direccion: string, nombre: string,estado:Estado) {
        this.direccion=direccion;
        this.nombre=nombre;
        this.estado=estado;
	}


    public get Id(): number {
        return this.id;
    }
    public set Id(value: number) {
        this.id = value;
    }
    public get Direccion(): string {
        return this.direccion;
    }
    public set Direccion(value: string) {
        this.direccion = value;
    }
    public get Nombre(): string {
        return this.nombre;
    }
    public set Nombre(value: string) {
        this.nombre = value;
    }
}
