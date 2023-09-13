import { Estado } from "app/utils/estado";
import { Pedido } from "./pedido";

export class Ronda {

    id: number;
    fechaInicio: Date;
	fechaFin: Date;
	fechaRetiro: Date; 
	rangoHorario: string;
	pedidos: Pedido[];
    estado:Estado;


	constructor(fechaInicio: Date, fechaFin: Date, fechaRetiro: Date, rangoHorario: string, pedidos: Pedido[], estado:Estado) {
        this.fechaInicio=fechaInicio;
        this.fechaFin=fechaFin;
        this.fechaRetiro=fechaRetiro; 
        this.rangoHorario=rangoHorario;
        this.pedidos=pedidos;   
        this.estado=estado;
    }


    public get Pedidos(): Pedido[] {
        return this.pedidos;
    }
    public set Pedidos(value: Pedido[]) {
        this.pedidos = value;
    }

    public get Id(): number {
        return this.id;
    }
    public set Id(value: number) {
        this.id = value;
    }
    public get FechaInicio(): Date {
        return this.fechaInicio;
    }
    public set FechaInicio(value: Date) {
        this.fechaInicio = value;
    }
    public get FechaFin(): Date {
        return this.fechaFin;
    }
    public set FechaFin(value: Date) {
        this.fechaFin = value;
    }
    public get FechaRetiro(): Date {
        return this.fechaRetiro;
    }
    public set FechaRetiro(value: Date) {
        this.fechaRetiro = value;
    }
    public get RangoHorario(): string {
        return this.rangoHorario;
    }
    public set RangoHorario(value: string) {
        this.rangoHorario = value;
    }
}
