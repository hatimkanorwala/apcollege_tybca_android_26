package tybca.sem6.android;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import tybca.sem6.android.DB.UsersDB;

public class newRegistration extends AppCompatActivity {
    TextInputEditText _newregistration_et_username,_newregistration_et_password,_newregistration_et_email,_newregistration_et_contact;
    TextInputLayout _newregistration_etl_username,_newregistration_etl_password,_newregistration_etl_email,_newregistration_etl_contact;
    Button _newregistration_btn_submit;
    TextView _newregistration_tv_data;
    String username,password,email,contact,data;
    UsersDB usersDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_new_registration);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        usersDB = new UsersDB(newRegistration.this);
        _newregistration_et_username = findViewById(R.id.newregistration_et_username);
        _newregistration_et_password = findViewById(R.id.newregistration_et_password);
        _newregistration_et_email = findViewById(R.id.newregistration_et_email);
        _newregistration_et_contact = findViewById(R.id.newregistration_et_contact);
        _newregistration_btn_submit = findViewById(R.id.newregistration_btn_submit);
        _newregistration_tv_data = findViewById(R.id.newregistration_tv_data);
        _newregistration_etl_username = findViewById(R.id.newregistration_etl_username);
        _newregistration_etl_password = findViewById(R.id.newregistration_etl_password);
        _newregistration_etl_email = findViewById(R.id.newregistration_etl_email);
        _newregistration_etl_contact = findViewById(R.id.newregistration_etl_contact);

    }
    public void NewregisterData(View view){
        username = _newregistration_et_username.getText().toString().trim();
        password = _newregistration_et_password.getText().toString().trim();
        email = _newregistration_et_email.getText().toString().trim();
        contact = _newregistration_et_contact.getText().toString().trim();
        if(username.isEmpty()){
            _newregistration_etl_username.setError("Username cannot be empty");
            _newregistration_etl_username.requestFocus();
            return;
        }
        else{
            _newregistration_etl_username.setErrorEnabled(false);
        }
        if(password.isEmpty()){
            _newregistration_etl_password.setError("Password cannot be empty");
            _newregistration_etl_password.requestFocus();
            return;
        }
        else{
            _newregistration_etl_password.setErrorEnabled(false);
        }
        if(password.length() < 8){
            _newregistration_etl_password.setError("Password cannot be less than 8 digits");
            _newregistration_etl_password.requestFocus();
            return;
        }
        else{
            _newregistration_etl_password.setErrorEnabled(false);
        }
        if(email.isEmpty()){
            _newregistration_etl_email.setError("Email cannot be empty");
            _newregistration_etl_email.requestFocus();
            return;
        }
        else{
            _newregistration_etl_email.setErrorEnabled(false);
        }
        if(contact.isEmpty() || contact.length() != 10){
            _newregistration_etl_contact.setError("Contact cannot be empty or less than 10 digits");
            _newregistration_etl_contact.requestFocus();
            return;
        }
        else{
            _newregistration_etl_contact.setErrorEnabled(false);
        }
        String result = usersDB.insertUserData(username,email,contact,password);
        if(result.equals("Error")){
            Toast.makeText(this, "Failed to Create User, Try Again!", Toast.LENGTH_LONG).show();
        }
        else if(result.equals("Success")){
            Toast.makeText(this, "User Registration Successfully Done.", Toast.LENGTH_SHORT).show();
            Intent i = new Intent(newRegistration.this,newLogin.class);
            startActivity(i);
            finish();
        }
        else if(result.equals("DuplicateUser")){
            Toast.makeText(this, "User Already Registered with this Email ID", Toast.LENGTH_LONG).show();
        }
        else{
            Toast.makeText(this, "Some Error Occured Try Again!", Toast.LENGTH_SHORT).show();
        }
    }
}