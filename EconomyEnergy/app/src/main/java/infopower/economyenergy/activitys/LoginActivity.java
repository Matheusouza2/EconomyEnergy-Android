package infopower.economyenergy.activitys;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.SystemClock;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import infopower.economyenergy.R;
import infopower.economyenergy.dominio.Usuario;
import infopower.economyenergy.webService.Criptografia;
import infopower.economyenergy.webService.InstanciaRetrofit;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    private EditText login,senha;
    private Button entrar;
    private ImageView logoApp;
    private Animation animacaoImagem, animacaoEdit;
    private Intent telaInicio;
    private Usuario usuario;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Lê a preferencia e confirma se o usuario está logado
        SharedPreferences prefs = getSharedPreferences("logado", 0);
        boolean jaLogou = prefs.getBoolean("estaLogado", false);
        if(jaLogou) {
            try {
                Usuario usuario;
                FileInputStream fis = openFileInput("UsuarioLogado");
                ObjectInputStream ois = new ObjectInputStream(fis);
                usuario = (Usuario) ois.readObject();
                ois.close();
                fis.close();
                login(usuario.getLogin(),usuario.getSenha());
            }catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
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

    public Usuario login(final String login, String senha){
        //Inicia a barra de progresso com tempo indeterminado.
        final ProgressDialog mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setIndeterminate(true);
        mProgressDialog.setMessage("Realizando login");
        mProgressDialog.show();
        // Envia a requisição para o web service atraves da interfave UsuarioAPI
        final Call<Usuario> clienteLogin = new InstanciaRetrofit().intanciarRetrofit().login(login,senha);
        //Trata o retorno do webService
        clienteLogin.enqueue(new Callback<Usuario>() {
            //Se tiver dado tudo certo na comunicação ele pega o valor que retornou com o metodo response.body()
            @Override
            public void onResponse(Call<Usuario> call, Response<Usuario> response) {
                usuario = response.body();
                if(usuario!=null){
                    //Se o retorno for difrerente de nulo ele para a barra de progresso
                    if(mProgressDialog.isShowing()) {
                        mProgressDialog.dismiss();
                    }
                    try {
                        // salva os dados retornados do webService em um arquivo
                        FileOutputStream fos = openFileOutput("UsuarioLogado", Context.MODE_PRIVATE);
                        ObjectOutputStream oos = new ObjectOutputStream(fos);
                        oos.writeObject(usuario);
                        oos.close();
                        fos.close();

                        //Grava a preferencia dizendo que o usuario está logado!
                        SharedPreferences prefs = getSharedPreferences("logado", 0);
                        SharedPreferences.Editor editor = prefs.edit();
                        editor.putBoolean("estaLogado", true);
                        editor.commit();

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    // Chama a tela principal da aplicação
                    telaInicio = new Intent(getApplicationContext(), TelaTabsActivity.class);
                    startActivity(telaInicio);
                    finish();
                }else{
                    //Se for nulo o resultado é porque o login ou senha estão errados então a barra de progresso para e
                    //ele apresenta um Toast
                    if(mProgressDialog.isShowing()) {
                        mProgressDialog.dismiss();
                    }
                    Toast.makeText(getApplicationContext(),"Login ou senha invalidos.",Toast.LENGTH_SHORT).show();
                }
            }
            //Se a conexão falhar pode ser por falta de internet ou por erro no servidor
            @Override
            public void onFailure(Call<Usuario> call, Throwable t) {
                Log.i("ERRO REQUISIÇÃO",t.getMessage());
                if(t.getMessage().equals("Socket closed")){
                    Toast.makeText(getApplicationContext(),"Erro nos servidores. Tente mais tarde",Toast.LENGTH_SHORT).show();
                }else if(t.getMessage().contains("Failed to connect to")){
                    Toast.makeText(getApplicationContext(),"Verifique sua conexão com a internet",Toast.LENGTH_SHORT).show();
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