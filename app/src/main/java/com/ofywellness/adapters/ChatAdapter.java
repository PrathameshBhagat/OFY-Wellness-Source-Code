package com.ofywellness.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.ofywellness.R;
import com.ofywellness.modals.Chat;

import java.util.List;
import java.util.Objects;

public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.ViewHolder> {

    public static final int CHAT_TYPE_LEFT = 0;
    public static final int CHAT_TYPE_RIGHT = 1;
    private final Context mContext;
    private final List<Chat> mChats;

    private final String currentUsersEmailID;

    public ChatAdapter(Context mContext, List<Chat> chats) {
        // Set the chats and context objects
        this.mChats = chats;
        this.mContext = mContext;
        // Get the current user's email ID
        currentUsersEmailID  = GoogleSignIn.getLastSignedInAccount(mContext).getEmail();
    }

    @NonNull
    @Override
    public ChatAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        // View for holding the parent view
        View view;

        if(viewType == CHAT_TYPE_LEFT)
            // If chat type is left, i.e sender is some other person, hence use chat_left layout
            view = LayoutInflater.from(mContext).inflate(R.layout.fragment_messaging_tab_chat_left, parent, false);

        else
            // Else chat type is Right, i.e sender is the user itself, hence use chat_right layout
            view = LayoutInflater.from(mContext).inflate(R.layout.fragment_messaging_tab_chat_right, parent, false);

        // return the view holder
        return new ViewHolder(view);

    }

    @Override
    public int getItemViewType(int position) {

        // If sender is the current user itself, that is the sender emailID and current user emailID's are equal
        if(mChats.get(position).getSenderEmailID().equals(currentUsersEmailID))
            // Then return right chat type
            return CHAT_TYPE_RIGHT;

        // Return left chat type by default i.e. sender is someone other person
        return CHAT_TYPE_LEFT;
    }

    @Override
    public void onBindViewHolder(@NonNull ChatAdapter.ViewHolder holder, int position) {

        // Get the chat object corresponding to this view holder
        Chat chat = mChats.get(position);

        // Set the message to display
        holder.messageTextView.setText(chat.getMessage());

        // Now if the current user is "not" the sender
        if(!chat.getSenderEmailID().equals(currentUsersEmailID))
            // Then we display the sender name
            holder.senderTextView.setText(chat.getSenderName());
        else
            // Else we hide the sender name text view as it is not required
            holder.senderTextView.setVisibility(View.GONE);

    }

    @Override
    public int getItemCount() {
        // return the total count of chats
        return mChats.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView messageTextView;
        public TextView senderTextView;

        public ViewHolder(View itemView) {
            super(itemView);
            // Set the message text view and the sender text view
            messageTextView = itemView.findViewById(R.id.chat_message);
            senderTextView = itemView.findViewById(R.id.chat_sender);
        }

    }
}
