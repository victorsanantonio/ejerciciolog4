/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

/**
 *
 * @author admin
 */
public class Persona {

    private int id;
    private int id_ciudad;
    private String nombre;
    private char tipo;
    private int infectado;

    public Persona() {
    }

    public Persona(int id, int id_ciudad, String nombre, char tipo, int infectado) {
        this.id = id;
        this.id_ciudad = id_ciudad;
        this.nombre = nombre;
        this.tipo = tipo;
        this.infectado = infectado;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_ciudad() {
        return id_ciudad;
    }

    public void setId_ciudad(int id_ciudad) {
        this.id_ciudad = id_ciudad;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public char getTipo() {
        return tipo;
    }

    public void setTipo(char tipo) {
        this.tipo = tipo;
    }

    public int getInfectado() {
        return infectado;
    }

    public void setInfectado(int infectado) {
        this.infectado = infectado;
    }
    
    public String toString(){
        String s="Id: "+id+" | ";
        s+="Id Ciudad: "+id_ciudad+" | ";
        s+="Nombre: "+nombre+" | ";
        s+="Tipo: "+tipo+" | ";
        s+="Infectado: "+infectado;
        return s;
    }
}
