
package EvaluacionFinal;

import java.util.Objects;

public class Componente {
    int id;
    String nombre;
    String talla;
    String color;
    boolean esComunitario;
    double precio;

    public Componente(int id, String nombre, String talla, String color, boolean escomunitario, double precio) {
        this.id = id;
        this.nombre = nombre;
        this.talla = talla;
        this.color = color;
        this.esComunitario = escomunitario;
        this.precio = precio;
    }
    //getters y setters
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTalla() {
        return talla;
    }

    public void setTalla(String talla) {
        this.talla = talla;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public boolean isEscomunitario() {
        return esComunitario;
    }

    public void setEscomunitario(boolean escomunitario) {
        this.esComunitario = escomunitario;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    @Override
    public String toString() {
        return "Componente{" + "id=" + id + 
                ", nombre=" + nombre + 
                ", talla=" + talla + 
                ", color=" + color + 
                ", escomunitario=" + esComunitario + 
                ", precio=" + precio +
                '}';
    }
//creamos equals   
    @Override
    public boolean equals(Object obj) { 
        if (this == obj) {
            return true;
        } 
        if (obj == null) {
            return false;
        }
            
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Componente other = (Componente)obj;
            return this.id == other.id; 
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + this.id;
        return hash;
    }

    public int compareTo(EvaluacionFinal.Componente componente) {
        return Integer.compare(getId(), componente.getId());
    }
}
