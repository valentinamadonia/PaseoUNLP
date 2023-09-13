import { EstadoPedido } from "../utils/estado-pedido";
import { RetiroEntrega } from "../utils/retiro-entrega";
import { ProductoEncargado } from "./producto-encargado";
import { PuntoDeRetiro } from "./punto-de-retiro";
import { Ronda } from "./ronda";
import { Usuario } from "./usuario";

export class Pedido {
     id: number;
     fecha: Date;
	 fechaEntrega: Date;
	 usuario: Usuario;
	 retiroEntrega: RetiroEntrega;
	 estado: EstadoPedido;
	 direccionRetiro: PuntoDeRetiro;
	 direccionEntrega: string;
	 montoTotal: number;
	 rangoHorario: string;
	 listaProductos: ProductoEncargado[];
     ronda: Ronda|null;


	constructor( fecha: Date, fechaEntrega: Date, usuario: Usuario, retiroEntrega: RetiroEntrega, estado: EstadoPedido, direccionRetiro: PuntoDeRetiro, direccionEntrega: string, montoTotal: number, rangoHorario: string, listaProductos: ProductoEncargado[], ronda: Ronda | null ) {
        this.fecha=fecha;
        this.fechaEntrega=fechaEntrega;
        this.usuario=usuario;
        this.retiroEntrega=retiroEntrega;
        this.estado=estado;
        this.direccionRetiro=direccionRetiro;
        this.direccionEntrega=direccionEntrega;
        this.montoTotal=montoTotal;
        this.rangoHorario=rangoHorario;
        this.listaProductos=listaProductos;
        this.ronda=ronda;
    }

    public get Ronda(): Ronda|null {
        return this.ronda;
    }
    public set Ronda(value: Ronda) {
        this.ronda = value;
    }
    public get Id(): number {
        return this.id;
    }
    public set Id(value: number) {
        this.id = value;
    }
    public get Fecha(): Date {
        return this.fecha;
    }
    public set Fecha(value: Date) {
        this.fecha = value;
    }
    public get Usuario(): Usuario {
        return this.usuario;
    }
    public set Usuario(value: Usuario) {
        this.usuario = value;
    }
    
    public get FechaEntrega(): Date {
        return this.fechaEntrega;
    }
    public set FechaEntrega(value: Date) {
        this.fechaEntrega = value;
    }
    public get RetiroEntrega(): RetiroEntrega {
        return this.retiroEntrega;
    }
    public set RetiroEntrega(value: RetiroEntrega) {
        this.retiroEntrega = value;
    }
    public get Estado(): EstadoPedido {
        return this.estado;
    }
    public set Estado(value: EstadoPedido) {
        this.estado = value;
    }
    public get DireccionRetiro(): PuntoDeRetiro {
        return this.direccionRetiro;
    }
    public set DireccionRetiro(value: PuntoDeRetiro) {
        this.direccionRetiro = value;
    }
    public get DireccionEntrega(): string {
        return this.direccionEntrega;
    }
    public set DireccionEntrega(value: string) {
        this.direccionEntrega = value;
    }
    public get MontoTotal(): number {
        return this.montoTotal;
    }
    public set MontoTotal(value: number) {
        this.montoTotal = value;
    }
    public get RangoHorario(): string {
        return this.rangoHorario;
    }
    public set RangoHorario(value: string) {
        this.rangoHorario = value;
    }
    public get Productos(): ProductoEncargado[] {
        return this.listaProductos;
    }
    public set Productos(value: ProductoEncargado[]) {
        this.listaProductos = value;
    }
}
