package com.framgia.hien.moviedb.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MovieByPerson implements Serializable{
    @SerializedName("page")
    @Expose
    private int mPage;

    @SerializedName("total_results")
    @Expose
    private int mTotalResults;

    @SerializedName("total_pages")
    @Expose
    private int mTotalPages;

    @SerializedName("results")
    @Expose
    private List<ResultMovie> mItems = new ArrayList<>();

    public int getPage() {
        return mPage;
    }

    public void setPage(int page) {
        mPage = page;
    }

    public int getTotalResults() {
        return mTotalResults;
    }

    public void setTotalResults(int totalResults) {
        mTotalResults = totalResults;
    }

    public int getTotalPages() {
        return mTotalPages;
    }

    public void setTotalPages(int totalPages) {
        mTotalPages = totalPages;
    }

    public List<ResultMovie> getItems() {
        return mItems;
    }

    public void setItems(List<ResultMovie> items) {
        mItems = items;
    }
}
