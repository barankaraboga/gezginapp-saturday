package com.denemebaran2.baran.gezgindostum.activities;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.denemebaran2.baran.gezgindostum.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddNoteActivity extends AppCompatActivity {
    EditText userNoteEt;
    Button addNoteBtn;
    Button goToNotesPage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);
        userNoteEt = findViewById(R.id.user_notes_et);
        addNoteBtn = findViewById(R.id.add_notes_btn);
        goToNotesPage = findViewById(R.id.go_to_notes_btn);
        addNoteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addNote();

            }
        });
        goToNotesPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });


    }

    private void addNote() {
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference myRef = firebaseDatabase.getReference().child("GezdigimYerler");
        String notesId = myRef.push().getKey();
        String receivedUserNote = userNoteEt.getText().toString();
        if (receivedUserNote.length() > 0) {
            myRef.child(notesId).child("sehirAdi").setValue(receivedUserNote);
            showDialog("İşlem Başarılı",
                    "Notunuz kaydedildi!");
        } else {
            showDialog("İşlem başarısız",
                    "Not alanı Boş Bırakkılamaz");

    }
        userNoteEt.setText("");
    }

    private void showDialog(String title, String message) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(AddNoteActivity.this);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.setNegativeButton("Tamam", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        builder.show();
    }

}
