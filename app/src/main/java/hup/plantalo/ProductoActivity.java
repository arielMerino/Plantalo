package hup.plantalo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class ProductoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_producto);

        TextView nombre = (TextView) findViewById(R.id.nombre_producto_detalle);
        TextView descripcion = (TextView) findViewById(R.id.descripcion_producto_detalle);
        ImageView imagen = (ImageView) findViewById(R.id.imagen_producto_detalle);
        TextView tienda = (TextView) findViewById(R.id.tienda_producto_detalle);
        TextView valoracion = (TextView) findViewById(R.id.valoracion_producto_detalle);
        TextView precio = (TextView) findViewById(R.id.precio_producto_detalle);

        Intent intent = getIntent();
        String posicion = intent.getStringExtra("posicion");

        final ProductoClass producto = new ProductoClass(posicion);

        nombre.setText(producto.getNombre());
        descripcion.setText(producto.getDescripcion());
        imagen.setImageResource(producto.getImagen());
        tienda.setText(producto.getTienda());
        valoracion.setText(String.valueOf(producto.getValoracion()).substring(0, 3));
        precio.setText("$  " + producto.getPrecio() + ".-");

        tienda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),TiendaActivity.class);
                intent.putExtra("tienda",producto.getTienda());
                startActivity(intent);
            }
        });
    }
}
