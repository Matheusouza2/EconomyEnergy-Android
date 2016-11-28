package infopower.economyenergy.activitys;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

import infopower.economyenergy.R;
import infopower.economyenergy.fragments.AcaoFragment;

public class AcoesSalaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acoes_sala);


        FragmentManager fragmentManager = getSupportFragmentManager();

        if(savedInstanceState==null){
            AcaoFragment fragment = new AcaoFragment();
            fragment.setArguments(getIntent().getExtras());

            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.add(R.id.activity_acoes_sala, fragment, AcaoFragment.TAG);
            fragmentTransaction.commit();
        }

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle("Tela de Ações");
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