package com.ofywellness;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.GoogleAuthProvider;
import com.ofywellness.db.ofyDatabase;

public class LoginActivity extends AppCompatActivity {
    private GoogleSignInClient ofyGoogleSignInClient;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        // Make the user signIn to his google account
        signInToGoogleAccount();

    }

    //Implementing Google Sign-In
    void signInToGoogleAccount() {

        // Objects required
        SignInButton ofyGoogleSignInButton = findViewById(R.id.google_image);

        GoogleSignInOptions ofyGoogleSignInOptions = new GoogleSignInOptions
                .Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.web_client_id))
                .requestEmail().build();

        ofyGoogleSignInClient = GoogleSignIn.getClient(this, ofyGoogleSignInOptions);

        //onClick for Google Sign-In Button
        ofyGoogleSignInButton.setOnClickListener(view -> {
            Intent signInIntent = ofyGoogleSignInClient.getSignInIntent();
            // This intent will make user to signIn to his account,
            // and  control will move onActivityResult method
            startActivityForResult(signInIntent, 1000);
        });
    }

    // To handle Google Sign-In result
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // Check if the requested code is same as given before
        if (requestCode == 1000) {
            try {
                //Get Signed Account
                GoogleSignInAccount account = GoogleSignIn.getSignedInAccountFromIntent(data).getResult();

                // Show a Toast message
                Toast.makeText(getApplicationContext(), "Google account: " + account.getIdToken() + account.getDisplayName(), Toast.LENGTH_SHORT).show();
                AuthCredential firebaseCredential = GoogleAuthProvider.getCredential(account.getIdToken(),null);
                firebaseAuth.signInWithCredential(firebaseCredential).addOnCompleteListener(this, task -> {
                    // Check condition
                    if (task.isSuccessful()) {
                        // When task is successful redirect to profile activity display Toast
                        startActivity(new Intent(MainActivity.this, ProfileActivity.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
                        displayToast("Firebase authentication successful");
                    } else {
                        // When task is unsuccessful display Toast
                        displayToast("Authentication Failed :" + task.getException().getMessage());
                    }
                });

                // Check if the user is present in Firebase Database with email
                // And if found move to next activity
                ofyDatabase.findUserInFirebaseAndNext(LoginActivity.this, account.getEmail());

            } catch (Exception e) {
                //Log Error
                Log.e(" Error ", "Error Signing in to Google *******************************************************************");
                // Print full Error
                e.printStackTrace();
                // Show a Toast message
                Toast.makeText(getApplicationContext(), "Something Went Wrong", Toast.LENGTH_SHORT).show();
            }
        }
    }
}