package hup.plantalo;

import android.app.Activity;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.text.Html;
import android.text.Spanned;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.TextView;

import java.util.ArrayList;

import hup.plantalo.database.DatabaseOperations;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link HomeFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static final String ARG_SECTION_NUMBER = "section_number";
    ListView listadoComentarios;
    ListView listadoTips;
    MyAdapter myAdapterComentarios;
    MyAdapter myAdapterTips;
    ArrayList<ListViewComentario> filas;
    ArrayList<ListViewComentario> filasTips;
    TabHost tabHost;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public HomeFragment() {
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
        filasTips = new ArrayList<>();
        HomeTipsFragment tipsFragment = new HomeTipsFragment();
        Bundle args = new Bundle();
        args.putInt("section_number", 100);
        tipsFragment.setArguments(args);


        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.contenidoFragment, tipsFragment).commit();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_home2, container, false);
        DatabaseOperations dbop = new DatabaseOperations(getActivity());

        tabHost = (TabHost)rootView.findViewById(R.id.tabHostHome);
        tabHost.setup();
        TabHost.TabSpec tab1 = tabHost.newTabSpec("tab1");
        TabHost.TabSpec tab2 = tabHost.newTabSpec("tab2");

        tab1.setIndicator("Tips");
        tab1.setContent(R.id.ejemplo1Home);

        tab2.setIndicator("Comentarios");
        tab2.setContent(R.id.ejemplo2Home);

        tabHost.addTab(tab1);
        tabHost.addTab(tab2);

        for (int i=0; i < 2 ; i++) {
            TextView tv = (TextView) tabHost.getTabWidget().getChildAt(i).findViewById(android.R.id.title);
            tv.setTextColor(Color.parseColor("#ffffff"));
        }
        TextView tv1 = (TextView) tabHost.getCurrentTabView().findViewById(android.R.id.title);
        tv1.setTextColor(Color.parseColor("#96FCB0"));

        tabHost.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            @Override
            public void onTabChanged(String tabId) {
                for (int i = 0; i < 2; i++) {
                    TextView tv = (TextView) tabHost.getTabWidget().getChildAt(i).findViewById(android.R.id.title);
                    tv.setTextColor(Color.parseColor("#ffffff"));
                }

                TextView tv = (TextView) tabHost.getCurrentTabView().findViewById(android.R.id.title);
                tv.setTextColor(Color.parseColor("#96FCB0"));

                int current = tabHost.getCurrentTab();
                if (current == 0) {
                    HomeTipsFragment comentarios = new HomeTipsFragment();

                    Bundle args = new Bundle();
                    args.putInt("section_number", 100);
                    comentarios.setArguments(args);


                    FragmentManager fragmentManager = getFragmentManager();
                    fragmentManager.beginTransaction().replace(R.id.contenidoFragment, comentarios).commit();
                }

                if (current == 1) {
                    HomeComentariosFragment tips = new HomeComentariosFragment();

                    Bundle args = new Bundle();
                    args.putInt("section_number", 100);
                    tips.setArguments(args);

                    FragmentManager fragmentManager = getFragmentManager();
                    fragmentManager.beginTransaction().replace(R.id.contenidoFragment, tips).commit();
                }
            }
        });

        return rootView;
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

    private class MyAdapter extends ArrayAdapter<ListViewComentario> {

        private final Activity context;
        private ArrayList<ListViewComentario> filas;

        public MyAdapter(Activity context, ArrayList<ListViewComentario> filas) {
            super(context, R.layout.casilla_home, filas);
            this.context = context;
            this.filas = filas;
        }
        public View getView(int position,View view,ViewGroup parent) {
            LayoutInflater inflater=context.getLayoutInflater();
            View rowView=inflater.inflate(R.layout.casilla_home, null, true);

            TextView autor = (TextView) rowView.findViewById(R.id.autor_comentario);
            TextView comentario = (TextView) rowView.findViewById(R.id.comentario);
            ImageView imagenAutor = (ImageView) rowView.findViewById(R.id.imagen_autor);

            Uri uri = Uri.parse(filas.get(position).getImagenAutor());
            imagenAutor.setImageURI(uri);
            Spanned text = Html.fromHtml("<b>" + filas.get(position).getAutor() + "</b> comento en <b>" + filas.get(position).getCultivo() + "</b>");
            autor.setText(text);
            comentario.setText(filas.get(position).getComentario());
            return rowView;

        }
    }

}
