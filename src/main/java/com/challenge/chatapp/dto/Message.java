package com.challenge.chatapp.dto;

public class Message {
  private String username;
  private String message;
  private byte[] attachment;

  public Message() {
  }

  public Message(String username, String message) {
    this.username = username;
    this.message = message;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public byte[] getAttachment() {
    return attachment;
  }

  public void setAttachment(byte[] attachment) {
    this.attachment = attachment;
  }
}