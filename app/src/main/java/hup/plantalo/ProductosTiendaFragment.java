package hup.plantalo;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ProductosTiendaFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ProductosTiendaFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProductosTiendaFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public ProductosTiendaFragment() {
        // Required empty public constructor
    }

    TextView nombreTienda;
    TextView valoracionTienda;
    ImageView imagenTienda;
    TextView descripcionTienda;
    TextView correoTienda;
    TextView fonoTienda;
    TextView direccionTienda;
    ListView listaProductos;
    ArrayList<ProductoClass> productos;

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

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ProductosTiendaFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ProductosTiendaFragment newInstance(String param1, String param2) {
        ProductosTiendaFragment fragment = new ProductosTiendaFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
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
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_productos_tienda, container, false);

        productos = new ArrayList<>();
        int t = -1;

        String tienda = getArguments().getString("tienda");

        listaProductos = (ListView) view.findViewById(R.id.lista_productos_por_tienda);

        for (int i = 0; i < nombresP.length; i++) {
            if (tiendasP[i].toLowerCase().equalsIgnoreCase(tienda.toLowerCase())) {
                productos.add(new ProductoClass(imagenesP[i], nombresP[i], tiendasP[i], descripcionesP[i], preciosP[i], valoracionesP[i]));
            }
        }


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

        MyAdapter adapter = new MyAdapter(getActivity(),productos);
        listaProductos.setAdapter(adapter);

        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
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
