package infopower.economyenergy.acoes;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import infopower.economyenergy.R;
import infopower.economyenergy.settingsRecycler.Settings;

/**
 * Created by Mathe on 13/09/2016.
 */
public class AcoesAdapter extends RecyclerView.Adapter<AcoesAdapter.MyViewHolder> {

    private List<Acoes> acoesList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView titulo, descricao;
        public ImageView img;

        public MyViewHolder(View view) {
            super(view);
            titulo = (TextView) view.findViewById(R.id.title_tv);
            descricao = (TextView) view.findViewById(R.id.description_tv);
            img = (ImageView)view.findViewById(R.id.img_settings);
        }
    }

    public SettingsAdapter(List<Settings> acoesList) {
        this.acoesList = acoesList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.movie_list_row, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Settings settings = acoesList.get(position);

        holder.titulo.setText(settings.getTitle());
        holder.descricao.setText(settings.getDescription());
        holder.img.setImageResource(settings.getImg());
    }

    @Override
    public int getItemCount() {
        return acoesList.size();
    }
}