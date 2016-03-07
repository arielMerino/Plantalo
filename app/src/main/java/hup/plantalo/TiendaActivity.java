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
    String direccionesT[] = {"La cisterna no tengo idea el numero"};
    String descripcionesT[] = {"Tienda de productos de jardinería, arbustos, árboles, flores y materiales de cultivo"};
    String telefonosT[] = {"75298657"};
    String correosT[] = {"jardin.flaminia@correo.cl"};
    int imagenesT[] = {R.drawable.jardin_flaminia};
    double valoracionesT[] = {5.9};
    String comunasT[] = {"La cisterna"};

    /*Datos de todos los productos registrados*/
    String nombresP[] = {"Pala","Macetero Redondo", "Carpa Indoor", "Tierra de hoja", "Semillas de tomate", "Semillas de tomate cherry"};
    String descripcionesP[] = {
            "esta es una descripción de ejemplo para la pala, nada de lo que salga aquí existe realmente, please no pesquen",
            "esta es una descripción de ejemplo para el macetero redondo, nada de lo que salga aquí existe realmente, please no pesquen",
            "esta es una descripción de ejemplo para la carpa indoor, nada de lo que salga aquí existe realmente, please no pesquen",
            "esta es una descripción de ejemplo para la tierra de hoja, nada de lo que salga aquí existe realmente, please no pesquen",
            "esta es una descripción de ejemplo para las semillas de tomate, nada de lo que salga aquí existe realmente, please no pesquen",
            "esta es una descripción de ejemplo para las semillas de tomate cherry, nada de lo que salga aquí existe realmente, please no pesquen"};
    int imagenesP[] = {R.drawable.pala,R.drawable.macetero_redondo,R.drawable.carpa_indoor,R.drawable.tierra_hoja,R.drawable.semillas_tomate,R.drawable.semillas_tomate_cherry};
    String tiendasP[] = {"","jardin flaminia","","","jardin flaminia","jardin flaminia"};
    float valoracionesP[] = {(float)2.0, (float)5.9, (float)3.6, (float)7.0, (float)6.5, (float)9.2};
    //String valoracionesP[] = {"2.0", "5.9", "3.6", "7.0", "6.5", "9.2"};
    int preciosP[] = {800, 2000, 25000, 300, 1200, 1850};

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
                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.contenedor_tienda, mapaTiendaFragment).commit();
            }
        });

        /*
        TextView descripcion = (TextView) findViewById(R.id.);

        nombreTienda = (TextView) findViewById(R.id.nombre_tienda_detalle);
        valoracionTienda = (TextView) findViewById((R.id.valoracion_tienda_detalle));
        imagenTienda = (ImageView) findViewById(R.id.imagen_tienda_detalle);
        descripcionTienda = (TextView) findViewById(R.id.descripcion_tienda_detalle);
        correoTienda = (TextView) findViewById(R.id.correo_tienda_detalle);
        fonoTienda = (TextView) findViewById(R.id.fono_tienda_detalle);
        direccionTienda = (TextView) findViewById(R.id.direccion_tienda_detalle);
        listaProductos = (ListView) findViewById(R.id.lista_productos_por_tienda);
        scroll = (ScrollView) findViewById(R.id.scroll_tienda);

        for (int i = 0; i < nombresT.length; i++) {
            if (nombresT[i].toLowerCase().equalsIgnoreCase(tienda.toLowerCase())) {
                t = i;
                break;
            }
        }

        nombreTienda.setText(nombresT[t].toUpperCase());
        valoracionTienda.setText(String.valueOf(valoracionesT[t]));
        imagenTienda.setImageResource(imagenesT[t]);
        descripcionTienda.setText(descripcionesT[t]);
        correoTienda.setText(correosT[t]);
        direccionTienda.setText(direccionesT[t]);
        fonoTienda.setText("(+56) 9 " + telefonosT[t]);

        int contador = 0;
        for (int i = 0; i < nombresP.length; i++) {
            if (tiendasP[i].toLowerCase().equalsIgnoreCase(tienda.toLowerCase())) {
                productos.add(new ProductoClass(imagenesP[i], nombresP[i], tiendasP[i], descripcionesP[i], preciosP[i], valoracionesP[i]));
                contador++;
            }
        }

        Log.i("informacion", "hay " + contador + " coincidencias en productos");

        listaProductos.setOnTouchListener(new ListView.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int action = event.getAction();
                switch (action) {
                    case MotionEvent.ACTION_DOWN:
                        // Disallow ScrollView to intercept touch events.
                        v.getParent().requestDisallowInterceptTouchEvent(true);
                        break;

                    case MotionEvent.ACTION_UP:
                        // Allow ScrollView to intercept touch events.
                        v.getParent().requestDisallowInterceptTouchEvent(false);
                        break;
                }

                // Handle ListView touch events.
                v.onTouchEvent(event);
                return true;
            }
        });



        MyAdapter adapter = new MyAdapter(this,productos);
        listaProductos.setAdapter(adapter);

        justifyListView(listaProductos);
        scroll.scrollTo(0, scroll.getTop());
    */
    }

    private class MyAdapter extends ArrayAdapter<ProductoClass> {

        private final Activity context;
        private ArrayList<ProductoClass> filas;

        public MyAdapter(Activity context, ArrayList<ProductoClass> filas) {
            super(context, R.layout.casilla_productos, filas);
            this.context = context;
            this.filas = filas;
        }
        public View getView(int position,View view,ViewGroup parent) {
            LayoutInflater inflater=context.getLayoutInflater();
            View rowView = inflater.inflate(R.layout.casilla_productos, null, true);

            TextView nombre = (TextView) rowView.findViewById(R.id.nombre_producto);
            TextView descripcion = (TextView) rowView.findViewById(R.id.descripcion_producto);
            TextView valoracion = (TextView) rowView.findViewById(R.id.valoracion_producto);
            TextView precio = (TextView) rowView.findViewById(R.id.precio_producto);
            ImageView imagen = (ImageView) rowView.findViewById(R.id.imagen_producto);

            String p = "$ " + String.valueOf(filas.get(position).getPrecio()) + ".-";

            nombre.setText(filas.get(position).getNombre());
            descripcion.setText(filas.get(position).getDescripcion());
            valoracion.setText(String.valueOf(filas.get(position).getValoracion()));
            precio.setText(p);
            imagen.setImageResource(filas.get(position).getImagen());
            return rowView;
        }
    }

    public void justifyListView (ListView listView) {

        ListAdapter adapter = listView.getAdapter();

        if (adapter == null) {
            return;
        }
        ViewGroup vg = listView;
        int totalHeight = 0;
        for (int i = 0; i < adapter.getCount(); i++) {
            View listItem = adapter.getView(i, null, vg);
            listItem.measure(0, 0);
            totalHeight += listItem.getMeasuredHeight();
        }

        ViewGroup.LayoutParams par = listView.getLayoutParams();
        par.height = totalHeight + (listView.getDividerHeight() * (adapter.getCount() - 1));
        listView.setLayoutParams(par);
        listView.requestLayout();
    }
}
