package com.example.meliinterview.Model.POJO;

import com.google.gson.annotations.SerializedName;

public class Description {
    @SerializedName("plain_text")
    private String description;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
