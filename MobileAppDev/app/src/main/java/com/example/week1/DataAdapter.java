package com.example.week1;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.ArrayList;

import Model.ListData;

public class DataAdapter extends RecyclerView.Adapter<DataAdapter.ListViewHolder> {

    private ArrayList<ListData> list;

    public DataAdapter(ArrayList<ListData> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public ListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.card_list, parent, false);
        return new ListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListViewHolder holder, int position) {
        holder.card_nama.setText(list.get(position).getNama());
        holder.card_umur.setText(list.get(position).getUmur());
        holder.card_alamat.setText(list.get(position).getAlamat());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), DetailsActivity.class);
                intent.putParcelableArrayListExtra("arraylist", list);
                intent.putExtra("position", position);
                v.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ListViewHolder extends RecyclerView.ViewHolder {
        private TextView card_nama, card_alamat, card_umur;

        public ListViewHolder(@NonNull View itemView) {
            super(itemView);

            card_nama = itemView.findViewById(R.id.card_nama);
            card_alamat = itemView.findViewById(R.id.card_alamat);
            card_umur = itemView.findViewById(R.id.card_umur);
        }
    }
}
