import java.util.*;
import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
public class Main{
    static Scanner scanner = new Scanner(System.in);
    static ArrayList<Gimnasta> Gimnastas = new ArrayList<Gimnasta>();
    static ArrayList<Entrenador> Entrenadores = new ArrayList<Entrenador>();
    static ArrayList<Torneo> Torneos = new ArrayList<Torneo>();
    static ArrayList<Participacion> Participaciones = new ArrayList<Participacion>();
    public static void main(String[] args){
        eject();
    }

    public static List<String> leerArchivo(String archivo) throws Exception {
    List<String> lista = new ArrayList<>();
    File file = new File(archivo);
    if (!file.exists()) {
        throw new FileNotFoundException("El archivo " + archivo + " no existe");
    }
    FileReader fileReader = new FileReader(archivo);
    BufferedReader bufferedReader = new BufferedReader(fileReader);

    String linea;
    while ((linea = bufferedReader.readLine())!= null) {
        lista.add(linea);
    }

    bufferedReader.close();
    fileReader.close();

    return lista;
    }

    public static String getNewNombreG(){
        String nombre = "";
        try{
            List<String> nombres = leerArchivo("nombres.txt");

            nombre = nombres.get((int)(Math.random() * nombres.size()));
        }catch (Exception e){
            e.printStackTrace(System.out);
        }
        return nombre;
    }

    public static String getNewApellido(){
        String apellido = "";
        try{
            List<String> apellidos = leerArchivo("apellidos.txt");

            apellido = apellidos.get((int)(Math.random() * apellidos.size()));
        }catch (Exception e){
            e.printStackTrace(System.out);
        }
        return apellido;
    }

    public static void generateGimnastas(){
        for(int i = 1; i <= 200; i++){
            String nombre = getNewNombreG();
            String apaterno = getNewApellido();
            String amaterno = getNewApellido();
            int edad = (int)(Math.random() * (30-12));
            float peso = (float)(Math.random() * (65-50));
            String ncontrol = "G-" + String.valueOf(i);
            Gimnastas.add(new Gimnasta(nombre,apaterno,amaterno, edad, peso, ncontrol));
        }
    }

    public static String getNewNombreE(){
        String nombre = "";
        try{
            List<String> nombres = leerArchivo("nombresE.txt");

            nombre = nombres.get((int)(Math.random() * nombres.size()));
        }catch (Exception e){
            e.printStackTrace(System.out);
        }
        return nombre;
    }

    public static void generateEntrenadores(){
        for(int i = 1; i <= 150; i++){
            String nombre = getNewNombreE();
            String apaterno = getNewApellido();
            String amaterno = getNewApellido();
            int edad = (int)(Math.random() * (30-12));
            String folio = "F-" + String.valueOf(i);
            Entrenadores.add(new Entrenador(nombre,apaterno,amaterno, edad, folio));
        }
    }

    public static String getNewCity(){
        String ciudad = "";
        try{
            List<String> ciudades = leerArchivo("ciudades.txt");

            ciudad = ciudades.get((int)(Math.random() * ciudades.size()));
        }catch (Exception e){
            e.printStackTrace(System.out);
        }
        return ciudad;
    }

    public static void generateTorneos(){
        for(int i = 1; i <= 20; i++){
            String codigotorneo = "T-" + String.valueOf(i);
            String nombre = "Torneo" + String.valueOf(i);
            String ciudad = getNewCity();
            int Dia = (int)(Math.random() * 31) + 1;
            int Mes = (int)(Math.random() * 12);
            int Anio = (int)(Math.random() * (2024-2000));
            Date fecha = new Date();
            fecha.setDate(Dia);
            fecha.setMonth(Mes);
            fecha.setYear(Anio);
            Torneos.add(new Torneo(codigotorneo, nombre, fecha, ciudad));
        }
    }

