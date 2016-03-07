package hup.plantalo;

/**
 * Created by eliasPlease on 03-03-2016.
 */
public class ListViewComentario {
    private String autor;
    private String comentario;
    private String fecha;
    private String cultivo;
    private String imagenAutor;
    private String tipo;

    public ListViewComentario(String autor, String comentario, String fecha, String cultivo, String imagenAutor, String tipo){
        this.autor = autor;
        this.comentario = comentario;
        this.fecha = fecha;
        this.cultivo = cultivo;
        this.imagenAutor = imagenAutor;
        this.tipo = tipo;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getImagenAutor() {
        return imagenAutor;
    }

    public void setImagenAutor(String imagenAutor) {
        this.imagenAutor = imagenAutor;
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
