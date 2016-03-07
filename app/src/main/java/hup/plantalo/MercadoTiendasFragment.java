package hup.plantalo;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;


public class MercadoTiendasFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static final String ARG_VALOR_BUSQUEDA = "valor_busqueda";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    TextView mensajeBusqueda;
    ListView lista;
    MyAdapter myAdapter;
    ArrayList<TiendaClass> tiendas = new ArrayList<>();

    String nombres[] = {"Jardin Flaminia"};
    String direcciones[] = {"La cisterna no tengo idea el numero"};
    String descripciones[] = {"Tienda de productos de jardinería, arbustos, árboles, flores y materiales de cultivo"};
    int telefonos[] = {12345678};
    String correos[] = {"jardin.flaminia@correo.cl"};
    int imagenes[] = {R.drawable.jardin_flaminia};
    double valoraciones[] = {5.9};
    String comunas[] = {"La cisterna"};

    private OnFragmentInteractionListener mListener;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CultivosFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MercadoTiendasFragment newInstance(String param1, String param2) {
        MercadoTiendasFragment fragment = new MercadoTiendasFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public MercadoTiendasFragment() {

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
        View view = inflater.inflate(R.layout.fragment_mercado_tiendas, container, false);
        String s = getArguments().getString(ARG_VALOR_BUSQUEDA);

        lista = (ListView) view.findViewById(R.id.listado_tiendas);
        lista.setEnabled(true);
        mensajeBusqueda = (TextView) view.findViewById(R.id.mensaje_busqueda);

        for (int i = 0; i < nombres.length; i++) {
            tiendas.add(new TiendaClass(nombres[i], direcciones[i], descripciones[i], telefonos[i], correos[i], imagenes[i], valoraciones[i], comunas[i]));
        }
        final ArrayList<TiendaClass> resultados = new ArrayList<>();
        int conteo = 0;
        if (s != null) {
            if (s.length() >= 3) {
                for (int i = 0; i < tiendas.size(); i++) {
                    if (tiendas.get(i).getNombre().toLowerCase().contains(s.toLowerCase())) {
                        resultados.add(tiendas.get(i));
                        conteo++;
                    }
                }
            }else {
                mensajeBusqueda.setText("La búsqueda necesita por lo menos 3 letras...");
            }
        } else {
            mensajeBusqueda.setText("La búsqueda necesita por lo menos 3 letras...");
        }

        if (conteo > 0) { mensajeBusqueda.setText("");
        }else {
            mensajeBusqueda.setText("No se han encontrado coincidencias...");
        }

        myAdapter = new MyAdapter(getActivity(),resultados);
        lista.setAdapter(myAdapter);
        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.i("informacion", "se ha seleccionado la tienda: "+ resultados.get(position).getNombre());
                /*
                Fragment fragmento = new TiendaFragment();

                Bundle args = new Bundle();
                args.putString("nombre_tienda", resultados.get(position).getNombre());
                fragmento.setArguments(args);

                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                transaction.replace(R.id.container,fragmento);
                transaction.addToBackStack(null);
                transaction.commit();
                */
                Intent intent = new Intent(getContext(),TiendaActivity.class);
                intent.putExtra("tienda",resultados.get(position).getNombre());
                startActivity(intent);
            }
        });

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
    private class MyAdapter extends ArrayAdapter<TiendaClass> {

        private final Activity context;
        private ArrayList<TiendaClass> filas;

        public MyAdapter(Activity context, ArrayList<TiendaClass> filas) {
            super(context, R.layout.casilla_tiendas, filas);
            this.context = context;
            this.filas = filas;
        }
        public View getView(int position,View view,ViewGroup parent) {
            LayoutInflater inflater=context.getLayoutInflater();
            View rowView = inflater.inflate(R.layout.casilla_tiendas, null, true);

            TextView nombre = (TextView) rowView.findViewById(R.id.nombre_tienda);
            TextView descripcion = (TextView) rowView.findViewById(R.id.descripcion_tienda);
            TextView telefono = (TextView) rowView.findViewById(R.id.telefono_tienda);
            TextView correo = (TextView) rowView.findViewById(R.id.correo_tienda);
            ImageView imagen = (ImageView) rowView.findViewById(R.id.imagen_tienda);
            TextView valoracion = (TextView) rowView.findViewById(R.id.valoracion_tienda);

            nombre.setText(filas.get(position).getNombre());
            descripcion.setText(filas.get(position).getDescripcion());
            telefono.setText(String.valueOf(filas.get(position).getTelefono()));
            correo.setText(filas.get(position).getCorreo());
            imagen.setImageResource(filas.get(position).getImagen());
            valoracion.setText(String.valueOf(filas.get(position).getValoracion()));
            return rowView;
        }
    }
}