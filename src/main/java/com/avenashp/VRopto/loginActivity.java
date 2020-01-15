package com.avenashp.VRopto;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import static android.widget.Toast.LENGTH_SHORT;

public class loginActivity extends AppCompatActivity {

    public Button logButton;
    public TextInputEditText logMail, logPassword;
    public TextInputLayout[] inputLayout=new TextInputLayout[2];
    public String[] inputEditText=new String[2];
    private FirebaseAuth firebaseAuth;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        logButton=findViewById(R.id.logButton);
        logMail=findViewById(R.id.logMail);
        logPassword=findViewById(R.id.logPassword);
        inputLayout[0]=findViewById(R.id.inputLogMail);
        inputLayout[1]=findViewById(R.id.inputLogPassword);

        FirebaseApp.initializeApp(this);
        firebaseAuth= FirebaseAuth.getInstance();
        progressDialog=new ProgressDialog(loginActivity.this);
        FirebaseUser user=firebaseAuth.getCurrentUser();
        if(user!=null){
            finish();
            startActivity(new Intent(loginActivity.this, homeActivity.class));
        }

        logButton.setOnClickListener(view ->  {
            for(int i=0;i<2;i++){
                inputLayout[i].setErrorEnabled(false);
                inputLayout[i].setError(null);
            }
            inputEditText[0]=logMail.getText().toString().trim();
            inputEditText[1]=logPassword.getText().toString().trim();

            if(inputEditText[0].isEmpty() || inputEditText[1].isEmpty()){
                for(int i=0;i<2;i++){
                    if(inputEditText[i].isEmpty()){
                        inputLayout[i].setErrorEnabled(true);
                        inputLayout[i].setError("* Required");
                    }
                }
            }
            else{
                firebaseAuth.signInWithEmailAndPassword(inputEditText[0],inputEditText[1]).addOnCompleteListener(task -> {
                    if(task.isSuccessful()){
                        progressDialog.dismiss();
                        startActivity(new Intent(loginActivity.this, homeActivity.class));
                        finish();
                    }else{
                        if(!(task.getException().getMessage().equals("The email address is already in use by another account."))){
                            Toast.makeText(loginActivity.this,"Invalid Email / Password", LENGTH_SHORT).show();
                            progressDialog.dismiss();
                        }
                        else{
                            Toast.makeText(loginActivity.this, "Network error!", Toast.LENGTH_SHORT).show();
                            progressDialog.dismiss();
                        }
                    }
                });
                progressDialog.setMessage("Please wait..!");
                progressDialog.show();
            }
        });
    }
    @Override
    public void onBackPressed(){
        startActivity(new Intent(loginActivity.this,userselectionActivity.class));
        finish();
    }
}
