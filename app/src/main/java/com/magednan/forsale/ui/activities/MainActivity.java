package com.magednan.forsale.ui.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.MenuItemCompat;
import androidx.databinding.DataBindingUtil;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.SearchView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.magednan.forsale.R;
import com.magednan.forsale.databinding.ActivityMainBinding;
import com.magednan.forsale.databinding.AppBarMainBinding;
import com.magednan.forsale.databinding.ContentMainBinding;
import com.magednan.forsale.firebase.FireBaseAuth;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    ContentMainBinding contentMainBinding;
    private NavHostFragment navHostFragment;
    private AppBarConfiguration appBarConfiguration;
    private FireBaseAuth manager;
    private AppBarMainBinding appBarMainBinding;

    public static void show(Context context) {
        Intent intent = new Intent(context, MainActivity.class);
        context.startActivity(intent);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        manager=new FireBaseAuth();
        appBarConfiguration =
                new AppBarConfiguration.Builder(R.id.categoriesFragment, R.id.createAdFragment
                       , R.id.allAdsFragment,R.id.myAdsFragment
                ).setDrawerLayout(drawer).build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);


        ActionBarDrawerToggle drawerToggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.open
                , R.string.close) {
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }
        };


    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater=getMenuInflater();
        menuInflater.inflate(R.menu.op_menu,menu);
        MenuItem searchItem=menu.findItem(R.id.app_bar_search);
        SearchView searchView= (SearchView) MenuItemCompat.getActionView(searchItem);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                if(s.length() >= 3){
                Bundle bundle= new Bundle();
                bundle.putString("searchItem",s);
                Navigation.findNavController(MainActivity.this,R.id.nav_host_fragment).navigate(R.id.searchFragment,bundle);
                return false;}
                else {
                    Toast.makeText(getApplicationContext(),"please ",Toast.LENGTH_LONG).show();
                }return true;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==R.id.op_menu_signout){
            manager.signOut(this);
            startActivity(new Intent(this,AuthActivity.class));
            return true;
        }
        else return super.onOptionsItemSelected(item);
    }
}