package hup.plantalo;

import android.app.Activity;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.speech.RecognizerIntent;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;

import java.util.ArrayList;

public class TiendaActivity extends AppCompatActivity {

    TextView nombreTienda;
    TextView valoracionTienda;
    ImageView imagenTienda;
    TextView descripcionTienda;
    TextView correoTienda;
    TextView fonoTienda;
    TextView direccionTienda;
    ListView listaProductos;
    ArrayList<ProductoClass> productos;
    ScrollView scroll;

    TextView descripcion;

    /*Datos de las tiendas que estan registradas*/
    String nombresT[] = {"Jardin Flaminia"};
    double valoracionesT[] = {5.9};
    String posiciones[] = {"-33.5287469;-70.6664111"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tienda);
        Intent intent = getIntent();
        productos = new ArrayList<>();

        int t = -1;

        descripcion = (TextView) findViewById(R.id.descripcion_tienda);
        final TextView productos = (TextView) findViewById(R.id.productos_de_la_tienda);
        final TextView mapa = (TextView) findViewById(R.id.mapa_tienda);
        TextView nombreTienda = (TextView) findViewById(R.id.nombre_tienda_detalle);
        TextView valoracionTienda = (TextView) findViewById(R.id.valoracion_tienda_detalle);

        final String tienda = intent.getStringExtra("tienda");
        int contador = -1;

        for(int i = 0; i < nombresT.length; i++) {
            if (nombresT[i].toLowerCase().equalsIgnoreCase(tienda.toLowerCase())) {
                contador = i;
            }
        }

        valoracionTienda.setText(String.valueOf(valoracionesT[contador]).substring(0,3));
        nombreTienda.setText(nombresT[contador].toUpperCase());


        Log.i("informacion", "nombre de la tienda: " + tienda);

        final ColorStateList original = descripcion.getTextColors();

        descripcion.setTextColor(Color.parseColor("#058e05"));

        TiendaFragment tiendaFragment = new TiendaFragment();
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        Bundle args = new Bundle();
        args.putString("tienda",tienda);
        tiendaFragment.setArguments(args);
        ft.replace(R.id.contenedor_tienda, tiendaFragment).commit();

        descripcion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                descripcion.setTextColor(Color.parseColor("#058e05"));
                productos.setTextColor(original);
                mapa.setTextColor(original);
                TiendaFragment tiendaFragment = new TiendaFragment();
                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                Bundle args = new Bundle();
                args.putString("tienda", tienda);
                tiendaFragment.setArguments(args);
                ft.replace(R.id.contenedor_tienda, tiendaFragment).commit();
            }
        });
        productos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                descripcion.setTextColor(original);
                productos.setTextColor(Color.parseColor("#058e05"));
                mapa.setTextColor(original);
                ProductosTiendaFragment productosTiendaFragment = new ProductosTiendaFragment();
                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                Bundle args = new Bundle();
                args.putString("tienda", tienda);
                productosTiendaFragment.setArguments(args);
                ft.replace(R.id.contenedor_tienda, productosTiendaFragment).commit();
            }
        });
        mapa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                descripcion.setTextColor(original);
                productos.setTextColor(original);
                mapa.setTextColor(Color.parseColor("#058e05"));
                MapaTiendaFragment mapaTiendaFragment = new MapaTiendaFragment();
                Bundle args = new Bundle();
                args.putString("tienda",tienda);
                mapaTiendaFragment.setArguments(args);
                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.contenedor_tienda, mapaTiendaFragment).commit();
            }
        });
    }
}
