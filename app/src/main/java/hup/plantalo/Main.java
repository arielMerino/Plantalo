package hup.plantalo;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import java.io.ByteArrayOutputStream;

import hup.plantalo.database.DatabaseOperations;

public class Main extends AppCompatActivity {

    Button botonCultivo;
    DatabaseOperations bd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_layout);

        bd = new DatabaseOperations(getApplicationContext());
        if (!bd.dbExiste(bd)) {
            Bitmap bitmap = BitmapFactory.decodeResource(this.getResources(), R.drawable.tomates);
            Bitmap bitmap2 = BitmapFactory.decodeResource(this.getResources(), R.drawable.papas);

            bd.agregarInformacionCultivo(bd, "Tomate", "Tomate rojo del sur de Chile", getBytes(bitmap));
            bd.agregarInformacionCultivo(bd, "Papa", "Papa orgánica del norte de Chile", getBytes(bitmap2));
            bd.close();
        }


        botonCultivo = (Button) findViewById(R.id.cultivo);

        botonCultivo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Home.class);
                startActivity(intent);
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
