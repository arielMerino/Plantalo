package hup.plantalo;

/**
 * Created by eliasPlease on 03-03-2016.
 */
public class ListViewComentario {
    private String autor;
    private String comentario;
    private String fecha;
    private String cultivo;

    public ListViewComentario(String autor, String comentario, String fecha, String cultivo){
        this.autor = autor;
        this.comentario = comentario;
        this.fecha = fecha;
        this.cultivo = cultivo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getCultivo() {
        return cultivo;
    }

    public void setCultivo(String cultivo) {
        this.cultivo = cultivo;
    }
}
