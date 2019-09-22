package com.example.meliinterview.Model.POJO;

import java.util.List;

public class Product extends SearchResult {
    private List<Picture> pictures;

    public List<Picture> getPictures() {
        return pictures;
    }

    public void setPictures(List<Picture> pictures) {
        this.pictures = pictures;
    }
}
