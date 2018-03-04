package com.example.pmtios.poc;

/**
 * Created by PMTIOS on 3/3/18.
 */

public class FolderItem {
    int draw_one,draw_two,draw_three,draw_four;
    String title;

    public FolderItem setDrawables(int[] drawables) {
        this.draw_one = draw_one;
        this.draw_two = draw_two;
        this.draw_three = draw_three;
        this.draw_four = draw_four;
        return this;
    }


    public int getDraw_one() {
        return draw_one;
    }

    public FolderItem setDraw_one(int draw_one) {
        this.draw_one = draw_one;
        return this;
    }

    public int getDraw_two() {
        return draw_two;
    }

    public FolderItem setDraw_two(int draw_two) {
        this.draw_two = draw_two;
        return this;
    }

    public int getDraw_three() {
        return draw_three;
    }

    public FolderItem setDraw_three(int draw_three) {
        this.draw_three = draw_three;
        return this;
    }

    public int getDraw_four() {
        return draw_four;
    }

    public FolderItem setDraw_four(int draw_four) {
        this.draw_four = draw_four;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public FolderItem setTitle(String title) {
        this.title = title;
        return this;
    }
}
