
package EvaluacionFinal;

import java.util.Scanner;

public class Menu {
     public static void main(String[] args)throws Exception {
        int opcion = 1;
        Scanner scanner = new Scanner(System.in);
        
        FabricadeTrajes fabrica = new FabricadeTrajes();
        fabrica.insertarDatos();
        
        while(opcion != 7){
            fabrica.escribirMenu();
            System.out.print("Seleccione una opcion del menu = ");
            opcion = Integer.parseInt(scanner.nextLine());
            
            if(opcion == 1){
                System.out.println("-----");
                fabrica.anadirComponenteAAlmacen();
            }
            if(opcion == 2){
                System.out.println("-----");
                fabrica.listarComponentes();
            }
            if(opcion == 3){
                System.out.println("-----");
                fabrica.anadirTrajeAAlmacen();
            }
            if(opcion == 4){
                System.out.println("-----");
                fabrica.listarTrajes();
            }
            if(opcion == 5){
                System.out.println("-----");
                fabrica.activarDesactivarRebajas();
            }
            if(opcion == 6){
                System.out.println("-----");
                fabrica.crearEnvío();
            }
        }
    }

}
