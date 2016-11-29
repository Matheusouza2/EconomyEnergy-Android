package infopower.economyenergy.fragments;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import infopower.economyenergy.R;

public class AcaoFragment extends Fragment {

    public static final String TAG = "AcaoFragment";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle saveInstanceState){
        View view = inflater.inflate(R.layout.acao_fragment, container, false);
        if(getArguments()!=null){
            TextView textViewPais = (TextView) view.findViewById(R.id.text_view_acao);
            String consulta = getArguments().getString("acao");
            textViewPais.setText(consulta);
        }
        return view;
    }

    public void setConsulta(String consulta){
        TextView textViewConsulta = (TextView) getView().findViewById(R.id.text_view_acao);
        textViewConsulta.setText(consulta);
    }
}