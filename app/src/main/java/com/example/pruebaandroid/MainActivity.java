package com.example.pruebaandroid;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.pruebaandroid.api.ApiDog;
import com.example.pruebaandroid.api.RetrofitClient;
import com.example.pruebaandroid.model.BreedImageListResponse;
import com.example.pruebaandroid.model.BreedListResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    FragmentDetails fragmentDetails;
    FragmentFavourite fragmentFavourite;
    FragmentListBreeds fragmentListBreeds;

    private String perro1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fragmentListBreeds = new FragmentListBreeds();
        fragmentFavourite = new FragmentFavourite();
        fragmentDetails = new FragmentDetails();

        getSupportFragmentManager().beginTransaction().add(R.id.fragments,fragmentListBreeds).commit();

// API

        ApiDog service = RetrofitClient.getRetrofitInstance().create(ApiDog.class);
        Call<BreedListResponse> call = service.getBreedList();

        //Async
        call.enqueue(new Callback<BreedListResponse>() {
            @Override
            public void onResponse(Call<BreedListResponse> call, Response<BreedListResponse> response) {
                List<String> perritos = response.body().getBreedList();
                perro1 = perritos.get(0);
                Log.e("PERRITOS", String.valueOf(perritos));

                if (!perro1.equals("")) {
                    whoLetTheDogsOut();
                }


                Toast.makeText(getApplicationContext(), "Funciona!!",
                        Toast.LENGTH_SHORT).show();
                Log.e("PERRITOS",String.valueOf(perritos));

            }

            @Override
            public void onFailure(Call<BreedListResponse> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Error: intentelo otra vez",
                        Toast.LENGTH_SHORT).show();
                Log.e("PERRITOS", String.valueOf(t));
            }
        });





    }

    public void onClick(View view) {
        FragmentTransaction transaction =  getSupportFragmentManager().beginTransaction();

        switch (view.getId()){
            case R.id.btnFavorito:
                transaction.replace(R.id.fragments,fragmentFavourite);
            break;

            case R.id.btnLista:
                transaction.replace(R.id.fragments,fragmentListBreeds);
                break;

            case R.id.btnDetalles:
                transaction.replace(R.id.fragments,fragmentDetails);
                break;


            default:
                transaction.replace(R.id.fragments,fragmentFavourite);
                break;
        }
           transaction.commit();
    }


    private void whoLetTheDogsOut(){

        ApiDog service = RetrofitClient.getRetrofitInstance().create(ApiDog.class);
        Call<BreedImageListResponse> callImages = service.getBreedImageList(perro1);
        callImages.enqueue(new Callback<BreedImageListResponse>() {
            @Override
            public void onResponse(Call<BreedImageListResponse> call, Response<BreedImageListResponse> response) {
                List<String> imagesURL = response.body().getImageURL();
                Log.e("IMAGESDOGS", String.valueOf(imagesURL));
            }
            @Override
            public void onFailure(Call<BreedImageListResponse> call, Throwable t) {
                Log.e("FALLO", String.valueOf(t));
            }
        });
    }



}
