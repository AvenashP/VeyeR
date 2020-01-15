package com.avenashp.VRopto;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class userselectionActivity extends AppCompatActivity {

    public Button user1Button;
    public Button user2Button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userselection);

        user1Button=findViewById(R.id.user1Button);
        user2Button=findViewById(R.id.user2Button);

        user1Button.setOnClickListener(view -> {
            startActivity(new Intent(userselectionActivity.this, registerActivity.class));
            finish();
        });

        user2Button.setOnClickListener(view ->{
            startActivity(new Intent(userselectionActivity.this, loginActivity.class));
            finish();
        });

    }
    @Override
    public void onBackPressed(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this,R.style.AlertDialogStyle);
        builder.setTitle("Exit Application!");
        builder.setMessage("Are you sure?");
        builder.setPositiveButton("Yes", (dialog, which) ->
        {
            finish();
            System.exit(0);
        });
        builder.setNegativeButton("No", (dialog, which) -> dialog.dismiss());
        builder.setCancelable(true);
        builder.show();
    }
}
