package com.example.norkholis.piapia.adapter;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.norkholis.piapia.R;
import com.example.norkholis.piapia.activity.DetailSkripsiActivity;
import com.example.norkholis.piapia.model.SkripsiModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.example.norkholis.piapia.activity.DetailSkripsiActivity.EXTRA_ABSTRAK;
import static com.example.norkholis.piapia.activity.DetailSkripsiActivity.EXTRA_BIDANG;
import static com.example.norkholis.piapia.activity.DetailSkripsiActivity.EXTRA_JUDUL;
import static com.example.norkholis.piapia.activity.DetailSkripsiActivity.EXTRA_KUNCI;
import static com.example.norkholis.piapia.activity.DetailSkripsiActivity.EXTRA_PENULIS;

public class SkripsiAdapter extends RecyclerView.Adapter<SkripsiAdapter.SkripsiHolder>  {
    private ArrayList<SkripsiModel> mData = new ArrayList<>();
    private LayoutInflater mInflater;
    private Context context;

    public SkripsiAdapter(Context context) {
        this.context = context;
    }
    public void setData(List<SkripsiModel> items){
        mData.addAll(items);
        notifyDataSetChanged();
    }

    public ArrayList<SkripsiModel> getmData() {
        return mData;
    }

    @NonNull
    @Override
    public SkripsiHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemRow = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_skripsi, parent, false);
        return new SkripsiHolder(itemRow,context);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull SkripsiHolder holder, final int position) {
        holder.judulnya.setText(mData.get(position).getJudulSkripsi());
        holder.bidangnya.setText(mData.get(position).getBidangMinat());
        holder.penulisnya.setText(mData.get(position).getNamaPenulis()+" / "+mData.get(position).getNpmPenulis());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context , DetailSkripsiActivity.class);
                i.putExtra(EXTRA_JUDUL,mData.get(position).getJudulSkripsi());
                i.putExtra(EXTRA_BIDANG,mData.get(position).getBidangMinat());
                i.putExtra(EXTRA_PENULIS,mData.get(position).getNamaPenulis()+" / "+mData.get(position).getNpmPenulis());
                i.putExtra(EXTRA_ABSTRAK,mData.get(position).getAbstrak());
                i.putExtra(EXTRA_KUNCI,mData.get(position).getKataKunci());
                context.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }


    public class SkripsiHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.judul_skripsi) TextView judulnya;
        @BindView(R.id.bidang_skripsi) TextView bidangnya;
        @BindView(R.id.penulis_skripsi) TextView penulisnya;
        public SkripsiHolder(View itemView, Context context) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
