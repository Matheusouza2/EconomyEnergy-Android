package infopower.economyenergy.activitys;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import infopower.economyenergy.R;

public class AcaoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acao);

        Intent intent = getIntent();
        String consulta = intent.getStringExtra("acao");
        TextView textView = (TextView) findViewById(R.id.acoes_text_name);
        textView.setText(consulta);
    }
}
