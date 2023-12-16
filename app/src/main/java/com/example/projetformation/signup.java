package com.example.projetformation;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class signup extends AppCompatActivity {
    private TextView goToSignIn;
    private EditText name , email, phone , cin, password;
    private Button bouttonsignup ;
    private String fullNameS, emailS, phoneS, cinS, passS;
    private static final String EMAIL_REGEX =
            "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                    + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    private FirebaseAuth firebaseAuth ;
    private ProgressDialog progressDialog ;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        goToSignIn = findViewById(R.id.bouttonsignup);
        name = findViewById(R.id.name);
        email = findViewById(R.id.email);
     phone = findViewById(R.id.phone);
       cin = findViewById(R.id.cin);
        password = findViewById(R.id.password);
        bouttonsignup = findViewById(R.id.bouttonsignup);
        firebaseAuth = FirebaseAuth.getInstance();
        progressDialog = new ProgressDialog(this);


        goToSignIn.setOnClickListener(v -> {
            startActivity(new Intent(signup.this, signin.class));
        });
        bouttonsignup.setOnClickListener(v -> {
            if (validate()) {
                progressDialog.setMessage("Please wait...!!");
                progressDialog.show();
                String emailUser = email.getText().toString().trim();
                String passwordUser = password.getText().toString().trim();
                firebaseAuth.createUserWithEmailAndPassword(emailUser,passwordUser).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            Toast.makeText(signup.this, "Account Created Successfully !", Toast.LENGTH_SHORT).show();
                            progressDialog.dismiss();
                        }
                        else {
                            Toast.makeText(signup.this, "Register Failed !", Toast.LENGTH_SHORT).show();
                            progressDialog.dismiss();
                        }
                    }
                });
            }

        });
    }

    private boolean validate() {
        boolean result = false;
        fullNameS = name.getText().toString();
        emailS = email.getText().toString();
        cinS = cin.getText().toString();
        phoneS = phone.getText().toString();
        passS = password.getText().toString();
        if (fullNameS.isEmpty() || fullNameS.length()<7){
            name.setError("Full Name is invalid!");
        } else if (!isValidEmail(emailS)) {
            email.setError("Email is invalid!");

        } else if (cinS.isEmpty() || cinS.length() != 8 ) {
            cin.setError("Cin is invalid!");

        } else if (phoneS.isEmpty() || phoneS.length() != 8 ) {
            phone.setError("Phone is invalid!");
        } else if (passS.isEmpty() || passS.length()<7) {
            password.setError("Password is invalid");
        } else {
            result = true ;
        }
        return result;
    }

    public static boolean isValidEmail(String email) {
        Pattern pattern = Pattern.compile(EMAIL_REGEX);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }


}
