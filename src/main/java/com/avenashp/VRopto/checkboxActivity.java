package com.avenashp.VRopto;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;

public class checkboxActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    public Button btnrepot;
    CheckBox checkBox1 ;
    CheckBox checkBox2;
    CheckBox checkBox3 ;
    CheckBox checkBox4;
    CheckBox checkBox5 ;
    CheckBox checkBox7 ;
    CheckBox checkBox6 ;
    CheckBox checkBox8 ;
    CheckBox checkBox9;
    CheckBox checkBox10;

    DrawerLayout drawer;
    NavigationView navi;
    ActionBarDrawerToggle toggle;

    boolean isBlackChecked, isWhiteChecked, isBlueChecked, isDarkBlueChecked, isYellowChecked, isGreenChecked, isPinkChecked, isOrangeChecked, isPurpleChecked, isRedChecked;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkbox);
        checkBox1 = findViewById(R.id.checkBox);
        checkBox2 = findViewById(R.id.checkBox2);
        checkBox3 = findViewById(R.id.checkBox3);
        checkBox4 = findViewById(R.id.checkBox4);
        checkBox5 = findViewById(R.id.checkBox5);
        checkBox7 = findViewById(R.id.checkBox7);
        checkBox6 = findViewById(R.id.checkBox6);
        checkBox8 = findViewById(R.id.checkBox8);
        checkBox9 = findViewById(R.id.checkBox9);
        checkBox10 = findViewById(R.id.checkBox10);
        btnrepot = findViewById(R.id.button3);

        drawer=findViewById(R.id.drawe);
        navi=findViewById(R.id.navig);

        toggle=new ActionBarDrawerToggle(this,drawer,R.string.open,R.string.close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        navi.setNavigationItemSelectedListener(this);

        btnrepot.setOnClickListener(v -> {
            isBlackChecked = checkBox1.isChecked();
            isWhiteChecked = checkBox2.isChecked();
            isBlueChecked = checkBox3.isChecked();
            isDarkBlueChecked = checkBox4.isChecked();
            isGreenChecked = checkBox6.isChecked();
            isPinkChecked = checkBox7.isChecked();
            isPurpleChecked = checkBox9.isChecked();
            isRedChecked = checkBox10.isChecked();
            isYellowChecked = checkBox5.isChecked();
            isOrangeChecked = checkBox8.isChecked();

            int noOfColor = 0;
            double power = 0;

            if (isBlackChecked) {
                power += 0;
                noOfColor++;
            }
            if (isWhiteChecked) {
                power += -0.25;
                noOfColor++;
            }
            if (isOrangeChecked) {
                power += -2;
                noOfColor++;
            }
            if (isDarkBlueChecked) {
                power += -0.75;
                noOfColor++;
            }
            if (isBlueChecked) {
                power += -0.5;
                noOfColor++;
            }
            if (isPurpleChecked) {
                power += -2.5;
                noOfColor++;
            }
            if (isPinkChecked) {
                power += -1.5;
                noOfColor++;
            }
            if (isGreenChecked) {
                power += -1.2;
                noOfColor++;
            }
            if (isYellowChecked) {
                power += -1;
                noOfColor++;
            }
            if (isRedChecked) {
                power += -3;
                noOfColor++;
            }

            Double avg = power / noOfColor;

            String disorder="Visual Acuity";
            String type="Myopia";
            String description="It occurs when the shape of your eye causes light rays to bend incorrectly, focusing images in front of your retina instead of on your retina.";
            String treatment="This can be corrected with eyeglasses , contact lenses or refractive surgery. Depending on the degree of your myopia, you may need to wear your glasses or contact lenses all the time or only when you need very clear distance vision, like when driving, seeing a chalkboard or watching a movie.";
            if(avg == 0){
                disorder="No Problem";
                type="-----";
                description="You have a Beautiful eyes !";
                treatment="Sleep Well";
            }
            Log.i("test", "onCreate:  " + avg + " " + power + "  " + noOfColor);

            Intent intent = new Intent(checkboxActivity.this, resultActivity.class);

            intent.putExtra("disorder",disorder);
            intent.putExtra("power", avg.toString());
            intent.putExtra("type",type);
            intent.putExtra("description",description);
            intent.putExtra("treatment",treatment);
            if(noOfColor!=0){
                startActivity(intent);
            }
            else{
                Toast.makeText(checkboxActivity.this,"Please select the colors!",Toast.LENGTH_SHORT).show();
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
                startActivity(new Intent(checkboxActivity.this,profileActivity.class));
                break;
            case R.id.rateus:
                Toast.makeText(checkboxActivity.this,"Thank You \uD83D\uDE09",Toast.LENGTH_SHORT).show();
                break;
            case R.id.about:
                startActivity(new Intent(checkboxActivity.this,aboutActivity.class));
                break;
            case R.id.signout:
                android.app.AlertDialog.Builder builder = new AlertDialog.Builder(this,R.style.AlertDialogStyle);
                builder.setTitle("Sign Out!");
                builder.setMessage("Are you sure?");
                builder.setPositiveButton("Yes", (dialog, which) ->
                {
                    FirebaseAuth.getInstance().signOut();
                    startActivity(new Intent(checkboxActivity.this, splashActivity.class));
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
