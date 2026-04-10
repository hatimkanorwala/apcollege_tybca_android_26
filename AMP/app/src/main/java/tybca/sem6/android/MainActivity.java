package tybca.sem6.android;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    EditText _main_et_username,_main_et_password;
    Button _main_btn_login;
    TextView _main_tv_forgotPassword,_main_tv_newUser;
    String username,password;
    public static final String myPreference = "myPrefs";
    SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        _main_et_username = findViewById(R.id.main_et_username);
        _main_et_password = findViewById(R.id.main_et_password);
        _main_btn_login = findViewById(R.id.main_btn_login);
        _main_tv_forgotPassword = findViewById(R.id.main_tv_forgotPassword);
        _main_tv_newUser = findViewById(R.id.main_tv_newUser);

        sharedPreferences = getSharedPreferences(myPreference, Context.MODE_PRIVATE);
        if(sharedPreferences.getAll().containsKey("isLoggedIn")){
            if(sharedPreferences.getAll().containsKey("username")){
                Intent i = new Intent(MainActivity.this,SecondActivity.class);
                startActivity(i);
                finish();
            }
        }

        _main_btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                username = _main_et_username.getText().toString().trim();
                password = _main_et_password.getText().toString().trim();
                if(username.isEmpty()){
                    _main_et_username.setError("Enter Username");
                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                    builder.setTitle("Error");
                    builder.setMessage("Please Enter Username");
                    builder.setCancelable(false);
                    builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                        }
                    });
                    builder.setNegativeButton("Cancel",null);
                    builder.setNeutralButton("Neutral",null);
                    builder.setIcon(R.drawable.error_icon);
                    builder.show();
                }
                else if(password.isEmpty()){
                    _main_et_password.setError("Enter Password");
                    AlertDialog.Builder alertBuilder = new AlertDialog.Builder(MainActivity.this);
                    LayoutInflater inflater = getLayoutInflater();
                    View view_error = inflater.inflate(R.layout.error_layout,null);
                    alertBuilder.setView(view_error);
                    TextView _error_tv_title = view_error.findViewById(R.id.erro_tv_title);
                    TextView _error_tv_message = view_error.findViewById(R.id.erro_tv_message);
                    Button _error_btn_close = view_error.findViewById(R.id.error_btn_close);
                    alertBuilder.setCancelable(false);
                    final AlertDialog dialog = alertBuilder.create();
                    _error_tv_title.setText("Password Error");
                    _error_tv_message.setText("Password Cannot be Empty");
                    _error_btn_close.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            dialog.dismiss();
                        }
                    });
                    dialog.show();

                }
                else if(password.length() <= 8){
                    _main_et_password.setError("Password cannot be less than 8 characters");
                }
                else{
                    if(username.equals("admin") && password.equals("admin@12345")){
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString("username",username);
                        editor.putString("isLoggedIn","1");
                        editor.commit();
                        Intent i = new Intent(MainActivity.this,SecondActivity.class);
                        startActivity(i);
                        finish();
                    }
                    else{
                        Toast.makeText(MainActivity.this, "Invalid Credentials, Try Again!", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
    public void redirectToRegistration(View view){
        Intent i = new Intent(MainActivity.this,Registration.class);
        startActivity(i);
    }
}