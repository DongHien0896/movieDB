package com.framgia.hien.moviedb.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class GenresResponse implements Serializable{
    @SerializedName("genres")
    @Expose
    private List<Genre> items = new ArrayList<>();

    public GenresResponse(List<Genre> items){
        this.items = items;
    }

    public List<Genre> getItems() {
        return items;
    }

    public void setItems(List<Genre> items) {
        this.items = items;
    }
}
