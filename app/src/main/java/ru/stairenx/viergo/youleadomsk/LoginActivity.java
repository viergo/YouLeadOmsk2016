package ru.stairenx.viergo.youleadomsk;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.sothree.slidinguppanel.SlidingUpPanelLayout;

import ru.stairenx.viergo.youleadomsk.authClient.Authorization;
import ru.stairenx.viergo.youleadomsk.authClient.Registration;
import ru.stairenx.viergo.youleadomsk.server.ServerAction;

/**
 * Created by viergo on 06.10.16.
 */
public class LoginActivity extends AppCompatActivity {
    private SlidingUpPanelLayout mLayout;
    private EditText mLoginPhone;
    private EditText mLoginPass;
    private EditText mRegPhone;
    private EditText mRegEmail;
    private EditText mRegName;
    private EditText mRegSername;
    private EditText mRegPas;
    private EditText mRegRePas;
    private Button mSignInButton;
    private FloatingActionButton mSignUpFAB;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mLayout = (SlidingUpPanelLayout) findViewById(R.id.sliding_layout);

        mLoginPhone = (EditText) findViewById(R.id.sign_in_login);

        mLoginPass = (EditText) findViewById(R.id.sign_in_pas);
        mRegPhone = (EditText) findViewById(R.id.sign_up_phone);
        mRegEmail = (EditText) findViewById(R.id.sign_up_email);
        mRegName = (EditText) findViewById(R.id.sign_up_name);
        mRegSername = (EditText) findViewById(R.id.sign_up_sername);
        mRegPas = (EditText) findViewById(R.id.sign_up_pas);
        mRegRePas = (EditText) findViewById(R.id.sign_up_repas);
        mSignInButton = (Button) findViewById(R.id.sign_up_button);
        mSignUpFAB = (FloatingActionButton) findViewById(R.id.sign_up_fab);


        mSignInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean key;
                key = Authorization.entering(mLoginPhone.getText().toString(),mLoginPass.getText().toString());
                if(key == true){
                    ServerAction.getLogin(mLoginPhone.getText().toString());
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    //intent.putExtra("Login",mLoginPhone.getText().toString());
                    startActivity(intent);
                    LoginActivity.this.finish();
                }else {
                    Toast.makeText(LoginActivity.this, "Введен неверный логин или пароль. Повторите попытку", Toast.LENGTH_LONG).show();
                }
            }
        });

        mSignUpFAB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    if(mRegPas.getText().toString().equals(mRegRePas.getText().toString())){
                        String name = mRegSername.getText().toString()+" "+mRegName.getText().toString();
                        Registration.regAcount(mRegPhone.getText().toString(),mRegPas.getText().toString(),name,mRegEmail.getText().toString());
                        mLayout.setPanelState(SlidingUpPanelLayout.PanelState.COLLAPSED);
                        Toast.makeText(LoginActivity.this, "Регистрация прошла успешно",Toast.LENGTH_LONG).show();
                        mLoginPhone.setText(mRegPhone.getText().toString());
                        mLoginPass.setText(mRegPas.getText().toString());
                    }else {
                        Toast.makeText(LoginActivity.this, "пароли не совпадают",Toast.LENGTH_LONG).show();
                    }
            }
        });

    }
}