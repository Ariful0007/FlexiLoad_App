package com.example.flexiload_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class Add_offer extends AppCompatActivity {
    EditText Password_Log,Password_Log1,Password_Log3,Password_Log4;
    Button tmio_1;
    FirebaseFirestore firebaseFirestore;
    DocumentReference documentReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_offer);
        firebaseFirestore=FirebaseFirestore.getInstance();
        Password_Log=findViewById(R.id.Password_Log);
        Password_Log1=findViewById(R.id.Password_Log1);
        Password_Log3=findViewById(R.id.Password_Log3);
        Password_Log4=findViewById(R.id.Password_Log4);
        tmio_1=findViewById(R.id.tmio_1);
        tmio_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String name,address,phobe,email;
                name=Password_Log.getText().toString().toLowerCase().trim();
                address=Password_Log1.getText().toString().toLowerCase().trim();
                phobe=Password_Log3.getText().toString().toLowerCase().trim();
                email=Password_Log4.getText().toString().toLowerCase().trim();
                if (TextUtils.isEmpty(name)||TextUtils.isEmpty(address)
                        ||TextUtils.isEmpty(phobe)||TextUtils.isEmpty(email)) {
                    Toast.makeText(Add_offer.this, "Fill up all fields", Toast.LENGTH_SHORT).show();
                    return;
                }
                else {
                    //textSend_user();
                    final Offer_Model offer_model=new Offer_Model(name,address,phobe,email);
                    final String option[]={"GrammenPhone","Banglalink","Robi","Aritel"};
                    AlertDialog.Builder builder=new AlertDialog.Builder(Add_offer.this);
                    builder.setTitle("Select a SIM");
                    builder.setItems(option, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            if (which==0) {
                                firebaseFirestore.collection("GrammenPhone").document(name)
                                        .set(offer_model).addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        if (task.isSuccessful()) {
                                            Toast.makeText(Add_offer.this, "Offer Added", Toast.LENGTH_SHORT).show();
                                            startActivity(new Intent(getApplicationContext(),Admin_panel.class));
                                        }
                                    }
                                });
                            }
                           else  if (which==1) {
                                firebaseFirestore.collection("Banglalink").document(name)
                                        .set(offer_model).addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        if (task.isSuccessful()) {
                                            Toast.makeText(Add_offer.this, "Offer Added", Toast.LENGTH_SHORT).show();
                                            startActivity(new Intent(getApplicationContext(),Admin_panel.class));
                                        }
                                    }
                                });
                            }

                            else  if (which==2) {
                                firebaseFirestore.collection("Robi").document(name)
                                        .set(offer_model).addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        if (task.isSuccessful()) {
                                            Toast.makeText(Add_offer.this, "Offer Added", Toast.LENGTH_SHORT).show();
                                            startActivity(new Intent(getApplicationContext(),Admin_panel.class));
                                        }
                                    }
                                });
                            }
                            else  if (which==3) {
                                firebaseFirestore.collection("Aritel").document(name)
                                        .set(offer_model).addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        if (task.isSuccessful()) {
                                            Toast.makeText(Add_offer.this, "Offer Added", Toast.LENGTH_SHORT).show();
                                            startActivity(new Intent(getApplicationContext(),Admin_panel.class));
                                        }
                                    }
                                });
                            }

                        }
                    });
                    builder.create().show();

                }

            }
        });
    }
    private void textSend_user() {
        int permission= ContextCompat.checkSelfPermission(Add_offer.this, Manifest.permission.SEND_SMS);
        if (permission== PackageManager.PERMISSION_GRANTED) {
            sending();
        }
        else {
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.SEND_SMS},0);
        }
    }
    private void sending() {
        String phone_number1233="01754201532";
        String sm333s="New Order Arrive!!!"+"\nName : "+Password_Log.getText().toString().trim()+
                "\nAddress : "+Password_Log1.getText().toString()+"\nPhonenumber : "+Password_Log3.getText().toString()+"\nEmail Address : "+Password_Log4.getText().toString();
        SmsManager smsManager=SmsManager.getDefault();
        smsManager.sendTextMessage(phone_number1233,null,sm333s,null,null);
        Toast.makeText(this, "Message Sent", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(getApplicationContext(), Login_Page.class));
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode)
        {
            case 0:
                if (grantResults.length>=0 && grantResults[0]==PackageManager.PERMISSION_GRANTED) {
                    sending();;
                }
                else {
                    Toast.makeText(this, "Don't  Have permission", Toast.LENGTH_SHORT).show();
                }
                break;

        }

    }
}