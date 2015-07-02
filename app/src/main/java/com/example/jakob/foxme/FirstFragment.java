package com.example.jakob.foxme;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.Switch;

import com.example.jakob.foxme.Backend.AnzeigenServiceImpl;
import com.example.jakob.foxme.Backend.ProfilSpeicherungsVerwaltung;
import com.example.jakob.foxme.Backend.RevieverTask;

import java.util.ArrayList;



/**
 * A simple {@link android.support.v4.app.Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link FirstFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link FirstFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FirstFragment extends Fragment {
    private static final String ARG_SECTION_NUMBER = "sectionNumber";
    public int mParam1;
    public String filterbool = "true";
    ListView konsumentListView;
    ArrayAdapter konsumentArrayAdapter;
    ArrayList<String> konsumentListeInfoTexte=new ArrayList();
    AnzeigenServiceImpl anzeigenService = new AnzeigenServiceImpl();
    ProfilSpeicherungsVerwaltung a;
    private OnFragmentInteractionListener mListener;
    private String localTags;
    public FirstFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param sectionNumber Parameter 1.
     * @return A new instance of fragment FirstFragment.
     */
    public static FirstFragment newInstance(int sectionNumber) {
        FirstFragment fragment = new FirstFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getInt(ARG_SECTION_NUMBER);
        }
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        //Initalisieren des Contexts und der Verbindung zum Speicher
        Context c = getActivity();
        a = new ProfilSpeicherungsVerwaltung(c.getApplicationContext());
        localTags = a.load();
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_konsument, container, false);
        konsumentListView = (ListView) view.findViewById(R.id.konsument_list_view);
        konsumentArrayAdapter = new ArrayAdapter<String>(getActivity(), R.layout.info_layout, R.id.info_item, konsumentListeInfoTexte);
        konsumentListView.setAdapter(konsumentArrayAdapter);
        final Button button_erneuern = (Button) view.findViewById(R.id.button_konsument_fetchit);
        button_erneuern.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                RevieverTask testklasse = new RevieverTask(getActivity(), filterbool, localTags);
                testklasse.execute();
            }
        });
        Switch aSwitch = (Switch) view.findViewById(R.id.togglebutton);
        aSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    filterbool = "true";
                } else {
                    filterbool = "false";
                }
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
