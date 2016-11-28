package infopower.economyenergy.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import infopower.economyenergy.R;
import infopower.economyenergy.acoesRecycler.Acoes;
import infopower.economyenergy.acoesRecycler.AcoesAdapter;
import infopower.economyenergy.activitys.AcoesSalaActivity;
import infopower.economyenergy.activitys.GraficoActivity;
import infopower.economyenergy.activitys.RelatorioActivity;
import infopower.economyenergy.consultasRecycler.Consultas;
import infopower.economyenergy.consultasRecycler.ConsultasAdapter;
import infopower.economyenergy.settingsRecycler.RecyclerTouchListener;

/**
 * Created by Mathe on 31/08/2016.
 */
public class AcoesFragments extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.acoes_fragment, container, false);
        ListView listViewConsult = (ListView) rootView.findViewById(R.id.recycler_view_consultas);
        listViewConsult.setAdapter(new AcoesAdapter(getActivity()));
        listViewConsult.setOnItemClickListener(onItemClickPais());

        return rootView;
    }

    public AdapterView.OnItemClickListener onItemClickPais(){

        return new AdapterView.OnItemClickListener(){
            public void onItemClick(AdapterView<?> parente, View view, int position, long id){
                AcoesAdapter adapter = (AcoesAdapter) parente.getAdapter();
                String consulta = (String) adapter.getItem(position);

                    Intent intent = new Intent(getActivity(), AcoesSalaActivity.class);
                    intent.putExtra("acao", consulta);
                    startActivity(intent);

            }
        };

    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater menuInflater) {
        super.onCreateOptionsMenu(menu,menuInflater);
        menu.getItem(1).setVisible(false);
        menuInflater.inflate(R.menu.menu_tela_tabs, menu);
    }
}