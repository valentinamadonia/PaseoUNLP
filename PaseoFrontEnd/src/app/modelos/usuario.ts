import { Estado } from "../utils/estado";
import { Rol } from "../utils/rol";
import { Pedido } from "./pedido";

export class Usuario {
    id: number| null;
    nombre: string;
    apellido: string;
    email: string;
    direccion: string;
    telefono: number;
    password: string;
    pedidos: Pedido[];
    rol: Rol;
    estado: Estado;


	constructor(nombre: string, apellido: string, email: string, direccion: string, telefono: number, password: string, pedidos: Pedido[], rol: Rol, estado:Estado) {
       this.nombre=nombre;
       this.apellido=apellido;
       this.email=email;
       this.direccion=direccion;
       this.telefono=telefono;
       this.pedidos=pedidos;
       this.password=password;
       this.estado=estado;
       this.rol=rol;
    }

    public set Id(id:number) {
        this.id=id;
    }
    public set Nombre(nombre: string) {
        this.nombre = nombre;
      }
    public set Apellido(apellido:string) {
        this.apellido=apellido;
    }
    public set Email(email:string) {
        this.email=email;
    }
    public set Direccion(direccion:string) {
        this.direccion=direccion;
    }
    public set Telefono(telefono:number) {
        this.telefono=telefono;
    }
    public set Pedidos(pedidos:Pedido[]){
        this.pedidos=pedidos;
    }
    public set Password(password:string) {
        this.password=password;
    }
    public set Estado(estado:Estado) {
        this.estado=estado;
    }
    public set Rol(rol:Rol) {
        this.rol=rol;
    }
}
