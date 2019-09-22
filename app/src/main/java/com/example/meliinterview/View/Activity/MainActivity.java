package com.example.meliinterview.View.Activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.meliinterview.Model.POJO.ProductNotifier;
import com.example.meliinterview.R;
import com.example.meliinterview.View.Fragment.ProductFragment;
import com.example.meliinterview.View.Fragment.SearchFragment;

public class MainActivity extends AppCompatActivity implements ProductNotifier {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loadFragment(new SearchFragment());
    }

    private void loadFragment(Fragment fragment){
        loadFragment(fragment, null);
    }

    private void loadFragment(Fragment fragment, Bundle bundle){
        if(bundle != null)
            fragment.setArguments(bundle);
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.am_fl_fragment, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    @Override
    public void listen(String id) {
        Bundle bundle = new Bundle();
        bundle.putString(ProductFragment.ID_KEY, id);
        loadFragment(new ProductFragment(), bundle);
    }
}
