import motivoTipo;

public class motivoFueraServicio{
    private String comentario;
    private motivoTipo motivoTipo;

    public motivoFueraServicio(String comentario, motivoTipo motivoTipo) {
        this.comentario = comentario;
        this.motivoTipo = motivoTipo;
    }
    public String getComentario() {
        return comentario;
    }
    public void setComentario(String comentario) {
        this.comentario = comentario;
    }
    public motivoTipo getMotivoTipo() {
        return motivoTipo;
    }
    public void setMotivoTipo(motivoTipo motivoTipo) {
        this.motivoTipo = motivoTipo;
    }
    
}