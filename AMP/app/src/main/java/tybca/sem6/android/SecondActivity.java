package tybca.sem6.android;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import tybca.sem6.android.DB.UsersDB;
import tybca.sem6.android.adapter.usersAdapter;
import tybca.sem6.android.model.usersModel;

public class SecondActivity extends AppCompatActivity {
    public static final String myPreference = "myPrefs";
    SharedPreferences sharedPreferences;
    String username;
    List<usersModel> usersList;
    UsersDB usersDB;
    RecyclerView _second_recyclerView_users;
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
        _second_recyclerView_users = findViewById(R.id.second_recyclerView_users);

        LinearLayoutManager layoutManager = new LinearLayoutManager(SecondActivity.this,LinearLayoutManager.VERTICAL,false);
        _second_recyclerView_users.setHasFixedSize(true);
        _second_recyclerView_users.setLayoutManager(layoutManager);

        usersDB = new UsersDB(SecondActivity.this);
        usersList = new ArrayList<>();


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
        loadUsersData();
    }
    private void loadUsersData() {
        Cursor cursor = usersDB.getAllData();
        if(cursor.getCount() == 0){
        }
        else{
            while(cursor.moveToNext()){
                usersList.add(new usersModel(
                        cursor.getInt(cursor.getColumnIndexOrThrow("id")),
                        cursor.getString(cursor.getColumnIndexOrThrow("username")),
                        cursor.getString(cursor.getColumnIndexOrThrow("phone")),
                        cursor.getString(cursor.getColumnIndexOrThrow("email")),
                        cursor.getString(cursor.getColumnIndexOrThrow("updated_at"))
                ));
            }
            usersAdapter adapter = new usersAdapter(SecondActivity.this,usersList);
            _second_recyclerView_users.setAdapter(adapter);
            adapter.notifyDataSetChanged();
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
        Intent i = new Intent(SecondActivity.this,newLogin.class);
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
            Intent i = new Intent(SecondActivity.this, profile.class);
            startActivity(i);
        }
        //Added New for change password
        else if(item_id == R.id.menu_changePassword){
            Intent i = new Intent(SecondActivity.this, change_password.class);
            startActivity(i);
        }
        else if(item_id == R.id.menu_settings){

        }
        else if(item_id == R.id.menu_logout){
            logout();
        }
        return true;
    }
}