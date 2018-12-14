package com.framgia.hien.moviedb.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class CastResponse implements Serializable {
    @SerializedName("id")
    @Expose
    private Integer mId;
    @SerializedName("cast")
    @Expose
    private List<Cast> mCasts = null;
    @SerializedName("crew")
    @Expose
    private List<Object> mCrews = null;

    public Integer getId() {
        return mId;
    }

    public void setId(Integer id) {
        this.mId = id;
    }

    public List<Cast> getCast() {
        return mCasts;
    }

    public void setCast(List<Cast> cast) {
        this.mCasts = cast;
    }

    public List<Object> getCrew() {
        return mCrews;
    }

    public void setCrew(List<Object> crew) {
        this.mCrews = crew;
    }
}
