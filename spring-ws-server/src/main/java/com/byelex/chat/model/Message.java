package com.byelex.chat.model;

import lombok.*;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Message implements Serializable {
    private String senderName;
    private String receiverName;
    private String message;
    private String date;
    private Status status;
}
