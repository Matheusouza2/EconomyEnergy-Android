package infopower.economyenergy;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

public class LoginActivity extends AppCompatActivity {

    private EditText login,senha;
    private Button entrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        ImageView image = (ImageView)findViewById(R.id.imageView);
        Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.myanimation);
        image.startAnimation(animation);


        login = (EditText) findViewById(R.id.login);
        Animation animation1 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade);
        login.startAnimation(animation1);

        senha = (EditText) findViewById(R.id.senha);
        senha.startAnimation(animation1);

        entrar = (Button) findViewById(R.id.entrar_btn);
        entrar.startAnimation(animation1);
    }
}