package com.framgia.hien.moviedb.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Cast extends Person implements Serializable {
    @SerializedName("cast_id")
    @Expose
    private Integer mCastId;
    @SerializedName("character")
    @Expose
    private String mCharacter;
    @SerializedName("credit_id")
    @Expose
    private String mCreditId;
    @SerializedName("gender")
    @Expose
    private Integer mGender;
    @SerializedName("order")
    @Expose
    private Integer mOrder;

    public Integer getCastId() {
        return mCastId;
    }

    public void setCastId(Integer castId) {
        this.mCastId = castId;
    }

    public String getCharacter() {
        return mCharacter;
    }

    public void setCharacter(String character) {
        this.mCharacter = character;
    }

    public String getCreditId() {
        return mCreditId;
    }

    public void setCreditId(String creditId) {
        this.mCreditId = creditId;
    }

    public Integer getGender() {
        return mGender;
    }

    public void setGender(Integer gender) {
        this.mGender = gender;
    }

    public Integer getOrder() {
        return mOrder;
    }

    public void setOrder(Integer order) {
        this.mOrder = order;
    }
}
