package com.example.meliinterview.Controller;

import com.example.meliinterview.Model.DAO.RetrofitConnector;
import com.example.meliinterview.Model.DAO.ItemListener;
import com.example.meliinterview.Model.POJO.Product;
import com.example.meliinterview.Model.POJO.SearchList;

public class MeliController {
    private RetrofitConnector retrofitConnector;
    private static MeliController instance;

    public static MeliController getInstance(){
        if(instance == null) instance = new MeliController();
        return instance;
    }
    private MeliController(){
        this.retrofitConnector = new RetrofitConnector();
    }

    public void getItems(final ItemListener<SearchList> listener, String item){
        this.retrofitConnector.getSearchItems(new ItemListener<SearchList>() {
            @Override
            public void listener(SearchList items) {
                listener.listener(items);
            }
        }, item);
    }

    public void getProduct(final ItemListener<Product> listener, String id){
        this.retrofitConnector.getProduct(new ItemListener<Product>() {
            @Override
            public void listener(Product items) {
                listener.listener(items);
            }
        }, id);
    }

}
