package infopower.economyenergy.consultasRecycler;


import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;
import infopower.economyenergy.R;
import infopower.economyenergy.consultasRecycler.Consultas;
import infopower.economyenergy.consultasRecycler.ConsultasAdapter;


public class ConsultasAdapter extends RecyclerView.Adapter<ConsultasAdapter.MyViewHolder> {

    private List<Consultas> consultasList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView titulo1, descricao1;
        public ImageView img1;

        public MyViewHolder(View view) {
            super(view);
            titulo1 = (TextView) view.findViewById(R.id.consultas_text_name);
            descricao1 = (TextView) view.findViewById(R.id.consultas_text_description);
            img1 = (ImageView)view.findViewById(R.id.consultas_img);
        }
    }

    public ConsultasAdapter(List<Consultas> consultasList) {this.consultasList = consultasList;}

    @Override
    public ConsultasAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.consultas_recycler_list, parent, false);

        return new MyViewHolder(itemView);
    }

    public void onBindViewHolder(ConsultasAdapter.MyViewHolder holder, int position) {
       Consultas consultas = consultasList.get(position);

        holder.titulo1.setText(consultas.getTitulo());
        holder.descricao1.setText(consultas.getDescricao());
        holder.img1.setImageResource(consultas.getImg());
    }

    @Override
    public int getItemCount() {

        return 2;
    }


    public Object getItem(int position) {
        return consultasList.get(position);
    }

}
