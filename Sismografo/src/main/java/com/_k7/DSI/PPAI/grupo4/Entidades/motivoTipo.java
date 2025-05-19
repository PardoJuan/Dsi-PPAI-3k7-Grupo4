@Entity
public class motivoTipo {
    private String descripcion; 

    public setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    public String getDescripcion() {
        return descripcion;
    }
    public motivoTipo(String descripcion) {
        this.descripcion = descripcion;
    }
}