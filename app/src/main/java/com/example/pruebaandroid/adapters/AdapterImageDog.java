package com.example.pruebaandroid.adapters;


import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.pruebaandroid.R;
import com.example.pruebaandroid.model.BreedImageListResponse;

import java.util.List;


public class AdapterImageDog extends RecyclerView.Adapter<AdapterImageDog.ViewHolderDog> {

    private List<String> breedPhoto;
    private Context context;
    private ItemOnClik2 listener;

    public AdapterImageDog(List<String> breedPhoto, Context context, ItemOnClik2 listener) {
        this.breedPhoto = breedPhoto;
        this.context = context;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolderDog onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view = LayoutInflater.from(parent.getContext())
               .inflate(R.layout.item_image_dog,parent,false);

        return new ViewHolderDog(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderDog holder, int position) {

        Log.e("POSITION", String.valueOf(position));
        String breedPhotoList = breedPhoto.get(position);
        Log.e("POSITIONR",String.valueOf(breedPhotoList));
        Glide.with(holder.dogPhotoV.getContext()).load(breedPhotoList)
                .into(holder.dogPhotoV);


    }

    @Override
    public int getItemCount() {

        Log.e("CONT",String.valueOf(breedPhoto.size()));
        return breedPhoto.size();
    }

    public class ViewHolderDog extends RecyclerView.ViewHolder {
        private ImageView dogPhotoV;
        public ViewHolderDog(@NonNull View itemView) {

            super(itemView);
            dogPhotoV = itemView.findViewById(R.id.idImagen);
        }
    }

    public interface ItemOnClik2{

    }
}
