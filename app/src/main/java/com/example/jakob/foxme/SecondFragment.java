package com.example.jakob.foxme;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.jakob.foxme.Backend.AnzeigenServiceImpl;
import com.example.jakob.foxme.Backend.MainController;

import java.util.ArrayList;



/**
 * A simple {@link android.support.v4.app.Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link SecondFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link SecondFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SecondFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    //private static final String ARG_PARAM2 = "param2";
    AnzeigenServiceImpl anzeigenService = new AnzeigenServiceImpl();
    ListView tagAnzeigeListe;
    ArrayAdapter tagListAdapter;
    ArrayList<String> aktiveTags = new ArrayList();
    boolean initalerknopfDruck = true;
    //Jakob------------------------------------------------------------------
    private EditText anzeigenText;
    private EditText anzeigenAdresse;
    //-----------------------------------------------------------------------
    //private String mParam2;
    // TODO: Rename and change types of parameters
    private int mParam1;
    private OnFragmentInteractionListener mListener;


    public SecondFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @return A new instance of fragment SecondFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SecondFragment newInstance(int param1) {
        SecondFragment fragment = new SecondFragment();
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

        }
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container,
                             final Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_second, container, false);

        //jakob----------------------------------------------------------------------------------------------------------------------------
        tagAnzeigeListe = (ListView) view.findViewById(R.id.ausgewaehlte_tag_liste);
        tagListAdapter = new ArrayAdapter<>(getActivity(), R.layout.tag_in_liste_layout, R.id.tag_einzeln_Anzeige, aktiveTags);
        tagAnzeigeListe.setAdapter(tagListAdapter);

        //dinge die im Spinner tagAuswahl angeklickt werden kommen in die Liste der activen tags und werden angezeigt
        Spinner tagAuswahlSpinner=(Spinner) view.findViewById(R.id.spinner3);
        tagAuswahlSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(initalerknopfDruck==true){
                    initalerknopfDruck=false;
                    Log.i("Prodozent; Spinner3 ", "initialer knopfdruck");
                }
                else {
                    Log.i("Prodozent; Spinner3 ", "ein tag gewaehlt");
                    TextView gewaehltesTag = (TextView) view;
                    Log.i("Prodozent; Spinner3 ", "gewaehltes tag:" + gewaehltesTag.getText());
                    aktiveTags.add((String) gewaehltesTag.getText());
                    Log.i("Prodozent; Spinner3 ", "erstes Element der aktivenTag ArrayList: " + aktiveTags.toString());
                    tagListAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        //----------------------------------------------------------------------------------------------------------------------------

        final Button buttonAnzeigeAbschicken = (Button) view.findViewById(R.id.Anzeige_abschicken);
        buttonAnzeigeAbschicken.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                anzeigenText = (EditText) view.findViewById(R.id.editText); //liest Textfeld der Anzeige aus
                anzeigenAdresse = (EditText) view.findViewById(R.id.editText2);
                String text = anzeigenText.getText().toString();
                String adresse = anzeigenAdresse.getText().toString();
                String tagList = anzeigenService.tagListToString(aktiveTags);
                //das Löschen der felder
                    anzeigenText.getText().clear();
                    anzeigenAdresse.getText().clear();
                    aktiveTags.clear();
                    tagListAdapter.notifyDataSetChanged();
                new MainController().execute("Insert", text, adresse, tagList);
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
