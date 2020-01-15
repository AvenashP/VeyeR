package com.avenashp.VRopto;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;

public class questionActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    public TextView backtovrbutton;
    public Button submitbutton;
    public RadioGroup rg1,rg2,rg3;
    DrawerLayout drawer;
    NavigationView navi;
    ActionBarDrawerToggle toggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);
        rg1=findViewById(R.id.rg1);
        rg2=findViewById(R.id.rg2);
        rg3=findViewById(R.id.rg3);
        backtovrbutton=findViewById(R.id.backtovrbut);
        submitbutton=findViewById(R.id.submitbut);

        drawer=findViewById(R.id.drawe);
        navi=findViewById(R.id.navig);
        toggle=new ActionBarDrawerToggle(this,drawer,R.string.open,R.string.close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        navi.setNavigationItemSelectedListener(this);

        backtovrbutton.setOnClickListener(view -> {
            Intent intent=new Intent(questionActivity.this, UnityPlayerActivity.class);
            startActivity(intent);
        });
        submitbutton.setOnClickListener(view -> {
            int index1=rg1.indexOfChild(findViewById(rg1.getCheckedRadioButtonId()));
            int index2=rg2.indexOfChild(findViewById(rg2.getCheckedRadioButtonId()));
            int index3=rg3.indexOfChild(findViewById(rg3.getCheckedRadioButtonId()));

            Log.i("powerValue", "powerValue:  " + index1+ " "+index2 + " "+ index3);
            String disorder="";
            String power="-----";
            String type="";
            String description="";
            String treatment="";

            if(index1 == 0 && index2 == 0 && index3 == 0){
                disorder="No Problem";
                type="-----";
                description="You have a Beautiful eyes !";
                treatment="Sleep Well";
            }
            else if(index1 == 2 && index2 == 2 && index3 == 2){
                disorder="Color Blindness";
                type="Complete Color Blindness";
                description="You have complete color blindness, you can’t see colors at all. This is also called monochromacy, and it’s quite uncommon. Depending on the type, you may also have trouble seeing clearly and you may be more sensitive to light.";
                treatment="There is No Cure for this Color Blindness.";
            }
            else if(index1 == 1 || index1 == 2) {
                disorder="Color Blindness";
                if(index1==2){
                    type="Deuteranopia";
                    description="Deuteranopia makes you unable to tell the difference between red and green at all.";
                }else{
                    type = "Protanomaly";
                    description="Protanomaly makes red look more green and less bright. This type is mild and usually doesn’t get in the way of normal activities. Commonly known as Red-Green Color Blindness.";
                }
                treatment="There is No Cure for Color Blindness but there are special Contact lenses and Glasses available in market which might help in adjusting your Deficiency.";
            }
            else  if(index2 == 1 || index2 == 2) {
                disorder="Color Blindness";
                type = "Tritanomaly";
                description="Tritanomaly makes it hard to tell the difference between blue and green, and between yellow and red. Commonly known as Blue-Yellow Color Blindness";
                treatment="There is No Cure for Color Blindness but there are special Contact lenses and Glasses available in market which might help in adjusting your Deficiency.";
            }
            else  if(index3 == 1 || index3 == 2) {
                disorder="Color Blindness";
                type = "Tritanopia";
                description="Tritanopia makes you unable to tell the difference between purple, red, yellow and pink. It also makes colors look less bright. Commonly known as Blue-Yellow Color Blindness";
                treatment="There is No Cure for Color Blindness but there are special Contact lenses and Glasses available in market which might help in adjusting your Deficiency.";
            }
            Intent intent=new Intent(questionActivity.this, resultActivity.class);
            intent.putExtra("disorder",disorder);
            intent.putExtra("power",power);
            intent.putExtra("type",type);
            intent.putExtra("description",description);
            intent.putExtra("treatment",treatment);

            if(index1 >= 0 || index2 >= 0 || index3 >= 0){
                startActivity(intent);
            }else{
                Toast.makeText(questionActivity.this,"Please select the colors!",Toast.LENGTH_SHORT).show();
            }

        });

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
                startActivity(new Intent(questionActivity.this,profileActivity.class));
                break;
            case R.id.rateus:
                Toast.makeText(questionActivity.this,"Thank You \uD83D\uDE09",Toast.LENGTH_SHORT).show();
                break;
            case R.id.about:
                startActivity(new Intent(questionActivity.this,aboutActivity.class));
                break;
            case R.id.signout:
                android.app.AlertDialog.Builder builder = new AlertDialog.Builder(this,R.style.AlertDialogStyle);
                builder.setTitle("Sign Out!");
                builder.setMessage("Are you sure?");
                builder.setPositiveButton("Yes", (dialog, which) ->
                {
                    FirebaseAuth.getInstance().signOut();
                    startActivity(new Intent(questionActivity.this, splashActivity.class));
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
