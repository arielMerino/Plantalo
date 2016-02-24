package hup.plantalo;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TabHost;

/**
 * Created by eliasPlease on 19-02-2016.
 */
public class TabBarActivity extends TabActivity {
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cultivo);

        TabHost tabHost = getTabHost();
        TabHost.TabSpec spec;
        Intent intent;

        intent = new Intent().setClass(this, Cultivo.class);
        spec = tabHost.newTabSpec("First").setIndicator("Comentarios").setContent(intent);
        tabHost.addTab(spec);

        intent = new Intent().setClass(this, Main.class);
        spec = tabHost.newTabSpec("Second").setIndicator("Tips").setContent(intent);
        tabHost.addTab(spec);

        intent = new Intent().setClass(this, Cultivo.class);
        spec = tabHost.newTabSpec("Third").setIndicator("Consejos").setContent(intent);
        tabHost.addTab(spec);
    }
}
