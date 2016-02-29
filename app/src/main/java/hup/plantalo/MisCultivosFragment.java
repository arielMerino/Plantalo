package hup.plantalo;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link MisCultivosFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link MisCultivosFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MisCultivosFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static final String ARG_SECTION_NUMBER = "section_number";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    MyAdapter myAdapter;
    ArrayList<String> filas;
    ListView listado;

    private OnFragmentInteractionListener mListener;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MisCultivos.
     */
    // TODO: Rename and change types and number of parameters
    public static MisCultivosFragment newInstance(String param1, String param2) {
        MisCultivosFragment fragment = new MisCultivosFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public MisCultivosFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
        filas = new ArrayList<>();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_mis_cultivos, container, false);
        listado = (ListView) rootView.findViewById(R.id.listado_mis_cultivos);


        filas.add("Tomate");
        filas.add("Papa");
        filas.add("Lechuga");
        filas.add("Repollo");
        filas.add("Apio");
        filas.add("Cilantro");
        filas.add("Perejil");
        myAdapter = new MyAdapter(getActivity(), filas);
        listado.setAdapter(myAdapter);

        return rootView;
    }

    private class MyAdapter extends ArrayAdapter<String> {

        private final Activity context;
        private ArrayList<String> filas;

        public MyAdapter(Activity context, ArrayList<String> filas) {
            super(context, R.layout.casilla_mis_cultivos, filas);
            this.context = context;
            this.filas = filas;
        }
        public View getView(int position,View view,ViewGroup parent) {
            LayoutInflater inflater=context.getLayoutInflater();
            View rowView=inflater.inflate(R.layout.casilla_mis_cultivos, null, true);

            TextView nombre = (TextView) rowView.findViewById(R.id.texto);

            nombre.setText(filas.get(position));
            return rowView;

        }
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
        ((Home) activity).onSectionAttached(
                getArguments().getInt(ARG_SECTION_NUMBER));
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
        public void onFragmentInteraction(Uri uri);
    }

}