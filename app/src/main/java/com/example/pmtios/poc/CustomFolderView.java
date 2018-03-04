package com.example.pmtios.poc;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.constraint.ConstraintLayout;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

/**
 * Created by PMTIOS on 3/3/18.
 */

public class CustomFolderView extends ConstraintLayout {
    ImageView iv_one,iv_two,iv_three,iv_four;
    TextView tv_title;
    Context context;
    public CustomFolderView(Context context) {
        super(context);
        init(context);
    }

    public CustomFolderView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public CustomFolderView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }
    public void init(Context context){
        this.context=context;
        View rootView = inflate(context, R.layout.custom_folder_view, this);

        iv_one = rootView.findViewById(R.id.iv_one);
        iv_two = rootView.findViewById(R.id.iv_two);
        iv_three = rootView.findViewById(R.id.iv_three);
        iv_four = rootView.findViewById(R.id.iv_four);
        tv_title = rootView.findViewById(R.id.tv_title);

    }
    public void loadImages(int[] drawables){
        Glide.with(context).load(drawables[0]).into(iv_one);
        Glide.with(context).load(drawables[1]).into(iv_two);
        Glide.with(context).load(drawables[2]).into(iv_three);
        Glide.with(context).load(drawables[3]).into(iv_four);
    }
    public void setTitle(String title){
        tv_title.setText(title);
    }
    public String getTitle(){
        return tv_title.getText().toString();
    }
}
