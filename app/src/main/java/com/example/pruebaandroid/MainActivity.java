package com.example.pruebaandroid;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    FragmentDetails fragmentDetails;
    FragmentFavourite fragmentFavourite;
    FragmentListBreeds fragmentListBreeds;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fragmentListBreeds = new FragmentListBreeds();
        fragmentFavourite = new FragmentFavourite();
        fragmentDetails = new FragmentDetails();

        getSupportFragmentManager().beginTransaction().add(R.id.fragments,fragmentListBreeds).commit();



    }

    public void onClick(View view) {
        FragmentTransaction transaction =  getSupportFragmentManager().beginTransaction();

        switch (view.getId()){
            case R.id.btnFavorito:
                transaction.replace(R.id.fragments,fragmentFavourite);
            break;
        }



        transaction.commit();
    }
}
