package com.ofywellness.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.material.snackbar.Snackbar;
import com.ofywellness.R;
import com.ofywellness.db.ofyDatabase;
import com.ofywellness.modals.Chat;

/**
 * Fragment for Services tab in Home page
 */
public class Messaging extends Fragment {

    private RecyclerView mRecyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_messaging_tab, container, false);

        // Get the recycler view and set it to have fixed size
        mRecyclerView = view.findViewById(R.id.messaging_chat_recycler_view);
        mRecyclerView.setHasFixedSize(true);

        // Get a linear layout manager to align messages
        LinearLayoutManager mlinearLayoutManager = new LinearLayoutManager(requireActivity());
        // Now set the linear layout to display messages in reverse order
        mlinearLayoutManager.setStackFromEnd(true);
        // Set the layout manager
        mRecyclerView.setLayoutManager(mlinearLayoutManager);

        // Now read the messages from the database
        readMessages(view);


        // Set onClick listener for send message button
        view.findViewById(R.id.messaging_send_image_view).setOnClickListener(v -> {

            // Get the message text field
            TextView messageField = view.findViewById(R.id.messaging_message_field);
            // Get the message to be sent
            String message = messageField.getText().toString();

            // If the message is empty
            if (message.trim().isEmpty())
                // Return
                return;

            // Clear the message field for new message
            messageField.setText(null);

            GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(requireActivity());

            // Call database method to send message (to all user's)
            ofyDatabase.sendMessageToAll(view.findViewById(R.id.messaging_chat_recycler_view), new Chat(account.getDisplayName(),account.getEmail(),message));

        });

        // return the root view
        return view;
    }

    // Method to read all the messages and display them to the user
    void readMessages(View view) {

        // Simple try catch block
        try {
            // Try to read all messages and show it to the user
            ofyDatabase.readAllMessagesAndDisplay(view.findViewById(R.id.messaging_chat_recycler_view), requireActivity(), mRecyclerView);

        } catch (Exception e) {
            // Catch exception, show a snack-bar error message and print error stack
            Snackbar.make(view, "Unable to read messages", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }
}