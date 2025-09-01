package Model;

public class Usuario {
    private String nombre;
    private String id;
    
    public Usuario(String nombre, String id) {
        this.nombre = nombre;
        this.id = id;
    }
    
    // Getters y setters
    public String getNombre() {
        return nombre;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    public String getId() {
        return id;
    }
    
    public void setId(String id) {
        this.id = id;
    }
    
    @Override
    public String toString() {
        return "Usuario{nombre='" + nombre + "', id='" + id + "'}";
    }
}
