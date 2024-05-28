
package EvaluacionFinal;

import java.util.ArrayList;

public class Traje implements Comparable<Traje>{
    ArrayList<Componente> piezas;
    String nombre;

    public Traje() {
    }

    public ArrayList<Componente> getPiezas() {
        return piezas;
    }

    public void setPiezas(ArrayList<Componente> piezas) {
        this.piezas = piezas;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return "Traje{" + 
                "piezas=" + piezas + 
                ", nombre=" + nombre + 
                '}';
    }

    @Override
    public int compareTo(Traje traje) {
        return nombre.compareTo(traje.getNombre());
    }
}
