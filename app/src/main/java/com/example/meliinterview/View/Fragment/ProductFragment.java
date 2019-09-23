package com.example.meliinterview.View.Fragment;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.meliinterview.Controller.BackupController;
import com.example.meliinterview.Controller.ItemListener;
import com.example.meliinterview.Controller.MeliController;
import com.example.meliinterview.Model.POJO.Description;
import com.example.meliinterview.Model.POJO.Picture;
import com.example.meliinterview.Model.POJO.Product;
import com.example.meliinterview.R;
import com.example.meliinterview.View.Adapter.PictureAdapter;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProductFragment extends Fragment {
    public static final String ID_KEY = "ProductID";
    private ProgressBar progressBar;
    private TextView title;
    private TextView price;
    private TextView desc;
    private ViewPager viewPager;

    public ProductFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_product, container, false);
        progressBar = view.findViewById(R.id.fp_pb_progress);
        progressBar.setIndeterminate(true);
        progressBar.setVisibility(View.VISIBLE);

        Bundle bundle = getArguments();
        if (bundle != null) {
            initializeViews(view);
            String id = bundle.getString(ID_KEY);
            loadProduct(view, id);
        }

        return view;
    }

    private void initializeViews(View view) {
        desc = view.findViewById(R.id.fp_tv_description);
        title = view.findViewById(R.id.fp_tv_title);
        price = view.findViewById(R.id.fp_tv_price);
        viewPager = view.findViewById(R.id.fp_vp_images);
    }

    private void loadInformation(Product product) {
        price.setText(product.getPriceValue());
        price.setVisibility(View.VISIBLE);
        price.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(v, "Purchase wasn't implemented", Snackbar.LENGTH_SHORT).show();
            }
        });
        title.setText(product.getTitle());

        if (product.getDescription() != null) {
            desc.setText(product.getDescription().getDescription());
            progressBar.setIndeterminate(false);
            progressBar.setVisibility(View.GONE);
        }

        PictureAdapter pictureAdapter = new PictureAdapter(getChildFragmentManager(), loadPictureFragments(product.getPictures()));
        viewPager.setAdapter(pictureAdapter);
    }

    private void loadProduct(final View view, final String id) {
        final MeliController meliController = MeliController.getInstance();
        meliController.getProduct(new ItemListener<Product>() {
            @Override
            public void listen(final Product product) {
                if (product != null) {
                    meliController.getProductDescription(new ItemListener<Description>() {
                        @Override
                        public void listen(Description description) {
                            if (description != null) {
                                desc.setText(description.getDescription());
                            } else {
                                Snackbar.make(view, "There was an error while retrieving the product's description", Snackbar.LENGTH_SHORT).show();
                            }
                            product.setDescription(description);
                            progressBar.setIndeterminate(false);
                            progressBar.setVisibility(View.GONE);
                        }
                    }, id);
                    loadInformation(product);
                } else {
                    Snackbar.make(view, "There was an error while retrieving the product", Snackbar.LENGTH_SHORT).show();
                    progressBar.setIndeterminate(false);
                    progressBar.setVisibility(View.GONE);
                }
            }
        }, id);
    }

    private List<Fragment> loadPictureFragments(List<Picture> pictures) {
        List<Fragment> fragments = new ArrayList<>();
        for (Picture picture : pictures) {
            fragments.add(PictureFragment.factory(picture.getUrl()));
        }
        return fragments;
    }
}
