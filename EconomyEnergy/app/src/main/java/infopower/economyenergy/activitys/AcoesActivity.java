package infopower.economyenergy.activitys;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import infopower.economyenergy.R;
import infopower.economyenergy.acoesRecycler.AcoesAdapter;
import infopower.economyenergy.consultasRecycler.ConsultasAdapter;

public class AcoesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acoes);

        ListView listViewConsult = (ListView) findViewById(R.id.recycler_view_consultas);
        listViewConsult.setAdapter(new AcoesAdapter(this));
        listViewConsult.setOnItemClickListener(onItemClickPais());
    }

    public AdapterView.OnItemClickListener onItemClickPais(){

        return new AdapterView.OnItemClickListener(){
            public void onItemClick(AdapterView<?> parente, View view, int position, long id){
                AcoesAdapter adapter = (AcoesAdapter) parente.getAdapter();
                String pais = (String) adapter.getItem(position);
                Intent intent = new Intent(AcoesActivity.this, AcaoActivity.class);
                intent.putExtra("pais", pais);
                startActivity(intent);
            }
        };

    }
}
