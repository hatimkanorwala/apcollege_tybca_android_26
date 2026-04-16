package tybca.sem6.android;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import tybca.sem6.android.DB.UsersDB;

public class change_password extends AppCompatActivity {
    UsersDB usersDB;
    public static final String myPreference = "myPrefs";
    SharedPreferences sharedPreferences;
    String username;
    TextInputEditText _chgpass_et_currPass,_chgpass_et_newPass,_chgpass_et_confirmPass;
    TextInputLayout _chgpass_etl_currPass,_chgpass_etl_newPass,_chgpass_etl_confirmPass;
    Button _chgpass_btn_submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_change_password);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        usersDB = new UsersDB(change_password.this);
        sharedPreferences = getSharedPreferences(myPreference, Context.MODE_PRIVATE);
        _chgpass_et_currPass = findViewById(R.id.chgpass_et_currPass);
        _chgpass_et_newPass = findViewById(R.id.chgpass_et_newPass);
        _chgpass_et_confirmPass = findViewById(R.id.chgpass_et_confirmPass);
        _chgpass_etl_currPass = findViewById(R.id.chgpass_etl_currPass);
        _chgpass_etl_newPass = findViewById(R.id.chgpass_etl_newPass);
        _chgpass_etl_confirmPass = findViewById(R.id.chgpass_etl_confirmPass);
        _chgpass_btn_submit = findViewById(R.id.chgpass_btn_submit);

        usersDB = new UsersDB(change_password.this);
        sharedPreferences = getSharedPreferences(myPreference,Context.MODE_PRIVATE);
        if(sharedPreferences.getAll().containsKey("isLoggedIn")){
            if(sharedPreferences.getAll().containsKey("username")){
            }
            else{
                logout();
            }
        }
        else{
            logout();
        }
        _chgpass_btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String currentPassword = _chgpass_et_currPass.getText().toString().trim();
                String newPassword = _chgpass_et_newPass.getText().toString().trim();
                String confirmPassword = _chgpass_et_confirmPass.getText().toString().trim();

                if(currentPassword.isEmpty()){

                }
                else if(newPassword.isEmpty()){

                }
                else if(newPassword.length() < 8){

                }
                else if(confirmPassword.isEmpty()){

                }
                else if(confirmPassword.length() < 8){

                }
                else if(newPassword != confirmPassword){
                }
                else{
                    //Check if Password Matches the current Password
                    Cursor cursor  = usersDB.getUserData(sharedPreferences.getString("username",""));
                    if(cursor.getCount() == 0){
                        logout();
                    }
                    else{
                        if(cursor.moveToFirst()){
                            if(currentPassword.equals(cursor.getString(4))){
                                if(newPassword.equals(confirmPassword)){
                                    boolean result = usersDB.updateUserPassword(Integer.valueOf(sharedPreferences.getString("user_id","")),newPassword);
                                    if(result){
                                        Toast.makeText(change_password.this, "Password Updated Successfully", Toast.LENGTH_SHORT).show();
                                        logout();
                                    }
                                    else{
                                        Toast.makeText(change_password.this, "Some Error Occured Try Again!", Toast.LENGTH_SHORT).show();
                                    }

                                }
                            }
                            else{
                                Toast.makeText(change_password.this, "Current Password doesnot match, Try Again!", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }

                }
            }
        });
    }
    public void logout(){
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.commit();
        finish();
        Intent i = new Intent(change_password.this,newLogin.class);
        startActivity(i);
        finish();
    }
}