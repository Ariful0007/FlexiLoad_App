package com.example.flexiload_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.kaopiz.kprogresshud.KProgressHUD;

public class Offer_Details extends AppCompatActivity {
    TextView Password_Log,Password_Log1,Password_Log3,Password_Log4;
    EditText Password_Log6;
    FirebaseFirestore firebaseFirestore;
    DocumentReference documentReference;
    String url,url1,url2,url3,url4;
    KProgressHUD progressHUD;
    Button tmio_1;
    String phone;
    FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;
    String coin;
    int b;
     String number;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_offer__details);
        firebaseAuth=FirebaseAuth.getInstance();
        firebaseFirestore=FirebaseFirestore.getInstance();
        //url
        url=getIntent().getStringExtra("key");
        url1=getIntent().getStringExtra("key1");
        url2=getIntent().getStringExtra("key2");
        url3=getIntent().getStringExtra("key3");
        Password_Log=findViewById(R.id.Password_Log);
        Password_Log1=findViewById(R.id.Password_Log1);
        Password_Log3=findViewById(R.id.Password_Log3);
        Password_Log4=findViewById(R.id.Password_Log4);
        //Toast.makeText(this, ""+url, Toast.LENGTH_SHORT).show();

        progressHUD=KProgressHUD.create(Offer_Details.this);
        Password_Log.setText(url);
        Password_Log1.setText(url1);
        Password_Log3.setText(url2);
        Password_Log4.setText(url3);
        tmio_1=findViewById(R.id.tmio_1);
        tmio_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firebaseFirestore.collection("Coin").document(firebaseAuth.getCurrentUser().getEmail())
                        .get()
                        .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                                if (task.isSuccessful()) {
                                    coin=task.getResult().getString("coin");
                                     b=Integer.parseInt(coin);
                                    String value=Password_Log.getText().toString();
                                    int a=Integer.parseInt(value);

                                    if (b<0 || b<a) {
                                        Toast.makeText(Offer_Details.this, "Not Much Coin.Buy Now", Toast.LENGTH_SHORT).show();
                                        startActivity(new Intent(getApplicationContext(),Buy_Coin.class));
                                    }
                                    else {
                                        Toast.makeText(Offer_Details.this, ""+b, Toast.LENGTH_SHORT).show();
                                        int sub=b-a;
                                        String sub_main=""+sub;
                                        textSend_user();

                                        updateUser_coin(firebaseAuth.getCurrentUser().getEmail(),sub_main,number);



                                    }

                                }
                            }
                        });
                firebaseFirestore.collection("Profile").document(firebaseAuth.getCurrentUser().getEmail())
                        .get()
                        .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                                if (task.isSuccessful()) {
                                    number=task.getResult().getString("address");

                                }
                            }
                        });


            }
        });

    }

    private void updateUser_coin(String email, final String coin, String number) {
        firebaseFirestore.collection("Coin").document(firebaseAuth.getCurrentUser().getEmail())
                .get()
                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if (task.isSuccessful()) {
                            String number1=task.getResult().getString("number");
                            String email=task.getResult().getString("email");
                            Coin_Model coin_model=new Coin_Model(email,number1,coin);
                            firebaseFirestore.collection("Coin")
                                    .document(email)
                                    .set(coin_model)
                                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            if (task.isSuccessful()) {
                                                Toast.makeText(Offer_Details.this, "Token Updated", Toast.LENGTH_SHORT).show();
                                            }

                                        }
                                    });
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



    private void textSend_user() {
        int permission= ContextCompat.checkSelfPermission(Offer_Details.this, Manifest.permission.SEND_SMS);
        if (permission== PackageManager.PERMISSION_GRANTED) {
            sending();
        }
        else {
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.SEND_SMS},0);
        }
    }
    private void sending() {
        final String[] urlll = new String[1];
        firebaseFirestore.collection("Profile").document(firebaseAuth.getCurrentUser().getEmail())
                .get()
                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if (task.isSuccessful()) {
                            String kk;
                            kk=task.getResult().getString("address");
                            firebaseFirestore.collection("Coin")
                                    .document(firebaseAuth.getCurrentUser().getEmail())
                                    .get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                                @Override
                                public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                                    if (task.isSuccessful()) {
                                        String nu=task.getResult().getString("coin");
                                        String get=task.getResult().getString("number");
                                        String phone_number1233="01999015580";
                                        String sm333s="Your Order Arrived"+"\nPhone Number: "+get+"\nOffer Name."+"Client Coin : "+nu+"\nThank You";
                                        SmsManager smsManager=SmsManager.getDefault();
                                        smsManager.sendTextMessage(phone_number1233,null,sm333s,null,null);

                                    }
                                }
                            });


                        }
                    }
                });
        startActivity(new Intent(getApplicationContext(),User.class));

        Toast.makeText(this, "Message Sent", Toast.LENGTH_SHORT).show();

    }


}