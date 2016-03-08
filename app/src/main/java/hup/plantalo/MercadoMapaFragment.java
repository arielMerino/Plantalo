package hup.plantalo;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link MercadoMapaFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link MercadoMapaFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MercadoMapaFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    GoogleMap googleMap;
    MapView mapView;
    String nombresT[] = {"Jardin Flaminia"};
    String direccionesT[] = {"Direccion 1234"};
    String posiciones[] = {"-33.5287469;-70.6664111"};

    public MercadoMapaFragment() {
        // Required empty public constructor
    }

    @Override
    public void onResume(){
        super.onResume();
        mapView.onResume();
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        mapView.onDestroy();
    }

    @Override
    public void onPause(){
        super.onPause();
        mapView.onPause();
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MercadoMapaFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MercadoMapaFragment newInstance(String param1, String param2) {
        MercadoMapaFragment fragment = new MercadoMapaFragment();
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
        View view = inflater.inflate(R.layout.fragment_mercado_mapa, container, false);

        mapView = (MapView) view.findViewById(R.id.map_mercado);
        mapView.onCreate(savedInstanceState);
        googleMap = mapView.getMap();
        googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        //googleMap.setMyLocationEnabled(true);
        double pX = -33.4498735f;
        double pY = -70.6888173f;

        for (int i = 0; i < nombresT.length; i++) {
            double posicionX = Double.valueOf(posiciones[i].split(";")[0]);
            double posicionY = Double.valueOf(posiciones[i].split(";")[1]);
            Marker marker = googleMap.addMarker(new MarkerOptions().position(new LatLng(posicionX, posicionY)).draggable(true).title(nombresT[i]).snippet(direccionesT[i]));
            marker.setAlpha(14f);
            marker.showInfoWindow();
        }

        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(pX,pY),14.3f));

        googleMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                String tienda = marker.getTitle();
                Intent intent = new Intent(getContext(), TiendaActivity.class);
                intent.putExtra("tienda", tienda);
                startActivity(intent);
                return false;
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
