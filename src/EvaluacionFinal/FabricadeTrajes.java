package EvaluacionFinal;

import java.util.ArrayList;
import java.util.TreeSet;
import java.util.Objects;
import java.util.Scanner;

public class FabricadeTrajes implements IFabricadeTrajes {
    public ArrayList<Componente>componentesAlmacen;;
    public TreeSet<Traje>trajesAlmacen;
    public ArrayList<Traje>listadoDeEnvios;
    public boolean sonRebajas=false;
    
    public FabricadeTrajes() {
        componentesAlmacen = new ArrayList<>();
        trajesAlmacen = new TreeSet<>();
        listadoDeEnvios = new ArrayList<>();
    }
    
    public void insertarDatos(){
        System.out.println("inicie a insertar datos ");
        Blusa blusa = new Blusa(10, "blusa 10", "l", "Azul", true, 8000, true);
        componentesAlmacen.add(blusa);
        Blusa blusa2 = new Blusa(17, "blusa M", "xl", "Verde", true, 8000, false);
        componentesAlmacen.add(blusa2);
        
        Pantalon pantalon = new Pantalon(46, "pantalon", "s", "cafe",false,150,true);
        componentesAlmacen.add(pantalon);
        Pantalon pantalon2 = new Pantalon(362, "pan", "m", "cafe",false,150,true);
        componentesAlmacen.add(pantalon2);
        
        Falda falda = new Falda(32, "falda", "s", "rosado", false, 1250, true);
        componentesAlmacen.add(falda);
        Falda falda2 = new Falda(110, "fal", "s", "rosado", false, 1250, true);
        componentesAlmacen.add(falda2);
        
        Chaqueta chaqueta = new Chaqueta(252,"chaqueta","s","rojo",false,200,5);
        componentesAlmacen.add(chaqueta);
        Chaqueta chaqueta2 = new Chaqueta(21,"chaq","s","rojo",false,200,5);
        componentesAlmacen.add(chaqueta2);
    }
    
    public void escribirMenu(){
        System.out.println("MENU FABRICA DE TRAJES");
        System.out.println("1 - Añadir componente a almacen");
        System.out.println("2 - Listar componente almacen");
        System.out.println("3 - Crear traje y añadir a almacen");
        System.out.println("4 - Listar traje del almacen");
        System.out.println("5 - Activar/Desactivar las rebajas");
        System.out.println("6 - Crear envio");
        System.out.println("7 - Salir");
    }    

    @Override
    public void anadirComponenteAAlmacen() throws Exception {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Que prenda de ropa desea escoger?");
        System.out.print("Falda, Blusa, Pantalon y Chaqueta = ");
        String componente = scanner.nextLine();
        
        System.out.println("Ingrese los datos de la prenda: " + componente);
        
        System.out.print("Ingrese el id = ");
        int id = Integer.parseInt(scanner.nextLine());
        this.validarId(id);
        
        System.out.print("Ingrese el nombre = ");
        String nombre = scanner.nextLine();
        
        System.out.print("Ingrese la talla = ");
        String talla = scanner.nextLine();
            
        System.out.print("Ingrese el color = ");
        String color = scanner.nextLine();
        
        System.out.print("Ingrese si es comunitario (si / no) ==> ");
        String comunitario = scanner.nextLine();
        boolean esComunitario = false;
        if(Objects.equals(comunitario, "si")){
            esComunitario = true;
            this.comunitariosNoPuedeSerMayorAMitad();
        }
        
        System.out.print("Ingrese el precio = ");
        double precio = Double.parseDouble(scanner.nextLine());
        
        if(Objects.equals(componente, "Falda")){
            System.out.print("Ingrese si tiene cremallera (si / no) ==> ");
            String cremallera = scanner.nextLine();
            boolean conCremallera = false;
            if(Objects.equals(cremallera, "si")){
                conCremallera = true; 
                precio = precio + 1;
            }
            
            Falda falda = new Falda(id, nombre, talla, color, esComunitario, precio, conCremallera);
            componentesAlmacen.add(falda);   
        }   
        
        if(Objects.equals(componente, "Blusa")){//Blusa//Comparar 2 cosas string
            
            System.out.print("Ingrese si tiene Manga Larga (si / no) = ");
            String mangaLarga = scanner.nextLine();
            boolean conMangaLarga = false;
            if(Objects.equals(mangaLarga, "si")){
                conMangaLarga = true; 
            }
            
            Blusa blusa = new Blusa(id, nombre, talla, color, esComunitario, precio, conMangaLarga);
            this.validarExistenciaManga(blusa);
        }
            
        if(Objects.equals(componente, "Chaqueta")){ //chaqueta
            System.out.print("Ingrese el numero de botones = ");
            int numBotones = Integer.parseInt(scanner.nextLine());
            int valorAdicional = numBotones * 2;    //2 * 2 =4    se agregan 2 euros por boton
            precio = precio + valorAdicional;       //100 + 4 = 104
            
            Chaqueta chaqueta = new Chaqueta(id, nombre, talla, color, esComunitario, precio, numBotones);
            componentesAlmacen.add(chaqueta);
        }
        
        if(Objects.equals(componente, "Pantalon")){//pantalon
            System.out.print("Ingrese si lleva Cremallera (si / no) = ");
            String cremallera = scanner.nextLine();
            boolean conCremallera = false;
            if(Objects.equals(cremallera, "si")){
                conCremallera = true;
                precio = precio + 1;
            }
           
            Pantalon pantalon = new Pantalon(id ,nombre , talla ,color ,esComunitario , precio, conCremallera);
            componentesAlmacen.add(pantalon);
        }
    }
    
