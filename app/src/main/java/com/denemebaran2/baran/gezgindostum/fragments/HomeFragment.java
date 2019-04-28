package com.denemebaran2.baran.gezgindostum.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.denemebaran2.baran.gezgindostum.R;
import com.denemebaran2.baran.gezgindostum.activities.DetailActivity;
import com.denemebaran2.baran.gezgindostum.adapters.CustomAdapter;
import com.denemebaran2.baran.gezgindostum.models.SehirModel;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {


    public HomeFragment() {
        // Required empty public constructor
    }

    List<SehirModel> listem = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container,
                false);
        ListView listView = view.findViewById(R.id.listviewSehirler);

        listem.add(new SehirModel(R.mipmap.ic_launcher,"Muğla"
                ,"Türkiyenin En Güzel Şehri"));
        listem.add(new SehirModel(R.mipmap.ic_launcher,"Elazığ"
                ,"Türkiyenin En Güzel Şehri"));
        listem.add(new SehirModel(R.mipmap.ic_launcher,"Ağrı"
                ,"Türkiyenin En Güzel Şehri"));
        CustomAdapter adapter = new CustomAdapter(getLayoutInflater(),
                listem);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView,
                                    View view, int i, long l) {
                Intent a = new Intent(getContext(),DetailActivity.class);
                switch (i) {
                    case 0:
                        a.putExtra("baslik","Mugla");
                        a.putExtra("aciklama","Muglanin aciklamasi");
                        startActivity(a);
                        break;
                    case 1:
                        a.putExtra("baslik","Elazig");
                        a.putExtra("aciklama","Elazig aciklamasi");
                        startActivity(a);
                        break;
                    case 2:
                        a.putExtra("baslik","Agri");
                        a.putExtra("aciklama","Agri aciklamasi");
                        startActivity(a);
                        break;
                }
            }
        });

        return view;
    }

}