    public static void generateParticipaciones(){
        for(int i = 1; i <= 200; i++){
            String codigotorneo = Torneos.get((int)(Math.random()*Torneos.size())).getCodigotorneo();
            String folioentrenador = Entrenadores.get((int)(Math.random()*Entrenadores.size())).getFolioentrenador();
            String ncontrol = Gimnastas.get(i-1).getNcontrol();
            double calificacion = (double)(Math.random() * 10) + 1;

            Participaciones.add(new Participacion(codigotorneo, folioentrenador, ncontrol, calificacion));
        }

        for(Torneo T : Torneos){
            ArrayList<Double> califT = new ArrayList<Double>();
            for(Participacion part : Participaciones){
                if(T.getCodigotorneo().equals(part.getCodigotorneo())){
                    califT.add(part.getCalificacion());
                }
            }
            Comparator<Double> comparador = Collections.reverseOrder();
            Collections.sort(califT, comparador);
            int d = 0;
            for(double i: califT){
                d++;
                for(int c = 0; c <= Participaciones.size(); c++){
                    if(Participaciones.get(c).getCalificacion() == i){
                        Participaciones.get(c).setLugarobtenido(d);
                    }
                }
            }
        }
    }

    public static void eject(){
        generateGimnastas();
        generateEntrenadores();
        generateTorneos();
        generateParticipaciones();
        while(true){
            try{
                switch(opcion()){
                    case 1: listarGimnastasPorEntrenador();
                        break;
                    case 2: listarGimnastasPorTorneo();
                        break;
                    case 3: listarEntrenadoresPorTorneo();
                        break;
                    case 4: listarGimnastasPorCiudad();
                        break;
                    case 5: listarEntrenadoresPorCiudad();
                        break;
                    case 6: listarGimnastasPorFecha();
                        break;
                    case 7: listarGimnastasPrimerLugar();
                        break;
                    case 8: listarGimnastasSinTopTres();
                        break;
                    case 9: gimnastasConMasPrimerosLugares();
                        break;
                    case 10: ordenarGimnastasPorPeso();
                        break;
                    case 11: goout();
                    default: System.out.println("Ingrese un valor entre 1 y 11");
                }
            } catch (InputMismatchException e){
                System.out.println("Error: Debe ingresar un numero entero.");
                scanner.next();
            } catch (Exception e){
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    public static int opcion(){
        System.out.println("-----MENU-----");
        System.out.println("1. Listar Gimnastas por Entrenador");
        System.out.println("2. Gimnastas por Torneo Especifico");
        System.out.println("3. Entrenadores en Torneo Especifico");
        System.out.println("4. Gimnastas que visitaron Ciudad");
        System.out.println("5. Entrenadores que Visitaron Ciudad");
        System.out.println("6. Gimnastas que Compitieron en Fecha Específica");
        System.out.println("7. Gimnastas que Han Ganado Primer Lugar");
        System.out.println("8. Gimnastas que No Están en los Tres Primeros Lugares");
        System.out.println("9. Gimnastas con Más Primeros Lugares");
        System.out.println("10. Gimnastas Ordenadas por Peso");
        System.out.println("11. Salir");
        System.out.println("Escoga una opcion");
        int op;
        try{
            op = scanner.nextInt();
        } catch (InputMismatchException e){
            System.out.println("Error: Debe ingresar un numero entero.");
            scanner.next(); op = -1;
        }
        return op;
    }

    public static boolean out(String res){
        try{
            if(res.toUpperCase().equals("SI") && res.toLowerCase().equals("si")){
                return true;
            }
            return false;
        } catch (NullPointerException e){
            System.out.println("Error: No se ha ingresado ningun valor");
            return false;
        }
    }

    public static void goout(){
        System.out.println("¿Desea Salir? si/no");
        try{
            
        }catch (Exception e){
            System.out.println("Error: " + e.getMessage());
        }
    }

    public static void listarGimnastasPorEntrenador(){
        int p = 0;
        try{
            for(Entrenador entrenador: Entrenadores){
                p++;
                System.out.println(p + ". " + entrenador.toString());
            }
            System.out.println("Elija el numero de entrenador: ");
            int pe = scanner.nextInt();
            Entrenador trainer = Entrenadores.get(pe-1);
            for(Participacion part: Participaciones){
                if(trainer.getFolioentrenador().equals(part.getFolioentrenador())){
                    for(Gimnasta gim : Gimnastas){
                        if(part.getNcontrol().equals(gim.getNcontrol())){
                            System.out.println(gim.toString());
                        }
                    }
                }
            }
        } catch (InputMismatchException e) {
            System.out.println("Error: Debe ingresar un número entero.");
            scanner.next();
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public static void listarGimnastasPorTorneo(){
        int t = 0;
        try{
            if(Torneos.isEmpty()){
                throw new Exception("No hay torneos registrados");
            }
            for(Torneo torneo : Torneos){
                t++;
                System.out.println(t + ". " + torneo.toString());
            }
            System.out.println("Elija un torneo:");
            int to = scanner.nextInt();
            if(to < 1 || to > Torneos.size()){
                throw new IndexOutOfBoundsException("Indice fuera de rango");
            }
            
            Torneo torneo = Torneos.get(to-1);
            
            if(Participaciones.isEmpty()){
                throw new Exception("No hay participaciones registradas");
            }
            if(Gimnastas.isEmpty()){
                throw new Exception("No hay gimnastas registradas");
            }
            for(Participacion part : Participaciones){
                if(torneo.getCodigotorneo().equals(part.getCodigotorneo())){
                    for(Gimnasta gim : Gimnastas){
                        if(part.getNcontrol().equals(gim.getNcontrol())){
                            System.out.println(gim.toString());
                        }
                    }
                }
            }
        } catch (InputMismatchException e) {
            System.out.println("Error: Debe ingresar un número válido");
            scanner.next();
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Error: Índice fuera de rango");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public static void listarEntrenadoresPorTorneo(){
        int t = 0;
        try{
            if (Torneos.isEmpty()) {
                throw new Exception("No hay torneos registrados");
            }
            for(Torneo torneo : Torneos){
                t++;
                System.out.println(t + ". " + torneo.toString());
            }
            System.out.println("Elija un torneo:");
            int to = scanner.nextInt();
            if (to < 1 || to > Torneos.size()) {
                throw new IndexOutOfBoundsException("Indice fuera de rango");
            }
            Torneo torneo = Torneos.get(to-1);
    
            if (Participaciones.isEmpty()) {
                throw new Exception("No hay participaciones registradas");
            }
            if (Entrenadores.isEmpty()) {
                throw new Exception("No hay entrenadores registrados");
            }
            
            ArrayList<Entrenador> Entrenadors = new ArrayList<Entrenador>();
            for(Participacion part : Participaciones){
                if(torneo.getCodigotorneo().equals(part.getCodigotorneo())){
                    for(Entrenador trainer : Entrenadores){
                        if(part.getFolioentrenador().equals(trainer.getFolioentrenador())){
                            if(Entrenadors.isEmpty()){
                                Entrenadors.add(trainer);
                            }
                            else if(!Entrenadors.contains(trainer)){
                                Entrenadors.add(trainer);
                            }
                        }
                    }
                }
            }
        } catch (InputMismatchException e) {
            System.out.println("Error: Debe ingresar un número válido");
            scanner.next();
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Error: Índice fuera de rango");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public static void listarGimnastasPorCiudad(){
        ArrayList<String> ciudades = new ArrayList<String>();
        try{
            for(Torneo t : Torneos){
                if(ciudades.isEmpty()){
                    ciudades.add(t.getCiudad());
                }else if(!ciudades.contains(t.getCiudad())){
                    ciudades.add(t.getCiudad());
                }
            }
            int c = 0;
            for(String ciudad : ciudades){
                c++;
                System.out.println(c + ". " + ciudad);
            }
            System.out.println("Elija la Ciudad:");
            int ci = scanner.nextInt();
            String ciudad = ciudades.get(ci-1);
            if(ci < 1 || ci > ciudades.size()){
                throw new IndexOutOfBoundsException("Índice fuera de rango");
            }
            System.out.println("Gimnastas que estuvieron en " + ciudad + ":");
            for(Torneo t : Torneos){
                if(t.getCiudad().equals(ciudad)){
                    for(Participacion part : Participaciones){
                        if(part.getCodigotorneo().equals(t.getCodigotorneo())){
                            for(Gimnasta gim : Gimnastas){
                                if(part.getNcontrol().equals(gim.getNcontrol())){
                                    System.out.println(gim.toString());
                                }
                            }
                        }
                    }
                }
            }
        } catch (InputMismatchException e) {
            System.out.println("Error: Debe ingresar un número entero");
            scanner.next();
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Error: Índice fuera de rango");
        } catch (NullPointerException e) {
            System.out.println("Error: No hay torneos o gimnastas registrados");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public static void listarEntrenadoresPorCiudad(){
        ArrayList<String> ciudades = new ArrayList<String>();
        try{
            for(Torneo t : Torneos){
                if(ciudades.isEmpty()){
                    ciudades.add(t.getCiudad());
                }else if(!ciudades.contains(t.getCiudad())){
                    ciudades.add(t.getCiudad());
                }
            }
            int c = 0;
            for(String ciudad : ciudades){
                c++;
                System.out.println(c + ". " + ciudad);
            }
            System.out.println("Elija la Ciudad:");
            int ci = scanner.nextInt();
            if(ci < 1 || ci > ciudades.size()){
                throw new IndexOutOfBoundsException("Índice fuera de rango");
            }
            String ciudad = ciudades.get(ci-1);
            System.out.println("Gimnastas que estuvieron en " + ciudad + ":");
            for(Torneo t : Torneos){
                if(t.getCiudad().equals(ciudad)){
                    for(Participacion part : Participaciones){
                        if(part.getCodigotorneo().equals(t.getCodigotorneo())){
                            for(Entrenador trainer : Entrenadores){
                                if(part.getFolioentrenador().equals(trainer.getFolioentrenador())){
                                    System.out.println(trainer.toString());
                                }
                            }
                        }
                    }
                }
            }
        } catch (InputMismatchException e) {
            System.out.println("Error: Debe ingresar un número entero");
            scanner.next();
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Error: Índice fuera de rango");
        } catch (NullPointerException e) {
            System.out.println("Error: No hay torneos, participaciones o entrenadores registrados");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public static void listarGimnastasPorFecha(){
        ArrayList<LocalDate> Fechas = new ArrayList<LocalDate>();
        try{
            for(Torneo t : Torneos){
                if(Fechas.isEmpty()){
                    Fechas.add(t.getFecha());
                } else if(!Fechas.contains(t.getFecha())){
                    Fechas.add(t.getFecha());
                }
            }
            int F = 0;
            for(LocalDate fecha : Fechas){
                F++;
                System.out.println(F + ". " + fecha.format(DateTimeFormatter.ofPattern("dd-MM-yyyy")));
            }
            System.out.println("Elija la fecha:");
            int fe = scanner.nextInt();
            if(fe < 1 || fe > Fechas.size()){
                throw new IndexOutOfBoundsException("Índice fuera de rango");
            }
            LocalDate fecha = Fechas.get(fe-1);
            System.out.println("Gimnastas que participaron en la Fecha " + fecha.format(DateTimeFormatter.ofPattern("dd-MM-yyyy")));
            for(Torneo t : Torneos){
                if(t.getFecha().equals(fecha)){
                    for(Participacion part : Participaciones){
                        if(t.getCodigotorneo().equals(part.getCodigotorneo())){
                            for(Gimnasta gim : Gimnastas){
                                if(part.getNcontrol().equals(gim.getNcontrol())){
                                    System.out.println(gim.toString());
                                }
                            }
                        }
                    }
                }
            }
        } catch (InputMismatchException e) {
            System.out.println("Error: Debe ingresar un número entero");
            scanner.next();
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Error: Índice fuera de rango");
        } catch (NullPointerException e) {
            System.out.println("Error: No hay torneos, participaciones o gimnastas registrados");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public static void listarGimnastasPrimerLugar(){
        try{
            for(Participacion part : Participaciones){
                if(part.getLugarobtenido()==1){
                    for(Gimnasta gim : Gimnastas){
                        if(part.getNcontrol().equals(gim.getNcontrol())){
                            System.out.print(gim.toString() + " obtuvo el " + part.getLugarobtenido() + " ");
                            for(Torneo T : Torneos){
                                if(part.getCodigotorneo().equals(T.getCodigotorneo())){
                                    System.out.print("en el " + T.toString());
                                }
                            }
                        }
                    }
                    System.out.println("");
                }
            }
        }  catch (NullPointerException e) {
            System.out.println("Error: No hay participaciones, gimnastas o torneos registrados");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public static void listarGimnastasSinTopTres(){
        try{
            for(Participacion part : Participaciones){
                if(part.getLugarobtenido() !=0 && part.getLugarobtenido() > 3){
                    for(Gimnasta gim : Gimnastas){
                        if(part.getNcontrol().equals(gim.getNcontrol())){
                            System.out.print(gim.toString() + " solo obtuvo el " + part.getLugarobtenido() + " ");
                            for(Torneo T: Torneos){
                                if(part.getCodigotorneo().equals(T.getCodigotorneo())){
                                    System.out.print("en el " + T.toString());
                                }
                            }
                        }
                    }
                    System.out.println("");
                }
            }
        } catch (NullPointerException e) {
            System.out.println("Error: No hay participaciones, gimnastas o torneos registrados");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public static void gimnastasConMasPrimerosLugares(){
        try{
            for(Gimnasta gim : Gimnastas){
                int pl = 0;
                for(Participacion part : Participaciones){
                    if(gim.getNcontrol().equals(part.getNcontrol()) && part.getLugarobtenido() == 1){
                        pl++;
                    }
                }
                System.out.println("La gimnasta " + gim.toString() + " obtuvo " + pl + " veces el primer lugar");
            }
        } catch (NullPointerException e) {
            System.out.println("Error: No hay gimnastas o participaciones registradas");
        } catch (Exception e) {
            System.out.println("Error desconocido: " + e.getMessage());
        }
    }

    public static void ordenarGimnastasPorPeso(){
        try{
            System.out.println("Ordenar de (1)Mayor a menor peso o (2)Menor a mayor peso ?");
            int or = scanner.nextInt();
            ArrayList<Float> pesos = new ArrayList<Float>();
    
            for(Gimnasta gim : Gimnastas){
                pesos.add(gim.getPeso());
            }
            if(or==1){
                Collections.sort(pesos, Collections.reverseOrder());
                int l = 0;
                for(float peso : pesos){
                    l++;
                    for(Gimnasta gim : Gimnastas){
                        if(gim.getPeso() == peso){
                            System.out.println(l + ". " + gim.toString());
                        }
                    }
                }
            }else if(or==2){
                Collections.sort(pesos);
                int l = 0;
                for(float peso : pesos){
                    l++;
                    for(Gimnasta gim : Gimnastas){
                        if(gim.getPeso() == peso){
                            System.out.println(l + ". " + gim.toString());
                        }
                    }
                }
            } else {
                throw new InputMismatchException("Opción inválida. Debe ser 1 o 2.");
            }
        } catch (InputMismatchException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (NullPointerException e) {
            System.out.println("Error: No hay gimnastas registrados.");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}