package com.example.pruebaandroid.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.pruebaandroid.R;
import com.example.pruebaandroid.pojo.Usuario;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class UsuarioAdapter extends RecyclerView.Adapter<UsuarioAdapter.ViewHolder>{
    List<Usuario> usuarioList = new ArrayList<>();
    Context context;

    public UsuarioAdapter(Context context, List<Usuario> usuarioList){
        this.context = context;
        this.usuarioList = usuarioList;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item,parent,false);
        ViewHolder viewHolder = new ViewHolder(itemView);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.title.setText(usuarioList.get(position).getUsuarioId());
        //Picasso.with(context).load(usuarioList.get(position).getTwitter()).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return usuarioList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView title;
        ImageView imageView;



        public ViewHolder(View item){
            super(item);
            title = (TextView) item.findViewById(R.id.title);
            imageView = (ImageView) item.findViewById(R.id.imageview);
        }
    }

}
