package com.denemebaran2.baran.gezgindostum.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.denemebaran2.baran.gezgindostum.R;
import com.denemebaran2.baran.gezgindostum.models.SehirModel;

import java.util.List;

public class CustomAdapter extends BaseAdapter {

    LayoutInflater layoutInflater;
    List<SehirModel> sehirModelList;
    public CustomAdapter(LayoutInflater layoutInflater,
                         List<SehirModel> sehirModels) {

        this.layoutInflater = layoutInflater;
        this.sehirModelList = sehirModels;
    }
    @Override
    public int getCount() {
        return sehirModelList.size();
    }
    @Override
    public Object getItem(int i) {
        return sehirModelList.get(i);
    }
    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View listeView = layoutInflater.inflate(
                R.layout.liste_tasarim,null
        );
        ImageView imageView = listeView.findViewById(R.id.imageView3);
        TextView baslik = listeView.findViewById(R.id.textView2);
        TextView aciklama = listeView.findViewById(R.id.textView3);

        SehirModel sehirModel = sehirModelList.get(i);
        imageView.setImageResource(sehirModel.getResim());
        baslik.setText(sehirModel.getIsim());
        aciklama.setText(sehirModel.getAciklama());

        return listeView;
    }
}
