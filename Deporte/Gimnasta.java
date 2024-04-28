public class Gimnasta extends Persona{
    private float peso;
    private String ncontrol;
    
    public Gimnasta(String nombre, String Apaterno, String Amaterno, int Edad, float peso, String ncontrol) throws NullPointerException, IllegalArgumentException {
        super(nombre, Apaterno, Amaterno, Edad);
        if (ncontrol == null) {
            throw new NullPointerException("El número de control no puede ser nulo");
        }
        if (ncontrol.trim().isEmpty()) {
            throw new IllegalArgumentException("El número de control no puede estar vacío");
        }
        if (peso <= 0) {
            throw new IllegalArgumentException("El peso debe ser mayor que cero");
        }
        this.peso = peso;
        this.ncontrol = ncontrol;
    }
    
    public void setPeso(float peso) throws IllegalArgumentException {
        if (peso <= 0) {
            throw new IllegalArgumentException("El peso debe ser mayor que cero");
        }
        this.peso = peso;
    }
    public float getPeso(){
        return peso;
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
    
    public String toString(){
        return nombre + " " + Apaterno + " " + Amaterno + ", edad: " + Edad + ", peso: " + peso + ", ncontrol: " + ncontrol;
    }
}