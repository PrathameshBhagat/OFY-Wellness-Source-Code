package com.ofywellness.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ofywellness.R;
import com.ofywellness.modals.Chat;

import java.util.List;

public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.ViewHolder> {

    public static final int CHAT_TYPE_LEFT = 0;
    public static final int CHAT_TYPE_RIGHT = 1;

    private Context mContext;
    private List<Chat> mChats;

    public ChatAdapter(Context mContext, List<Chat> chats) {
        this.mChats = chats;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public ChatAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view;

        if(viewType == CHAT_TYPE_LEFT)

            view = LayoutInflater.from(mContext).inflate(R.layout.fragment_messaging_tab_chat_left, parent, false);

        else

            view = LayoutInflater.from(mContext).inflate(R.layout.fragment_messaging_tab_chat_right, parent, false);

        return new ChatAdapter.ViewHolder(view);

    }

    @Override
    public int getItemViewType(int position) {

        if(mChats.get(position).getSender().equals("Me"))
            return CHAT_TYPE_RIGHT;
        else return CHAT_TYPE_LEFT;
    }

    @Override
    public void onBindViewHolder(@NonNull ChatAdapter.ViewHolder holder, int position) {
        Chat chat = mChats.get(position);
        holder.messageTextView.setText(chat.getMessage());
        holder.senderTextView.setText(chat.getSender());
    }

    @Override
    public int getItemCount() {
        return mChats.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView messageTextView;
        public TextView senderTextView;

        public ViewHolder(View itemView) {
            super(itemView);
            messageTextView = itemView.findViewById(R.id.chat_message);
            senderTextView = itemView.findViewById(R.id.chat_sender);
        }

    }
}
