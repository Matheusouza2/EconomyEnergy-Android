package infopower.economyenergy.activitys;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.GridLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import infopower.economyenergy.R;
import infopower.economyenergy.settingsRecycler.DividerItemDecoration;
import infopower.economyenergy.settingsRecycler.RecyclerTouchListener;
import infopower.economyenergy.settingsRecycler.Settings;
import infopower.economyenergy.settingsRecycler.SettingsAdapter;

public class MudarSenhaActivity extends AppCompatActivity {

    private TextView nomeUserLogado;
    private GridLayout layoutUser;
    private CircleImageView imgUserLogado;

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

        layoutUser = (GridLayout)findViewById(R.id.layout_usuario);
        layoutUser.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

            }
        });

    }


    @Override
    protected void onResume(){
        super.onResume();
        nomeUserLogado.setText("Matheus Souza");

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
