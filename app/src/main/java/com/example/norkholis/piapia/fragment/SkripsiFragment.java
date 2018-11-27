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
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.norkholis.piapia.DashActivity;
import com.example.norkholis.piapia.R;
import com.example.norkholis.piapia.adapter.SkripsiAdapter;
import com.example.norkholis.piapia.api.APIPerpus;
import com.example.norkholis.piapia.model.SkripsiModel;
import com.example.norkholis.piapia.presenter.SkripsiPresenter;
import com.example.norkholis.piapia.view.SkripsiView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;

/**
 * A simple {@link Fragment} subclass.
 */
public class SkripsiFragment extends Fragment implements SkripsiView {
    private SkripsiAdapter SA;
    private APIPerpus apiClient ;
    private RecyclerView RV;
    private SwipeRefreshLayout SW;
    private Spinner SP;
    View view;
    SkripsiPresenter skripsiPresenter;

    public SkripsiFragment() {
        // Required empty public constructor
    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
      //  setHasOptionsMenu(true);
    }
//    @Override
//    public void onCreateOptionsMenu(Menu menu,MenuInflater inflater) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        inflater.inflate(R.menu.dash, menu);
//        super.onCreateOptionsMenu(menu, inflater);
//    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ((DashActivity) getActivity()).setActionBarTitle("Skripsi");
        view= inflater.inflate(R.layout.fragment_skripsi, container, false);
        RV = (RecyclerView)view.findViewById(R.id.rv_skripsi);
        skripsiPresenter=new SkripsiPresenter();
        SW=view.findViewById(R.id.sw_skripsi);
        SP=view.findViewById(R.id.spin_skripsi);
        final String[] spinname=getResources().getStringArray(R.array.bidangminat);
        //val spinnerAdapter = ArrayAdapter(ctx, android.R.layout.simple_spinner_dropdown_item, spinnerItems)
        //spinner.adapter = spinnerAdapter

        ArrayAdapter spinnerAdapter= new ArrayAdapter(getContext(),android.R.layout.simple_spinner_dropdown_item,spinname);
        SP.setAdapter(spinnerAdapter);
        SP.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(position==0){
                    SW.setRefreshing(true);
                    loadBerita();
                }else {
                    SW.setRefreshing(true);
                    loadBidang(spinname[position]);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

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
        apiClient= new APIPerpus();
        Call<List<SkripsiModel>> apiCall = apiClient.getService().getSkripsiList();
        skripsiPresenter.setSkripsiView(this);
        skripsiPresenter.setApiCall(apiCall);
        skripsiPresenter.setApiClient(apiClient);
        skripsiPresenter.retrieveSkripsi();
    }
    public void loadBidang(String bidang){
        apiClient= new APIPerpus();
        Call<List<SkripsiModel>> apiCall = apiClient.getService().getSkripsiByBidang(bidang);
        skripsiPresenter.setSkripsiView(this);
        skripsiPresenter.setApiCall(apiCall);
        skripsiPresenter.setApiClient(apiClient);
        skripsiPresenter.retrieveSkripsi();
    }

    @Override
    public void showSkripsi(List<SkripsiModel> items) {
        RV.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
        RV.setLayoutManager(new LinearLayoutManager(getContext()));
        SA = new SkripsiAdapter(getContext());
        SA.setData(items);
        RV.setAdapter(SA);
        SW.setRefreshing(false);
    }

    @Override
    public void showError(String t) {
        Toast.makeText(view.getContext(),"Load Failed: "+t,Toast.LENGTH_LONG).show();
        SW.setRefreshing(false);
    }
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        switch (item.getItemId()){
//            case R.id.action_all:
//                SW.setRefreshing(true);
//                loadBerita();
//                break;
//            case R.id.action_cis:
//                SW.setRefreshing(true);
//                loadBidang("Computer Intelligent System");
//                break;
//            case R.id.action_seng:
//                SW.setRefreshing(true);
//                loadBidang("Software Engineering");
//                break;
//            case R.id.action_itns:
//                SW.setRefreshing(true);
//                loadBidang("IT Network Security");
//                break;
//            case R.id.action_itsm:
//                SW.setRefreshing(true);
//                loadBidang("IT Service Management");
//                break;
//        }
//        return super.onOptionsItemSelected(item);
//    }
}
