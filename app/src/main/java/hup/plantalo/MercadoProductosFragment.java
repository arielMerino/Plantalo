package hup.plantalo;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Calendar;
import java.util.Random;
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


    Random generator = new Random(Calendar.getInstance().getTimeInMillis());

    TextView coincidencias;

    ArrayList<ProductoClass> productos = new ArrayList<>();

    String nombres[] = {"Pala","Macetero Redondo", "Carpa Indoor", "Tierra de hoja", "Semillas de tomate", "Semillas de tomate cherry"};
    String descripciones[] = {"","","","","",""};
    String imagenes[] = {"","","","","",""};
    String tiendas[] = {"","","","","",""};
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
        coincidencias = (TextView) view.findViewById(R.id.kaka);
        for (int i = 0; i < nombres.length; i++) {
            productos.add(new ProductoClass(imagenes[i],nombres[i],tiendas[i],descripciones[i],precios[i], generator.nextFloat()));
        }

        String s = getArguments().getString(ARG_VALOR_BUSQUEDA);
        int contador = 0;
        if (s != null) {
            if (s.length() >= 3) {
                for (int i = 0; i < productos.size(); i++) {
                    if (productos.get(i).getNombre().toLowerCase().contains(s.toLowerCase())) {
                        contador++;
                    }
                }
                coincidencias.setText(contador + "");
            }else {
                coincidencias.setText(0 + "");
            }
        }else {
            coincidencias.setText(0 + "");
        }

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

}