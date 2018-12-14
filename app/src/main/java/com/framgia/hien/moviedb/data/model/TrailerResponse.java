package com.framgia.hien.moviedb.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class TrailerResponse implements Serializable {
    @SerializedName("id")
    @Expose
    private Integer mId;
    @SerializedName("results")
    @Expose
    private List<Trailer> mResults = null;

    public Integer getId() {
        return mId;
    }

    public void setId(Integer id) {
        this.mId = id;
    }

    public List<Trailer> getResults() {
        return mResults;
    }

    public void setResults(List<Trailer> results) {
        this.mResults = results;
    }
}
