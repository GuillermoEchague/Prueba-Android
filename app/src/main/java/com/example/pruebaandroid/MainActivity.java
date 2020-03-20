package com.example.pruebaandroid;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pruebaandroid.api.ApiDog;
import com.example.pruebaandroid.api.RetrofitClient;
import com.example.pruebaandroid.model.BreedListResponse;
import com.example.pruebaandroid.ui.FragmentDetails;
import com.example.pruebaandroid.ui.FragmentFavourite;
import com.example.pruebaandroid.ui.FragmentListBreeds;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    FragmentFavourite fragmentFavourite;

    private String perro1;
    private FirebaseFirestore db;
    private Button btnFavorito;
    TextView textView;
    FrameLayout frameLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fragmentFavourite = new FragmentFavourite();

        // API
        ApiDog service = RetrofitClient.getRetrofitInstance().create(ApiDog.class);
        Call<BreedListResponse> call = service.getBreedList();

        //Async
        call.enqueue(new Callback<BreedListResponse>() {
            @Override
            public void onResponse(Call<BreedListResponse> call, Response<BreedListResponse> response) {
                initializeViews();
                if (response.body() != null) {
                    List<String> perritos = response.body().getBreedList();
                    Log.e("PERRITOS", String.valueOf(perritos));
                    inFragment(perritos);
                }
            }
            @Override
            public void onFailure(Call<BreedListResponse> call, Throwable t) {
              //  Toast.makeText(getApplicationContext(), "Error: intentelo otra vez",
              //          Toast.LENGTH_SHORT).show();
                Log.e("PERRITOS", String.valueOf(t));
            }
        });

        // Desarrollo Conexión BD Firestore

        // db = FirebaseFirestore.getInstance();

        btnFavorito = findViewById(R.id.btnFavorito);
        btnFavorito.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"Fragment Favorito",Toast.LENGTH_SHORT).show();
                getSupportFragmentManager().beginTransaction().
                        add(R.id.fragments,fragmentFavourite).
                        addToBackStack(null).
                        commit();
            }
        });
    }

     public void onClick(View view) {
                getSupportFragmentManager().beginTransaction().
                add(R.id.fragments, fragmentFavourite).addToBackStack(null)
                   .commit();
     }

    private void inFragment(List<String> list){
      FragmentListBreeds fragmentListBreeds = FragmentListBreeds.newInstance(list);
      getSupportFragmentManager()
              .beginTransaction()
              .add(R.id.fragments, fragmentListBreeds, fragmentListBreeds.getClass().getSimpleName())
      .commit();

    }

    private void initializeViews(){
        textView = findViewById(R.id.textListDog);
        frameLayout = findViewById(R.id.fragments);
    }


}
