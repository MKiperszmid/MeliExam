package com.example.meliinterview.View.Fragment;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.meliinterview.Controller.MeliController;
import com.example.meliinterview.Controller.ItemListener;
import com.example.meliinterview.Model.POJO.Description;
import com.example.meliinterview.Model.POJO.Picture;
import com.example.meliinterview.View.Adapter.PictureAdapter;
import com.example.meliinterview.Model.POJO.Product;
import com.example.meliinterview.R;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

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
            loadProduct(view, id);
        }

        return view;
    }

    private void loadProduct(final View view, String id){
        MeliController meliController = MeliController.getInstance();
        meliController.getProduct(new ItemListener<Product>() {
            @Override
            public void listen(Product product) {
                if(product != null){
                    TextView title = view.findViewById(R.id.fp_tv_title);
                    TextView price = view.findViewById(R.id.fp_tv_price);

                    price.setVisibility(View.VISIBLE);
                    price.setText(product.getPriceValue());

                    price.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Snackbar.make(v, "Purchase wasn't implemented", Snackbar.LENGTH_SHORT).show();
                        }
                    });

                    title.setText(product.getTitle());
                    ViewPager viewPager = view.findViewById(R.id.fp_vp_images);
                    PictureAdapter pictureAdapter = new PictureAdapter(getFragmentManager(), loadPictureFragments(product.getPictures()));
                    viewPager.setAdapter(pictureAdapter);
                } else{
                    Snackbar.make(view, "There was an error while retrieving the product", Snackbar.LENGTH_SHORT).show();
                }
            }
        }, id);

        meliController.getProductDescription(new ItemListener<Description>() {
            @Override
            public void listen(Description description) {
                if(description != null){
                    TextView desc = view.findViewById(R.id.fp_tv_description);
                    desc.setText(description.getDescription());
                } else{
                    Snackbar.make(view, "There was an error while retrieving the product's description", Snackbar.LENGTH_SHORT).show();
                }
            }
        }, id);
    }

    private List<Fragment> loadPictureFragments(List<Picture> pictures){
        List<Fragment> fragments = new ArrayList<>();
        for(Picture picture : pictures){
            fragments.add(PictureFragment.factory(picture.getUrl()));
        }
        return fragments;
    }
}
