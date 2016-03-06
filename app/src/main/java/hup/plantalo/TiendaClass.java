package hup.plantalo;

/**
 * Created by ARIEL on 05-03-2016.
 */
public class TiendaClass {
    private String nombre;
    private String direccion;
    private String descripcion;
    private int telefono;
    private String correo;
    private int imagen;
    private double valoracion;
    private String comuna;

    public TiendaClass(){
        this.nombre = "";
        this.direccion = "";
        this.descripcion = "";
        this.telefono = -1;
        this.correo = "";
        this.imagen = -1;
        this.valoracion = 0.0;
        this.comuna = "";
    }
    /**@param nombre nombre de la tienda
     * @param direccion direccion de la tienda
     * @param descripcion descripcion de la tienda
     * @param telefono
     * @param correo
     * @param imagen
     * @param valoracion
     * @param comuna
     */
    public TiendaClass(String nombre, String direccion, String descripcion, int telefono, String correo, int imagen, double valoracion, String comuna) {
        this.nombre = nombre;
        this.direccion = direccion;
        this.descripcion = descripcion;
        this.telefono = telefono;
        this.correo = correo;
        this.imagen = imagen;
        this.valoracion = valoracion;
        this.comuna = comuna;
    }

    public String getComuna() {
        return comuna;
    }
    public void setComuna(String comuna) {
        this.comuna = comuna;
    }
    public double getValoracion() {
        return valoracion;
    }
    public void setValoracion(double valoracion) {
        this.valoracion = valoracion;
    }
    public int getImagen() {
        return imagen;
    }
    public void setImagen(int imagen) {
        this.imagen = imagen;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getDireccion() {
        return direccion;
    }
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
    public String getDescripcion() {
        return descripcion;
    }
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    public int getTelefono() {
        return telefono;
    }
    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }
    public String getCorreo() {
        return correo;
    }
    public void setCorreo(String correo) {
        this.correo = correo;
    }
}
