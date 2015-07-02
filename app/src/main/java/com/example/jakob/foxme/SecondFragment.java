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
import android.widget.TextView;

import com.example.jakob.foxme.Backend.AnzeigenServiceImpl;
import com.example.jakob.foxme.Backend.SenderTask;

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
    ListView alleTagsListe;
    ArrayAdapter alleTagsListeAdapter;
    ArrayList<String> alleTags =new ArrayList();
    String auswahlRemove = "";
    String auswahlAdd = "";
    private EditText anzeigenText;
    //-----------------------------------------------------------------------
    private EditText anzeigenAdresse;
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

        alleTagsListe=(ListView) view.findViewById(R.id.alle_tags_Liste);
        alleTagsListeAdapter=new ArrayAdapter(getActivity(), R.layout.tag_in_liste_layout, R.id.tag_einzeln_Anzeige,alleTags);
        alleTagsListe.setAdapter(alleTagsListeAdapter);

        //ToDo: alle möglichen Tags hinzufuegen
        alleTags.add("Alles");
        alleTags.add("Weiblich");
        alleTags.add("Männlich");
        //essgewohnheit
        alleTags.add("Halal");
        alleTags.add("Koscher");
        alleTags.add("Pescetarier");
        alleTags.add("Veganer");
        alleTags.add("Vegetarier");

        alleTags.add("Alkohol");
        alleTags.add("Fisch");
        alleTags.add("Kinder");
        alleTags.add("Italienisch");
        alleTags.add("Afrikanisch");
        alleTags.add("Mexikanisch");
        alleTags.add("Asiatisch");


        tagAnzeigeListe.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                TextView gewaehltesTag =(TextView) view.findViewById(R.id.tag_einzeln_Anzeige);
                auswahlRemove = gewaehltesTag.getText().toString();
                Log.i("SecondFragment", "die Liste der Aktiven tags wurde angeklickt; das Element: " + auswahlRemove);
                view.setSelected(true);
            }
        });

        alleTagsListe.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                TextView gewaehltesTag = (TextView) view.findViewById(R.id.tag_einzeln_Anzeige);
                auswahlAdd=gewaehltesTag.getText().toString();
                Log.i("SecondFragment","die Liste aller Tags wurde angeklickt; das Element: "+auswahlAdd);
                view.setSelected(true);
            }
        });

        Button waehlen=(Button) view.findViewById(R.id.wählen);
        waehlen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(auswahlAdd.equals("")){
                    Log.i("SecondFragment","Button waehlen: hinzufuegen von nichts ");
                }
                else{
                    String temp=auswahlAdd;
                    auswahlAdd="";
                    Log.i("SecondFragment","Button waehlen: hinzufuegen von "+temp);
                    alleTags.remove(temp);
                    alleTagsListeAdapter.notifyDataSetChanged();
                    aktiveTags.add(temp);
                    tagListAdapter.notifyDataSetChanged();
                }
            }
        });

        Button loeschen=(Button) view.findViewById(R.id.löschen);
        loeschen.setOnClickListener(new View.OnClickListener(){
            @Override
        public void onClick(View view){
                if(auswahlRemove.equals("")){
                    Log.i("SecondFragment","Button löschen: nichts wurde gelöscht");
                }
                else{
                    String temp=auswahlRemove;
                    auswahlRemove="";
                    Log.i("SecondFragment","Button Löschen: Löschen von "+temp);
                    aktiveTags.remove(temp);
                    tagListAdapter.notifyDataSetChanged();
                    alleTags.add(temp);
                    alleTagsListeAdapter.notifyDataSetChanged();
                }
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
                new SenderTask().execute("Insert", text, adresse, tagList);
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
