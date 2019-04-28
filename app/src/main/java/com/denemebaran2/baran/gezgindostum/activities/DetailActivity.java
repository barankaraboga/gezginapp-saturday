package com.denemebaran2.baran.gezgindostum.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.denemebaran2.baran.gezgindostum.R;

public class DetailActivity extends AppCompatActivity {

    private TextView baslik,aciklama;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        baslik = findViewById(R.id.textViewDetayBaslik);
        aciklama = findViewById(R.id.textViewDetayAciklama);
        Bundle bundle = getIntent().getExtras();
        String baslik_deger = bundle.getString("baslik");
        String aciklama_deger = bundle.getString("aciklama");
        baslik.setText(baslik_deger);
        aciklama.setText(aciklama_deger);

    }
}
