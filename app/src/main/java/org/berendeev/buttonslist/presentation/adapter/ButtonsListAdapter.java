package org.berendeev.buttonslist.presentation.adapter;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.Adapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.berendeev.buttonslist.R;
import org.berendeev.buttonslist.domain.model.Item;
import org.berendeev.buttonslist.presentation.OnItemClick;
import org.berendeev.buttonslist.views.FilledButton;

import java.util.List;


public class ButtonsListAdapter extends Adapter<ButtonsListAdapter.ButtonsHolder> {

    private List<Item> items;
    private OnItemClick presenter;

    public ButtonsListAdapter(List<Item> items, OnItemClick presenter) {
        this.items = items;
        this.presenter = presenter;
    }

    @Override public ButtonsHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        final ButtonsHolder holder = new ButtonsHolder(view);
        view.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                int adapterPosition = holder.getAdapterPosition();
                if(adapterPosition != RecyclerView.NO_POSITION){
                    presenter.onClick(holder.getAdapterPosition());
                }

            }
        });
        return holder;
    }

    @Override public void onBindViewHolder(ButtonsHolder holder, int position) {
        holder.label.setText("" + items.get(position).getNumber());
        holder.filledButton.setFill(items.get(position).getFill());
    }

    @Override public int getItemCount() {
        return items.size();
    }

    public void setItems(List<Item> items){
        this.items = items;
        notifyDataSetChanged();
    }

    public void addItem(Item item){
        items.add(item);
        notifyItemInserted(items.size() - 1);
    }

    public static class ButtonsHolder extends RecyclerView.ViewHolder{

        private FilledButton filledButton;
        private TextView label;
        public ButtonsHolder(View itemView) {
            super(itemView);
            filledButton = (FilledButton) itemView.findViewById(R.id.filled_button);
            label = (TextView) itemView.findViewById(R.id.label);
        }
    }
}
