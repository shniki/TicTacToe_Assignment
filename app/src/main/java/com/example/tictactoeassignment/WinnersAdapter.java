package com.example.tictactoeassignment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class WinnersAdapter extends RecyclerView.Adapter<WinnersAdapter.ViewHolder>{
        List<Winner> wins;

    public WinnersAdapter(List<Winner> wins) {
        this.wins = wins;
    }

    @NonNull
    @Override
    public WinnersAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //inflating...
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.winner_layout,parent,false);

        return new ViewHolder(view); //returning a view-holder item, which contains the inflated view information
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Winner win = wins.get(position);

        //puts information view (actually in view-holder)
        holder.placeTV.setText((position+1)+")");
        holder.timeTV.setText(win.getName());
        holder.nameTV.setText(stringTime(win.getTime()));
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