    private void validarId(int idCreado)throws IdException{
        for(Componente componenteRecorreLista: componentesAlmacen){
            int idIterando = componenteRecorreLista.getId();
            if(idCreado == idIterando){
               throw new IdException();
            }    
        }
    }
    
    private void comunitariosNoPuedeSerMayorAMitad() throws MuchoExtraComunitarioException {
        int totalPrendas = 0;
        int cantidadPrendasComunitarias = 0;
        
        for(Componente componenteRecorreLista: componentesAlmacen){
            totalPrendas ++;
            boolean esComunitario = componenteRecorreLista.isEscomunitario();
            if(esComunitario){
               cantidadPrendasComunitarias ++; 
            }
        }
        //calcula el maximo de prendas comunitarias que puede haber
        double maximo = totalPrendas * 0.5;
        if(cantidadPrendasComunitarias > maximo){
            throw new MuchoExtraComunitarioException();
        }
    }
    
    private void validarExistenciaManga(Blusa blusaNueva) throws MangaException{
        boolean insertarAlListado = false;
        String colorBlusaGuardada = blusaNueva.getColor();
        boolean mangaLargaBlusa = blusaNueva.isMangaLarga();
        for(Componente componenteRecorreLista : componentesAlmacen){
            
            String colorComponente = componenteRecorreLista.getColor();
            if(componenteRecorreLista instanceof Blusa && componenteRecorreLista.getColor().equals(colorBlusaGuardada) ){
                boolean mangaLarga = ((Blusa) componenteRecorreLista).isMangaLarga();
                
                if(mangaLarga != mangaLargaBlusa){ //false != true
                    insertarAlListado = true;
                }
            }
        }
        System.out.println("insertar" + insertarAlListado);
        if(insertarAlListado){
           componentesAlmacen.add(blusaNueva); 
        }else{
            throw new MangaException();
        }
    }    
    
    @Override
    public void listarComponentes() {
        int contador = 0;
        for(Componente componenteRecorreLista : componentesAlmacen){
            contador++;
            System.out.println("Fila " + contador + 
                    ":" + " " 
                    + componenteRecorreLista.toString());
        }
        System.out.println("Total prendas: " + componentesAlmacen.size());
    }

