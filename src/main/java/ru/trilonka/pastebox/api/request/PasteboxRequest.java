package ru.trilonka.pastebox.api.request;

import lombok.Data;
import ru.trilonka.pastebox.util.PublicStatus;

@Data
public class PasteboxRequest {

    private String data;
    private long expirationTimeSeconds;
    private PublicStatus status;
}
