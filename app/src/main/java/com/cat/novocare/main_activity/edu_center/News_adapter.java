package com.cat.novocare.main_activity.edu_center;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.cat.novocare.R;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class News_adapter extends RecyclerView.Adapter<News_adapter.ViewHolder> {

    private final List<News_item> items;

    private final Context mContext;

    public News_adapter(Context context, ArrayList<News_item> items) {
        this.mContext = context;
        this.items = items;
    }

    @NonNull
    @Override
    public News_adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_news, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull News_adapter.ViewHolder holder, final int position) {

        holder.title.setText(items.get(position).getTitle());
        holder.caption.setText(items.get(position).getCaption());
        holder.date.setText(String.valueOf(items.get(position).getDate()));

        Glide.with(mContext).load(items.get(position).getImageUrl()).placeholder(R.drawable.image_loading).into(holder.image);

        holder.parent_layout.setOnClickListener(v -> {
            Intent i = new Intent(mContext, NewsDetails.class);
            i.putExtra("newsItem", (Serializable) items.get(position));
            mContext.startActivity(i);
        });

    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView title, date, caption;
        ImageView image;
        LinearLayout parent_layout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
            caption = itemView.findViewById(R.id.caption);
            date = itemView.findViewById(R.id.date);
            image = itemView.findViewById(R.id.img);
            parent_layout = itemView.findViewById(R.id.parent_layout);
        }
    }
}