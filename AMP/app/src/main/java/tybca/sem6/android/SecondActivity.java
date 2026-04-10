package tybca.sem6.android;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class SecondActivity extends AppCompatActivity {
    public static final String myPreference = "myPrefs";
    SharedPreferences sharedPreferences;
    String username;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_second);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        if(getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Home | From Code");
            getSupportActionBar().setIcon(R.drawable.settings_icon);
        }
        sharedPreferences = getSharedPreferences(myPreference, Context.MODE_PRIVATE);

        if(sharedPreferences.getAll().containsKey("isLoggedIn")){
            if(sharedPreferences.getAll().containsKey("username")){
                username = sharedPreferences.getString("username","");
                Toast.makeText(this, "Username: " + username, Toast.LENGTH_LONG).show();
            }
            else{
                logout();
            }
        }
        else{
            logout();
        }
    }
    public void btnLogout(View view){
        logout();
    }
    public void logout(){
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.commit();
        finish();
        Intent i = new Intent(SecondActivity.this,MainActivity.class);
        startActivity(i);
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.home_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int item_id = item.getItemId();
        if(item_id == R.id.menu_home){

        }
        else if(item_id == R.id.menu_aboutus){

        }
        else if(item_id == R.id.menu_contactUs){

        }
        else if(item_id == R.id.menu_profile){

        }
        else if(item_id == R.id.menu_settings){

        }
        else if(item_id == R.id.menu_logout){
            logout();
        }
        return true;
    }
}