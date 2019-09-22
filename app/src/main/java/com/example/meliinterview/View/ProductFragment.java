package com.example.meliinterview.View;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.meliinterview.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProductFragment extends Fragment {
    public static final String ID_KEY = "ProductID";

    public ProductFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_product, container, false);
        Bundle bundle = getArguments();
        if(bundle != null){
            String id = bundle.getString(ID_KEY);
            Toast.makeText(getContext(), "ALTO ID " + id, Toast.LENGTH_SHORT).show();
        } else{
            Toast.makeText(getContext(), "There was an error while receiving the product", Toast.LENGTH_SHORT).show();
        }
        return view;
    }

}
