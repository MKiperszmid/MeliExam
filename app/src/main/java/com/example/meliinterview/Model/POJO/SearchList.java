package com.example.meliinterview.Model.POJO;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class SearchList implements Serializable {
    private List<SearchResult> results;

    public SearchList() {
        results = new ArrayList<>();
    }

    public List<SearchResult> getResults() {
        return results;
    }

    public void setResults(List<SearchResult> results) {
        this.results = results;
    }

    public void addResults(List<SearchResult> results) {
        this.results.addAll(results);
    }

    public void addResults(SearchList results) {
        this.results.addAll(results.getResults());
    }

    public void clearResults() {
        this.results.clear();
    }
}
