package com.example.meliinterview.View.Fragment;


import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.meliinterview.Controller.BackupController;
import com.example.meliinterview.Controller.ItemListener;
import com.example.meliinterview.Controller.MeliController;
import com.example.meliinterview.Model.POJO.ProductNotifier;
import com.example.meliinterview.Model.POJO.SearchList;
import com.example.meliinterview.R;
import com.example.meliinterview.View.Adapter.ResultAdapter;
import com.libizo.CustomEditText;

/**
 * A simple {@link Fragment} subclass.
 */
public class SearchFragment extends Fragment implements ProductNotifier {
    private ProductNotifier productNotifier;
    private MeliController meliController;
    private RecyclerView searchRecycler;
    private String lastSearch;
    private boolean isLoading;
    private ProgressBar progressBar;
    private TextView errorMessage;
    private BackupController backupController;

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
        backupController = BackupController.getInstance();
        progressBar = view.findViewById(R.id.fs_pb_progress);
        searchEditText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                boolean handled = false;
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    handled = validateInput(searchEditText.getText());
                }
                return handled;
            }
        });

        errorMessage = view.findViewById(R.id.fs_tv_error);
        searchRecycler = view.findViewById(R.id.fs_rv_results);
        searchRecycler.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false));
        searchRecycler.setAdapter(new ResultAdapter(this));

        searchRecycler.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                if (!isLoading) {
                    LinearLayoutManager layoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
                    int initPos = layoutManager.findLastVisibleItemPosition();
                    int finalPos = layoutManager.getItemCount();

                    //Numero de celdas antes de traer nuevos resultados
                    if (finalPos - initPos <= 5)
                        loadSearch(lastSearch);
                }
            }
        });
        loadBackup();
        return view;
    }

    private void loadBackup() {
        if(backupController.getSearchList() != null)
            addToRecycler(backupController.getSearchList());
        if(backupController.getSearch() != null)
            lastSearch = backupController.getSearch();
    }

    private Boolean validateInput(Editable text) {
        if (text == null || text.toString().isEmpty() || text.toString().trim().length() <= 0) {
            return true;
        }
        showError(false);
        meliController.resetSearch();
        ((ResultAdapter) searchRecycler.getAdapter()).clearList();
        lastSearch = text.toString();
        backupController.setSearch(lastSearch);
        loadSearch(text.toString());
        return false;
    }

    private void loadSearch(String item) {
        if (meliController.getSearchPages()) {
            isLoading = true;
            progressBar.setVisibility(View.VISIBLE);
            progressBar.setIndeterminate(true);
            meliController.getItems(new ItemListener<SearchList>() {
                @Override
                public void listen(SearchList searchList) {
                    if (searchList != null) {
                        addToRecycler(searchList);
                    } else{
                        showError(true);
                    }
                    isLoading = false;
                    progressBar.setVisibility(View.GONE);
                    progressBar.setIndeterminate(false);
                }
            }, item);
        }
    }

    private void addToRecycler(SearchList items) {
        ResultAdapter resultAdapter = (ResultAdapter) searchRecycler.getAdapter();
        resultAdapter.addList(items);

        backupController.setSearchList(resultAdapter.getSearchList());
    }

    private void showError(Boolean show){
        if(show){
            errorMessage.setVisibility(View.VISIBLE);
            searchRecycler.setVisibility(View.GONE);
        } else{
            errorMessage.setVisibility(View.GONE);
            searchRecycler.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void listen(String id) {
        this.productNotifier.listen(id);
    }
}
