package hup.plantalo;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import hup.plantalo.database.DatabaseOperations;

public class Cultivo extends AppCompatActivity {

    TextView nombre;
    Cursor cursor;
    ImageView imagen;
    TextView seguir;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cultivo);

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

        seguir = (TextView) findViewById(R.id.text_seguir);
        seguir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbop.agregarMiCultivo(dbop, cursor.getString(0));
                Intent intent = new Intent(getApplicationContext(), MiCultivo.class);
                intent.putExtra("fila", cursor.getString(0));
                startActivity(intent);
                finish();
            }
        });
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
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
