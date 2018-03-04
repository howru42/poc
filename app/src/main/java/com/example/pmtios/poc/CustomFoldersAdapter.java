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

public class CustomFoldersAdapter extends RecyclerView.Adapter<CustomFoldersAdapter.MyViewHolder> {
    private final ArrayList<FolderItem> itemsList;
    private final Context mContext;

    public CustomFoldersAdapter(Context context, ArrayList<FolderItem> itemsList) {
        this.itemsList = itemsList;
        this.mContext = context;
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView iv_one,iv_two,iv_three,iv_four;
        TextView tv_title;

        public MyViewHolder(View itemView) {
            super(itemView);

            iv_one = itemView.findViewById(R.id.iv_one);
            iv_two = itemView.findViewById(R.id.iv_two);
            iv_three = itemView.findViewById(R.id.iv_three);
            iv_four = itemView.findViewById(R.id.iv_four);
            tv_title = itemView.findViewById(R.id.tv_title);
        }
    }


    @Override
    public CustomFoldersAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_folder_view, parent, false));

    }

    @Override
    public void onBindViewHolder(final CustomFoldersAdapter.MyViewHolder _holder, final int position) {
        final MyViewHolder holder = (MyViewHolder) _holder;
        FolderItem item=itemsList.get(position);

        holder.tv_title.setText(item.getTitle());

//        int[] drawables=new int[]{R.drawable.dummy_1,R.drawable.dummy_2,R.drawable.dummy_1,R.drawable.dummy_2};
        Glide.with(mContext).load(item.getDraw_one()).into(_holder.iv_one);
        Glide.with(mContext).load(item.getDraw_two()).into(_holder.iv_two);
        Glide.with(mContext).load(item.getDraw_three()).into(_holder.iv_three);
        Glide.with(mContext).load(item.getDraw_four()).into(_holder.iv_four);


    }

    @Override
    public int getItemCount() {
        return itemsList.size();
    }


}