package hup.plantalo;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.text.Spanned;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import hup.plantalo.database.DatabaseOperations;

public class MiCultivo extends AppCompatActivity {

    ImageView imagen;
    TextView nombre;
    TextView comentar;
    TextView tips;
    TextView calificar;
    TextView dejarDeSeguir;
    Cursor cursor;
    ListView listado;
    MyAdapter myAdapterComentarios;
    ArrayList<ListViewComentario> filas;
    ActionBar actionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mi_cultivo);

        actionBar = getSupportActionBar();
        actionBar.setTitle("Mi Cultivo");
        actionBar.setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();
        String posicionRecibida = intent.getStringExtra("fila");

        final DatabaseOperations dbop = new DatabaseOperations(getApplicationContext());
        cursor = dbop.obtenerCultivo(dbop, posicionRecibida);
        cursor.moveToPosition(0);

        nombre = (TextView) findViewById(R.id.nombreCultivo);
        nombre.setText(cursor.getString(0));

        imagen = (ImageView) findViewById(R.id.imagenCultivo);
        Uri uri = Uri.parse(cursor.getString(2));
        imagen.setImageURI(uri);

        comentar = (TextView) findViewById(R.id.text_comentar);
        comentar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), CultivoComentar.class);
                String[] datos_usuario = {cursor.getString(0),cursor.getString(1),cursor.getString(2)};
                intent.putExtra("datos_cultivo",datos_usuario);
                startActivity(intent);
            }
        });

        tips = (TextView) findViewById(R.id.text_tips);
        tips.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), CultivoTips.class);
                String[] datos_usuario = {cursor.getString(0),cursor.getString(1),cursor.getString(2)};
                intent.putExtra("datos_cultivo",datos_usuario);
                startActivity(intent);
            }
        });

        dejarDeSeguir = (TextView) findViewById(R.id.text_dejar_seguir);
        dejarDeSeguir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbop.eliminarMiCultivo(dbop, cursor.getString(0));
                Intent intent = new Intent(getApplicationContext(), Cultivo.class);
                intent.putExtra("fila", cursor.getString(0));
                startActivity(intent);
                finish();
            }
        });

        filas = new ArrayList<>();
        listado = (ListView) findViewById(R.id.listado_mi_cultivo);
        int contador = 0;
        Cursor cursorLista = dbop.obtenerComentariosDeMiCultivo(dbop, cursor.getString(0));
        cursorLista.moveToPosition(-1);
        while (cursorLista.moveToNext()) {
            filas.add(new ListViewComentario(cursorLista.getString(0), cursorLista.getString(1), cursorLista.getString(2), cursorLista.getString(4), cursorLista.getString(5), cursorLista.getString(3)));
            contador++;
        }
        myAdapterComentarios = new MyAdapter(this, filas);
        listado.setAdapter(myAdapterComentarios);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_cultivo, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == android.R.id.home) {
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public static Bitmap getImage(byte[] image) {
        return BitmapFactory.decodeByteArray(image, 0, image.length);
    }

    private class MyAdapter extends ArrayAdapter<ListViewComentario> {

        private final Activity context;
        private ArrayList<ListViewComentario> filas;

        public MyAdapter(Activity context, ArrayList<ListViewComentario> filas) {
            super(context, R.layout.casilla_home, filas);
            this.context = context;
            this.filas = filas;
        }
        public View getView(int position,View view, ViewGroup parent) {
            LayoutInflater inflater=context.getLayoutInflater();
            View rowView=inflater.inflate(R.layout.casilla_home, null, true);

            TextView autor = (TextView) rowView.findViewById(R.id.autor_comentario);
            TextView comentario = (TextView) rowView.findViewById(R.id.comentario);
            TextView tipo = (TextView) rowView.findViewById(R.id.tipo_publicacion);
            ImageView imagenAutor = (ImageView) rowView.findViewById(R.id.imagen_autor);

            Uri uri = Uri.parse(filas.get(position).getImagenAutor());
            imagenAutor.setImageURI(uri);
            Spanned text = Html.fromHtml("<b>" + filas.get(position).getAutor() + "</b> comento en <b>" + filas.get(position).getCultivo() + "</b>");
            autor.setText(text);
            comentario.setText(filas.get(position).getComentario());
            if (filas.get(position).getTipo().equals("c")){
                tipo.setText("Comentario");
            }
            else{
                tipo.setText("Tips");
            }
            return rowView;
        }
    }
}
