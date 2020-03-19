package com.example.pruebaandroid.ui;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.pruebaandroid.R;
import com.example.pruebaandroid.adapters.AdapterImageDog;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentDetails#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentDetails extends Fragment implements AdapterImageDog.ItemOnClik2 {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    //private static final String ARG_PARAM2 = "param2";
    private ArrayList<String> mParam1;

    private AdapterImageDog adapter;
    private RecyclerView recyclerView;
    List<String> dogs = new ArrayList<>();

    // TODO: Rename and change types of parameters
    //private String mParam1;
    //private String mParam2;

    public FragmentDetails() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragmentDetails.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentDetails newInstance(List<String> param1) {
        FragmentDetails fragment = new FragmentDetails();
        Bundle args = new Bundle();
        args.putStringArrayList(ARG_PARAM1, (ArrayList<String>) param1);
        //args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mParam1 = getArguments().getStringArrayList(ARG_PARAM1);
            dogs = mParam1;

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_details, container, false);
        recyclerView = view.findViewById(R.id.details);
        Log.e("Algo pasa", String.valueOf(dogs.size()));
        adapter = new AdapterImageDog(dogs, getContext(), this);
        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);

        return view;
    }
}
