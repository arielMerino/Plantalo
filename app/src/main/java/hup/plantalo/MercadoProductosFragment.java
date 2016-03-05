package hup.plantalo;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import java.util.ArrayList;


public class MercadoProductosFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static final String ARG_SECTION_NUMBER = "section_number";
    private static final String ARG_VALOR_BUSQUEDA = "valor_busqueda";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    TextView mensajeBusqueda;
    ListView lista;
    MyAdapter myAdapter;


    ArrayList<ProductoClass> productos = new ArrayList<>();

    String nombres[] = {"Pala","Macetero Redondo", "Carpa Indoor", "Tierra de hoja", "Semillas de tomate", "Semillas de tomate cherry"};
    String descripciones[] = {
            "esta es una descripción de ejemplo para la pala, nada de lo que salga aquí existe realmente, please no pesquen",
            "esta es una descripción de ejemplo para el macetero redondo, nada de lo que salga aquí existe realmente, please no pesquen",
            "esta es una descripción de ejemplo para la carpa indoor, nada de lo que salga aquí existe realmente, please no pesquen",
            "esta es una descripción de ejemplo para la tierra de hoja, nada de lo que salga aquí existe realmente, please no pesquen",
            "esta es una descripción de ejemplo para las semillas de tomate, nada de lo que salga aquí existe realmente, please no pesquen",
            "esta es una descripción de ejemplo para las semillas de tomate cherry, nada de lo que salga aquí existe realmente, please no pesquen"};
    int imagenes[] = {R.drawable.pala,R.drawable.macetero_redondo,R.drawable.carpa_indoor,R.drawable.tierra_hoja,R.drawable.semillas_tomate,R.drawable.semillas_tomate_cherry};
    //String imagenes[] = {"","","","","",""};
    String tiendas[] = {"","","","","",""};
    float valoraciones[] = {(float)2.0, (float)5.9, (float)3.6, (float)7.0, (float)6.5, (float)9.2};
    int precios[] = {800, 2000, 25000, 300, 1200, 1850};

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CultivosFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MercadoProductosFragment newInstance(String param1, String param2) {
        MercadoProductosFragment fragment = new MercadoProductosFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public MercadoProductosFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mercado_productos, container, false);
        lista = (ListView) view.findViewById(R.id.listado_productos);
        mensajeBusqueda = (TextView) view.findViewById(R.id.mensaje_busqueda);

        for (int i = 0; i < nombres.length; i++) {
            productos.add(new ProductoClass(imagenes[i],nombres[i],tiendas[i],descripciones[i],precios[i], valoraciones[i]));
        }
        ArrayList<ProductoClass> resultados = new ArrayList<>();
        String s = getArguments().getString(ARG_VALOR_BUSQUEDA);
        int conteo = 0;
        if (s != null) {
            if (s.length() >= 3) {
                for (int i = 0; i < productos.size(); i++) {
                    if (productos.get(i).getNombre().toLowerCase().contains(s.toLowerCase())) {
                        resultados.add(productos.get(i));
                        conteo++;
                    }else {
                        mensajeBusqueda.setText("No se han encontrado coincidencias...");
                    }
                }
            }else {
                mensajeBusqueda.setText("La búsqueda necesita por lo menos 3 letras...");
            }
        } else {
            mensajeBusqueda.setText("La búsqueda necesita por lo menos 3 letras...");
        }

        if (conteo > 0) { mensajeBusqueda.setText("");}

        myAdapter = new MyAdapter(getActivity(),resultados);
        lista.setAdapter(myAdapter);

        return view;

    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        public void onFragmentInteraction(Uri uri);
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

}