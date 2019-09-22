package com.example.meliinterview.Model.POJO;

import java.text.DecimalFormat;

public class SearchResult {
    private String id;
    private String title;
    private Double price;
    private String thumbnail;

    //TODO: Agregar SellerType? Platinum, Gold, etc.

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Double getPrice() {
        return price;
    }

    public String getPriceValue(){
        DecimalFormat format = new DecimalFormat("0.##");
        return "$ " + format.format(price);
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }
}
