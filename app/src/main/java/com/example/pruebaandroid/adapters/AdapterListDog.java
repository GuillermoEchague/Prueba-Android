package com.example.pruebaandroid.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pruebaandroid.R;
import com.example.pruebaandroid.model.BreedListResponse;

import java.util.List;

public class AdapterListDog extends RecyclerView.Adapter<AdapterListDog.ViewHolderListDog> {

    private List<String> BreedList;
   // private OnClickListener listener;
   //private OnLongClickListener listenerLong;
    private ItemOnClick listener;
    private Context context;

    public AdapterListDog(List<String> breedList, Context context, ItemOnClick listener) {
        this.BreedList = breedList;
        this.context = context;
        this.listener = listener;

    }

    @NonNull
    @Override
    public ViewHolderListDog onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_dog,parent,false);
        return new ViewHolderListDog(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderListDog holder, final int position) {
        holder.breedList.setText(BreedList.get(position));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onClick(BreedList.get(position));
            }
        });

    }

    @Override
    public int getItemCount() {
        return BreedList.size();
    }

    public class ViewHolderListDog extends RecyclerView.ViewHolder {
       private TextView breedList;

        public ViewHolderListDog(@NonNull View itemView) {
            super(itemView);
            breedList = itemView.findViewById(R.id.titleDog);
        }
    }

    public interface ItemOnClick {
        void onClick(String ListDog);
    }
}
