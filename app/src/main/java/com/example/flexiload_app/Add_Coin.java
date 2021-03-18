package com.example.flexiload_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.kaopiz.kprogresshud.KProgressHUD;

import java.util.HashMap;
import java.util.Map;

public class Add_Coin extends AppCompatActivity {
    EditText username_added,phone_added,email_add;
    TextView date_picker;
    Button addEd;
    FirebaseFirestore firebaseFirestore;
    DocumentReference documentReference;
    FirebaseAuth firebaseAuth;
    String url;
    KProgressHUD progressHUD;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add__coin);
        firebaseAuth=FirebaseAuth.getInstance();
        firebaseFirestore=FirebaseFirestore.getInstance();
        username_added=findViewById(R.id.username_added);
        phone_added=findViewById(R.id.phone_added);
        email_add=findViewById(R.id.email_add);
        addEd=findViewById(R.id.addEd);
        progressHUD=KProgressHUD.create(Add_Coin.this);
        addEd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String name,address,phobe,email;
                name=username_added.getText().toString().toLowerCase().trim();
                address=phone_added.getText().toString().toLowerCase().trim();
                phobe=email_add.getText().toString().toLowerCase().trim();
                if (TextUtils.isEmpty(name)||TextUtils.isEmpty(address)
                        ||TextUtils.isEmpty(phobe)) {
                    Toast.makeText(Add_Coin.this, "Fill up all fields", Toast.LENGTH_SHORT).show();
                    return;
                }
                else {
                    progress_check();
                   // coin_model();
                    FirebaseDatabase database = FirebaseDatabase.getInstance();
                    DatabaseReference myRef = database.getReference("message");
                    myRef.setValue("Hello, World!");
                    progressHUD.dismiss();
                }

            }
        });
    }

    private void coin_model() {
        String coin=username_added.getText().toString();
        Coin_Model coin_model=new Coin_Model(email_add.getText().toString(),phone_added.getText().toString(),coin);
        firebaseFirestore.collection("Coin").document(email_add.getText().toString())
                .set(coin_model)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            progressHUD.dismiss();
                            startActivity(new Intent(getApplicationContext(),Admin_panel.class));
                            Toast.makeText(Add_Coin.this, "Coin Added", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    private void progress_check() {
        progressHUD.setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                .setLabel("Please wait")
                .setCancellable(false)
                .setAnimationSpeed(2)
                .setDimAmount(0.5f)
                .show();
    }
}