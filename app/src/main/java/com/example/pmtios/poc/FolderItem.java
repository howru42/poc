package com.example.pmtios.poc;

/**
 * Created by PMTIOS on 3/3/18.
 */

public class FolderItem {
    int draw_one, draw_two, draw_three, draw_four;
    String title;
    String id;

    public FolderItem setDrawables(int[] drawables) {
        this.draw_one = drawables[0];
        this.draw_two = drawables[1];
        this.draw_three = drawables[2];
        this.draw_four = drawables[3];
        return this;
    }



    public String getId() {
        return id;
    }

    public FolderItem setId(String id) {
        this.id = id;
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
