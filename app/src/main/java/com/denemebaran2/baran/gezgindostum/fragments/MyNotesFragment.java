package com.denemebaran2.baran.gezgindostum.fragments;


import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.denemebaran2.baran.gezgindostum.R;
import com.denemebaran2.baran.gezgindostum.activities.AddNoteActivity;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class MyNotesFragment extends Fragment {
ArrayList<String> myNotesList =new ArrayList<>();
String myPlaces;
ListView myNotesListLv;
ArrayAdapter<String> arrayAdapter;
private ProgressDialog progressDialog;

    public MyNotesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        myNotesList= getMyNotes();
        View myNotesView=inflater.inflate(R.layout.fragment_my_notes,
                container, false);
        Button addNotesBtn=myNotesView.findViewById(R.id.fragment_add_notes_btn);
        addNotesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent addNoteIntent= new Intent(getContext(),AddNoteActivity.class);
                startActivity(addNoteIntent);
            }
        });
        myNotesListLv=myNotesView.findViewById(R.id.my_notes_lv);
        arrayAdapter=new ArrayAdapter<String>(getContext(),
                android.R.layout.simple_list_item_1,
                android.R.id.text1, myNotesList);
        myNotesListLv.setAdapter(arrayAdapter);
        return myNotesView ;
    }
    private ArrayList<String> getMyNotes(){
        showProgressDialog();
        final ArrayList<String>  myNotes=new ArrayList<>();
        FirebaseDatabase database=FirebaseDatabase.getInstance();
        final DatabaseReference myRef=database.getReference().child("GezdiğimYerler");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                progressDialog.dismiss();
                for (DataSnapshot ds:dataSnapshot.getChildren()){
                    myPlaces=ds.child("sehirAdi").getValue().toString();
                    myNotes.add(myPlaces);
                }
                arrayAdapter.notifyDataSetChanged();
;            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
progressDialog.dismiss();
            }
        });
        return myNotes;
    }
private  void showProgressDialog(){
        progressDialog= new ProgressDialog(getContext());
        progressDialog.setMessage("Yüklenşyor...");
        progressDialog.setCancelable((false));
        progressDialog.show();
}
}
