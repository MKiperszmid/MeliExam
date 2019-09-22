package com.example.meliinterview.Model.DAO;

import com.example.meliinterview.Controller.MeliConnector;
import com.example.meliinterview.Model.POJO.Product;
import com.example.meliinterview.Model.POJO.SearchList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitConnector {
    private Retrofit retrofit;
    private MeliConnector meliConnector;

    public RetrofitConnector(){
        this.retrofit = new Retrofit.Builder().baseUrl("https://api.mercadolibre.com/").addConverterFactory(GsonConverterFactory.create()).build();
        this.meliConnector = this.retrofit.create(MeliConnector.class);
    }

    public void getSearchItems(final ItemListener<SearchList> itemListener, String query){
        final Call<SearchList> searchListCall = this.meliConnector.getItems(query);
        searchListCall.enqueue(new Callback<SearchList>() {
            @Override
            public void onResponse(Call<SearchList> call, Response<SearchList> response) {
                itemListener.listener(response.body());
            }

            @Override
            public void onFailure(Call<SearchList> call, Throwable t) {
                itemListener.listener(null);
            }
        });
    }

    public void getProduct(final ItemListener<Product> itemListener, String id){
        final Call<Product> productCall = this.meliConnector.getProduct(id);
        productCall.enqueue(new Callback<Product>() {
            @Override
            public void onResponse(Call<Product> call, Response<Product> response) {
                itemListener.listener(response.body());
            }

            @Override
            public void onFailure(Call<Product> call, Throwable t) {
                itemListener.listener(null);
            }
        });
    }
}
