package hup.plantalo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import hup.plantalo.database.DatabaseOperations;

public class CultivoTips extends AppCompatActivity {

    EditText editTextTips;
    ActionBar actionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cultivo_tips);
        editTextTips = (EditText) findViewById(R.id.editTextPublicarTips);

        actionBar = getSupportActionBar();
        actionBar.setTitle("Tips");
        actionBar.setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_cultivo_tips, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.publicarTips) {
            DatabaseOperations db = new DatabaseOperations(getApplicationContext());
            Intent intent = getIntent();
            DateFormat date = new SimpleDateFormat("yyyyMMdd hhmmss");
            date.setLenient(false);
            Date today = new Date();
            String fechaHoy = date.format(today);
            String[] datos = intent.getStringArrayExtra("datos_usuario");
            db.agregarComentarioTips(db, "Elias Sobarzo", editTextTips.getText().toString(), fechaHoy, "t", intent.getStringArrayExtra("datos_cultivo")[0],"android.resource://"+this.getPackageName()+"/" + R.drawable.elias);
            finish();
        }
        if (id == android.R.id.home){
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
