

package EvaluacionFinal;

public class Pantalon extends Componente {
    boolean conCremallera;

    public Pantalon(int id, String nombre, String talla, String color, boolean escomunitario, double precio, boolean conCremallera) {
        super(id, nombre, talla, color, escomunitario, precio);
        this.conCremallera = conCremallera;
    }

    public boolean isConCremallera() {
        return conCremallera;
    }

    public void setConCremallera(boolean conCremallera) {
        this.conCremallera = conCremallera;
    }

    @Override
    public String toString() {
        return super.toString()+"Pantalon{" + "conCremallera=" + conCremallera + '}';
    }

    
}
