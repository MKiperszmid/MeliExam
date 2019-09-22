package com.example.meliinterview.Model.POJO;

import java.util.List;

public class SearchList {
    private List<SearchResult> results;

    public List<SearchResult> getResults() {
        return results;
    }

    public void setResults(List<SearchResult> results) {
        this.results = results;
    }

    public void addResults(List<SearchResult> results) { this.results.addAll(results); }

    public void addResults(SearchList results) { this.results.addAll(results.getResults()); }
}
