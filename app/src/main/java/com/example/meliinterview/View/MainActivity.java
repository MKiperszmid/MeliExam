package com.example.meliinterview.View;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.meliinterview.Controller.MeliController;
import com.example.meliinterview.Model.DAO.ItemListener;
import com.example.meliinterview.Model.POJO.Product;
import com.example.meliinterview.Model.POJO.ProductNotifier;
import com.example.meliinterview.Model.POJO.SearchList;
import com.example.meliinterview.R;

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
