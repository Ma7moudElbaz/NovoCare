package com.cat.novocare.main_activity.more.faq;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.cat.novocare.R;

import java.util.ArrayList;
import java.util.List;


public class Faq_adapter extends RecyclerView.Adapter<Faq_adapter.ViewHolder> {

    private final List<Faq_item> items;

    private final Context mContext;

    public Faq_adapter(Context context, ArrayList<Faq_item> items) {
        this.mContext = context;
        this.items = items;
    }

    @NonNull
    @Override
    public Faq_adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_faq, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Faq_adapter.ViewHolder holder, final int position) {

        holder.question.setText(items.get(position).getQuestion());
        holder.answer.setText(items.get(position).getAnswer());

        if (items.get(position).isExpanded()) {
            holder.toggle.setImageResource(R.drawable.ic_minus);
            holder.answer.setVisibility(View.VISIBLE);
        } else {

            holder.toggle.setImageResource(R.drawable.ic_plus);
            holder.answer.setVisibility(View.GONE);
        }


        holder.question_layout.setOnClickListener(v -> {
            items.get(position).setExpanded(!(items.get(position).isExpanded()));
            notifyItemChanged(position);
        });


    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView question, answer;
        RelativeLayout question_layout;
        ImageView toggle;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            question = itemView.findViewById(R.id.question);
            answer = itemView.findViewById(R.id.answer);
            toggle = itemView.findViewById(R.id.toggle);
            question_layout = itemView.findViewById(R.id.question_layout);
        }
    }
}