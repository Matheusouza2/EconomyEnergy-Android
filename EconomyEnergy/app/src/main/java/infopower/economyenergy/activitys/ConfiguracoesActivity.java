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
import infopower.economyenergy.settingsRecycler.Settings;
import infopower.economyenergy.settingsRecycler.SettingsAdapter;
import infopower.economyenergy.settingsRecycler.RecyclerTouchListener;

public class ConfiguracoesActivity extends AppCompatActivity {
    private List<Settings> settingsList = new ArrayList<>();
    private RecyclerView recyclerView;
    private SettingsAdapter mAdapter;
    private TextView nomeUserLogado;
    private GridLayout layoutUser;
    private CircleImageView imgUserLogado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configuracao);

        ActionBar actionBar = getSupportActionBar();

        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle("Configuração");

        nomeUserLogado = (TextView)findViewById(R.id.nome_user_logado);
        imgUserLogado = (CircleImageView)findViewById(R.id.img_user_logado);
        imgUserLogado.setImageResource(R.drawable.user_1);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        layoutUser = (GridLayout)findViewById(R.id.layout_usuario);
        layoutUser.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ConfiguracoesActivity.this,PerfilUsuarioActivity.class);
                startActivity(i);
            }
        });

        mAdapter = new SettingsAdapter(settingsList);

        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);

        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getApplicationContext(), recyclerView, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                Intent intent;
                switch (position){
                    case 0:
                        intent = new Intent(ConfiguracoesActivity.this, MudarSenhaActivity.class);
                        startActivity(intent);
                        break;
                    case 1:
                        intent = new Intent(ConfiguracoesActivity.this, SobreActivity.class);
                        startActivity(intent);
                        break;
                    case 2:
                        Toast.makeText(ConfiguracoesActivity.this, "Sair", Toast.LENGTH_SHORT).show();
                        break;
                    default:
                        Toast.makeText(ConfiguracoesActivity.this, "Nada Clicado", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onLongClick(View view, int position) {
                Settings settings = settingsList.get(position);
                switch (position){
                    case 0:
                        Toast.makeText(ConfiguracoesActivity.this, "Modificar Senha", Toast.LENGTH_SHORT).show();
                        break;
                    case 1:
                        Toast.makeText(ConfiguracoesActivity.this, "Sobre", Toast.LENGTH_SHORT).show();
                        break;
                    case 2:
                        Toast.makeText(ConfiguracoesActivity.this, "Sair", Toast.LENGTH_SHORT).show();
                        break;
                    default:
                        Toast.makeText(ConfiguracoesActivity.this, "Nada Clicado", Toast.LENGTH_SHORT).show();
                }
            }
        }));
        prepareMovieData();
    }
    private void prepareMovieData() {
        Settings settings = new Settings( "Mudar Senha", "Clique para mudar a senha de usuário", R.drawable.password);
        settingsList.add(settings);

        settings = new Settings("Sobre", "Informações do sistema Economy Energy ", R.drawable.about);
        settingsList.add(settings);

        settings = new Settings("Sair", "Clique para sair do aplicativo", R.drawable.exit);
        settingsList.add(settings);

        mAdapter.notifyDataSetChanged();
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