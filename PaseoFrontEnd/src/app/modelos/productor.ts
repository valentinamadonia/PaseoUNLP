import { Estado } from "../utils/estado";
import { Producto } from "./producto";
import { Image } from "./image";

export class Productor {
    id:number;
    nombre:string;
    descripcion:string;
    estado:Estado;
    productos:Producto[];
    imagenes:Image[];


	constructor( nombre: string, descripcion: string, estado: Estado, productos: Producto[], imagenes: Image[]) {
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.estado = estado;
		this.productos = productos;
		this.imagenes = imagenes;
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
     * Getter descripcion
     * @return {string}
     */
	public get Descripcion(): string {
		return this.descripcion;
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
     * Getter imagenes
     * @return {Image[]}
     */
	public get Imagenes(): Image[] {
		return this.imagenes;
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
     * Setter descripcion
     * @param {string} value
     */
	public set Descripcion(value: string) {
		this.descripcion = value;
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

    /**
     * Setter imagenes
     * @param {Image[]} value
     */
	public set Imagenes(value: Image[]) {
		this.imagenes = value;
	}

}
