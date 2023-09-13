export class Image {
     id:number;
	 nombre:string;
	 imagen: string;


	constructor( nombre:string, imagen: string ) {
        this.nombre=nombre;
        this.imagen=imagen;
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
     * Getter imagen
     * @return {Image}
     */
	public get Imagen(): string {
		return this.imagen;
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
     * Setter imagen
     * @param {Image} value
     */
	public set Imagen(value: string) {
		this.imagen = value;
	}

}
