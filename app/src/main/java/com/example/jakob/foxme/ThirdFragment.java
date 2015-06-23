package com.example.jakob.foxme;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    //private static final String ARG_PARAM2 = "param2";
    //jakob
    MainActivity mainActivity = new MainActivity();
    //private String mParam2;
    ProfilSpeicherungsVerwaltung pSV = new ProfilSpeicherungsVerwaltung(mainActivity.refContext);
    Spinner spinner1;
    Spinner spinner2;
    int[] zustaende=new int[2];
    // TODO: Rename and change types of parameters
    private int mParam1;
    private OnFragmentInteractionListener mListener;
    //jakob ende

    public ThirdFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @return A new instance of fragment ThirdFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ThirdFragment newInstance(int param1) {
        ThirdFragment fragment = new ThirdFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_PARAM1, param1);
        //args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getInt(ARG_PARAM1);
            //mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_third, container, false);
        //Jakob

        //laed den zustand des profils aus dem speicher
        String speicherGesammt=pSV.load();
        Log.i("Thirdfragment","speicherGesammt: "+speicherGesammt);
        String[] speicherTeile=speicherGesammt.split(" ");
        Log.i("Thirdfragment","speicherTeile: "+speicherTeile.toString());

        //setze den zustand des profils auf den gespeicherten Zustand
        zustaende[0]=1;
        zustaende[1]=2;

        //setzt die oberfläche gemäß dem zustand
        spinner1=(Spinner) view.findViewById(R.id.spinner);
        spinner1.setSelection(zustaende[0]);

        spinner2=(Spinner) view.findViewById(R.id.spinner2);
        spinner2.setSelection(zustaende[1]);

        /*
        spinner1=(Spinner) view.findViewById(R.id.spinner2);
        if(speicherTeile.length>0){
            Log.i("Thirdfragment","im if 1");
            spinner1.setSelection(Integer.parseInt(speicherTeile[0]));
        }
        else{
            Log.i("Thirdfragment","nicht in if 1");
        }
        /*
        /*
        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                pSV.save(spinner1.getSelectedItemPosition()+" ");
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
        */
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


}
