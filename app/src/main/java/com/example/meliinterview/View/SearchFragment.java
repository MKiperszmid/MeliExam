package com.example.meliinterview.View;


import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.meliinterview.Controller.MeliController;
import com.example.meliinterview.Model.DAO.ItemListener;
import com.example.meliinterview.Model.POJO.ProductNotifier;
import com.example.meliinterview.Model.POJO.ResultAdapter;
import com.example.meliinterview.Model.POJO.SearchList;
import com.example.meliinterview.R;
import com.libizo.CustomEditText;

/**
 * A simple {@link Fragment} subclass.
 */
public class SearchFragment extends Fragment implements ProductNotifier {
    private ProductNotifier productNotifier;

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

        searchEditText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                return validateInput(view, searchEditText.getText());
            }
        });
        return view;
    }

    private Boolean validateInput(View view, Editable text){
        if(text == null || text.toString().isEmpty() || text.toString().trim().length() <= 0){
            return true;
        }
        loadRecycler(view, text.toString());
        return false;
    }

    private void loadRecycler(final View view, String search){
        final ProductNotifier notifier = this;
        MeliController.getInstance().getItems(new ItemListener<SearchList>() {
            @Override
            public void listen(SearchList items) {
                if(items == null){
                    Toast.makeText(getContext(), "Hubo un error al comunicarse con MercadoLibre, intentar mas tarde", Toast.LENGTH_SHORT).show();
                } else{
                    RecyclerView recyclerView = view.findViewById(R.id.fs_rv_results);
                    recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false));
                    recyclerView.setAdapter(new ResultAdapter(items, notifier));
                }
            }
        }, search);
    }

    @Override
    public void listen(String id) {
        this.productNotifier.listen(id);
    }
}
