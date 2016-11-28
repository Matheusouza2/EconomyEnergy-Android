package infopower.economyenergy.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import infopower.economyenergy.R;
import infopower.economyenergy.activitys.GraficoActivity;
import infopower.economyenergy.activitys.RelatorioActivity;
import infopower.economyenergy.consultasRecycler.Consultas;
import infopower.economyenergy.consultasRecycler.ConsultasAdapter;
import infopower.economyenergy.settingsRecycler.DividerItemDecoration;
import infopower.economyenergy.settingsRecycler.RecyclerTouchListener;

public class ConsultasFragments extends Fragment {
    private List<Consultas> consultasList = new ArrayList<>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.consultas_fragment, container, false);


       Consultas consultas = new Consultas("Relatorio","Relatorios do seu consumo, agora mesmo!",R.drawable.relatorio_icn);
        consultasList.add(consultas);

       consultas = new Consultas("Grafico","Geração de grafico do seu consumo",R.drawable.graficos_icn);
        consultasList.add(consultas);

        final RecyclerView rv = (RecyclerView) rootView.findViewById(R.id.recycler_view_consultas);
        rv.setHasFixedSize(true);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
        rv.setLayoutManager(mLayoutManager);
        rv.addItemDecoration(new DividerItemDecoration(getActivity(), LinearLayoutManager.VERTICAL));
        rv.setItemAnimator(new DefaultItemAnimator());

        ConsultasAdapter adapter = new ConsultasAdapter(consultasList);
        rv.setAdapter(adapter);

        rv.addOnItemTouchListener(new RecyclerTouchListener(getActivity().getApplicationContext(), rv, new RecyclerTouchListener.ClickListener(){

            @Override
            public void onClick(View view, int position) {

                switch (position){
                    case 0:
                        Intent intent = new Intent(getActivity(), RelatorioActivity.class);
                        startActivity(intent);
                        Toast.makeText(getActivity(), "Relatorio", Toast.LENGTH_SHORT).show();
                        break;
                    case 1:
                        Intent intent2 = new Intent(getActivity(), GraficoActivity.class);
                        startActivity(intent2);
                        Toast.makeText(getActivity(), "Grafico", Toast.LENGTH_SHORT).show();
                        break;
                    default:
                        Toast.makeText(getActivity(), "Nada Clicado", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));

        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        rv.setLayoutManager(llm);

        return rootView;
    }



}
