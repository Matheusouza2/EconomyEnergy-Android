package infopower.economyenergy.activitys;


import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.List;
import de.hdodenhof.circleimageview.CircleImageView;

import infopower.economyenergy.R;
import infopower.economyenergy.dominio.Usuario;
import infopower.economyenergy.settingsRecycler.DividerItemDecoration;
import infopower.economyenergy.settingsRecycler.Settings;
import infopower.economyenergy.settingsRecycler.SettingsAdapter;
import infopower.economyenergy.settingsRecycler.RecyclerTouchListener;

public class ConfiguracoesActivity extends AppCompatActivity {
    private List<Settings> settingsList = new ArrayList<>();
    private RecyclerView recyclerView;
    private SettingsAdapter mAdapter;
    private TextView nomeUserLogado;
    private CircleImageView imgUserLogado;
    private static FileInputStream fis;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configuracao);

        ActionBar actionBar = getSupportActionBar();

        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle("Configuração");

        nomeUserLogado = (TextView)findViewById(R.id.nome_user_logado);
        imgUserLogado = (CircleImageView)findViewById(R.id.img_user_logado);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);


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
                        android.app.AlertDialog alertDialog = new android.app.AlertDialog.Builder(ConfiguracoesActivity.this).create();
                        alertDialog.setTitle("Fechando...");
                        alertDialog.setMessage("Fechar Aplicativo?");
                        alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                               finish();
                                //Grava a preferencia dizendo que o usuario fez logout
                                SharedPreferences prefs = getSharedPreferences("logado", 0);
                                SharedPreferences.Editor editor = prefs.edit();
                                editor.clear().commit();

                                onBackPressed();
                            }
                        });
                        alertDialog.setIcon(R.drawable.exit);
                        alertDialog.show();
                        break;
                    default:
                        Toast.makeText(ConfiguracoesActivity.this, "Nada Clicado", Toast.LENGTH_SHORT).show();
                }
            }


            public void onBackPressed() {
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.putExtra("EXIT", true);
                startActivity(intent);
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
        //Leitura do arquivo com informações do usuario
        Usuario usuario = null;
        try {
            fis = openFileInput("UsuarioLogado");
            ObjectInputStream ois = new ObjectInputStream(fis);
            usuario = (Usuario) ois.readObject();
            ois.close();
            fis.close();
        }catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        imgUserLogado.setImageResource(R.drawable.user_1);
        nomeUserLogado.setText(usuario.getNome());
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