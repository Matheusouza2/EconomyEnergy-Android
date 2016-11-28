package infopower.economyenergy.activitys;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.SystemClock;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import infopower.economyenergy.R;
import infopower.economyenergy.dominio.Usuario;
import infopower.economyenergy.webService.ClienteDes;
import infopower.economyenergy.webService.Criptografia;
import infopower.economyenergy.webService.UsuarioAPI;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginActivity extends AppCompatActivity {

    private EditText login,senha;
    private Button entrar;
    private ImageView logoApp;
    private Animation animacaoImagem, animacaoEdit;
    private Intent telaInicio;
    private final String baseURL = "http://192.168.0.100:9090/WebServiceMobile/";
    private UsuarioAPI usuarioAPI;
    private Usuario usuario;
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


        entrar.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view) {
                login(login.getText().toString().toLowerCase(), Criptografia.criptografar(senha.getText().toString()));
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        Gson gson = new GsonBuilder().registerTypeAdapter(Usuario.class, new ClienteDes()).create();

        Retrofit retrofit = new Retrofit
                .Builder()
                .baseUrl(baseURL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        usuarioAPI = retrofit.create(UsuarioAPI.class);
    }


    public Usuario login(String login, String senha){
        final ProgressDialog mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setIndeterminate(true);
        mProgressDialog.setMessage("Realizando login");
        mProgressDialog.show();
        final Call<Usuario> clienteLogin = usuarioAPI.login(login,senha);
        clienteLogin.enqueue(new Callback<Usuario>() {
            @Override
            public void onResponse(Call<Usuario> call, Response<Usuario> response) {
                usuario = response.body();
                if(usuario!=null){
                    if(mProgressDialog.isShowing()) {
                        mProgressDialog.dismiss();
                    }
                    try {
                        File file = getFileStreamPath("usuario");
                        FileOutputStream fos = new FileOutputStream(file);
                        ObjectOutputStream oos = new ObjectOutputStream(fos);
                        oos.writeObject(usuario);
                        oos.close();
                        fos.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    telaInicio = new Intent(getApplicationContext(), TelaTabsActivity.class);
                    startActivity(telaInicio);
                    finish();
                }else{
                    if(mProgressDialog.isShowing()) {
                        mProgressDialog.dismiss();
                    }
                    Toast.makeText(getApplicationContext(),"Login ou senha invalidos.",Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<Usuario> call, Throwable t) {
                Log.i("ERRO AO ENVIAR IMAGEM: ",t.getMessage());
                if(t.getMessage().equals("Socket closed")){
                    Toast.makeText(getApplicationContext(),"Erro nos servidores. Tente mais tarde",Toast.LENGTH_SHORT).show();
                }

                if(mProgressDialog.isShowing()) {
                    mProgressDialog.dismiss();
                }
            }
        });
        new Thread() {
            @Override
            public void run() {
                super.run();
                SystemClock.sleep(2000);
                clienteLogin.cancel();
            }
        }.start();

        return usuario;
    }
}