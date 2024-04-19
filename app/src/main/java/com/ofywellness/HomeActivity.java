package com.ofywellness;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.material.tabs.TabLayout;
import com.ofywellness.fragments.AdapterForTabs;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        // Objects to show tabs
        TabLayout tabLayout = findViewById(R.id.tab_layout);
        ViewPager2 viewPager2 = findViewById(R.id.view_pager);
        AdapterForTabs adapterForTabs = new AdapterForTabs(this);

        // Set adapter to tab viewer
        viewPager2.setAdapter(adapterForTabs);

        // Add listeners on tab heads
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                // open respective tab fragment for tab header
                viewPager2.setCurrentItem(tab.getPosition());

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        // To change tab header on tab fragment change
        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);

                // Get tab fragment position and select that tab head
                tabLayout.getTabAt(position).select();
            }
        });

        // Google signout functionality
        ImageButton profile;
        TextView tname;
        GoogleSignInOptions gso;
        GoogleSignInClient gsc;
        profile = findViewById(R.id.profile);
        tname = findViewById(R.id.name);
        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();
        gsc = GoogleSignIn.getClient(this, gso);
        GoogleSignInAccount accnt = GoogleSignIn.getLastSignedInAccount(this);
        if (accnt != null) {
            String name = accnt.getDisplayName();
            if (name.equals("")) name = "Hello There !";
            tname.setText(name);
        }
        profile.setOnClickListener(view1 -> {
            gsc.signOut().addOnCompleteListener(task -> {
                finish();
                finishAffinity();
                startActivity(new Intent(HomeActivity.this, LoginActivity.class));
                Toast.makeText(getApplicationContext(), "Logged Out !, Signin Again ", Toast.LENGTH_SHORT).show();
            });
        });
    }

    public void categorystart(View view) {
        Intent home = new Intent(HomeActivity.this, Category.class);
        startActivity(home.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP));

    }
}