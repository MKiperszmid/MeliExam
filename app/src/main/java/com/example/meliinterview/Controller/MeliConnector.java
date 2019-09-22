package com.example.meliinterview.Controller;

import com.example.meliinterview.Model.POJO.Description;
import com.example.meliinterview.Model.POJO.Product;
import com.example.meliinterview.Model.POJO.SearchList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface MeliConnector {
    @GET("sites/MLU/search")
    Call<SearchList> getItems(@Query("q") String query);

    @GET("items/{id}")
    Call<Product> getProduct(@Path("id") String id);

    @GET("items/{id}/description")
    Call<Description> getProductDescription(@Path("id") String id);

}