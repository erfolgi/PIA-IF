package com.example.norkholis.piapia.activity;

import android.annotation.SuppressLint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.norkholis.piapia.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailSkripsiActivity extends AppCompatActivity {
    @BindView(R.id.skripsi_judul) TextView judulnya;
    @BindView(R.id.skripsi_bidang) TextView bidangnya;
    @BindView(R.id.skripsi_penulis) TextView penulisnya;
    @BindView(R.id.skripsi_abstrak) TextView abstraknya;
    @BindView(R.id.skripsi_katakunci) TextView kuncinya;

    public static String EXTRA_JUDUL="judulnya";
    public static String EXTRA_BIDANG = "bidangnya";
    public static String EXTRA_PENULIS= "penulisnya";
    public static String EXTRA_ABSTRAK= "abstraknya";
    public static String EXTRA_KUNCI= "kunci";
    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setTitle("Abstrak");
        setContentView(R.layout.activity_detail_skripsi);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ButterKnife.bind(this);
        EXTRA_JUDUL=getIntent().getStringExtra(EXTRA_JUDUL);
        EXTRA_BIDANG = getIntent().getStringExtra(EXTRA_BIDANG);
        EXTRA_PENULIS= getIntent().getStringExtra(EXTRA_PENULIS);
        EXTRA_ABSTRAK= getIntent().getStringExtra(EXTRA_ABSTRAK);
        EXTRA_KUNCI= getIntent().getStringExtra(EXTRA_KUNCI);

        judulnya.setText(EXTRA_JUDUL);
        bidangnya.setText(EXTRA_BIDANG);
        penulisnya.setText(EXTRA_PENULIS);
        abstraknya.setText(EXTRA_ABSTRAK);
        kuncinya.setText("kata kunci : "+EXTRA_KUNCI);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                return true;
            default:
                return true;

        }

    }
}
