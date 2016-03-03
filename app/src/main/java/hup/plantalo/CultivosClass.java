package hup.plantalo;

import android.graphics.Bitmap;
import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by eliasPlease on 02-03-2016.
 */
public class CultivosClass implements Parcelable {
    private String nombre;
    private String descripción;
    private Bitmap imagen;

    public CultivosClass(String nombre, String descripcion, Bitmap imagen){
        this.nombre = nombre;
        this.descripción = descripcion;
        this.imagen = imagen;
    }

    protected CultivosClass(Parcel in) {
        nombre = in.readString();
        descripción = in.readString();
        imagen = in.readParcelable(Bitmap.class.getClassLoader());
    }

    public static final Creator<CultivosClass> CREATOR = new Creator<CultivosClass>() {
        @Override
        public CultivosClass createFromParcel(Parcel in) {

            return new CultivosClass(in);
        }

        @Override
        public CultivosClass[] newArray(int size) {

            return new CultivosClass[size];
        }
    };

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripción() {
        return descripción;
    }

    public void setDescripción(String descripción) {
        this.descripción = descripción;
    }

    public Bitmap getImagen() {
        return imagen;
    }

    public void setImagen(Bitmap imagen) {
        this.imagen = imagen;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(nombre);
        dest.writeString(descripción);
        dest.writeParcelable(imagen, flags);
    }
}
