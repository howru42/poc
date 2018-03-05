package com.example.pmtios.poc;

/**
 * Created by PMTIOS on 3/4/18.
 */

public class SelectedImageItem {
    String imgUrl;
    String imgName;
    int imageDrawable;
    boolean isSelected;

    public String getImgUrl() {
        return imgUrl;
    }

    public SelectedImageItem setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
        return this;
    }

    public int getImageDrawable() {
        return imageDrawable;
    }

    public SelectedImageItem setImageDrawable(int imageDrawable) {
        this.imageDrawable = imageDrawable;
        return this;
    }

    public String getImgName() {
        return imgName;
    }

    public SelectedImageItem setImgName(String imgName) {
        this.imgName = imgName;
        return this;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }
}
