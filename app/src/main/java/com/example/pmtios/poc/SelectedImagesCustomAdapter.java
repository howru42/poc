package com.example.pmtios.poc;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

/**
 * Created by PMTIOS on 3/3/18.
 */

public class SelectedImagesCustomAdapter extends RecyclerView.Adapter<SelectedImagesCustomAdapter.MyViewHolder> {
    private final ArrayList<SelectedImageItem> itemsList;
    private final Context mContext;

    public SelectedImagesCustomAdapter(Context context, ArrayList<SelectedImageItem> itemsList) {
        this.itemsList = itemsList;
        this.mContext = context;
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView iv_item, iv_cancel;
        TextView tv_item;

        public MyViewHolder(View itemView) {
            super(itemView);

            iv_item = itemView.findViewById(R.id.iv_item);
            iv_cancel = itemView.findViewById(R.id.iv_cancel);
            tv_item = itemView.findViewById(R.id.tv_item);
        }
    }


    @Override
    public SelectedImagesCustomAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_selected_images, parent, false));
    }

    @Override
    public void onBindViewHolder(final SelectedImagesCustomAdapter.MyViewHolder _holder, final int position) {
        final MyViewHolder holder = (MyViewHolder) _holder;
        final SelectedImageItem item = itemsList.get(position);

        holder.tv_item.setText(item.getImgName());
        Glide.with(mContext).load(item.getImageDrawable()).into(_holder.iv_item);
        holder.iv_cancel.setVisibility(item.isSelected() ? View.VISIBLE : View.INVISIBLE);
        holder.iv_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                item.setSelected(!item.isSelected());
                notifyDataSetChanged();
            }
        });


    }

    @Override
    public int getItemCount() {
        return itemsList.size();
    }

    public ArrayList<SelectedImageItem> getItemsList() {
        return itemsList;
    }
}