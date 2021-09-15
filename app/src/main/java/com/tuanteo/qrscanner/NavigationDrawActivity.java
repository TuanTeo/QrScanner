package com.tuanteo.qrscanner;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;

import com.google.android.material.navigation.NavigationView;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.tuanteo.qrscanner.adapter.ItemMainAdapter;
import com.tuanteo.qrscanner.databinding.ActivityNavigationDrawBinding;
import com.tuanteo.qrscanner.listener.ItemAdapterListener;
import com.tuanteo.qrscanner.object.ItemCardView;

import java.util.ArrayList;

public class NavigationDrawActivity extends AppCompatActivity implements ItemAdapterListener {

    public static final String WEB_LINK = "web link";
    private static final int WEB_VIEW_RESULT_CODE = 200;
    private AppBarConfiguration mAppBarConfiguration;
    private ActivityNavigationDrawBinding binding;
    private RecyclerView mRecyclerView;
    private ItemMainAdapter mAdapter;
    private ArrayList<ItemCardView> mListCardView = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityNavigationDrawBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.appBarNavigationDraw.toolbar);
//        binding.appBarNavigationDraw.fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });
        DrawerLayout drawer = binding.drawerLayout;
        NavigationView navigationView = binding.navView;
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_navigation_draw);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

        initComponent();
    }

    private void initComponent() {
        mRecyclerView = findViewById(R.id.recycle_view);
        mListCardView = createListCardView();
        mAdapter = new ItemMainAdapter(getApplicationContext(), mListCardView, this);
        mRecyclerView.setAdapter(mAdapter);
        GridLayoutManager linearLayoutManager = new GridLayoutManager(getApplicationContext(), 2);
        mRecyclerView.setLayoutManager(linearLayoutManager);
    }

    private ArrayList<ItemCardView> createListCardView() {
        mListCardView.add(new ItemCardView(R.color.first_color, R.drawable.qr_code_image, "FirstName", "https://quantrimang.com/ly-thuyet-vpn-la-gi-117232"));
        mListCardView.add(new ItemCardView(R.color.first_color, R.drawable.qr_code_image, "FirstName", "https://www.youtube.com/"));
        mListCardView.add(new ItemCardView(R.color.first_color, R.drawable.qr_code_image, "FirstName", "https://www.youtube.com/watch?v=ZHJt9kaxjNo&list=RDMMZHJt9kaxjNo&start_radio=1&ab_channel=T%C3%B9ngD%C6%B0%C6%A1ngOfficial"));
        mListCardView.add(new ItemCardView(R.color.first_color, R.drawable.qr_code_image, "FirstName", "https://quantrimang.com/ly-thuyet-vpn-la-gi-117232"));
        return mListCardView;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.navigation_draw, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_navigation_draw);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    @Override
    public void showWebView(int position) {
        String webLink = mListCardView.get(position).getWebLink();
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        intent.putExtra(WEB_LINK, webLink);
        startActivityIfNeeded(intent, WEB_VIEW_RESULT_CODE);
    }
}