import java.time.LocalDate;
import java.util.Date;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class Torneo{
    private String codigotorneo;
    private String nombre;
    private LocalDate fecha;
    private String ciudad;
    
    public Torneo(String codigotorneo, String nombre, Date fech, String ciudad) throws NullPointerException, IllegalArgumentException {
        if (codigotorneo == null || nombre == null || ciudad == null) {
            throw new NullPointerException("Los campos código de torneo, nombre y ciudad no pueden ser nulos");
        }
        if (codigotorneo.trim().isEmpty() || nombre.trim().isEmpty() || ciudad.trim().isEmpty()) {
            throw new IllegalArgumentException("Los campos código de torneo, nombre y ciudad no pueden estar vacíos");
        }
        if (fech == null) {
            throw new NullPointerException("La fecha no puede ser nula");
        }
        this.codigotorneo = codigotorneo;
        this.nombre = nombre;
        fecha = fech.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        this.ciudad = ciudad;
    }
    
    public void setCodigotorneo(String codigotorneo) throws NullPointerException, IllegalArgumentException {
        if (codigotorneo == null) {
            throw new NullPointerException("El código de torneo no puede ser nulo");
        }
        if (codigotorneo.trim().isEmpty()) {
            throw new IllegalArgumentException("El código de torneo no puede estar vacío");
        }
        this.codigotorneo = codigotorneo;
    }
    public String getCodigotorneo(){
        return codigotorneo;
    }
    
    public void setNombre(String nombre) throws NullPointerException, IllegalArgumentException {
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
    
    public void setFecha(Date fech) throws NullPointerException {
        if (fech == null) {
            throw new NullPointerException("La fecha no puede ser nula");
        }
        fecha = fech.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }
    public LocalDate getFecha(){
        return fecha;
    }
    public String getFechaString(){
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        String fechaformateada = fecha.format(formato);
        return fechaformateada;
    }
    
    public void setCiudad(String ciudad) throws NullPointerException, IllegalArgumentException {
        if (ciudad == null) {
            throw new NullPointerException("La ciudad no puede ser nula");
        }
        if (ciudad.trim().isEmpty()) {
            throw new IllegalArgumentException("La ciudad no puede estar vacía");
        }
        this.ciudad = ciudad;
    }
    public String getCiudad(){
        return ciudad;
    }
    
    public String toString(){
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        String fechaformateada = fecha.format(formato);
        return "Torneo " + nombre + ", ciudad: " + ciudad + " con fecha " + fechaformateada; 
    }
}