package hup.plantalo;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.ByteArrayOutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import hup.plantalo.database.DatabaseOperations;

public class Main extends AppCompatActivity {

    Button botonUsuario;
    Button botonCliente;
    DatabaseOperations bd;
    ImageView imagenPrueba;
    ActionBar actionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_layout);

        actionBar = getSupportActionBar();
        actionBar.hide();

        imagenPrueba = (ImageView) findViewById(R.id.imagenprueba);
        Uri uri = Uri.parse("android.resource://"+this.getPackageName() + "/" + R.drawable.logoplantalo);
        imagenPrueba.setImageURI(uri);

        bd = new DatabaseOperations(getApplicationContext());
        if (!bd.dbExiste(bd)) {
            Bitmap bitmap = BitmapFactory.decodeResource(this.getResources(), R.drawable.tomates);
            Bitmap bitmap2 = BitmapFactory.decodeResource(this.getResources(), R.drawable.papas);
            //Bitmap bitmap3 = BitmapFactory.decodeResource(this.getResources(), R.drawable.lechuga);
            //Bitmap bitmap4 = BitmapFactory.decodeResource(this.getResources(), R.drawable.zanahoria);

            //imagenes cultivos
            String uri1 = "android.resource://"+this.getPackageName()+"/" + R.drawable.tomates;
            String uri2 = "android.resource://"+this.getPackageName()+"/" + R.drawable.papas;
            String uri3 = "android.resource://"+this.getPackageName()+"/" + R.drawable.lechuga;
            String uri4 = "android.resource://"+this.getPackageName()+"/" + R.drawable.zanahoria;
            //imagenes usuarios
            String imagen_elias = "android.resource://"+this.getPackageName()+"/" + R.drawable.elias;
            String imagen_ariel = "android.resource://"+this.getPackageName()+"/" + R.drawable.ariel;
            String imagen_roberto = "android.resource://"+this.getPackageName()+"/" + R.drawable.roberto;
            String imagen_nicolas = "android.resource://"+this.getPackageName()+"/" + R.drawable.nicolas;


            Log.d("Recursos", "La uri es: " + uri1);

            bd.agregarInformacionCultivo(bd, "Tomate", "Tomate rojo del sur de Chile", uri1);
            bd.agregarInformacionCultivo(bd, "Papa", "Papa orgánica del norte de Chile", uri2);
            bd.agregarInformacionCultivo(bd, "Lechuga", "Planta hortícola de hojas grandes, verdes, enteras o dentadas", uri3);
            bd.agregarInformacionCultivo(bd, "Zanahoria", "Es una hortaliza que pertenece a la familia de las umbelíferas, también denominadas apiáceas, y considerada la especie más importante y de mayor consumo dentro de esta familia", uri4);


            DateFormat date = new SimpleDateFormat("yyyyMMdd hhmmss");
            date.setLenient(false);
            Date today = new Date();
            String fechaHoy = date.format(today);
            bd.agregarComentarioTips(bd, "Ariel Meriño", "El tomate es un buen cultivo como para empezar. A mi me tiene muy satisfecho", fechaHoy, "c", "Tomate", imagen_ariel);
            bd.agregarComentarioTips(bd, "Elias Sobarzo", "La zanahoria es un buen cultivo como para empezar. A mi me tiene muy satisfecho", fechaHoy, "c", "Zanahoria", imagen_elias);
            bd.agregarComentarioTips(bd, "Roberto Sandoval", "La lechuga es un buen cultivo como para empezar. A mi me tiene muy satisfecho", fechaHoy, "c", "Lechuga", imagen_roberto);
            bd.agregarComentarioTips(bd, "Nicolas Uribe", "La papa es un buen cultivo como para empezar. A mi me tiene muy satisfecho", fechaHoy, "c", "Papa", imagen_nicolas);
            bd.agregarComentarioTips(bd, "Ariel Meriño", "La lechuga es un buen cultivo como para empezar. A mi me tiene muy satisfecho", fechaHoy, "t", "Lechuga", imagen_ariel);
            bd.agregarComentarioTips(bd, "Elias Sobarzo", "La papa es un buen cultivo como para empezar. A mi me tiene muy satisfecho", fechaHoy, "t", "Papa", imagen_elias);
            bd.agregarComentarioTips(bd, "Roberto Sandoval", "El tomate es un buen cultivo como para empezar. A mi me tiene muy satisfecho", fechaHoy, "c", "Tomate", imagen_roberto);
            bd.agregarComentarioTips(bd, "Nicolas Uribe", "El Zanahoria es un buen cultivo como para empezar. A mi me tiene muy satisfecho", fechaHoy, "t", "Zanahoria", imagen_nicolas);
            bd.agregarComentarioTips(bd, "Ariel Meriño", "El tomate es un buen cultivo como para empezar. A mi me tiene muy satisfecho", fechaHoy, "t", "Tomate", imagen_ariel);
            bd.agregarComentarioTips(bd, "Elias Sobarzo", "La zanahoria es un buen cultivo como para empezar. A mi me tiene muy satisfecho", fechaHoy, "c", "Zanahoria", imagen_elias);
            bd.agregarComentarioTips(bd, "Roberto Sandoval", "La lechuga es un buen cultivo como para empezar. A mi me tiene muy satisfecho", fechaHoy, "c", "Lechuga", imagen_roberto);
            bd.agregarComentarioTips(bd, "Nicolas Uribe", "La papa es un buen cultivo como para empezar. A mi me tiene muy satisfecho", fechaHoy, "c", "Papa", imagen_nicolas);
            bd.agregarComentarioTips(bd, "Ariel Meriño", "La lechuga es un buen cultivo como para empezar. A mi me tiene muy satisfecho", fechaHoy, "t", "Lechuga", imagen_ariel);
            bd.agregarComentarioTips(bd, "Elias Sobarzo", "La papa es un buen cultivo como para empezar. A mi me tiene muy satisfecho", fechaHoy, "t", "Papa", imagen_elias);
            bd.agregarComentarioTips(bd, "Roberto Sandoval", "El tomate es un buen cultivo como para empezar. A mi me tiene muy satisfecho", fechaHoy, "c", "Tomate", imagen_roberto);
            bd.agregarComentarioTips(bd, "Nicolas Uribe", "El Zanahoria es un buen cultivo como para empezar. A mi me tiene muy satisfecho", fechaHoy, "t", "Zanahoria", imagen_nicolas);
            bd.agregarComentarioTips(bd, "Ariel Meriño", "El tomate es un buen cultivo como para empezar. A mi me tiene muy satisfecho", fechaHoy, "c", "Tomate", imagen_ariel);
            bd.agregarComentarioTips(bd, "Elias Sobarzo", "La zanahoria es un buen cultivo como para empezar. A mi me tiene muy satisfecho", fechaHoy, "c", "Zanahoria", imagen_elias);
            bd.agregarComentarioTips(bd, "Roberto Sandoval", "La lechuga es un buen cultivo como para empezar. A mi me tiene muy satisfecho", fechaHoy, "c", "Lechuga", imagen_roberto);
            bd.agregarComentarioTips(bd, "Nicolas Uribe", "La papa es un buen cultivo como para empezar. A mi me tiene muy satisfecho", fechaHoy, "c", "Papa", imagen_nicolas);
            bd.agregarComentarioTips(bd, "Ariel Meriño", "La lechuga es un buen cultivo como para empezar. A mi me tiene muy satisfecho", fechaHoy, "t", "Lechuga", imagen_ariel);
            bd.agregarComentarioTips(bd, "Elias Sobarzo", "La papa es un buen cultivo como para empezar. A mi me tiene muy satisfecho", fechaHoy, "t", "Papa", imagen_elias);
            bd.agregarComentarioTips(bd, "Roberto Sandoval", "El tomate es un buen cultivo como para empezar. A mi me tiene muy satisfecho", fechaHoy, "t", "Tomate", imagen_roberto);
            bd.agregarComentarioTips(bd, "Nicolas Uribe", "El Zanahoria es un buen cultivo como para empezar. A mi me tiene muy satisfecho", fechaHoy, "t", "Zanahoria", imagen_nicolas);

            bd.agregarMiCultivo(bd, "Tomate");
            bd.agregarMiCultivo(bd, "Zanahoria");
            bd.agregarMiCultivo(bd, "Lechuga");

            bd.close();
        }


        botonUsuario = (Button) findViewById(R.id.modo_usuario);
        botonUsuario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Home.class);
                startActivity(intent);
            }
        });

        botonCliente = (Button) findViewById(R.id.modo_cliente);
        botonCliente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        //this.deleteDatabase("plantalo_db");
    }

    // convert from bitmap to byte array
    public static byte[] getBytes(Bitmap bitmap) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 0, stream);
        return stream.toByteArray();
    }

    // convert from byte array to bitmap
    public static Bitmap getImage(byte[] image) {
        return BitmapFactory.decodeByteArray(image, 0, image.length);
    }
}
