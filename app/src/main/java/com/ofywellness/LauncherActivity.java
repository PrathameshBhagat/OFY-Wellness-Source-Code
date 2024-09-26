package com.ofywellness;

import static com.ofywellness.db.ofyDatabase.findUserInFirebaseAndNext;
import static com.ofywellness.db.ofyDatabase.newFindUserInFirebaseAndNext;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.firebase.auth.FirebaseAuth;

public class LauncherActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Set the content view for this activity
        setContentView(R.layout.activity_launcher);

        // Get the background animation from the main layout
        AnimationDrawable animationDrawable = (AnimationDrawable) findViewById(R.id.launcher_relative_layout).getBackground();

        // Set the fade in and fade out durations for the animation
        animationDrawable.setEnterFadeDuration(400);
        animationDrawable.setExitFadeDuration(400);

        // Start the animation
        animationDrawable.start();

        // Now look for an google account which user signed in last time
        // And attempt to login to it

        // Look for the google account which user already logged in
        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);

        // Check if no past account exists, (neither in firebase nor in google)
        if ( account == null || FirebaseAuth.getInstance().getCurrentUser() == null )
            // If an account does not exists, start the LoginActivity to make user login to his account
            startActivity(new Intent(this, LoginActivity.class));

        else
            // Else If an account exists look for the account in firebase "database" and move to next activity
            //
            // Point to be noted "firebase" stores users data in itself as well but with minimal details
            // This detail lies in result of getCurrentUser()
            //
            // But we also store users data in "our" database ("firebase database")
            // So that we can also store user's height, weight and any other parameter we want
            // Which is not possible by getCurrentUser() which offers minimal details like email, name etc.
            // So we only get user ID from firebase and record user detail by RegisterActivity and store it
            newFindUserInFirebaseAndNext(this, FirebaseAuth.getInstance().getCurrentUser().getUid());

    }
}