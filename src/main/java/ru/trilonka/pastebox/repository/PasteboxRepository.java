package ru.trilonka.pastebox.repository;

import ru.trilonka.pastebox.model.PasteboxEntity;

import java.util.List;

public interface PasteboxRepository {

    PasteboxEntity getByHash(String hash);
    List<PasteboxEntity> getListOfPublicAndAlive(int amount);
    void add(PasteboxEntity pastebox);
}
