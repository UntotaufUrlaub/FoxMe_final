package com.example.jakob.foxme;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;

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

    //jakob
    ProfilSpeicherungsVerwaltung a;
    EditText geburtsdatum;
    Spinner spinner1;
    Spinner spinner2;
    Switch alkohol;
    Switch fisch;
    Switch kinder;
    Switch italienisch;
    Switch afrikanisch;
    Switch mexikanisch;
    Switch asiatisch;
    EditText firmenName;
    EditText firmenAdresse;
    EditText telefonNr;
    String[] zustaende={" ","Männlich","Halal","Alkohol","Fisch"," ","Italienisch","Afrikanisch"," ","Asiatisch"," "," "," "};
    private OnFragmentInteractionListener mListener;
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
        String[] speicherTeile=speicherGesammt.split("; ");
        int laenge=speicherTeile.length;
        //ausgabe der StringTeile
        for(int i=0;i<laenge;i++) {
            Log.i("Thirdfragment", "speicherTeile: " + i + " : " + speicherTeile[i]);
        }
        Log.i("Thirdfragment", "zustaende.length: "+zustaende.length);
        if(laenge==zustaende.length){
            for(int i=0;i<laenge;i++){
                zustaende[i]=speicherTeile[i];
            }
        }
        else{
            Log.i("Thirdfragment", "Länge des speichers passt nicht; länge des speichers: "+laenge+" Überschreiben des speichers; und setzt das Profil auf den standart zustand");
            a.save(parse(zustaende));
        }

        //setzt die oberfläche gemäß dem zustand
        geburtsdatum=(EditText) view.findViewById(R.id.editText3);
        if(zustaende[0].equals(" ")){
        }
        else{
            geburtsdatum.setHint(zustaende[0]);
        }

        spinner1=(Spinner) view.findViewById(R.id.spinner);
        spinner1.setSelection(getIndex(spinner1,zustaende[1]));


        spinner2=(Spinner) view.findViewById(R.id.spinner2);
        spinner2.setSelection(getIndex(spinner2,zustaende[2]));

        alkohol=(Switch) view.findViewById(R.id.switch1);
        if(zustaende[3].equals("Alkohol")){
            alkohol.setChecked(true);
            //alkohol.setSelected(false);
        }
        else{
            alkohol.setChecked(false);
        }
        //ab hier noch Listener
        fisch=(Switch) view.findViewById(R.id.switch2);
        if(zustaende[4].equals("Fisch")){
            fisch.setChecked(true);
        }
        else{
            fisch.setChecked(false);
        }

        kinder=(Switch) view.findViewById(R.id.switch3);
        if(zustaende[5].equals("Kinder")){
            kinder.setChecked(true);
        }
        else{
            kinder.setChecked(false);
        }

        italienisch=(Switch) view.findViewById(R.id.switch_italienisch);
        if(zustaende[6].equals("Italienisch")){
            italienisch.setChecked(true);
        }
        else{
            italienisch.setChecked(false);
        }

        afrikanisch=(Switch) view.findViewById(R.id.switch_afrikanisch);
        if(zustaende[7].equals("Afrikanisch")){
            afrikanisch.setChecked(true);
        }
        else{
            afrikanisch.setChecked(false);
        }

        mexikanisch=(Switch) view.findViewById(R.id.switch_mexikanisch);
        if(zustaende[8].equals("Mexikanisch")){
            mexikanisch.setChecked(true);
        }
        else{
            mexikanisch.setChecked(false);
        }

        asiatisch=(Switch) view.findViewById(R.id.switch_asiatisch);
        if(zustaende[9].equals("Asiatisch")){
            asiatisch.setChecked(true);
        }
        else{
            asiatisch.setChecked(false);
        }

        firmenName=(EditText) view.findViewById(R.id.editText6);
        if(zustaende[10].equals(" ")){
        }
        else{
            firmenName.setHint(zustaende[10]);
        }

        firmenAdresse=(EditText) view.findViewById(R.id.editText5);
        if(zustaende[11].equals(" ")){
        }
        else{
            firmenAdresse.setHint(zustaende[11]);
        }

        telefonNr=(EditText) view.findViewById(R.id.editText4);
        if(zustaende[12].equals(" ")){
        }
        else{
            telefonNr.setHint(zustaende[12]);
        }

        //speicherung durch Listener

        geburtsdatum.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                zustaende[0]=geburtsdatum.getText().toString();
                a.save(parse(zustaende));
            }
        });

        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String selectedItem=spinner1.getSelectedItem().toString();
                Log.i("spinner 1","selected item: "+selectedItem);
                zustaende[1]=selectedItem;
                a.save(parse(zustaende));
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String selectedItem=spinner2.getSelectedItem().toString();
                Log.i("spinner 2","selected id: "+selectedItem);
                zustaende[2]=selectedItem;
                a.save(parse(zustaende));
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

        alkohol.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b) {
                    zustaende[3] = "Alkohol";
                }
                else{
                    zustaende[3]= " ";
                }
                a.save(parse(zustaende));
                Log.i("Alkohol","changed to:" + zustaende[3]);
            }
        });

        fisch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b) {
                    zustaende[4] = "Fisch";
                }
                else{
                    zustaende[4]= " ";
                }
                a.save(parse(zustaende));
                Log.i("Fisch","changed to:" + zustaende[4]);
            }
        });

        kinder.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b) {
                    zustaende[5] = "Kinder";
                }
                else{
                    zustaende[5]= " ";
                }
                a.save(parse(zustaende));
                Log.i("Kinder","changed to:" + zustaende[5]);
            }
        });

        italienisch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b) {
                    zustaende[6] = "Italienisch";
                }
                else{
                    zustaende[6]= " ";
                }
                a.save(parse(zustaende));
                Log.i("Italienisch","changed to:" + zustaende[6]);
            }
        });

        afrikanisch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b) {
                    zustaende[7] = "Afrikanisch";
                }
                else{
                    zustaende[7]= " ";
                }
                a.save(parse(zustaende));
                Log.i("Afrikanisch","changed to:" + zustaende[7]);
            }
        });

        mexikanisch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b) {
                    zustaende[8] = "Mexikanisch";
                }
                else{
                    zustaende[8]= " ";
                }
                a.save(parse(zustaende));
                Log.i("Mexikanisch","changed to:" + zustaende[8]);
            }
        });

        asiatisch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b) {
                    zustaende[9] = "Asiatisch";
                }
                else{
                    zustaende[9]= " ";
                }
                a.save(parse(zustaende));
                Log.i("Asiatisch","changed to:" + zustaende[9]);
            }
        });

        firmenName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                zustaende[10]=firmenName.getText().toString();
                a.save(parse(zustaende));
            }
        });

        firmenAdresse.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                zustaende[11]=firmenAdresse.getText().toString();
                a.save(parse(zustaende));
            }
        });

        telefonNr.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                zustaende[12]=telefonNr.getText().toString();
                a.save(parse(zustaende));
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

    //Jakob
    private String parse(String[] eingabe) {
        String ausgabe = "";
        for (int i = 0; i < eingabe.length - 1; i++) {
            ausgabe = ausgabe + eingabe[i] + "; ";
        }
        ausgabe = ausgabe + eingabe[eingabe.length - 1];
        Log.i("ThirdFragment", "ergebnis von parse: " + ausgabe);
        return ausgabe;
    }

    private int getIndex(Spinner spinner,String value){
        int index=0;
        for (int i=0;i<spinner.getCount();i++){
            if (spinner.getItemAtPosition(i).toString().equalsIgnoreCase(value)){
                Log.i("ThirdFragment","getIndex: gefunden; "+"Item at i="+i+": "+spinner.getItemAtPosition(i).toString());
                index = i;
                break;
            }
        }
        Log.i("ThirdFragment","getIndex; spinner erstes Item: "+spinner.getItemAtPosition(0)+" value: "+value+" index: "+index);
        return index;
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
    //
}
