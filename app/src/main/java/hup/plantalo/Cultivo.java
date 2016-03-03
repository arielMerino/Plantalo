package hup.plantalo;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import hup.plantalo.database.DatabaseOperations;

public class Cultivo extends AppCompatActivity {

    ImageView imagen;
    TextView nombre;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cultivo);

        Intent intent = getIntent();
        int posicionRecibida = intent.getIntExtra("fila", -1);

        DatabaseOperations dbop = new DatabaseOperations(getApplicationContext());
        Cursor cursor = dbop.obtenerCultivos(dbop);
        cursor.moveToPosition(posicionRecibida);

        nombre = (TextView) findViewById(R.id.nombreCultivo);
        nombre.setText(cursor.getString(0));

        imagen = (ImageView) findViewById(R.id.imagenCultivo);
        imagen.setImageBitmap(getImage(cursor.getBlob(2)));
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

    public static Bitmap getImage(byte[] image) {
        return BitmapFactory.decodeByteArray(image, 0, image.length);
    }
}
