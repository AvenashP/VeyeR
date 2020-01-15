package com.avenashp.VRopto;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Random;

public class resultActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    public TextView power,disorder,type,desc,treat,tips;
    DrawerLayout drawer;
    NavigationView navi;
    ActionBarDrawerToggle toggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        power=findViewById(R.id.power);
        disorder=findViewById(R.id.disorder);
        type=findViewById(R.id.type);
        desc=findViewById(R.id.desc);
        treat=findViewById(R.id.treat);
        tips=findViewById(R.id.tips);

        drawer=findViewById(R.id.drawe);
        navi=findViewById(R.id.navig);
        toggle=new ActionBarDrawerToggle(this,drawer,R.string.open,R.string.close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        navi.setNavigationItemSelectedListener(this);

        String[] Tips = {"Eat a healthy, balanced diet","Maintain a healthy weight","Get regular eye exercise","Wear sunglasses on roads",
                        "Wear protective eye wear","Avoid smoking","Know your family medical history","Know your other risk factors",
                        "Take steps to prevent eye infections","Give your eyes some rest"};
        Random rand = new Random();
        int r = rand.nextInt(10); 

        String Disorder= getIntent().getStringExtra("disorder");
        String Power= getIntent().getStringExtra("power");
        String Type= getIntent().getStringExtra("type");
        String Description= getIntent().getStringExtra("description");
        String Treatment= getIntent().getStringExtra("treatment");

        /*if(powerValue == null) {
            powerValue= getIntent().getStringExtra("power");
        }
        Log.i("powerValue", "powerValue:  " + powerValue );*/

        tips.setText(Tips[r]);
        disorder.setText(Disorder);
        power.setText(Power);
        type.setText(Type);
        desc.setText(Description);
        treat.setText(Treatment);
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
            case R.id.home:
                startActivity(new Intent(resultActivity.this,homeActivity.class));
                break;
            case R.id.myprofile:
                startActivity(new Intent(resultActivity.this,profileActivity.class));
                break;
            case R.id.rateus:
                Toast.makeText(resultActivity.this,"Thank You \uD83D\uDE09",Toast.LENGTH_SHORT).show();
                break;
            case R.id.about:
                startActivity(new Intent(resultActivity.this,aboutActivity.class));
                break;
            case R.id.signout:
                android.app.AlertDialog.Builder builder = new AlertDialog.Builder(this,R.style.AlertDialogStyle);
                builder.setTitle("Sign Out!");
                builder.setMessage("Are you sure?");
                builder.setPositiveButton("Yes", (dialog, which) ->
                {
                    FirebaseAuth.getInstance().signOut();
                    startActivity(new Intent(resultActivity.this, splashActivity.class));
                    finish();
                });
                builder.setNegativeButton("No", (dialog, which) -> dialog.dismiss());
                builder.setCancelable(true);
                builder.show();
                return(true);
        }
        return false;
    }
}