    @Override
    public void anadirTrajeAAlmacen() throws Exception {
        Scanner sc1 = new Scanner(System.in);
        
        System.out.println("Lista de blusas existentes");
        for(Componente componenteRecorreLista : componentesAlmacen){
            if(componenteRecorreLista instanceof Blusa){
                System.out.println("Nombre de la blusa: " + componenteRecorreLista.getNombre() + " - " + "ID de la blusa: " + componenteRecorreLista.getId());
            }
        }
        System.out.println("Seleccione el ID de la blusa que desea");
        System.out.print("----> " );
        int idBlusaSeleccionada = Integer.parseInt(sc1.nextLine());
        
        
        System.out.println("Lista de chaquetas existentes");
        for(Componente componenteIterando : componentesAlmacen){
            if(componenteIterando instanceof Chaqueta){
                System.out.println("Nombre de la chaqueta: " + componenteIterando.getNombre() + " - " + "ID de la chaqueta: " + componenteIterando.getId());  
            }
        }
        System.out.println("Seleccione el ID de la chaqueta que desea");
        System.out.print("----> " );
        int idChaquetaSeleccionada = Integer.parseInt(sc1.nextLine());
        
        System.out.println("Lista de faldas y pantalones existentes");
        for(Componente componenteRecorreLista : componentesAlmacen){
            
            if(componenteRecorreLista instanceof Falda){
                System.out.println("Nombre de la falda: " + componenteRecorreLista.getNombre() + " - " + "ID de la falda: " + componenteRecorreLista.getId());  
            }
            if(componenteRecorreLista instanceof Pantalon){
                System.out.println("Nombre del pantalon: " + componenteRecorreLista.getNombre() + " - " + "ID del pantalon: " + componenteRecorreLista.getId());  
            }
        }
        System.out.println("Seleccione el ID de la falda o pantalon que desea");
        System.out.print("----> " );
        int idFaldaPantalonSeleccionada = Integer.parseInt(sc1.nextLine());
        
        try{
            ArrayList<Componente> piezas;
            piezas = this.validarElColorDelTraje(idBlusaSeleccionada, idChaquetaSeleccionada, idFaldaPantalonSeleccionada);
            piezas = this.validarTallas(idBlusaSeleccionada, idChaquetaSeleccionada, idFaldaPantalonSeleccionada);
            
            Traje traje = new Traje();
            traje.setPiezas(piezas);
            
            System.out.println("Ingrese el nombre del traje");
            System.out.print("----> " );
            String nombreTrajeCrear = sc1.nextLine();
        
            traje.setNombre(nombreTrajeCrear);
            this.validarNombreDelTraje(nombreTrajeCrear);
            trajesAlmacen.add(traje);
        
            this.eliminarComponentesYaUsados(idBlusaSeleccionada, idChaquetaSeleccionada, idFaldaPantalonSeleccionada);
        }catch(Exception e){
            System.out.println("Hay error un al realizar las validaciones");
            e.printStackTrace();
        }
    }
    
    private ArrayList<Componente> validarElColorDelTraje(int idBlusaSeleccionada, int idChaquetaSeleccionada, int idFaldaPantalonSeleccionada)throws Exception{
        
        Componente prenda1 = this.encontrarComponentePorId(idBlusaSeleccionada);
        Componente prenda2 = this.encontrarComponentePorId(idChaquetaSeleccionada);
        Componente prenda3 = this.encontrarComponentePorId(idFaldaPantalonSeleccionada);
        
        ArrayList<Componente> piezas = new ArrayList<>();
        piezas.add(prenda1);
        piezas.add(prenda2);
        piezas.add(prenda3);
        
        String color1 = prenda1.getColor();
        String color2 = prenda2.getColor();
        String color3 = prenda3.getColor();
        
        if(color1.equals(color2) && color2.equals(color3)){
            
            return piezas;
           
        } else {
            String inicialColor1 = color1.substring(0 , 1);
            String inicialColor2 = color2.substring(0 , 1);
            String inicialColor3 = color3.substring(0 , 1);
        
            if(inicialColor1.equals(inicialColor2) && inicialColor2.equals(inicialColor3)){
                return piezas;
            }else{
                throw new ColoresException();
            }
        }
    }
    
    private Componente encontrarComponentePorId(int id)throws Exception{
        for(Componente componenteRecorreLista : componentesAlmacen){
            if(componenteRecorreLista.getId() == id){
                return componenteRecorreLista;
            }
        }
        throw new Exception("Error al buscar el componente elegido");
    }
    
