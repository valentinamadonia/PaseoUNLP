import { Estado } from "../utils/estado";
import { Productor } from "./productor";
import { RubroDeProducto } from "./rubro-de-producto";
import { Image } from "./image";

export class Producto {
     id:number;
	 rubros:RubroDeProducto[];
	 nombre:string;
	 stock:number;
	 descripcion:string;
	 precio:number;
	 estado:Estado;
	 productor:Productor;
	 imagenes: Image[];


	constructor( rubros: RubroDeProducto[], nombre: string, stock: number, descripcion: string, precio: number, estado: Estado, productor: Productor, imagenes: Image[]) {
		this.rubros = rubros;
		this.nombre = nombre;
		this.stock = stock;
		this.descripcion = descripcion;
		this.precio = precio;
		this.estado = estado;
		this.productor = productor;
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
     * Getter rubros
     * @return {RubroDeProducto}
     */
	public get Rubros(): RubroDeProducto[] {
		return this.rubros;
	}

    /**
     * Getter nombre
     * @return {string}
     */
	public get Nombre(): string {
		return this.nombre;
	}

    /**
     * Getter stock
     * @return {number}
     */
	public get Stock(): number {
		return this.stock;
	}

    /**
     * Getter descripcion
     * @return {string}
     */
	public get Descripcion(): string {
		return this.descripcion;
	}

    /**
     * Getter precio
     * @return {number}
     */
	public get Precio(): number {
		return this.precio;
	}

    /**
     * Getter estado
     * @return {Estado}
     */
	public get Estado(): Estado {
		return this.estado;
	}

    /**
     * Getter productor
     * @return {Productor}
     */
	public get Productor(): Productor {
		return this.productor;
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
     * Setter rubros
     * @param {RubroDeProducto} value
     */
	public set Rubros(value: RubroDeProducto[]) {
		this.rubros = value;
	}

    /**
     * Setter nombre
     * @param {string} value
     */
	public set Nombre(value: string) {
		this.nombre = value;
	}

    /**
     * Setter stock
     * @param {number} value
     */
	public set Stock(value: number) {
		this.stock = value;
	}

    /**
     * Setter descripcion
     * @param {string} value
     */
	public set Descripcion(value: string) {
		this.descripcion = value;
	}

    /**
     * Setter precio
     * @param {number} value
     */
	public set Precio(value: number) {
		this.precio = value;
	}

    /**
     * Setter estado
     * @param {Estado} value
     */
	public set Estado(value: Estado) {
		this.estado = value;
	}

    /**
     * Setter productor
     * @param {Productor} value
     */
	public set Productor(value: Productor) {
		this.productor = value;
	}

    /**
     * Setter imagenes
     * @param {Image[]} value
     */
	public set Imagenes(value: Image[]) {
		this.imagenes = value;
	}


}
