package infopower.economyenergy.activitys;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

import infopower.economyenergy.R;
import infopower.economyenergy.fragments.AcaoFragment;
import infopower.economyenergy.webService.ComandoRele;

public class AcoesSalaActivity extends AppCompatActivity {

    private Switch tomada1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acoes_sala);


        ActionBar actionBar = getSupportActionBar();

        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle("Tela de Ações");

        final ComandoRele comandoRele = new ComandoRele();
        tomada1 = (Switch)findViewById(R.id.switch1);
        tomada1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    comandoRele.enviarComando("?ledon");
                } else {
                    comandoRele.enviarComando("?ledoff");
                }
            }
        });



        if(savedInstanceState==null){

            Intent intent = getIntent();
            String consulta = intent.getStringExtra("acao");
            TextView textView = (TextView) findViewById(R.id.text_view_acao);
            textView.setText(consulta);

        }
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