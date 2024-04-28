public class Participacion{
    private String codigotorneo;
    private String folioentrenador;
    private String ncontrol;
    private int lugarobtenido;
    private double calif;
    
    public Participacion(String codigotorneo, String folioentrenador, String ncontrol, double calif) throws NullPointerException, IllegalArgumentException {
        if (codigotorneo == null || folioentrenador == null || ncontrol == null) {
            throw new NullPointerException("Los campos código de torneo, folio de entrenador y número de control no pueden ser nulos");
        }
        if (codigotorneo.trim().isEmpty() || folioentrenador.trim().isEmpty() || ncontrol.trim().isEmpty()) {
            throw new IllegalArgumentException("Los campos código de torneo, folio de entrenador y número de control no pueden estar vacíos");
        }
        if (calif < 0) {
            throw new IllegalArgumentException("La calificación no puede ser negativa");
        }
        this.codigotorneo = codigotorneo;
        this.folioentrenador = folioentrenador;
        this.ncontrol = ncontrol;
        lugarobtenido = 0;
        this.calif = calif;
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
    
    public void setFolioentrenador(String folioentrenador) throws NullPointerException, IllegalArgumentException {
        if (folioentrenador == null) {
            throw new NullPointerException("El folio de entrenador no puede ser nulo");
        }
        if (folioentrenador.trim().isEmpty()) {
            throw new IllegalArgumentException("El folio de entrenador no puede estar vacío");
        }
        this.folioentrenador = folioentrenador;
    }
    public String getFolioentrenador(){
        return folioentrenador;
    }
    
    public void setNcontrol(String ncontrol) throws NullPointerException, IllegalArgumentException {
        if (ncontrol == null) {
            throw new NullPointerException("El número de control no puede ser nulo");
        }
        if (ncontrol.trim().isEmpty()) {
            throw new IllegalArgumentException("El número de control no puede estar vacío");
        }
        this.ncontrol = ncontrol;
    }
    public String getNcontrol(){
        return ncontrol;
    }
    
    public void setLugarobtenido(int lugarobtenido) throws IllegalArgumentException {
        if (lugarobtenido < 0) {
            throw new IllegalArgumentException("El lugar obtenido no puede ser negativo");
        }
        this.lugarobtenido = lugarobtenido;
    }
    public int getLugarobtenido(){
        return lugarobtenido;
    }
    
    public void setCalificacion(double calif) throws IllegalArgumentException {
        if (calif < 0) {
            throw new IllegalArgumentException("La calificación no puede ser negativa");
        }
        this.calif = calif;
    }
    public double getCalificacion(){
        return calif;
    }
}