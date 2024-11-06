package com.ofywellness.modals;

public class Chat {
    private String senderName;
    private String senderEmailID;
    private String message;

    public Chat() { }

    public Chat(String senderName, String senderEmailID, String message) {
        this.senderName = senderName;
        this.senderEmailID = senderEmailID;
        this.message = message;
    }

    public String getSenderName() {
        return senderName;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }

    public String getSenderEmailID() {
        return senderEmailID;
    }

    public void setSenderEmailID(String senderEmailID) {
        this.senderEmailID = senderEmailID;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
