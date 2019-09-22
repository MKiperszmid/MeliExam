package com.example.meliinterview.View.Fragment;


import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.meliinterview.Controller.MeliController;
import com.example.meliinterview.Controller.ItemListener;
import com.example.meliinterview.Model.POJO.ProductNotifier;
import com.example.meliinterview.View.Adapter.ResultAdapter;
import com.example.meliinterview.Model.POJO.SearchList;
import com.example.meliinterview.R;
import com.google.android.material.snackbar.Snackbar;
import com.libizo.CustomEditText;

/**
 * A simple {@link Fragment} subclass.
 */
public class SearchFragment extends Fragment implements ProductNotifier {
    private ProductNotifier productNotifier;
    private MeliController meliController;
    private RecyclerView searchRecycler;
    private String lastSearch;
    private Boolean isLoading;
    private ProgressBar progressBar;

    public SearchFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.productNotifier = (ProductNotifier) context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_search, container, false);
        final CustomEditText searchEditText = view.findViewById(R.id.fs_cet_search);

        meliController = MeliController.getInstance();
        progressBar = view.findViewById(R.id.fs_pb_progress);
        searchEditText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                boolean handled = false;
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    Log.d("METODO", "SEND");
                    handled = validateInput(searchEditText.getText());;
                }
                return handled;
            }
        });

        searchRecycler = view.findViewById(R.id.fs_rv_results);
        searchRecycler.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false));
        searchRecycler.setAdapter(new ResultAdapter(this));

        searchRecycler.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                if(!isLoading){
                    LinearLayoutManager layoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
                    int initPos = layoutManager.findLastVisibleItemPosition();
                    int finalPos = layoutManager.getItemCount();

                    if(finalPos - initPos <= 5)
                        loadSearch(lastSearch);
                }
            }
        });

        return view;
    }

    private Boolean validateInput(Editable text){
        if(text == null || text.toString().isEmpty() || text.toString().trim().length() <= 0){
            return true;
        }
        meliController.resetSearch();
        ((ResultAdapter) searchRecycler.getAdapter()).clearList();
        lastSearch = text.toString();
        loadSearch(text.toString());
        return false;
    }

    private void loadSearch(String item){
        if(meliController.getSearchPages()){
            isLoading = true;
            progressBar.setVisibility(View.VISIBLE);
            progressBar.setIndeterminate(true);
            meliController.getItems(new ItemListener<SearchList>() {
                @Override
                public void listen(SearchList items) {
                    if(items != null){
                        ((ResultAdapter) searchRecycler.getAdapter()).addList(items);
                        isLoading = false;
                        progressBar.setVisibility(View.GONE);
                        progressBar.setIndeterminate(false);
                    }
                }
            }, item);
        }
    }

    @Override
    public void listen(String id) {
        this.productNotifier.listen(id);
    }
}
