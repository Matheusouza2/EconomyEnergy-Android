package infopower.economyenergy.Activitys;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import infopower.economyenergy.R;

public class LoginActivity extends AppCompatActivity {

    private EditText login,senha;
    private Button entrar;
    private ImageView logoApp;
    private Animation animacaoImagem, animacaoEdit;
    private Intent telaInicio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        logoApp = (ImageView)findViewById(R.id.logoApp);
        animacaoImagem = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.myanimation);
        logoApp.startAnimation(animacaoImagem);

        login = (EditText) findViewById(R.id.login);
        animacaoEdit = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade);
        login.startAnimation(animacaoEdit);

        senha = (EditText) findViewById(R.id.senha);
        senha.startAnimation(animacaoEdit);

        entrar = (Button) findViewById(R.id.entrar_btn);
        entrar.startAnimation(animacaoEdit);

        telaInicio = new Intent(this, TelaTabs.class);
        entrar.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view) {
                if(login.getText().toString().equals("infopower") && senha.getText().toString().equals("grupo")){
                    startActivity(telaInicio);
                }else{
                    Toast.makeText(LoginActivity.this, "Login ou Senha incorretos", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}