import { Estado } from "../utils/estado";
import { Pedido } from "./pedido";
import { Producto } from "./producto";

export class ProductoEncargado {
     id: number;
     cantidad: number;
     producto: Producto;
     pedido: Pedido |null;
     pedidoId:number;
     estado: Estado;
    

	constructor(cantidad: number, producto: Producto, pedido: Pedido| null, estado: Estado) {
        this.cantidad=cantidad;
        this.producto=producto;
        this.pedido=pedido;
        this.estado=estado;
    }


    public get Id(): number {
        return this.id;
    }
    public set Id(value: number) {
        this.id = value;
    }
    public get Cantidad(): number {
        return this.cantidad;
    }
    public set Cantidad(value: number) {
        this.cantidad = value;
    }
    public get Producto(): Producto {
        return this.producto;
    }
    public set Producto(value: Producto) {
        this.producto = value;
    }
    public get Pedido(): Pedido | null {
        return this.pedido;
    }
    public set Pedido(value: Pedido) {
        this.pedido = value;
    }
    public get Estado(): Estado {
        return this.estado;
    }
    public set Estado(value: Estado) {
        this.estado = value;
    }
}

