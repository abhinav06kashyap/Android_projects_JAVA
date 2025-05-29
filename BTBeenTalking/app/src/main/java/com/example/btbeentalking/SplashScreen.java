package com.example.btbeentalking;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SplashScreen extends AppCompatActivity {

    private DatabaseReference RootRef;
    private FirebaseAuth mAuth;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        mAuth = FirebaseAuth.getInstance();

        RootRef = FirebaseDatabase.getInstance().getReference();


    }


    Thread timer=new Thread()
    {
        public void run() {
            try {

                sleep(3000);
            }
            catch (InterruptedException e) {
                Toast.makeText(SplashScreen.this, "Something went wrong.", Toast.LENGTH_SHORT).show();
            }
            finally
            {

                FirebaseUser currentUser = mAuth.getCurrentUser();
                if(currentUser == null)
                {
                    SendUserToLoginActivity();
                }
                else
                {
                    SendUserToMainActivity();
                }

            }

        }


    };


    @Override
 protected void onStart() {
        super.onStart();
        timer.start();
    }





            private void SendUserToLoginActivity()
            {

                Intent loginIntent = new Intent(SplashScreen.this, LoginActivity.class);
                loginIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(loginIntent);
                finish();
            }

    private void SendUserToMainActivity()
    {
        Intent mainIntent = new Intent(SplashScreen.this, MainActivity.class);
        mainIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(mainIntent);
        finish();
    }

}