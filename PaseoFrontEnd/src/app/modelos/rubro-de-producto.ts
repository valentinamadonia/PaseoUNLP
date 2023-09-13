import { Estado } from "../utils/estado";
import { Producto } from "./producto";

export class RubroDeProducto {

     id:number;
     nombre:string;
     estado:Estado;
     productos:Producto[];


	constructor(nombre: string, estado: Estado, productos: Producto[]) {
		this.nombre = nombre;
		this.estado = estado;
		this.productos = productos;
	}


    /**
     * Getter id
     * @return {number}
     */
	public get Id(): number {
		return this.id;
	}

    /**
     * Getter nombre
     * @return {string}
     */
	public get Nombre(): string {
		return this.nombre;
	}

    /**
     * Getter estado
     * @return {Estado}
     */
	public get Estado(): Estado {
		return this.estado;
	}

    /**
     * Getter productos
     * @return {Producto[]}
     */
	public get Productos(): Producto[] {
		return this.productos;
	}

    /**
     * Setter id
     * @param {number} value
     */
	public set Id(value: number) {
		this.id = value;
	}

    /**
     * Setter nombre
     * @param {string} value
     */
	public set Nombre(value: string) {
		this.nombre = value;
	}

    /**
     * Setter estado
     * @param {Estado} value
     */
	public set Estado(value: Estado) {
		this.estado = value;
	}

    /**
     * Setter productos
     * @param {Producto[]} value
     */
	public set Productos(value: Producto[]) {
		this.productos = value;
	}

}
