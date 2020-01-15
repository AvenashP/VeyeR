package com.avenashp.VRopto;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;

public class homeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    public Button virtualbut, colorbut;
    DrawerLayout drawer;
    NavigationView navi;
    ActionBarDrawerToggle toggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        virtualbut=findViewById(R.id.virtualbut);
        colorbut=findViewById(R.id.colorbut);

        drawer=findViewById(R.id.drawe);
        navi=findViewById(R.id.navig);
        toggle=new ActionBarDrawerToggle(this,drawer,R.string.open,R.string.close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        navi.setNavigationItemSelectedListener(this);

        virtualbut.setOnClickListener(view ->startActivity(new Intent(homeActivity.this,UnityPlayerActivity.class)));
        colorbut.setOnClickListener(view -> simpleAlert2());
    }
    public void simpleAlert2() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this, R.style.AlertDialogStyle);
        builder.setTitle("Be Sure!");
        builder.setMessage("Have you seen any Color Twice?");
        builder.setPositiveButton("Yes", (dialog, which) ->
                startActivity(new Intent(homeActivity.this, questionActivity.class)));
        builder.setNegativeButton("No", (dialog, which) ->
                startActivity(new Intent(homeActivity.this, checkboxActivity.class)));
        builder.setCancelable(true);
        builder.show();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(toggle.onOptionsItemSelected(item)){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()){
            case R.id.myprofile:
                startActivity(new Intent(homeActivity.this,profileActivity.class));
                break;
            case R.id.rateus:
                Toast.makeText(homeActivity.this,"Thank You \uD83D\uDE09",Toast.LENGTH_SHORT).show();
                break;
            case R.id.about:
                startActivity(new Intent(homeActivity.this,aboutActivity.class));
                break;
            case R.id.signout:
                AlertDialog.Builder builder = new AlertDialog.Builder(this,R.style.AlertDialogStyle);
                builder.setTitle("Sign Out!");
                builder.setMessage("Are you sure?");
                builder.setPositiveButton("Yes", (dialog, which) ->
                {
                    FirebaseAuth.getInstance().signOut();
                    startActivity(new Intent(homeActivity.this, splashActivity.class));
                    finish();
                });
                builder.setNegativeButton("No", (dialog, which) -> dialog.dismiss());
                builder.setCancelable(true);
                builder.show();
                return(true);
        }
        return false;
    }
    /* Exit Application */
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
