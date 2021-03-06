package hup.plantalo;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
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
 * {@link TiendaFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link TiendaFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TiendaFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static final String ARG_NOMBRE_TIENDA = "tienda";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    ImageView imagenTienda;
    TextView descripcionTienda;
    TextView correoTienda;
    TextView fonoTienda;
    TextView direccionTienda;
    ListView listaProductos;
    ArrayList<ProductoClass> productos;

    /*Datos de las tiendas que estan registradas*/
    String nombresT[] = {"Jardin Flaminia"};
    String direccionesT[] = {"La cisterna no tengo idea el numero"};
    String descripcionesT[] = {"Tienda de productos de jardinería, arbustos, árboles, flores y materiales de cultivo"};
    String telefonosT[] = {"75298657"};
    String correosT[] = {"jardin.flaminia@correo.cl"};
    int imagenesT[] = {R.drawable.jardin_flaminia};
    double valoracionesT[] = {5.9};

    public TiendaFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment TiendaFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static TiendaFragment newInstance(String param1, String param2) {
        TiendaFragment fragment = new TiendaFragment();
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
        View view = inflater.inflate(R.layout.fragment_tienda, container, false);

        String tienda = getArguments().getString(ARG_NOMBRE_TIENDA);
        productos = new ArrayList<>();
        int t = -1;

        ArrayList<ProductoClass> stock = new ArrayList<>();

        imagenTienda = (ImageView) view.findViewById(R.id.imagen_tienda_detalle);
        descripcionTienda = (TextView) view.findViewById(R.id.descripcion_tienda_detalle);
        correoTienda = (TextView) view.findViewById(R.id.correo_tienda_detalle);
        fonoTienda = (TextView) view.findViewById(R.id.fono_tienda_detalle);
        direccionTienda = (TextView) view.findViewById(R.id.direccion_tienda_detalle);
        listaProductos = (ListView) view.findViewById(R.id.lista_productos_por_tienda);

        Log.i("informacion2", "onCreateView: "+tienda);

        for (int i = 0; i < nombresT.length; i++) {
            if (nombresT[i].toLowerCase().equalsIgnoreCase(tienda.toLowerCase())) {
                t = i;
                break;
            }
        }

        imagenTienda.setImageResource(imagenesT[t]);
        descripcionTienda.setText(descripcionesT[t]);
        correoTienda.setText(correosT[t]);
        direccionTienda.setText(direccionesT[t]);
        fonoTienda.setText("(+56) 9 " + telefonosT[t]);

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
}
