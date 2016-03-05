package hup.plantalo;

/**
 * Created by Ariel on 04-03-2016.
 */
public class ProductoClass {
    private int imagen;
    private String nombre;
    private String tienda;
    private String descripcion;
    private int precio;
    private float valoracion;

    /**Constructores**/
    public ProductoClass(){
        this.imagen = -1;
        this.nombre = "";
        this.tienda = "";
        this.descripcion = "";
        this.precio = -1;
        this.valoracion = 0;
    }

    public ProductoClass(int imagen, String nombre, String tienda, String descripcion, int precio, float valoracion){
        this.imagen = imagen;
        this.nombre = nombre;
        this.tienda = tienda;
        this.descripcion = descripcion;
        this.precio = precio;
        this.valoracion = valoracion;
    }
    /**Setters y Getters**/

    public void setImagen(int direccion){
        this.imagen = direccion;
    }
    public void setNombre(String nombre){
        this.nombre = nombre;
    }
    public void setTienda(String tienda){
        this.tienda = tienda;
    }
    public void setDescripcion(String descripcion){
        this.descripcion = descripcion;
    }
    public void setPrecio(int precio){
        this.precio = precio;
    }
    public void setValoracion(float valoracion) {
        this.valoracion = valoracion;
    }
    public int getImagen(){
        return this.imagen;
    }
    public String getNombre(){
        return this.nombre;
    }
    public String getTienda(){
        return this.tienda;
    }
    public String getDescripcion(){
        return this.descripcion;
    }
    public int getPrecio(){
        return this.precio;
    }
    public float getValoracion(){
        return this.valoracion;
    }
}
