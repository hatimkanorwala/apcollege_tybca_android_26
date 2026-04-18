package tybca.sem6.android;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import androidx.activity.EdgeToEdge;
import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import tybca.sem6.android.DB.UsersDB;

public class profile extends AppCompatActivity {
    public static final String myPreference = "myPrefs";
    SharedPreferences sharedPreferences;
    String username,user_id;
    String _username,_email,_contact;
    String newUsername,newEmail,newContact;
    UsersDB usersDB;
    LinearLayout _profile_ll_viewProfile,_profile_ll_editProfile;
    Button _profile_btn_showEditSection,_profile_btn_editProfile,_profile_btn_cancelEditSection;
    TextView _profile_tv_header;
    TextView _profile_label_tv_username,_profile_label_tv_email,_profile_label_tv_contact;
    TextView _profile_value_tv_username,_profile_value_tv_email,_profile_value_tv_contact;
    TextInputLayout _profile_etl_username,_profile_etl_email,_profile_etl_contact;
    TextInputEditText _profile_et_username,_profile_et_email,_profile_et_contact;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_profile);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        if(getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Profile");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        sharedPreferences = getSharedPreferences(myPreference, Context.MODE_PRIVATE);

        usersDB = new UsersDB(profile.this);

        checkUserLogin();

        assignVariables();

        _profile_ll_viewProfile.setVisibility(VISIBLE);
        _profile_ll_editProfile.setVisibility(GONE);
        _profile_tv_header.setText("User Profile");

        getProfileDataFromDB();

        onBackPressAction();


    }

    private void onBackPressAction() {
        getOnBackPressedDispatcher().addCallback(this, new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                finish();
            }
        });
    }

    public void showProfileEditSection(View view){
        _profile_ll_viewProfile.setVisibility(GONE);
        _profile_ll_editProfile.setVisibility(VISIBLE);
        _profile_tv_header.setText("Edit Profile");
        _profile_et_username.setText(_username);
        _profile_et_email.setText(_email);
        _profile_et_contact.setText(_contact);
    }
    public void btnEditProfile(View view){
        newUsername = _profile_et_username.getText().toString().trim();
        newEmail = _profile_et_email.getText().toString().trim();
        newContact = _profile_et_contact.getText().toString().trim();

        if(newUsername.isEmpty()){
            _profile_etl_username.setError("Username cannot be empty");
            _profile_etl_username.requestFocus();
            return;
        }
        else{
            _profile_etl_username.setErrorEnabled(false);
        }
        if(newEmail.isEmpty()){
            _profile_etl_email.setError("Email cannot be empty");
            _profile_etl_email.requestFocus();
            return;
        }
        else{
            _profile_etl_email.setErrorEnabled(false);
        }
        if(newContact.isEmpty()){
            _profile_etl_contact.setError("Contact cannot be empty");
            _profile_etl_contact.requestFocus();
            return;
        }
        else{
            _profile_etl_contact.setErrorEnabled(false);
        }
        if(newContact.length() != 10){
            _profile_etl_contact.setError("Contact cannot be less than 10 digits");
            _profile_etl_contact.requestFocus();
            return;
        }
        else{
            _profile_etl_contact.setErrorEnabled(false);
        }
        boolean result = usersDB.updateUserData(Integer.valueOf(user_id),newUsername,newEmail,newContact);
        if(result){
            Toast.makeText(this, "Profile Updated Successfully", Toast.LENGTH_SHORT).show();
            recreate();
        }
        else{
            Toast.makeText(this, "Failed to update profile\nTry Again!", Toast.LENGTH_SHORT).show();
        }
    }
    public void btnCancelEditSection(View view){
        _profile_ll_viewProfile.setVisibility(VISIBLE);
        _profile_ll_editProfile.setVisibility(GONE);
        _profile_tv_header.setText("User Profile");
        _profile_et_username.setText("");
        _profile_et_email.setText("");
        _profile_et_contact.setText("");
    }

    private void getProfileDataFromDB() {
        Cursor cursor = usersDB.getUserData(username);
        if(cursor.getCount() == 0){
            logout();
        }
        else{
            if(cursor.moveToFirst()){
                _username = cursor.getString(cursor.getColumnIndexOrThrow("username"));
                _email = cursor.getString(cursor.getColumnIndexOrThrow("email"));
                _contact = cursor.getString(cursor.getColumnIndexOrThrow("phone"));
                _profile_value_tv_username.setText(_username);
                _profile_value_tv_email.setText(_email);
                _profile_value_tv_contact.setText(_contact);
            }
        }
    }

    private void assignVariables() {
        _profile_ll_viewProfile = findViewById(R.id.profile_ll_viewProfile);
        _profile_ll_editProfile = findViewById(R.id.profile_ll_editProfile);
        _profile_btn_showEditSection = findViewById(R.id.profile_btn_showEditSection);
        _profile_btn_editProfile = findViewById(R.id.profile_btn_editProfile);
        _profile_btn_cancelEditSection = findViewById(R.id.profile_btn_cancelEditSection);
        _profile_tv_header = findViewById(R.id.profile_tv_header);
        _profile_label_tv_username = findViewById(R.id.profile_label_tv_username);
        _profile_label_tv_email = findViewById(R.id.profile_label_tv_email);
        _profile_label_tv_contact = findViewById(R.id.profile_label_tv_contact);
        _profile_value_tv_username = findViewById(R.id.profile_value_tv_username);
        _profile_value_tv_email = findViewById(R.id.profile_value_tv_email);
        _profile_value_tv_contact = findViewById(R.id.profile_value_tv_contact);
        _profile_etl_username = findViewById(R.id.profile_etl_username);
        _profile_etl_email = findViewById(R.id.profile_etl_email);
        _profile_etl_contact = findViewById(R.id.profile_etl_contact);
        _profile_et_contact = findViewById(R.id.profile_et_contact);
        _profile_et_email = findViewById(R.id.profile_et_email);
        _profile_et_username = findViewById(R.id.profile_et_username);
    }

    private void checkUserLogin() {
        if(sharedPreferences.getAll().containsKey("isLoggedIn")){
            if(sharedPreferences.getAll().containsKey("username")){
                username = sharedPreferences.getString("username","");
                user_id = sharedPreferences.getString("user_id","");
                //Toast.makeText(this, "Username: " + username, Toast.LENGTH_LONG).show();
            }
            else{
                logout();
            }
        }
        else{
            logout();
        }
    }

    public void logout(){
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.commit();
        finish();
        Intent i = new Intent(getApplicationContext(),MainActivity.class);
        startActivity(i);
        finish();
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}