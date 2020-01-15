package com.avenashp.VRopto;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;

public class registerActivity extends AppCompatActivity {

    public Button regButton;
    public TextInputEditText regMail,regPassword1,regPassword2;
    public String[] inputEditText=new String[3];
    public TextInputLayout[] inputLayout =new TextInputLayout[3];
    private FirebaseAuth firebaseAuth;
    private ProgressDialog progressDial;
    public int flag;
    public String mailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        regMail = findViewById(R.id.regMail);
        regPassword1 = findViewById(R.id.regPassword1);
        regPassword2 = findViewById(R.id.regPassword2);
        inputLayout[0] = findViewById(R.id.inputRegMail);
        inputLayout[1] = findViewById(R.id.inputRegPassword1);
        inputLayout[2] = findViewById(R.id.inputRegPassword2);
        regButton = findViewById(R.id.regButton);

        firebaseAuth= FirebaseAuth.getInstance();
        progressDial=new ProgressDialog(registerActivity.this);

        regButton.setOnClickListener(view ->  {

            inputEditText[0] = regMail.getText().toString().trim();
            inputEditText[1] = regPassword1.getText().toString().trim();
            inputEditText[2] = regPassword2.getText().toString().trim();

            if(inputEditText[0].matches(mailPattern))
                flag=1;
            else
                flag=0;

            if(flag==1 && inputEditText[1].length()>=6 && inputEditText[2].length()>=6 && inputEditText[1].equals(inputEditText[2])){
                for(int i=0;i<=2;i++) {
                    inputLayout[i].setError(null);
                    inputLayout[i].setErrorEnabled(false);
                }
                progressDial.setMessage("Please wait..!");
                progressDial.show();
                firebaseAuth.createUserWithEmailAndPassword(inputEditText[0],inputEditText[2]).addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        progressDial.dismiss();
                        startActivity(new Intent(registerActivity.this, loginActivity.class));
                        finish();
                    } else {
                        if(task.getException().getMessage().equals("The email address is already in use by another account.")){
                            Toast.makeText(registerActivity.this, "Oops! Email exits already.", Toast.LENGTH_SHORT).show();
                            progressDial.dismiss();
                        }
                        else{
                            Toast.makeText(registerActivity.this, "Network error!", Toast.LENGTH_SHORT).show();
                            progressDial.dismiss();
                        }
                    }
                });
            }
            else{
                for(int i=0;i<=2;i++){
                    if(inputEditText[i].isEmpty()){
                        inputLayout[i].setErrorEnabled(true);
                        inputLayout[i].setError("* Required");
                    }
                    else if(i==0 && flag==0){
                        inputLayout[i].setErrorEnabled(true);
                        inputLayout[i].setError("Invalid Email");
                    }
                    else if(i==2){
                        if(inputEditText[i].length()<6){
                            inputLayout[i-1].setErrorEnabled(true);
                            inputLayout[i-1].setError("Min 6 characters");
                            inputLayout[i].setErrorEnabled(true);
                            inputLayout[i].setError("Min 6 characters");
                        }
                        else {
                            inputLayout[i].setErrorEnabled(true);
                            inputLayout[i].setError("Password mismatch");
                        }
                    }
                    else{
                        inputLayout[i].setError(null);
                        inputLayout[i].setErrorEnabled(false);
                    }
                }
            }
        });
    }
    @Override
    public void onBackPressed(){
        startActivity(new Intent(registerActivity.this,userselectionActivity.class));
        finish();
    }
}
