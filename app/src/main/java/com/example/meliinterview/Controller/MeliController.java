package com.example.meliinterview.Controller;

import com.example.meliinterview.Model.DAO.RetrofitConnector;
import com.example.meliinterview.Model.DAO.ItemListener;
import com.example.meliinterview.Model.POJO.Description;
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
            public void listen(SearchList items) {
                listener.listen(items);
            }
        }, item);
    }

    public void getProduct(final ItemListener<Product> listener, String id){
        this.retrofitConnector.getProduct(new ItemListener<Product>() {
            @Override
            public void listen(Product product) {
                listener.listen(product);
            }
        }, id);
    }

    public void getProductDescription(final ItemListener<Description> listener, String id){
        this.retrofitConnector.getProductDescription(new ItemListener<Description>() {
            @Override
            public void listen(Description description) {
                listener.listen(description);
            }
        }, id);
    }

}
