package infopower.economyenergy.activitys;

import android.app.ProgressDialog;
import android.os.SystemClock;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

import infopower.economyenergy.R;
import infopower.economyenergy.dominio.Relatorio;
import infopower.economyenergy.dominio.Usuario;
import infopower.economyenergy.webService.ClienteDes;
import infopower.economyenergy.webService.InstanciaRetrofit;
import infopower.economyenergy.webService.UsuarioAPI;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RelatorioActivity extends AppCompatActivity {

    private Relatorio relatorioResposta;
    private TextView idFatura, dataFatura, consumoDia, consumoMes, totalFatura;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_relatorio);

        idFatura = (TextView)findViewById(R.id.valor_fatura);
        dataFatura = (TextView)findViewById(R.id.data_fatura2);
        consumoDia = (TextView)findViewById(R.id.consumo_dia2);
        consumoMes = (TextView)findViewById(R.id.consumo_mes2);
        totalFatura = (TextView)findViewById(R.id.total_fatura2);

        //Inicia a barra de progresso como inderteminada
        final ProgressDialog mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setIndeterminate(true);
        mProgressDialog.setTitle("Gerando Relatorio");
        mProgressDialog.setMessage("Aguarde...");
        mProgressDialog.show();

        //Leitura do arquivo para pegar o ID do usuario
        Usuario usuario = null;
        try {
            FileInputStream fis = openFileInput("UsuarioLogado");
            ObjectInputStream ois = new ObjectInputStream(fis);
            usuario = (Usuario) ois.readObject();
            ois.close();
            fis.close();
        }catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        //Envia a requisição para o WebService e trata a resposta
        final Call<Relatorio> relatorio = new InstanciaRetrofit().intanciarRetrofit().receberRelatorio(usuario.getId());
        relatorio.enqueue(new Callback<Relatorio>() {
            @Override
            public void onResponse(Call<Relatorio> call, Response<Relatorio> response) {
                relatorioResposta = response.body();
                if(relatorioResposta!=null){
                    if(mProgressDialog.isShowing()) {
                        mProgressDialog.dismiss();
                    }
                    idFatura.setText(String.valueOf(relatorioResposta.getId()));
                    dataFatura.setText(relatorioResposta.getData());
                    consumoDia.setText(String.valueOf(relatorioResposta.getConsumoDia())+" Kw");
                    consumoMes.setText(String.valueOf( relatorioResposta.getConsumoMes())+" Kw");
                    totalFatura.setText("R$ "+String.valueOf(relatorioResposta.getConsumoRs()));

                }else{
                    if(mProgressDialog.isShowing()) {
                        mProgressDialog.dismiss();
                    }
                    Toast.makeText(getApplicationContext(),"Erro ao gerar relatorio",Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<Relatorio> call, Throwable t) {
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
                relatorio.cancel();
            }
        }.start();


        ActionBar actionBar = getSupportActionBar();

        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle("Relatorio");
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}