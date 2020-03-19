package com.example.pruebaandroid.ui;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.pruebaandroid.R;
import com.example.pruebaandroid.adapters.AdapterListDog;
import com.example.pruebaandroid.api.ApiDog;
import com.example.pruebaandroid.api.RetrofitClient;
import com.example.pruebaandroid.model.BreedImageListResponse;
import com.example.pruebaandroid.model.BreedListResponse;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.pruebaandroid.api.RetrofitClient.*;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentListBreeds#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentListBreeds extends Fragment  implements AdapterListDog.ItemOnClick {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER

    private static final String ARG_PARAM1 = "param1";
    //private static final String ARG_PARAM2 = "param2";
    private ArrayList<String> mParam1;
    private  AdapterListDog adapter;
    private RecyclerView recyclerView;
    List<String> dogList = new ArrayList<>();


    // TODO: Rename and change types of parameters



    public FragmentListBreeds() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FrgamentsListBreeds.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentListBreeds newInstance(List<String> param1) {
        FragmentListBreeds fragment = new FragmentListBreeds();
        Bundle args = new Bundle();
        args.putStringArrayList(ARG_PARAM1, (ArrayList<String>) param1);
        Log.e("BREEDLISTFRAG", String.valueOf(param1));
        //args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ApiDog service = RetrofitClient.getRetrofitInstance().create(ApiDog.class);
        Call<BreedListResponse> call = service.getBreedList();


        if (getArguments() != null) {
            mParam1 = getArguments().getStringArrayList(ARG_PARAM1);
            //mParam2 = getArguments().getString(ARG_PARAM2);
               dogList = mParam1;
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_breeds,container,false);
        recyclerView = view.findViewById(R.id.breedsList);
        Log.e("Pasa Algo", String.valueOf(dogList.size()));
        adapter = new AdapterListDog(dogList, getContext(), this);
        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        return view;
    }

    @Override
    public void onClick(String ListDog) {
        Toast.makeText(getContext(),ListDog,Toast.LENGTH_SHORT).show();
        photoCalls(ListDog);
    }

    private void photoCalls(String dogNames){
        ApiDog service = RetrofitClient.getRetrofitInstance().create(ApiDog.class);
        Call<BreedImageListResponse>  callImages = service.getBreedImageList(dogNames);
        callImages.enqueue(new Callback<BreedImageListResponse>() {
            @Override
            public void onResponse(Call<BreedImageListResponse> call, Response<BreedImageListResponse> response) {
                List<String> imageURL = response.body().getImageURL();
                Log.e("IMAGENDOG", String.valueOf(imageURL));
                instanPhotoFragment(imageURL);

            }

            @Override
            public void onFailure(Call<BreedImageListResponse> call, Throwable t) {
            Log.e("FAILT", String.valueOf(t));
            }
        });
    }

    private void instanPhotoFragment(List<String> list){
        FragmentDetails fragmentDetails = FragmentDetails.newInstance(list);
        getActivity().getSupportFragmentManager().beginTransaction().
                replace(R.id.fragments, fragmentDetails, "photoFragmet")
                .addToBackStack("Dog").commit();

    }
}
