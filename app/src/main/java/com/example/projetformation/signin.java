package com.example.projetformation;


import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class signin extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_in);
        /*goToSignIn = findViewById(R.id.signUp);
        goToSignIn.setOnClickListener(v -> {
            startActivity(new Intent(SignUpActivity.this,SignInActivity.class));
                });*/
    }
}

