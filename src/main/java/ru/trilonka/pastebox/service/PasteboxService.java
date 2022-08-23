package ru.trilonka.pastebox.service;

import ru.trilonka.pastebox.api.request.PasteboxRequest;
import ru.trilonka.pastebox.api.response.PasteboxResponse;
import ru.trilonka.pastebox.api.response.PasteboxUrlResponse;

import java.util.List;

public interface PasteboxService {

    PasteboxResponse getByHash(String hash);
    List<PasteboxResponse> getFirstPublicPasteboxes();
    PasteboxUrlResponse create(PasteboxRequest request);
}
