package com.example.jakob.foxme;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Spinner;

import com.example.jakob.foxme.Backend.ProfilSpeicherungsVerwaltung;


/**
 * A simple {@link android.support.v4.app.Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ThirdFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ThirdFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ThirdFragment extends Fragment {

    private OnFragmentInteractionListener mListener;

    //jakob
    ProfilSpeicherungsVerwaltung a;

    Spinner spinner1;
    Spinner spinner2;
    int[] zustaende={0,0};
    //jakob ende

    public ThirdFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment ThirdFragment.
     */
    public static ThirdFragment newInstance() {
        ThirdFragment fragment = new ThirdFragment();

        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_third, container, false);
        //Jakob

        //Initalisieren des Contexts und der Verbindung zum Speicher
        Context c=getActivity();
        a=new ProfilSpeicherungsVerwaltung(c.getApplicationContext());

        //laden aus dem speicher
        String speicherGesammt=a.load();
        Log.i("Thirdfragment","speicherGesammt: " + speicherGesammt);
        String[] speicherTeile=speicherGesammt.split(" ");
        int laenge=speicherTeile.length;
        //ausgabe der StringTeile
        for(int i=0;i<laenge;i++) {
            Log.i("Thirdfragment", "speicherTeile: " + i + " : " + speicherTeile[i]);
        }
        //Strings zu den Zustaenden parsen
        for(int i=0;i<laenge;i++){
            zustaende[i]=Integer.parseInt(speicherTeile[i]);
        }

        //setzt die oberfläche gemäß dem zustand
        spinner1=(Spinner) view.findViewById(R.id.spinner);
        spinner1.setSelection(zustaende[0]);


        spinner2=(Spinner) view.findViewById(R.id.spinner2);
        spinner2.setSelection(zustaende[1]);

        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                int position=spinner1.getSelectedItemPosition();
                Log.i("spinner 1","selected id: "+position);
                zustaende[0]=position;
                a.save(parse(zustaende));
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                int position=spinner2.getSelectedItemPosition();
                Log.i("spinner 2","selected id: "+position);
                zustaende[1]=position;
                a.save(parse(zustaende));
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

        //Jakob ende
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
        try {
            mListener = (OnFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
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

    //Jakob
    private String parse(int[] eingabe){
        String ausgabe="";
        for(int i=0;i<eingabe.length-1;i++){
            ausgabe=ausgabe+eingabe[i]+" ";
        }
        ausgabe=ausgabe+eingabe[eingabe.length-1];
        Log.i("ThirdFragment","ergebnis von parse: "+ausgabe);
        return ausgabe;
    }
    //
}
