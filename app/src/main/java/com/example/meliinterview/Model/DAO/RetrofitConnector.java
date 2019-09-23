package com.example.meliinterview.Model.DAO;

import com.example.meliinterview.Controller.ItemListener;
import com.example.meliinterview.Model.POJO.Description;
import com.example.meliinterview.Model.POJO.Product;
import com.example.meliinterview.Model.POJO.SearchList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitConnector {
    private MeliConnector meliConnector;

    public RetrofitConnector() {
        Retrofit retrofit = new Retrofit.Builder().baseUrl("https://api.mercadolibre.com/").addConverterFactory(GsonConverterFactory.create()).build();
        this.meliConnector = retrofit.create(MeliConnector.class);
    }

    public void getSearchItems(final ItemListener<SearchList> itemListener, String query, Integer offset, Integer limit) {
        final Call<SearchList> searchListCall = this.meliConnector.getItems(query, offset, limit);
        searchListCall.enqueue(new Callback<SearchList>() {
            @Override
            public void onResponse(Call<SearchList> call, Response<SearchList> response) {
                itemListener.listen(response.body());
            }

            @Override
            public void onFailure(Call<SearchList> call, Throwable t) {
                itemListener.listen(null);
            }
        });
    }

    public void getProduct(final ItemListener<Product> itemListener, String id) {
        final Call<Product> productCall = this.meliConnector.getProduct(id);
        productCall.enqueue(new Callback<Product>() {
            @Override
            public void onResponse(Call<Product> call, Response<Product> response) {
                itemListener.listen(response.body());
            }

            @Override
            public void onFailure(Call<Product> call, Throwable t) {
                itemListener.listen(null);
            }
        });
    }

    public void getProductDescription(final ItemListener<Description> itemListener, String id) {
        final Call<Description> descriptionCall = this.meliConnector.getProductDescription(id);
        descriptionCall.enqueue(new Callback<Description>() {
            @Override
            public void onResponse(Call<Description> call, Response<Description> response) {
                itemListener.listen(response.body());
            }

            @Override
            public void onFailure(Call<Description> call, Throwable t) {
                itemListener.listen(null);
            }
        });
    }
}
