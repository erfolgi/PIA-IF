package com.example.norkholis.piapia.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.view.menu.MenuView;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.example.norkholis.piapia.DashActivity;
import com.example.norkholis.piapia.R;
import com.example.norkholis.piapia.activity.DetailBeritaActivity;
import com.example.norkholis.piapia.model.BeritaModel;
import com.example.norkholis.piapia.model.Datum;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.example.norkholis.piapia.activity.DetailBeritaActivity.EXTRA_ID;

public class BeritaAdapter extends RecyclerView.Adapter<BeritaAdapter.BeritaHolder> {
    private ArrayList<Datum> mData = new ArrayList<>();
    private LayoutInflater mInflater;
    private Context context;

    public BeritaAdapter(Context con) {
        this.context = con;
//        mInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    public void setData(List<Datum> items){

        mData.addAll(items);
        notifyDataSetChanged();
    }


    public ArrayList<Datum> getmData() {
        return mData;
    }

    @NonNull
    @Override
    public BeritaHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemRow = LayoutInflater.from(parent.getContext()).inflate(R.layout.itemberita, parent, false);
        return new BeritaHolder(itemRow,context);
    }

    @Override
    public void onBindViewHolder(@NonNull final BeritaHolder holder, final int position) {
        holder.textViewJudul.setText(mData.get(position).getJudul());
        //holder.textViewDeskripsi.setText(mData.get(position).getIsi());
        holder.textViewTanggal.setText(mData.get(position).getTanggal());
        holder.idBerita=mData.get(position).getIdBerita();
        holder.textViewDeskripsi.setText(Html.fromHtml(Html.fromHtml(mData.get(position).getIsi()).toString()));

        if (!mData.get(position).getGambar().equals("")){
            holder.progressBar.setVisibility(View.VISIBLE);
            Glide.with(context)
                .load("http://piatf.000webhostapp.com/assets/uploads/files/"+mData.get(position).getGambar())
                .listener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                        holder.progressBar.setVisibility(View.INVISIBLE);
                            return false;
                        }

                        @Override
                        public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                            holder.progressBar.setVisibility(View.INVISIBLE);
                            return false;
                        }
                }).into(holder.gambar);
        }else {
            Glide.with(context)
                    .load(R.drawable.upn)
                    .into(holder.gambar);
            holder.progressBar.setVisibility(View.INVISIBLE);
        }


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context , DetailBeritaActivity.class);
                i.putExtra(EXTRA_ID,mData.get(position).getIdBerita());
                context.startActivity(i);
            }
        });

        holder.klik();


    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class BeritaHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.judul) TextView textViewJudul;
        @BindView(R.id.deskripsi) TextView textViewDeskripsi;
        @BindView(R.id.tanggal) TextView textViewTanggal;
        @BindView(R.id.loadgmbr) ProgressBar progressBar;
        @BindView(R.id.gambarberita) ImageView gambar;
        String idBerita;
        Context con;
        View view;
       // @BindView(R.id.gambar)  ImageView imageViewGambar;
        public BeritaHolder(View itemView, final Context con) {
            super(itemView);
            ButterKnife.bind(this,itemView);
            view=itemView;
            this.con=con;
            itemView.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view) {


                }
            });

        }
        public void klik(){

        }
    }


}
