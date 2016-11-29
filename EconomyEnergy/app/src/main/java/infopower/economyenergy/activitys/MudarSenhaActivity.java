package infopower.economyenergy.activitys;

import android.app.ProgressDialog;
import android.os.SystemClock;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

import de.hdodenhof.circleimageview.CircleImageView;
import infopower.economyenergy.R;
import infopower.economyenergy.dominio.Usuario;
import infopower.economyenergy.webService.Criptografia;
import infopower.economyenergy.webService.InstanciaRetrofit;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MudarSenhaActivity extends AppCompatActivity {

    private TextView nomeUserLogado;
    private CircleImageView imgUserLogado;
    private EditText senhaAtual, novaSenha, novaSenhaConfere;
    private Usuario usuario;
    public static Usuario usuarioFile;
    private Button btnMudarSenha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mudar_senha);

        ActionBar actionBar = getSupportActionBar();

        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle("Mudar Senha");

        nomeUserLogado = (TextView)findViewById(R.id.nome_user_logado);
        imgUserLogado = (CircleImageView)findViewById(R.id.img_user_logado);
        imgUserLogado.setImageResource(R.drawable.user_1);

        senhaAtual = (EditText)findViewById(R.id.editText_senha);
        novaSenha = (EditText)findViewById(R.id.editText_nova_senha);
        novaSenhaConfere = (EditText)findViewById(R.id.editText_nova_senha2);
        btnMudarSenha = (Button)findViewById(R.id.mudar_senha_btn);
        btnMudarSenha.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if(novaSenha.getText().toString().equals(novaSenhaConfere.getText().toString())){
                    mudarSenha();
                }else{
                    Toast.makeText(getApplicationContext(),"Senhas não conferem",Toast.LENGTH_SHORT).show();
                    senhaAtual.setText(null);
                    novaSenha.setText(null);
                    novaSenhaConfere.setText(null);
                }
            }
        });

        //Leitura do arquivo com informações do usuario
        try {
            FileInputStream fis = openFileInput("UsuarioLogado");
            ObjectInputStream ois = new ObjectInputStream(fis);
            usuarioFile = (Usuario) ois.readObject();
            ois.close();
            fis.close();
        }catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        nomeUserLogado.setText(usuarioFile.getNome());
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    protected void mudarSenha(){

        final ProgressDialog mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setIndeterminate(true);
        mProgressDialog.setMessage("Modificando Senha");
        mProgressDialog.show();
        final Call<Usuario> clienteLogin = new InstanciaRetrofit().intanciarRetrofit().mudarSenha(Criptografia.criptografar(senhaAtual.getText().toString()),
                Criptografia.criptografar(novaSenha.getText().toString()), usuarioFile.getLogin());
        clienteLogin.enqueue(new Callback<Usuario>() {
            @Override
            public void onResponse(Call<Usuario> call, Response<Usuario> response) {
                usuario = response.body();
                if(usuario!=null){
                    if(mProgressDialog.isShowing()) {
                        mProgressDialog.dismiss();
                    }
                    novaSenha.setText(null);
                    senhaAtual.setText(null);
                    novaSenhaConfere.setText(null);
                    Toast.makeText(getApplicationContext(),"Senha modificada com sucesso",Toast.LENGTH_SHORT).show();
                }else{
                    if(mProgressDialog.isShowing()) {
                        mProgressDialog.dismiss();
                    }
                    novaSenha.setText(null);
                    senhaAtual.setText(null);
                    novaSenhaConfere.setText(null);
                    Toast.makeText(getApplicationContext(),"Senha atual errada",Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<Usuario> call, Throwable t) {
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
    }
}