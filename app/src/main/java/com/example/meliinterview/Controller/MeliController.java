package com.example.meliinterview.Controller;

import com.example.meliinterview.Model.DAO.RetrofitConnector;
import com.example.meliinterview.Model.POJO.Description;
import com.example.meliinterview.Model.POJO.Product;
import com.example.meliinterview.Model.POJO.SearchList;

public class MeliController {
    private static MeliController instance;
    private RetrofitConnector retrofitConnector;
    private Integer searchOffset;
    private Integer searchLimit;
    private Boolean searchPages;

    private MeliController() {
        this.retrofitConnector = new RetrofitConnector();
        searchLimit = 15;
        resetSearch();
    }

    public static MeliController getInstance() {
        if (instance == null) instance = new MeliController();
        return instance;
    }

    public void resetSearch() {
        this.searchOffset = 0;
        this.searchPages = true;
    }

    public void getItems(final ItemListener<SearchList> listener, final String item) {
        this.retrofitConnector.getSearchItems(new ItemListener<SearchList>() {
            @Override
            public void listen(SearchList items) {
                if (items != null) {
                    if (items.getResults().size() < searchLimit) {
                        searchPages = false;
                    }
                    searchOffset += items.getResults().size();
                }
                listener.listen(items);
            }
        }, item, searchOffset, searchLimit);
    }

    public void getProduct(final ItemListener<Product> listener, String id) {
        this.retrofitConnector.getProduct(new ItemListener<Product>() {
            @Override
            public void listen(Product product) {
                listener.listen(product);
            }
        }, id);
    }

    public void getProductDescription(final ItemListener<Description> listener, String id) {
        this.retrofitConnector.getProductDescription(new ItemListener<Description>() {
            @Override
            public void listen(Description description) {
                listener.listen(description);
            }
        }, id);
    }

    public Boolean getSearchPages() {
        return searchPages;
    }
}
