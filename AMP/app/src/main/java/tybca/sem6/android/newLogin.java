package tybca.sem6.android;

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

public class newLogin extends AppCompatActivity {
    EditText _login_et_username,_login_et_password;
    Button _login_btn_login;
    TextView _login_tv_forgotPassword,_login_tv_newUser;
    String username,password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_new_login);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        _login_et_username = findViewById(R.id.login_et_username);
        _login_et_password = findViewById(R.id.login_et_password);
        _login_btn_login = findViewById(R.id.login_btn_login);
        _login_tv_forgotPassword = findViewById(R.id.login_tv_forgotPassword);
        _login_tv_newUser = findViewById(R.id.login_tv_newUser);

        _login_btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                username = _login_et_username.getText().toString().trim();
                password = _login_et_password.getText().toString().trim();
                if(username.isEmpty()){
                    _login_et_username.setError("Enter Username");
                    _login_et_username.requestFocus();
                }
                else if(password.isEmpty()){
                    _login_et_password.setError("Enter Password");
                    _login_et_password.requestFocus();
                }
                else if(password.length() <= 8){
                    _login_et_password.setError("Password cannot be less than 8 characters");
                }
                else{

                }
            }
        });
    }
    public void redirectToNewRegistration(View view){
        Intent i = new Intent(newLogin.this,newRegistration.class);
        startActivity(i);
    }
}