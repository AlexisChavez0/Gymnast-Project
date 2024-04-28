public class Persona{
    public String nombre;
    public String Apaterno;
    public String Amaterno;
    public int Edad;
    
    public Persona(String nombre, String Apaterno, String Amaterno, int Edad) throws NullPointerException, IllegalArgumentException{
        if (nombre == null || Apaterno == null || Amaterno == null) {
            throw new NullPointerException("Los nombres no pueden ser nulos");
        }
        if (nombre.trim().isEmpty() || Apaterno.trim().isEmpty() || Amaterno.trim().isEmpty()) {
            throw new IllegalArgumentException("Los nombres no pueden estar vacíos");
        }
        if (Edad < 0) {
            throw new IllegalArgumentException("La edad no puede ser negativa");
        }
        this.nombre = nombre;
        this.Apaterno = Apaterno;
        this.Amaterno = Amaterno;
        this.Edad = Edad;
    }
    
    public void setNombre(String nombre) throws NullPointerException, IllegalArgumentException{
        if (nombre == null) {
            throw new NullPointerException("El nombre no puede ser nulo");
        }
        if (nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre no puede estar vacío");
        }
        this.nombre = nombre;
    }
    public String getNombre(){
        return nombre;
    }
    
    public void setApaterno(String Apaterno) throws NullPointerException, IllegalArgumentException{
        if (Apaterno == null) {
            throw new NullPointerException("El apellido paterno no puede ser nulo");
        }
        if (Apaterno.trim().isEmpty()) {
            throw new IllegalArgumentException("El apellido paterno no puede estar vacío");
        }
        this.Apaterno = Apaterno;
    }
    public String getApaterno(){
        return Apaterno;
    }
    
    public void setAmaterno(String Amaterno) throws NullPointerException, IllegalArgumentException{
        if (Amaterno == null) {
            throw new NullPointerException("El apellido materno no puede ser nulo");
        }
        if (Amaterno.trim().isEmpty()) {
            throw new IllegalArgumentException("El apellido materno no puede estar vacío");
        }
        this.Amaterno = Amaterno;
    }
    public String getAmaterno(){
        return Amaterno;
    }
    
    public void setEdad(int Edad) throws IllegalArgumentException{
        if (Edad < 0) {
            throw new IllegalArgumentException("La edad no puede ser negativa");
        }
        this.Edad = Edad;
    }
    public int getEdad(){
        return Edad;
    }
}