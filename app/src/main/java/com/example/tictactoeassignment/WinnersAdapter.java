package com.example.tictactoeassignment;

import static java.lang.Long.parseLong;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class WinnersAdapter extends RecyclerView.Adapter<WinnersAdapter.WinsViewHolder>{
        List<Winner> wins;
        Context context;
        public class WinsViewHolder extends RecyclerView.ViewHolder
        {
            Winner winner;
            public WinsViewHolder(@NonNull View itemView) {
                super(itemView);
                winner = new Winner(itemView.findViewById(R.id.tvMainTitle).toString(), parseLong(itemView.findViewById(R.id.tvTime).toString()));
            }
        }
    public WinnersAdapter(Context c, List<Winner> wins) {
        this.wins = wins;
        context = c;
    }

    @NonNull
    @Override
    public WinnersAdapter.WinsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //inflating...
        View view = LayoutInflater.from(context)
                .inflate(R.layout.winner_row, parent,false);

        return new WinsViewHolder(view); //returning a view-holder item, which contains the inflated view information
    }

    @Override
    public void onBindViewHolder(@NonNull WinsViewHolder holder, int position) {
        holder.winner.setName(wins.get(position).getName());
        holder.winner.setTime(wins.get(position).getTime());
    }

    private String stringTime(long time)
    {
        //time is in seconds
        long min = (time % 3600) / 60;
        long sec = time % 60;
        return String.format("%02d:%02d", min, sec);
    }

    @Override
    public int getItemCount() {
        return wins.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        //a class inside adapter,
        // this class HOLDS (saves inside) the view of one object from our list

        //lets add inside all view items used for each variable inside one object
        TextView nameTV;
        TextView timeTV;
        TextView placeTV;

        //in C-TOR we get all view items from our layout using
        public ViewHolder(@NonNull View itemView) {
            super(itemView); //after it's inflated

            nameTV = itemView.findViewById(R.id.nameTV);  //get text-view item from our VIEW, so we could set it (by id)
            timeTV = itemView.findViewById(R.id.timeTV);
            placeTV = itemView.findViewById(R.id.placeTV);
        }
    }
}
