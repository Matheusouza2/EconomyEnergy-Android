package infopower.economyenergy.activitys;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import infopower.economyenergy.R;
import infopower.economyenergy.fragments.FragmentAdapter;

public class TelaTabsActivity extends AppCompatActivity {

    private TabLayout tabLayout;
    private ViewPager viewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_tabs);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        tabLayout = (TabLayout)findViewById(R.id.tab_layout);
        viewPager = (ViewPager)findViewById(R.id.view_pager);

        viewPager.setAdapter(new FragmentAdapter(getSupportFragmentManager(), getResources().getStringArray(R.array.nomes_tabs)));

        tabLayout.setupWithViewPager(viewPager);

        tabLayout.getTabAt(1).select();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_tela_tabs, menu);
        menu.getItem(0).setVisible(false);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getTitle().equals("Configurações")){
            Intent intent = new Intent(TelaTabsActivity.this, ConfiguracoesActivity.class);
            startActivity(intent);
        }
        return true;
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (getIntent().getBooleanExtra("EXIT", false)) {
            finish();
        }
    }
}