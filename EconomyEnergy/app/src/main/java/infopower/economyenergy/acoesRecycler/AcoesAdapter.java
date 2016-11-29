package infopower.economyenergy.acoesRecycler;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import infopower.economyenergy.R;

/**
 * Created by Mathe on 13/09/2016.
 */
public class AcoesAdapter extends BaseAdapter {


    private String[] nome = new String[]
            {"Sala de Estar", "Sala de Jantar", "Escritório", "Suíte 1", "Suíte 2", "Quarto 1", "Quarto 2", "Cozinha",
                    "Area de Serviço", "Banheiro"};
    private String[] descricao = new String[]
            {"Ações sobre lampadas e tomadas da Sala de Estar",
                    "Ações sobre lampadas e tomadas do Sala de Jantar",
                    "Ações sobre lampadas e tomadas do Escritório",
                    "Ações sobre lampadas e tomadas da Suíte 1",
                    "Ações sobre lampadas e tomadas da Suíte 2",
                    "Ações sobre lampadas e tomadas do Quarto 1",
                    "Ações sobre lampadas e tomadas do Quarto 2",
                    "Ações sobre lampadas e tomadas da Cozinha",
                    "Ações sobre lampadas e tomadas da Area de Serviço",
                    "Ações sobre lampadas e tomadas da Banheiro"};

    private int[] images = new int[]{
            R.drawable.sofa_icn,
            R.drawable.sofa_icn,
            R.drawable.escritorio_icn,
            R.drawable.quarto_icn,
            R.drawable.quarto_icn,
            R.drawable.quarto_icn,
            R.drawable.quarto_icn,
            R.drawable.cozinha_icn,
            R.drawable.banheiro_icn,
            R.drawable.banheiro_icn
    };
    private Context context;

    public AcoesAdapter(Context context) {
        super();
        this.context = context;
    }

    @Override
    public int getCount() {
        return nome.length;
    }

    @Override
    public Object getItem(int position) {
        return nome[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        String nomes = nome[position];
        String descricoes = descricao[position];
        int image = images[position];

        View view = LayoutInflater.from(context).inflate(R.layout.adapter_acoes, parent, false);

        TextView tnome = (TextView) view.findViewById(R.id.acoes_text_name);
        tnome.setText(nomes);

        TextView tdescricao = (TextView) view.findViewById(R.id.acoes_text_description);
        tdescricao.setText(descricoes);

        ImageView img = (ImageView)view.findViewById(R.id.acoes_img);
        img.setImageResource(image);

        return view;
    }


}