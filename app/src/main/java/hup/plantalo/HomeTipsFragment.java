package hup.plantalo;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.text.Spanned;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import hup.plantalo.database.DatabaseOperations;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link HomeTipsFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link HomeTipsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeTipsFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    ArrayList<ListViewComentario> filasTips;
    MyAdapter myAdapterTips;
    ListView listadoTips;

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
     * @return A new instance of fragment HomeTipsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeTipsFragment newInstance(String param1, String param2) {
        HomeTipsFragment fragment = new HomeTipsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public HomeTipsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
        filasTips = new ArrayList<>();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_home_tips, container, false);
        DatabaseOperations dbop = new DatabaseOperations(getActivity());
        int contador = 0;

        //Listview tips
        listadoTips = (ListView) rootView.findViewById(R.id.listado_home_tips);
        Cursor cursorTips = dbop.obtenerTipsDeMisCultivos(dbop);
        cursorTips.moveToPosition(-1);
        while(cursorTips.moveToNext()){
            filasTips.add(new ListViewComentario(cursorTips.getString(0),cursorTips.getString(1),cursorTips.getString(2), cursorTips.getString(4), cursorTips.getString(5)));
            contador++;
        }
        myAdapterTips = new MyAdapter(getActivity(), filasTips);
        listadoTips.setAdapter(myAdapterTips);

        //cuadro de dialogo... cargando
        Handler mHandler = new Handler();
        final ProgressDialog mDialog = ProgressDialog.show(getActivity(), "Espere un momento...", "Cargando informacion", true, false);

        Thread thread = new Thread();
        thread.start();

        mDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialogInterface) {
                // EL usuario cerró el dialog
            }
        });
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                mDialog.dismiss();
            }
        }, 2000);

        return  rootView;
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
