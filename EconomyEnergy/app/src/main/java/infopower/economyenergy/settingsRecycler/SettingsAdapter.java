package infopower.economyenergy.settingsRecycler;


import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import infopower.economyenergy.R;

/**
 * Created by aldemir on 08/09/2016.
 */
public class SettingsAdapter extends RecyclerView.Adapter<SettingsAdapter.MyViewHolder> {

    private List<Settings> moviesList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title, description;
        public ImageView img;

        public MyViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.title_tv);
            description = (TextView) view.findViewById(R.id.description_tv);
            img = (ImageView)view.findViewById(R.id.img_settings);
        }
    }

    public SettingsAdapter(List<Settings> moviesList) {
        this.moviesList = moviesList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.movie_list_row, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Settings settings = moviesList.get(position);

        holder.title.setText(settings.getTitle());
        holder.description.setText(settings.getDescription());
        holder.img.setImageResource(settings.getImg());
    }

    @Override
    public int getItemCount() {
        return moviesList.size();
    }
}