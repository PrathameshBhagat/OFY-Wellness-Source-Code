package com.ofywellness.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.Firebase;
import com.ofywellness.R;
import com.ofywellness.adapters.ChatAdapter;
import com.ofywellness.db.ofyDatabase;
import com.ofywellness.modals.Chat;

import java.util.ArrayList;

/**
 * Fragment for Services tab in Home page
 */
public class Messaging extends Fragment {

    ChatAdapter mChatAdapter;
    ArrayList<Chat> mchats = new ArrayList<>();
    RecyclerView mRecyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        mchats.add(new Chat("Me", "Hello"));
        mchats.add(new Chat("He", "Hello There"));
        mchats.add(new Chat("She", "Hello How are you ?"));
        mchats.add(new Chat("Him", "Hello, Well well "));
        mchats.add(new Chat("They", "Hello, Good morning"));
        mchats.add(new Chat("Me", "Hello"));
        mchats.add(new Chat("He", "Hello There"));
        mchats.add(new Chat("She", "Hello How are you ?"));
        mchats.add(new Chat("Him", "Hello, Well well "));
        mchats.add(new Chat("They", "Hello, Good morning"));
        mchats.add(new Chat("Me", "Hello"));
        mchats.add(new Chat("He", "Hello There"));
        mchats.add(new Chat("She", "Hello How are you ?"));
        mchats.add(new Chat("Him", "Hello, Well well "));
        mchats.add(new Chat("They", "Hello, Good morning"));
        mchats.add(new Chat("Me", "Hello"));
        mchats.add(new Chat("He", "Hello There"));
        mchats.add(new Chat("She", "Hello How are you ?"));
        mchats.add(new Chat("Him", "Hello, Well well "));
        mchats.add(new Chat("They", "Hello, Good morning"));
        mchats.add(new Chat("Me", "Hello"));
        mchats.add(new Chat("He", "Hello There"));
        mchats.add(new Chat("She", "Hello How are you ?"));
        mchats.add(new Chat("Him", "Hello, Well wellHello, Good morningHello, Good morningHello, Good morning "));
        mchats.add(new Chat("They", "Hello, Good morningHello, Good morningHello, Good morningHello, Good morningHello, Good morningHello, Good morningHello, Good morning"));

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

            if(message.equals(""))

                return;

            messageField.setText(null);

            ofyDatabase.sendMessageToAll(view.findViewById(R.id.messaging_chat_recycler_view) , requireActivity(), new Chat("Me", message));

        });

        return view;
    }

    void readMessages(View view ){



        ofyDatabase.readAllMessages(view.findViewById(R.id.messaging_chat_recycler_view) , requireActivity(),mRecyclerView);

    }
}