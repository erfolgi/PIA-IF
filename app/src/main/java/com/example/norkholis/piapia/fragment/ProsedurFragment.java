package com.example.norkholis.piapia.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.norkholis.piapia.DashActivity;
import com.example.norkholis.piapia.R;
import com.example.norkholis.piapia.adapter.BeritaAdapter;
import com.example.norkholis.piapia.api.APIClient;
import com.example.norkholis.piapia.model.Datum;
import com.example.norkholis.piapia.model.KategoriModel;
import com.example.norkholis.piapia.presenter.BeritaPresenter;
import com.example.norkholis.piapia.view.BeritaView;

import java.util.List;

import retrofit2.Call;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProsedurFragment extends Fragment implements BeritaView{
    private BeritaAdapter BA;
    private APIClient apiClient ;
    private RecyclerView RV;
    private SwipeRefreshLayout SW;
    View view;
    BeritaPresenter beritaPresenter;

    public ProsedurFragment() {
        // Required empty public constructor
    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(false);
    }
    @Override
    public void onPrepareOptionsMenu(Menu menu) {
        super.onPrepareOptionsMenu(menu);
// You can hide the state of the menu item here if you call getActivity().supportInvalidateOptionsMenu(); somewhere in your code
        //MenuItem menuItem = menu.findItem(R.id.action_all);
        //menu.setGroupEnabled();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ((DashActivity) getActivity()).setActionBarTitle("Prosedur");
        view= inflater.inflate(R.layout.fragment_prosedur, container, false);
        setHasOptionsMenu(false);
        RV = (RecyclerView)view.findViewById(R.id.rv_prosedur);
        beritaPresenter=new BeritaPresenter();

        SW=view.findViewById(R.id.sw_prosedur);
        SW.setColorSchemeResources(R.color.colorAccent,
                android.R.color.holo_blue_bright,
                android.R.color.holo_red_light,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light
        );
        SW.post(new Runnable() {
            @Override
            public void run() {
                SW.setRefreshing(true);
                loadBerita();
            }
        });
        SW.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                SW.setRefreshing(true);
                loadBerita();

            }
        });

        return view;
    }
    public void loadBerita(){
        apiClient= new APIClient();
        Call<KategoriModel> apiCall = apiClient.getService().getDataByKategori("Prosedur");
        beritaPresenter.setBeritaView(this);
        beritaPresenter.setApiCall(apiCall);
        beritaPresenter.setApiClient(apiClient);
        beritaPresenter.getBerita();
    }

    @Override
    public void showBerita(List<Datum> items) {
        RV.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
        RV.setLayoutManager(new LinearLayoutManager(getContext()));
        BA = new BeritaAdapter(getContext());
        BA.setData(items);
        RV.setAdapter(BA);
        SW.setRefreshing(false);
    }

    @Override
    public void showError(String t) {
        Toast.makeText(view.getContext(),"Load Failed: "+t,Toast.LENGTH_LONG).show();
        SW.setRefreshing(false);
    }

    @Override
    public void showLoading(ProgressBar progressBar) {

    }

    @Override
    public void hideLoading(ProgressBar progressBar) {

    }
}