    private ArrayList<Componente> validarTallas(int idBlusaSeleccionada, int idChaquetaSeleccionada, int idFaldaPantalonSeleccionada)throws Exception{
        Componente prenda1 = this.encontrarComponentePorId(idBlusaSeleccionada);
        Componente prenda2 = this.encontrarComponentePorId(idChaquetaSeleccionada);
        Componente prenda3 = this.encontrarComponentePorId(idFaldaPantalonSeleccionada);
        
        ArrayList<Componente> piezas = new ArrayList<>();
        piezas.add(prenda1);
        piezas.add(prenda2);
        piezas.add(prenda3);
        
        String tallaBlusa = prenda1.getTalla();
        String tallaChaqueta = prenda2.getTalla();
        String tallaFaldaPantalon = prenda3.getTalla();
        
        if (prenda3 instanceof Falda){
            if(tallaBlusa.equals(tallaChaqueta)){
                return piezas;
            }else{
                throw new TallaException();
            }
        }
        
        if (prenda3 instanceof Pantalon){
            if(tallaBlusa.equals(tallaChaqueta) && tallaChaqueta.equals(tallaFaldaPantalon)){
                return piezas;
            }else{
                throw new TallaException();
            }
        }
        throw new Exception("La tercera prenda debe ser falda o pantalon.");
    }
    
    private void validarNombreDelTraje(String nombreTrajeCrear)throws Exception{
         for(Traje traje : trajesAlmacen){
            String nombre = traje.getNombre();
            if(nombreTrajeCrear.equals(nombre)){
                throw new TrajeYaExistenteException();
            }
         }
     }
    
     private void eliminarComponentesYaUsados(int idBlusaSeleccionada, int idChaquetaSeleccionada, int idFaldaPantalonSeleccionada)throws Exception{
        Componente prenda1 = this.encontrarComponentePorId(idBlusaSeleccionada);
        Componente prenda2 = this.encontrarComponentePorId(idChaquetaSeleccionada);
        Componente prenda3 = this.encontrarComponentePorId(idFaldaPantalonSeleccionada);
        
        componentesAlmacen.remove(prenda1);
        componentesAlmacen.remove(prenda2);
        componentesAlmacen.remove(prenda3);
        
     }

    @Override
    public void listarTrajes() {
        int contador = 0;
        for(Traje traje : trajesAlmacen){
            contador++;
            System.out.println("Fila " + contador + ": " + " " + traje.toString());
        }
        System.out.println("Total trajes: " + trajesAlmacen.size());

    }

    @Override
    public void activarDesactivarRebajas() {
         if(this.sonRebajas){
            for(Componente componenteRecorreLista : componentesAlmacen){
                double resultado = componenteRecorreLista.getPrecio() * 2;//le duplica el precio 
                componenteRecorreLista.setPrecio(resultado);
            } 
           this.sonRebajas = false;
        }else{
            for(Componente componenteRecorreLista : componentesAlmacen){
                double resultado = componenteRecorreLista.getPrecio() / 2; //le rebaja la mitad del precio
                componenteRecorreLista.setPrecio(resultado);
            }
             this.sonRebajas = true;
        }
    }

    @Override
    public void crearEnvío() throws Exception {
        Scanner sc = new Scanner(System.in); 
        int contador = 0;
        for(Traje trajeIterando : trajesAlmacen){
            contador++;
            System.out.println("Nombre del traje " + contador + ": " + " " + trajeIterando.getNombre());
        }
        System.out.println("Ingrese el nombre del traje que desea");
        System.out.print("----> " );
        String nombreTraje = sc.nextLine();
        this.eliminarTrajeAlmacen(nombreTraje);
    }
    
    private Traje encontrarTrajePorSuNombre(String nombreTraje)throws Exception{
        for(Traje trajeIterando : trajesAlmacen){
            if(trajeIterando.getNombre().equals(nombreTraje)){
                return trajeIterando;
            }
        }
        throw new Exception("Error al buscar el componente seleccioando");
    }
    
    private void eliminarTrajeAlmacen(String nombreTraje)throws Exception{
        Traje trajeEncontrado = this.encontrarTrajePorSuNombre(nombreTraje);
        trajesAlmacen.remove(trajeEncontrado);
        listadoDeEnvios.add(trajeEncontrado);
        System.out.println("Listado actualizado: " + listadoDeEnvios);
    }
 }

