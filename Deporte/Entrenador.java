public class Entrenador extends Persona{
    private String folioentrenador;
    
    public Entrenador(String nombre, String Apaterno, String Amaterno, int Edad, String folioentrenador) throws NullPointerException, IllegalArgumentException {
        super(nombre, Apaterno, Amaterno, Edad);
        if (folioentrenador == null) {
            throw new NullPointerException("El folio del entrenador no puede ser nulo");
        }
        if (folioentrenador.trim().isEmpty()) {
            throw new IllegalArgumentException("El folio del entrenador no puede estar vacío");
        }
        this.folioentrenador = folioentrenador;
    }
    
    public void setFolioentrenador(String folioentrenador) throws NullPointerException, IllegalArgumentException {
        if (folioentrenador == null) {
            throw new NullPointerException("El folio del entrenador no puede ser nulo");
        }
        if (folioentrenador.trim().isEmpty()) {
            throw new IllegalArgumentException("El folio del entrenador no puede estar vacío");
        }
        this.folioentrenador = folioentrenador;
    }
    public String getFolioentrenador(){
        return folioentrenador;
    }
    
    public String toString(){
        return "Entrenador " + nombre + " " + Apaterno + " " + Amaterno + ", de " + Edad + " años. Folio: " + folioentrenador;
    }
}