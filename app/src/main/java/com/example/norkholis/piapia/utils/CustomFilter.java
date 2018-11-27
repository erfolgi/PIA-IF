package com.example.norkholis.piapia.utils;

import android.widget.Filter;

import com.example.norkholis.piapia.adapter.BeritaAdapter;
import com.example.norkholis.piapia.model.BeritaModel;

import java.util.ArrayList;

public class CustomFilter extends Filter{

    BeritaAdapter adapter;
    ArrayList<BeritaModel> filterList;

    public CustomFilter(ArrayList<BeritaModel> filterList,BeritaAdapter adapter)
    {
        this.adapter=adapter;
        this.filterList=filterList;

    }

    //FILTERING OCURS
    @Override
    protected FilterResults performFiltering(CharSequence constraint) {
        FilterResults results=new FilterResults();

        //CHECK CONSTRAINT VALIDITY
        if(constraint != null && constraint.length() > 0)
        {
            //CHANGE TO UPPER
            constraint=constraint.toString().toUpperCase();
            //STORE OUR FILTERED PLAYERS
            ArrayList<BeritaModel> filteredPlayers=new ArrayList<>();

            for (int i=0;i<filterList.size();i++)
            {
                //CHECK
                if(filterList.get(i).getJudul().toUpperCase().contains(constraint))
                {
                    //ADD PLAYER TO FILTERED PLAYERS
                    filteredPlayers.add(filterList.get(i));
                }
            }

            results.count=filteredPlayers.size();
            results.values=filteredPlayers;
        }else
        {
            results.count=filterList.size();
            results.values=filterList;

        }

        return results;
    }

    @Override
    protected void publishResults(CharSequence constraint, FilterResults results) {

        //adapter.getmData()= (ArrayList<BeritaModel>) results.values;

        //REFRESH
        adapter.notifyDataSetChanged();
    }
}