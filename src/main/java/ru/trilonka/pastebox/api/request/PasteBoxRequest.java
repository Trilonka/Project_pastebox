package ru.trilonka.pastebox.api.request;

import lombok.Data;

@Data
public class PasteBoxRequest {

    private String data;
    private long expirationTimeSeconds;
    private PublicStatus status;
}
