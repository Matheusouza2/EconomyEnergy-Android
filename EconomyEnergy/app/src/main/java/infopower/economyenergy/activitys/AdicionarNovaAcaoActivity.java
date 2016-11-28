package infopower.economyenergy.activitys;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import infopower.economyenergy.R;
import infopower.economyenergy.spinnerAdapter.SpinnerAdapterImage;

public class AdicionarNovaAcaoActivity extends AppCompatActivity {
    private Spinner spinner;
    private EditText nomeAcao,descricaoAcao;
    private Button btnCadastrarAcao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adicionar_nova_acao);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Adicionar nova ação");

        nomeAcao = (EditText)findViewById(R.id.nome_acao_et);
        descricaoAcao = (EditText)findViewById(R.id.descricao_acao_et);
        spinner = (Spinner)findViewById(R.id.spinner_images);
        btnCadastrarAcao = (Button)findViewById(R.id.cadastrar_nova_acao_btn);

        final SpinnerAdapterImage spinnerAdapter = new SpinnerAdapterImage(getApplicationContext(),new Integer[]{R.drawable.sofa_icn,
                R.drawable.escritorio_icn, R.drawable.cozinha_icn, R.drawable.quarto_icn, R.drawable.banheiro_icn});
        spinner.setAdapter(spinnerAdapter);

        btnCadastrarAcao.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
            }
        });
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