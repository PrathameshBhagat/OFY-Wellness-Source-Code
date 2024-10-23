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

import com.google.android.material.snackbar.Snackbar;
import com.ofywellness.R;
import com.ofywellness.db.ofyDatabase;
import com.ofywellness.modals.Chat;

/**
 * Fragment for Services tab in Home page
 */
public class Messaging extends Fragment {

    RecyclerView mRecyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_messaging_tab, container, false);
        mRecyclerView = view.findViewById(R.id.messaging_chat_recycler_view);
        mRecyclerView.setHasFixedSize(true);
        LinearLayoutManager mlinearLayoutManager = new LinearLayoutManager(requireActivity());
        mlinearLayoutManager.setStackFromEnd(true);
        mRecyclerView.setLayoutManager(mlinearLayoutManager);
        readMessages(view);


        view.findViewById(R.id.messaging_send_image_view).setOnClickListener(v -> {

            TextView messageField = view.findViewById(R.id.messaging_message_field);

            String message = messageField.getText().toString();

            if (message.equals(""))

                return;

            messageField.setText(null);

            ofyDatabase.sendMessageToAll(view.findViewById(R.id.messaging_chat_recycler_view), requireActivity(), new Chat("Me", message));

        });

        return view;
    }

    void readMessages(View view) {

        // Simple try catch block
        try {
            // Try to read all messages and show it to the user
            ofyDatabase.readAllMessages(view.findViewById(R.id.messaging_chat_recycler_view), requireActivity(), mRecyclerView);

        } catch (Exception e) {
            // Catch exception, show a toast error message and print error stack
            Snackbar.make(view, "Unable to read messages", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }
}