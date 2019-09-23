package com.example.meliinterview.Controller;

import com.example.meliinterview.Model.POJO.SearchList;

public class BackupController {
    private static BackupController instance;
    private SearchList searchList;
    private String search;

    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }

    public static BackupController getInstance(){
        if(instance == null) instance = new BackupController();
        return instance;
    }

    public SearchList getSearchList() {
        return searchList;
    }

    public void setSearchList(SearchList searchList) {
        this.searchList = searchList;
    }
}
