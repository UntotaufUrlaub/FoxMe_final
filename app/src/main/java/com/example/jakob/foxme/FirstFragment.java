package com.example.jakob.foxme;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.example.jakob.foxme.Backend.AnzeigenServiceImpl;
import com.example.jakob.foxme.Backend.Filter;
import com.example.jakob.foxme.Backend.MainController;

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
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_SECTION_NUMBER = "sectionNumber";
    //private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    public int mParam1;

    //private String mParam2;
    //Eigene Attribute Jakob-----------------------------------------------------------------------------------------------------------------------
    ListView konsumentListView;
    ArrayAdapter konsumentArrayAdapter;
    ArrayList<String> konsumentListeInfoTexte=new ArrayList();
    AnzeigenServiceImpl anzeigenService = new AnzeigenServiceImpl();
    private OnFragmentInteractionListener mListener;
    //---------------------------------------------------------------------------------------------------------------------------------------------

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
    // TODO: Rename and change types and number of parameters
    public static FirstFragment newInstance(int sectionNumber) {
        FirstFragment fragment = new FirstFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        //args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getInt(ARG_SECTION_NUMBER);
            //mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_konsument, container, false);

        //refresh button -----------------------------------------------------------------------------------------------------------------------------------

            //deklaration
        konsumentListView = (ListView) view.findViewById(R.id.konsument_list_view);
        konsumentArrayAdapter = new ArrayAdapter<String>(getActivity(), R.layout.info_layout, R.id.info_item, konsumentListeInfoTexte);
        konsumentListView.setAdapter(konsumentArrayAdapter);
            //deklaration ende

            //button
        Button button = (Button) view.findViewById(R.id.button_konsument_fetchit);
        button.setOnClickListener(new View.OnClickListener() {
            MainActivity mainActivity=new MainActivity();
            Filter filter = new Filter();
            public void onClick(View v) {
                // Perform action on click
                Log.i("firstFragment", "erneuern - button geklickt");
                ArrayList<String> temp= null;
                try {
                    new MainController().execute("Select",null,null);
                    temp = anzeigenService.fetchAnzeigentxt(filter.filterit(mainActivity.liste));//new AnzeigenServiceMockup().fetchAnzeige());
                } catch (Exception e) {
                    e.printStackTrace();
                }
                konsumentListeInfoTexte.clear();
                for(int i=0;i<temp.size();i++){
                    konsumentListeInfoTexte.add(temp.get(i));
                }
                konsumentArrayAdapter.notifyDataSetChanged();
            }
        });
       /* Button button_detail = (Button) view.findViewById(R.id.detail_button_Konsument_eine_Anzeige);
        button_detail.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Log.i("firstFragment","details - button geklickt");
                LayoutInflater inflater1 = inflater;
                ViewGroup container1=container;
                inflater1.inflate(R.layout.fragment_konsument, container1, false);
            }
        });*/
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
